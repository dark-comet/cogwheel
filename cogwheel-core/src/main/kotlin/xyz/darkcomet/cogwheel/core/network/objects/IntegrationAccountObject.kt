package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable

@Serializable
data class IntegrationAccountObject(
    val id: String,
    val name: String
)