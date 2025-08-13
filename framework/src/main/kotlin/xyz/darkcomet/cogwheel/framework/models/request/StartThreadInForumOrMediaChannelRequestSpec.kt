@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.ForumThreadMessageParametersObject
import xyz.darkcomet.cogwheel.core.network.objects.StartThreadInForumOrMediaChannelRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.ForumThreadMessageParameters
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import java.nio.file.Path

class StartThreadInForumOrMediaChannelRequestSpec(
    internal val channelId: ChannelId
) {
    private var name: String? = null
    private var autoArchiveDuration: MaybeAbsent<Int>? = null
    private var rateLimitPerUser: MaybeAbsent<Int>? = null
    private var message: ForumThreadMessageParametersObject? = null
    private var appliedTags: MaybeAbsent<List<Snowflake>>? = null
    private var files: List<Path>? = null
    
    internal var auditLogReason: String? = null
    
    fun name(name: String): StartThreadInForumOrMediaChannelRequestSpec
        = apply { this.name = name }
    
    fun autoArchiveDuration(numMinutes: Int): StartThreadInForumOrMediaChannelRequestSpec
        = apply { this.autoArchiveDuration = MaybeAbsent(numMinutes) }
    
    fun rateLimitPerUser(rateLimit: Int): StartThreadInForumOrMediaChannelRequestSpec
        = apply { this.rateLimitPerUser = MaybeAbsent(rateLimit) }
    
    fun message(parameters: ForumThreadMessageParameters): StartThreadInForumOrMediaChannelRequestSpec
        = apply { this.message = parameters.toObject() }
    
    fun auditLogReason(reason: String?): StartThreadInForumOrMediaChannelRequestSpec
        = apply { this.auditLogReason = reason }
    
    internal fun buildParameters(): StartThreadInForumOrMediaChannelRequestParameters {
        TODO()
    }
}