package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.ForumThreadMessageParametersObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class StartThreadInForumOrMediaChannelRequestParameters(
    val name: String,
    val autoArchiveDuration: Int? = null,
    val rateLimitPerUser: Int? = null,
    val message: ForumThreadMessageParametersObject? = null,
    val appliedTags: List<Snowflake>? = null,
    val files: List<String>? = null,
    val payloadJson: String? = null
)