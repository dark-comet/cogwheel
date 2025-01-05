package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable

@Serializable
data class GuildBanObject(
    val reason: String?,
    val user: UserObject
)