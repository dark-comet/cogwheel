package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.GuildWelcomeScreenChannelObject

@Serializable
data class ModifyGuildWelcomeScreenRequestParameters(
    val enabled: Boolean? = null,
    @SerialName("welcome_channels") val welcomeChannels: List<GuildWelcomeScreenChannelObject>? = null,
    val description: String? = null
)