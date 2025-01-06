package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.GuildMemberObject
import xyz.darkcomet.cogwheel.core.network.objects.UpdatePresenceObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class GuildMembersChunkEvent
internal constructor(override val data: DataObject) : Event<GuildMembersChunkEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Snowflake,
        val members: List<GuildMemberObject>,
        @SerialName("chunk_index") val chunkIndex: Int,
        @SerialName("chunk_count") val chunkCount: Int,
        @SerialName("not_found") val notFound: List<Snowflake>? = null,
        val presences: List<UpdatePresenceObject>? = null,
        val nonce: String? = null,
    )

}