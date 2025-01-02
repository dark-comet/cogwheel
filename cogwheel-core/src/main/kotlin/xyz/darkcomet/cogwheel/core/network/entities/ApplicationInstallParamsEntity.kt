package xyz.darkcomet.cogwheel.core.network.entities

import kotlinx.serialization.Serializable

@Serializable
data class ApplicationInstallParamsEntity(
    val scopes: List<String>,
    val permissions: String
)