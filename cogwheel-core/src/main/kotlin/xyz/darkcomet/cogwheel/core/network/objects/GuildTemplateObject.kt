package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class GuildTemplateObject(
    val code: String,
    val name: String,
    val description: String?,
    @SerialName("usage_count") val usageCount: Int,
    @SerialName("creator_id") val creatorId: Snowflake,
    val creator: UserObject,
    @SerialName("created_at") val createdAt: ISO8601Timestamp,
    @SerialName("updated_at") val updatedAt: ISO8601Timestamp,
    @SerialName("source_guild_id") val sourceGuildId: Snowflake,
    @SerialName("serialized_source_guild") val serializedSourceGuild: GuildObject,
    @SerialName("is_dirty") val isDirty: Boolean
)