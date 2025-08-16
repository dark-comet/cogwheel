package xyz.darkcomet.cogwheel.core.network.http.impl

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import okhttp3.OkHttpClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import xyz.darkcomet.cogwheel.core.impl.CwDiscordClientSettings
import xyz.darkcomet.cogwheel.core.impl.models.CwConfiguration
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.FileSupplier
import java.nio.file.Paths
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.io.path.readBytes

internal class KtorHttpClient(
    private val settings: CwDiscordClientSettings,
    private val config: CwConfiguration
) : CwHttpClient {
    
    private val logger: Logger = LoggerFactory.getLogger(KtorHttpClient::class.java)
    
    private val httpClient: HttpClient
    private val httpClientUserAgentHeaderValue: String
    private val rateLimitStrategy = settings.rateLimitStrategy
    
    init {
        httpClient = HttpClient(OkHttp) {
            if (settings.gatewayEnabled) {
                engine {
                    // TODO: Work out the specifics
                    preconfigured = OkHttpClient.Builder().pingInterval(20, TimeUnit.SECONDS).build()
                }
            }
        }
        
        httpClientUserAgentHeaderValue = getUserAgentHeaderValue()
        logger.info("User-Agent: $httpClientUserAgentHeaderValue")
    }

    private fun getUserAgentHeaderValue(): String {
        // Hard-coded values for this library
        val libName = config.clientName
        val libVersion = config.clientVersion
        val libUrl = config.clientUrl

        // Specific to client project settings
        val officialUrl = settings.customClientUrl ?: libUrl
        val officialVersion = settings.customClientVersion ?: libVersion

        return "DiscordBot ($officialUrl, $officialVersion) $libName/$libVersion"
    }

    override suspend fun submit(request: CwHttpRequest): CwHttpResponse.Raw {
        val endpointUrl = getEndpointUrl(request.route)
        val traceId = UUID.randomUUID()

        logger.trace(
            "Submitting HttpRequest: traceId={}, {} {}, files.count={}, jsonParams={}", 
            traceId, 
            request.method, 
            endpointUrl, 
            request.fileContent?.files?.size ?: 0, 
            request.jsonContent ?: ""
        )
        
        val response: HttpResponse?
        
        if (rateLimitStrategy == null || rateLimitStrategy.prepareRequestSubmit(request)) {
            response = submitRequestWithRetry(request, endpointUrl, request.jsonContent ?: "", request.fileContent)
            val responseBody = response.bodyAsText()
            logger.trace("Received HttpResponse for id={}, {}, bodyContent={}", traceId, response.toString(), responseBody)
            
            return KtorHttpResponse.Raw(response, settings.jsonSerializer, responseBody)
        } else {
            response = null
            logger.trace("Skipped submitting HttpRequest for id={}: aborted by local rate limiter", traceId)
            return KtorHttpResponse.Raw(response, settings.jsonSerializer, "")
        }
    }
    
    private suspend fun submitRequestWithRetry(
        request: CwHttpRequest,
        endpointUrl: String,
        jsonBody: String,
        files: FileSupplier?
    ): HttpResponse {
        
        var finalResponse: HttpResponse? = null
        var submitAttemptCount = 0
        
        do {
            submitAttemptCount++

            val response = submitRequest(request, endpointUrl, jsonBody, files)

            rateLimitStrategy?.record(request, response)

            if (response.status.isSuccess()) {
                finalResponse = response
            } else {
                // TODO: Tachometer, record failure rate
                if (response.status == HttpStatusCode.TooManyRequests) {
                    if (rateLimitStrategy?.isRetryable(request, response, submitAttemptCount) == true) {
                        rateLimitStrategy.prepareForRetry(request, response, submitAttemptCount)
                        continue
                    } else {
                        finalResponse = response
                    }
                } else {
                    // No retry for other kinds of errors
                    finalResponse = response
                }
            }
        } while (finalResponse == null)
        
        return finalResponse
    }

    private suspend fun submitRequest(
        request: CwHttpRequest,
        endpointUrl: String,
        jsonBody: String,
        fileAttachments: FileSupplier?
    ): HttpResponse {
        
        return httpClient.request(endpointUrl) {
            method = getHttpMethod(request.method)
            
            request.queryParameters.entries.forEach {
                parameter(it.key, it.value)
            }

            headers {
                append(HttpHeaders.Authorization, settings.token.getAuthorizationHeaderValue())
                append(HttpHeaders.UserAgent, httpClientUserAgentHeaderValue)

                request.headers.entries.forEach {
                    append(it.key, it.value)
                }
            }
            
            if (fileAttachments != null) {
                contentType(ContentType.MultiPart.FormData)
                formData() {
                    // TODO: Validate the files
                    for (file in fileAttachments.files) {
                        val fileName = file.fileName.toString().lowercase()
                        val extension = Paths.get(fileName).toFile().extension

                        append("file", file.readBytes(), Headers.build {
                            append(HttpHeaders.ContentDisposition, "form-data")
                            append(HttpHeaders.ContentType, "image/$extension")
                        })
                    }

                    append("payload_json", jsonBody, Headers.build {
                        append(HttpHeaders.ContentDisposition, "form-data")
                        append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                    })
                }
            } else {
                contentType(ContentType.Application.Json)
                setBody(jsonBody)
            }
        }
    }

    private fun getEndpointUrl(endpointUrl: String): String {
        var url = config.discordApiUrl
        
        if (url.endsWith("/")) {
            url = url.substring(0, url.length - 1)
        }

        url += "/v${config.discordApiVersion}${endpointUrl}"
        
        return url
    }
    
    private fun getHttpMethod(request: CwHttpMethod): HttpMethod {
        return when (request) {
            CwHttpMethod.GET -> HttpMethod.Get
            CwHttpMethod.PUT -> HttpMethod.Put
            CwHttpMethod.POST -> HttpMethod.Post
            CwHttpMethod.PATCH -> HttpMethod.Patch
            CwHttpMethod.DELETE -> HttpMethod.Delete
        }
    }

    internal class Factory : CwHttpClient.Factory {
        override fun create(settings: CwDiscordClientSettings, config: CwConfiguration): CwHttpClient {
            return KtorHttpClient(settings, config)
        }
    }
}