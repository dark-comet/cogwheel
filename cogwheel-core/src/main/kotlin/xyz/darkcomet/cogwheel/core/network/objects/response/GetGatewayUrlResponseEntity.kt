package xyz.darkcomet.cogwheel.core.network.objects.response

import kotlinx.serialization.Serializable

@Serializable
data class GetGatewayUrlResponseEntity(val url: String)