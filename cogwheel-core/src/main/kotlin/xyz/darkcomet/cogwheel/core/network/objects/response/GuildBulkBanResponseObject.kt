package xyz.darkcomet.cogwheel.core.network.objects.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class GuildBulkBanResponseObject(
    @SerialName("banned_users") val bannedUsers: List<Snowflake>,
    @SerialName("failed_users") val failedUsers: List<Snowflake>
)