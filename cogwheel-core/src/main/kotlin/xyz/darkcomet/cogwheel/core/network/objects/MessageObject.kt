package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class MessageObject(
    val id: Snowflake,
    val channelId: Snowflake,
    val author: UserObject,
    val content: String,
    val timestamp: ISO8601Timestamp,
    val editedTimestamp: ISO8601Timestamp?,
    val tts: Boolean,
    val mentionEveryone: Boolean,
    val mentions: List<UserObject>,
    val mentionRoles: List<RoleObject>,
    val mentionChannels: List<ChannelMentionObject>,
    val attachments: List<AttachmentObject>,
    val embeds: List<EmbedObject>,
    val reactions: List<ReactionObject>? = null,
    val nonce: String? = null,
    val pinned: Boolean,
    val webhookId: Snowflake? = null,
    val type: Int,
    val activity: MessageActivityObject? = null,
    val application: ApplicationObject? = null,
    val applicationId: Snowflake? = null,
    val flags: Int? = null,
    val messageReference: MessageReferenceObject? = null,
    val messageSnapshots: MessageSnapshotObject? = null,
    val referencedMessage: MessageReferenceObject? = null,
    val interactionMetadata: MessageInteractionMetadataObject? = null,
//    val interaction: MessageInteractionObject? = null,
    val thread: ChannelObject? = null,
    val components: List<MessageComponentObject>? = null,
//    val stickerItems: List<MessageStickerItemObject>? = null,
    val stickers: List<StickerObject>? = null,
    val position: Int? = null,
//    val roleSubscriptionData: RoleSubscriptionDataObject? = null,
//    val resolved: ResolvedObject? = null,
    val poll: PollObject? = null,
//    val call: MessageCallObject? = null
)