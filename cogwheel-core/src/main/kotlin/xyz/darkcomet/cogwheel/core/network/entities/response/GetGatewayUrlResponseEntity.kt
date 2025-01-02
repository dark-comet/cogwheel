package xyz.darkcomet.cogwheel.core.network.entities.response

import kotlinx.serialization.Serializable

@Serializable
data class GetGatewayUrlResponseEntity(val url: String)