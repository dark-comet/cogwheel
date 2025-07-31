@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.MessageId
import xyz.darkcomet.cogwheel.framework.primitives.UserId

class GetPollAnswerVotersRequestSpec(
    internal val channelId: ChannelId,
    internal val messageId: MessageId,
    internal val answerId: Int,
) {
    internal var after: Snowflake? = null
    internal var limit: Int? = null
    
    fun after(user: UserId?): GetPollAnswerVotersRequestSpec
        = apply { this.after = user?.snowflake }
    
    fun limit(quantity: Int?): GetPollAnswerVotersRequestSpec
        = apply { this.limit = quantity }
    
}