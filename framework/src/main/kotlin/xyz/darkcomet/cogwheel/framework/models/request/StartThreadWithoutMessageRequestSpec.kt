@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.StartThreadWithoutMessageRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.ThreadChannelType

class StartThreadWithoutMessageRequestSpec(
    internal val channelId: ChannelId
) {
    private var name: String? = null
    private var autoArchiveDuration: MaybeAbsent<Int>? = null
    private var type: Int? = null
    private var invitable: MaybeAbsent<Boolean>? = null
    private var rateLimitPerUser: MaybeAbsent<Int?>? = null
    
    internal var auditLogReason: String? = null
    
    fun name(name: String): StartThreadWithoutMessageRequestSpec
        = apply { this.name = name }
    
    fun autoArchiveDuration(numMinutes: Int): StartThreadWithoutMessageRequestSpec
        = apply { this.autoArchiveDuration = MaybeAbsent(numMinutes) }
    
    fun type(type: ThreadChannelType): StartThreadWithoutMessageRequestSpec
        = apply { this.type = type.key }
    
    fun invitable(invitable: Boolean): StartThreadWithoutMessageRequestSpec
        = apply { this.invitable = MaybeAbsent(invitable) }
    
    fun rateLimitPerUser(rateLimit: Int): StartThreadWithoutMessageRequestSpec
        = apply { this.rateLimitPerUser = MaybeAbsent(rateLimit) }
    
    fun auditLogReason(reason: String?): StartThreadWithoutMessageRequestSpec
        = apply { this.auditLogReason = reason }
    
    internal fun buildParameters(): StartThreadWithoutMessageRequestParameters {
        return StartThreadWithoutMessageRequestParameters(
            name = this.name ?: throw InvalidModelException("'name' is required"),
            autoArchiveDuration = this.autoArchiveDuration,
            type = MaybeAbsent(this.type ?: throw InvalidModelException("'type' is required")),
            invitable = this.invitable,
            rateLimitPerUser = this.rateLimitPerUser,
        )
    }
}