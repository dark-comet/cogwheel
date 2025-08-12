@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.StartThreadFromMessageRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.MessageId

class StartThreadFromMessageRequestSpec(
    val channelId: ChannelId,
    val messageId: MessageId,
) {
    private var name: String? = null
    private var autoArchiveDuration: MaybeAbsent<Int>? = null
    private var rateLimitPerUser: MaybeAbsent<Int?>? = null
    
    internal var auditLogReason: String? = null
    
    fun name(name: String): StartThreadFromMessageRequestSpec
        = apply { this.name = name }
    
    fun autoArchiveDuration(numMinutes: Int): StartThreadFromMessageRequestSpec
        = apply { this.autoArchiveDuration = MaybeAbsent(numMinutes) }
    
    fun rateLimitPerUser(rateLimit: Int?): StartThreadFromMessageRequestSpec
        = apply { this.rateLimitPerUser = MaybeAbsent(rateLimit) }
    
    fun auditLogReason(reason: String?): StartThreadFromMessageRequestSpec
        = apply { auditLogReason = reason }
    
    internal fun buildParameters(): StartThreadFromMessageRequestParameters {
        return StartThreadFromMessageRequestParameters(
            name = this.name ?: throw InvalidModelException("'name' is required"),
            autoArchiveDuration = this.autoArchiveDuration,
            rateLimitPerUser = this.rateLimitPerUser
        )
    }
}