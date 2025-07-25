package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.http.rest.ApplicationResource
import xyz.darkcomet.cogwheel.core.network.objects.ApplicationObject
import xyz.darkcomet.cogwheel.framework.models.entitles.application.Application
import xyz.darkcomet.cogwheel.framework.models.entitles.application.toModel
import xyz.darkcomet.cogwheel.framework.models.specs.EditCurrentApplicationRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.Invocation0
import xyz.darkcomet.cogwheel.framework.primitives.RequestInvocationS
import xyz.darkcomet.cogwheel.framework.primitives.Response

@Suppress("unused") // All exposed members are part of the public API
class ApplicationApi 
internal constructor(resource: ApplicationResource) {

    @JvmField
    val getCurrent = GetCurrentApplicationEndpoint(resource)
    
    @JvmField
    val editCurrent = EditCurrentApplicationEndpoint(resource)
    
}

class EditCurrentApplicationEndpoint
internal constructor(private val resource: ApplicationResource) 
    : RequestInvocationS<EditCurrentApplicationRequestSpec, Application>() {
        
    override fun createRequest(): EditCurrentApplicationRequestSpec {
        return EditCurrentApplicationRequestSpec();
    }

    override suspend fun invoke(request: EditCurrentApplicationRequestSpec): Response<Application> {
        val req = request.build()
        val rawResponse: CwHttpResponse<ApplicationObject?> = resource.editCurrentApplication(req)
        val model: Application? = rawResponse.data?.toModel();

        return Response(model, rawResponse);
    }
}

class GetCurrentApplicationEndpoint
internal constructor(private val resource: ApplicationResource) 
    : Invocation0<Application>() {
    
    override suspend fun invoke(): Response<Application> {
        val rawResponse: CwHttpResponse<ApplicationObject?> = resource.getCurrentApplication();
        val model: Application? = rawResponse.data?.toModel();

        return Response(model, rawResponse);
    }
}