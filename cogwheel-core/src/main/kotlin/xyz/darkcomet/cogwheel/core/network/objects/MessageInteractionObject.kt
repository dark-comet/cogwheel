package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class MessageInteractionObject(
    val id: Snowflake,
    @SerialName("application_id") val applicationId: Snowflake,
    val type: Int,
    val data: InteractionDataObject? = null,
    val guild: GuildObject? = null,
    @SerialName("guild_id") val guildId: Snowflake? = null,
    val channel: ChannelObject? = null,
    @SerialName("channel_id") val channelId: Snowflake,
    val member: GuildMemberObject? = null,
    val user: UserObject? = null,
    val token: String,
    val version: Int,
    val message: MessageObject? = null,
    @SerialName("app_permissions") val appPermissions: String,
    val locale: String? = null,
    @SerialName("guild_locale") val guildLocale: String? = null,
    val entitlements: List<EntitlementObject>,
    @SerialName("authorizing_integration_owners") val authorizingIntegrationOwners: Map<Int, String>
)