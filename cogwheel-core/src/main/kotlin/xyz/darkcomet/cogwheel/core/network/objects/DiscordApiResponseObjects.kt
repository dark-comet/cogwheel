package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class GetGatewayUrlResponseObject(
    val url: String
)

@Serializable
data class GuildBeginPruneResponseObject(
    val pruned: Int
)

@Serializable
data class GuildBulkBanResponseObject(
    @SerialName("banned_users") val bannedUsers: List<Snowflake>,
    @SerialName("failed_users") val failedUsers: List<Snowflake>
)

@Serializable
data class GuildGetPruneCountResponseObject(
    val pruned: Int
)

@Serializable
data class ListApplicationEmojisResponseObject(
    val items: List<EmojiObject>
)

@Serializable
data class ListGuildActiveThreadsResponseObject(
    val threads: List<ChannelObject>,
    val members: List<ThreadMemberObject>
)

@Serializable
data class ListGuildSoundboardSoundsResponseObject(
    val items: List<SoundboardSoundObject>
)

@Serializable
data class ListJoinedPrivateArchivedThreadsResponseObject(
    val threads: List<ChannelObject>,
    val members: List<ThreadMemberObject>,
    @SerialName("has_more") val hasMore: Boolean,
)

@Serializable
data class ListPrivateArchivedThreadsResponseObject(
    val threads: List<ChannelObject>,
    val members: List<ThreadMemberObject>,
    @SerialName("has_more") val hasMore: Boolean,
)

@Serializable
data class ListPublicArchivedThreadsResponseObject(
    val threads: List<ChannelObject>,
    val members: List<ThreadMemberObject>,
    @SerialName("has_more") val hasMore: Boolean,
)

@Serializable
data class GetPollAnswerVotersResponseObject(
    val users: List<UserObject>
)