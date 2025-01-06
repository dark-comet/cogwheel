package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.AutoModerationActionObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class AutoModerationActionExecutionEvent
internal constructor(
    override val data: DataObject
): Event<AutoModerationActionExecutionEvent.DataObject> {
    
    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Snowflake,
        val action: AutoModerationActionObject,
        @SerialName("rule_id") val ruleId: Snowflake,
        @SerialName("rule_trigger_type") val ruleTriggerType: Int,
        @SerialName("user_id") val userId: Snowflake,
        @SerialName("channel_id") val channelId: Snowflake? = null,
        @SerialName("message_id") val messageId: Snowflake? = null,
        @SerialName("alert_system_message_id") val alertSystemMessageId: Snowflake? = null,
        val content: String,
        @SerialName("matched_keyword") val matchedKeyword: String? = null,
        @SerialName("matched_content") val matchedContent: String? = null,
    )
    
}