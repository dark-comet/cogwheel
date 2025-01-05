package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildWelcomeScreenObject(
    val description: String?,
    @SerialName("welcome_channels") val welcomeChannels: List<GuildWelcomeScreenChannelObject>
)