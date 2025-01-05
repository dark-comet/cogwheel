package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
abstract class MessageInteractionMetadataObject {
    abstract val id: Snowflake
    abstract val type: Int
    abstract val user: UserObject
    
    @SerialName("authorizing_integration_owners")
    abstract val authorizingIntegrationOwners: Map<Int, Snowflake>

    @SerialName("original_response_message_id") 
    abstract val originalResponseMessageId: Snowflake?
}

@Serializable
data class ApplicationCommandInteractionMetadataObject(
    override val id: Snowflake,
    override val type: Int,
    override val user: UserObject,
    override val authorizingIntegrationOwners: Map<Int, Snowflake>,
    override val originalResponseMessageId: Snowflake? = null,
    @SerialName("target_user") val targetUser: UserObject? = null,
    @SerialName("target_message_id") val targetMessageId: Snowflake? = null,
) : MessageInteractionMetadataObject()

@Serializable
data class MessageComponentInteractionMetadataObject(
    override val id: Snowflake,
    override val type: Int,
    override val user: UserObject,
    override val authorizingIntegrationOwners: Map<Int, Snowflake>,
    override val originalResponseMessageId: Snowflake? = null,
    @SerialName("interacted_message_id") val interactedMessageId: Snowflake,
) : MessageInteractionMetadataObject()

@Serializable
data class ModalSubmitInteractionMetadataObject(
    override val id: Snowflake,
    override val type: Int,
    override val user: UserObject,
    override val authorizingIntegrationOwners: Map<Int, Snowflake>,
    override val originalResponseMessageId: Snowflake? = null,
    @SerialName("trigger_interaction_metadata") val triggerInteractionMetadata: MessageInteractionMetadataObject,
) : MessageInteractionMetadataObject()