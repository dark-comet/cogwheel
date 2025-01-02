package xyz.darkcomet.cogwheel.network.entities.response

import kotlinx.serialization.Serializable

@Serializable
data class GetGatewayResponseEntity(val url: String)