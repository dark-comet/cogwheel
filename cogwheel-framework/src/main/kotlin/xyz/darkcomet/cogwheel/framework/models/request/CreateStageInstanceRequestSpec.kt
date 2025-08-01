@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.CreateStageInstanceRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.GuildScheduledEventId
import xyz.darkcomet.cogwheel.framework.primitives.StageInstancePrivacyLevel

class CreateStageInstanceRequestSpec {
    
    internal var channelId: Snowflake? = null
    internal var topic: String? = null
    internal var privacyLevel: MaybeAbsent<Int>? = null
    internal var sendStartNotification: MaybeAbsent<Boolean>? = null
    internal var guildScheduledEventId: MaybeAbsent<Snowflake>? = null
    
    internal var auditLogReason: String? = null
    
    fun channelId(id: ChannelId): CreateStageInstanceRequestSpec
        = apply { this.channelId = id.snowflake }
    
    fun topic(topic: String): CreateStageInstanceRequestSpec
        = apply { this.topic = topic }
    
    fun privacyLevel(id: StageInstancePrivacyLevel): CreateStageInstanceRequestSpec
        = apply { this.privacyLevel = MaybeAbsent(id.key) }
    
    fun sendStartNotification(flag: Boolean): CreateStageInstanceRequestSpec
        = apply { this.sendStartNotification = MaybeAbsent(flag) }
    
    fun guildScheduleEventId(id: GuildScheduledEventId): CreateStageInstanceRequestSpec
        = apply { this.guildScheduledEventId = MaybeAbsent(id.snowflake) }
    
    fun auditLogReason(auditLogReason: String): CreateStageInstanceRequestSpec
        = apply { this.auditLogReason = auditLogReason }
    
    fun buildParameters(): CreateStageInstanceRequestParameters {
        return CreateStageInstanceRequestParameters(
            channelId = this.channelId ?: throw InvalidModelException("'channelId' is required"),
            topic = this.topic ?: throw InvalidModelException("'topic' is required"),
            privacyLevel = this.privacyLevel,
            sendStartNotification = this.sendStartNotification,
            guildScheduledEventId = this.guildScheduledEventId,
        )
    }
    
}