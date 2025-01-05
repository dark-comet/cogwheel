package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class ResolvedDataObject(
    val users: Map<Snowflake, UserObject>? = null,
    val members: Map<Snowflake, GuildMemberObject>? = null,
    val roles: Map<Snowflake, RoleObject>? = null,
    val channels: Map<Snowflake, ChannelObject>? = null,
    val messages: Map<Snowflake, MessageObject>? = null,
    val attachments: Map<Snowflake, AttachmentObject>? = null,
)