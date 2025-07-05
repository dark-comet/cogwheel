package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.*
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.Possible
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

interface Event<TData> {

    val data: TData?

}

class ApplicationCommandPermissionsUpdateEvent
internal constructor(
    override val data: ApplicationCommandPermissionObject
) : Event<ApplicationCommandPermissionObject>

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
        @SerialName("channel_id") val channelId: Possible<Snowflake>? = null,
        @SerialName("message_id") val messageId: Possible<Snowflake>? = null,
        @SerialName("alert_system_message_id") val alertSystemMessageId: Possible<Snowflake>? = null,
        val content: String,
        @SerialName("matched_keyword") val matchedKeyword: Possible<String>? = null,
        @SerialName("matched_content") val matchedContent: Possible<String>? = null,
    )

}

class AutoModerationRuleCreateEvent
internal constructor(
    override val data: GuildAutoModerationRuleObject
): Event<GuildAutoModerationRuleObject>

class AutoModerationRuleDeleteEvent
    internal constructor(
        override val data: GuildAutoModerationRuleObject
    ): Event<GuildAutoModerationRuleObject>

class AutoModerationRuleUpdateEvent
internal constructor(
    override val data: GuildAutoModerationRuleObject
): Event<GuildAutoModerationRuleObject>

class ChannelCreateEvent
internal constructor(override val data: ChannelObject): Event<ChannelObject>

class ChannelDeleteEvent
internal constructor(override val data: ChannelObject): Event<ChannelObject>

class ChannelPinsUpdateEvent
internal constructor(override val data: DataObject) : Event<ChannelPinsUpdateEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Snowflake,
        @SerialName("channel_id") val channelId: Snowflake,
        @SerialName("last_pin_timestamp") val lastPinTimestamp: Possible<ISO8601Timestamp>? = null,
    )

}

class ChannelUpdateEvent
internal constructor(override val data: ChannelObject): Event<ChannelObject>

class EntitlementCreateEvent
internal constructor(override val data: EntitlementObject) : Event<EntitlementObject>

class EntitlementDeleteEvent
internal constructor(override val data: EntitlementObject) : Event<EntitlementObject>

class EntitlementUpdateEvent
internal constructor(override val data: EntitlementObject) : Event<EntitlementObject>

class GatewayHeartbeatAckEvent : Event<Unit> {

    override val data: Unit?
        get() = null

}

class GatewayHeartbeatEvent : Event<Unit> {

    override val data: Unit?
        get() = null

}

class GatewayHelloEvent
internal constructor(override val data: DataObject) : Event<GatewayHelloEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("heartbeat_interval") val heartbeatInterval: Long,
    )

}

class GatewayInvalidSessionEvent(val isResumeRecommended: Boolean) : Event<Boolean> {

    override val data: Boolean
        get() = isResumeRecommended

}

class GatewayReadyEvent
internal constructor(override val data: DataObject) : Event<GatewayReadyEvent.DataObject> {

    @Serializable
    data class DataObject(
        val v: Int,
        val user: UserObject,
        val guilds: List<GuildObject>,
        @SerialName("session_id") val sessionId: String,
        @SerialName("resume_gateway_url") val resumeGatewayUrl: String,
        val shard: Possible<List<Int>>? = null,
        val application: ApplicationObject,
    )

}

class GatewayReconnectEvent : Event<Unit> {

    override val data: Unit?
        get() = null

}

class GatewayResumedEvent : Event<Unit> {

    override val data: Unit?
        get() = null

}

class GuildAuditLogEntryCreateEvent
internal constructor(override val data: AuditLogEntryObject) : Event<AuditLogEntryObject>

class GuildBanAddEvent
internal constructor(override val data: DataObject) : Event<GuildBanAddEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Snowflake,
        val user: UserObject
    )

}

class GuildBanRemoveEvent
internal constructor(override val data: DataObject) : Event<GuildBanRemoveEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Snowflake,
        val user: UserObject
    )

}

data class GuildCreateEvent
internal constructor(override val data: GuildObject) : Event<GuildObject>

data class GuildDeleteEvent
internal constructor(override val data: GuildObject) : Event<GuildObject>

class GuildEmojisUpdateEvent
internal constructor(override val data: DataObject) : Event<GuildEmojisUpdateEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Snowflake,
        val emojis: List<EmojiObject>
    )

}

class GuildIntegrationsUpdateEvent
internal constructor(override val data: DataObject) : Event<GuildIntegrationsUpdateEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Snowflake
    )

}

class GuildMemberAddEvent
internal constructor(override val data: GuildMemberObject) : Event<GuildMemberObject>

class GuildMemberRemoveEvent
internal constructor(override val data: DataObject) : Event<GuildMemberRemoveEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Snowflake,
        val user: UserObject
    )

}

class GuildMembersChunkEvent
internal constructor(override val data: DataObject) : Event<GuildMembersChunkEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Snowflake,
        val members: List<GuildMemberObject>,
        @SerialName("chunk_index") val chunkIndex: Int,
        @SerialName("chunk_count") val chunkCount: Int,
        @SerialName("not_found") val notFound: Possible<List<Snowflake>>? = null,
        val presences: Possible<List<UpdatePresenceObject>>? = null,
        val nonce: Possible<String>? = null,
    )

}

class GuildMemberUpdateEvent
internal constructor(override val data: DataObject) : Event<GuildMemberUpdateEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Snowflake,
        val roles: List<Snowflake>,
        val user: UserObject,
        val nick: Possible<String>? = null,
        val avatar: Possible<String>? = null,
        val banner: Possible<String>? = null,
        @SerialName("joined_at") val joinedAt: Possible<ISO8601Timestamp>? = null,
        @SerialName("premium_since") val premiumSince: Possible<ISO8601Timestamp>? = null,
        val deaf: Possible<Boolean>? = null,
        val mute: Possible<Boolean>? = null,
        val pending: Possible<Boolean>? = null,
        @SerialName("communication_disabled_until") val communicationDisabledUntil: Possible<ISO8601Timestamp>? = null,
        val flags: Possible<Int>? = null,
        @SerialName("avatar_decoration_data") val avatarDecorationData: Possible<UserAvatarDecorationDataObject>? = null
    )

}

class GuildRoleCreateEvent
internal constructor(override val data: DataObject) : Event<GuildRoleCreateEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Snowflake,
        val role: RoleObject
    )

}

class GuildRoleDeleteEvent
internal constructor(override val data: DataObject) : Event<GuildRoleDeleteEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Snowflake,
        @SerialName("role_id") val roleId: Snowflake
    )

}

class GuildRoleUpdateEvent
internal constructor(override val data: DataObject) : Event<GuildRoleUpdateEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Snowflake,
        val role: RoleObject
    )

}

class GuildScheduledEventCreateEvent
internal constructor(override val data: GuildScheduledEventObject) : Event<GuildScheduledEventObject>

class GuildScheduledEventDeleteEvent
internal constructor(override val data: GuildScheduledEventObject) : Event<GuildScheduledEventObject>

class GuildScheduledEventUpdateEvent
internal constructor(override val data: GuildScheduledEventObject) : Event<GuildScheduledEventObject>

class GuildScheduledEventUserAddEvent
internal constructor(override val data: DataObject) : Event<GuildScheduledEventUserAddEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("guild_scheduled_event_id") val guildScheduledEventId: Snowflake,
        @SerialName("user_id") val userId: Snowflake,
        @SerialName("guild_id") val guildId: Snowflake,
    )

}

class GuildScheduledEventUserRemoveEvent
internal constructor(override val data: DataObject) : Event<GuildScheduledEventUserRemoveEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("guild_scheduled_event_id") val guildScheduledEventId: Snowflake,
        @SerialName("user_id") val userId: Snowflake,
        @SerialName("guild_id") val guildId: Snowflake,
    )

}

class GuildSoundboardSoundCreateEvent
internal constructor(override val data: SoundboardSoundObject) : Event<SoundboardSoundObject>

class GuildSoundboardSoundDeleteEvent
internal constructor(override val data: DataObject) : Event<GuildSoundboardSoundDeleteEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("sound_id") val soundId: Snowflake,
        @SerialName("guild_id") val guildId: Snowflake
    )

}

class GuildSoundboardSoundsUpdateEvent
internal constructor(override val data: DataObject) : Event<GuildSoundboardSoundsUpdateEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("soundboard_sounds") val soundboardSounds: List<SoundboardSoundObject>,
        @SerialName("guild_id") val guildId: Snowflake
    )

}

class GuildSoundboardSoundUpdateEvent
internal constructor(override val data: SoundboardSoundObject) : Event<SoundboardSoundObject>

class GuildStickersUpdateEvent
internal constructor(override val data: DataObject) : Event<GuildStickersUpdateEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Snowflake,
        val stickers: List<StickerObject>
    )

}

class GuildUpdateEvent
internal constructor(override val data: GuildObject) : Event<GuildObject>

class IntegrationCreateEvent
internal constructor(override val data: GuildIntegrationObject) : Event<GuildIntegrationObject>

class IntegrationDeleteEvent
internal constructor(override val data: DataObject) : Event<IntegrationDeleteEvent.DataObject> {

    @Serializable
    data class DataObject(
        val id: Snowflake,
        @SerialName("guild_id") val guildId: Snowflake,
        @SerialName("application_id") val applicationId: Possible<Snowflake>? = null
    )

}

class IntegrationUpdateEvent
internal constructor(override val data: GuildIntegrationObject) : Event<GuildIntegrationObject>

class InteractionCreateEvent
internal constructor(override val data: InteractionObject) : Event<InteractionObject>

class InviteCreateEvent
internal constructor(override val data: DataObject) : Event<InviteCreateEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("channel_id") val channelId: Snowflake,
        val code: String,
        @SerialName("created_at") val createdAt: ISO8601Timestamp,
        @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
        val inviter: Possible<UserObject>? = null,
        @SerialName("max_age") val maxAge: Int,
        @SerialName("max_uses") val maxUses: Int,
        @SerialName("target_type") val targetType: Possible<Int>? = null,
        @SerialName("target_user") val targetUser: Possible<UserObject>? = null,
        @SerialName("target_application") val targetApplication: Possible<ApplicationObject>? = null,
        val temporary: Boolean,
        val uses: Int,
    )

}

class InviteDeleteEvent
internal constructor(override val data: DataObject) : Event<InviteDeleteEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("channel_id") val channelId: Snowflake,
        @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
        val code: String,
    )

}

class MessageCreateEvent
internal constructor(override val data: MessageObject) : Event<MessageObject>

class MessageDeleteBulkEvent
internal constructor(override val data: DataObject) : Event<MessageDeleteBulkEvent.DataObject> {

    @Serializable
    data class DataObject(
        val ids: List<Snowflake>,
        @SerialName("channel_id") val channelId: Snowflake,
        @SerialName("guild_id") val guildId: Possible<Snowflake>? = null
    )

}

class MessageDeleteEvent
internal constructor(override val data: DataObject) : Event<MessageDeleteEvent.DataObject> {

    @Serializable
    data class DataObject(
        val id: Snowflake,
        @SerialName("channel_id") val channelId: Snowflake,
        @SerialName("guild_id") val guildId: Possible<Snowflake>? = null
    )

}

class MessagePollVoteAddEvent
internal constructor(override val data: DataObject) : Event<MessagePollVoteAddEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("user_id") val userId: Snowflake,
        @SerialName("channel_id") val channelId: Snowflake,
        @SerialName("message_id") val messageId: Snowflake,
        @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
        @SerialName("answer_id") val answerId: Int,
    )

}

class MessagePollVoteRemoveEvent
internal constructor(override val data: DataObject) : Event<MessagePollVoteRemoveEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("user_id") val userId: Snowflake,
        @SerialName("channel_id") val channelId: Snowflake,
        @SerialName("message_id") val messageId: Snowflake,
        @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
        @SerialName("answer_id") val answerId: Int,
    )

}

class MessageReactionAddEvent
internal constructor(override val data: DataObject) : Event<MessageReactionAddEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("user_id") val userId: Snowflake,
        @SerialName("channel_id") val channelId: Snowflake,
        @SerialName("message_id") val messageId: Snowflake,
        @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
        val member: Possible<GuildMemberObject>? = null,
        val emoji: EmojiObject,
        @SerialName("message_author_id") val messageAuthorId: Possible<Snowflake>? = null,
        val burst: Boolean,
        @SerialName("burst_colors") val burstColors: List<String>,
        val type: Int
    )

}

class MessageReactionRemoveAllEvent
internal constructor(override val data: DataObject) : Event<MessageReactionRemoveAllEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("channel_id") val channelId: Snowflake,
        @SerialName("message_id") val messageId: Snowflake,
        @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
    )

}

class MessageReactionRemoveEmojiEvent
internal constructor(override val data: DataObject) : Event<MessageReactionRemoveEmojiEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("channel_id") val channelId: Snowflake,
        @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
        @SerialName("message_id") val messageId: Snowflake,
        val emoji: EmojiObject
    )

}

class MessageReactionRemoveEvent
internal constructor(override val data: DataObject) : Event<MessageReactionRemoveEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("user_id") val userId: Snowflake,
        @SerialName("channel_id") val channelId: Snowflake,
        @SerialName("message_id") val messageId: Snowflake,
        @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
        val emoji: EmojiObject,
        val burst: Boolean,
        val type: Int
    )

}

class MessageUpdateEvent
internal constructor(override val data: MessageObject) : Event<MessageObject>

class PresenceUpdateEvent
internal constructor(override val data: DataObject) : Event<PresenceUpdateEvent.DataObject> {

    @Serializable
    data class DataObject(
        val user: UserObject,
        @SerialName("guild_id") val guildId: Snowflake,
        val status: String,
        val activities: List<ActivityObject>,
        @SerialName("client_status") val clientStatus: ClientStatusObject
    )

}

class SoundboardSoundsEvent
internal constructor(override val data: DataObject) : Event<SoundboardSoundsEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("soundboard_sounds") val soundboardSounds: List<SoundboardSoundObject>,
        @SerialName("guild_id") val guildId: Snowflake,
    )

}

class StageInstanceCreateEvent
internal constructor(override val data: StageInstanceObject) : Event<StageInstanceObject>

class StageInstanceDeleteEvent
internal constructor(override val data: StageInstanceObject) : Event<StageInstanceObject>

class StageInstanceUpdateEvent
internal constructor(override val data: StageInstanceObject) : Event<StageInstanceObject>

class SubscriptionCreateEvent
internal constructor(override val data: SubscriptionObject) : Event<SubscriptionObject>

class SubscriptionDeleteEvent
internal constructor(override val data: SubscriptionObject) : Event<SubscriptionObject>

class SubscriptionUpdateEvent
internal constructor(override val data: SubscriptionObject) : Event<SubscriptionObject>

class ThreadCreateEvent
internal constructor(override val data: ChannelObject): Event<ChannelObject>

class ThreadDeleteEvent
internal constructor(override val data: ChannelObject): Event<ChannelObject>

class ThreadListSyncEvent
internal constructor(override val data: DataObject): Event<ThreadListSyncEvent.DataObject> {

    @Serializable
    data class DataObject(
        val guildId: Snowflake,
        val channelIds: Possible<List<Snowflake>>? = null,
        val threads: List<ChannelObject>,
        val members: List<ThreadMemberObject>
    )

}

class ThreadMembersUpdateEvent
internal constructor(override val data: DataObject): Event<ThreadMembersUpdateEvent.DataObject> {

    @Serializable
    data class DataObject(
        val id: Snowflake,
        @SerialName("guild_id") val guildId: Snowflake,
        @SerialName("member_count") val memberCount: Int,
        @SerialName("added_members") val addedMembers: Possible<List<ThreadMemberObject>>? = null,
        @SerialName("removed_member_ids") val removedMemberIds: Possible<List<Snowflake>>? = null
    )

}

class ThreadMemberUpdateEvent
internal constructor(override val data: ThreadMemberObject): Event<ThreadMemberObject>

class ThreadUpdateEvent
internal constructor(override val data: ChannelObject): Event<ChannelObject>

class TypingStartEvent
internal constructor(override val data: DataObject) : Event<TypingStartEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("channel_id") val channelId: Snowflake,
        @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
        @SerialName("user_id") val userId: Snowflake,
        val timestamp: Long,
        val member: Possible<GuildMemberObject>? = null,
    )

}

class UserUpdateEvent
internal constructor(override val data: UserObject) : Event<UserObject>

class VoiceChannelEffectSendEvent
internal constructor(override val data: DataObject) : Event<VoiceChannelEffectSendEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("channel_id") val channelId: Snowflake,
        @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
        @SerialName("user_id") val userId: Snowflake,
        val emoji: Possible<EmojiObject>? = null,
        @SerialName("animation_type") val animationType: Possible<Int>? = null,
        @SerialName("animation_id") val animationId: Possible<Int>? = null,
        @SerialName("sound_id") val soundId: Snowflake,
        @SerialName("sound_volume") val soundVolume: Double
    )

}

class VoiceServerUpdateEvent
internal constructor(override val data: DataObject) : Event<VoiceServerUpdateEvent.DataObject> {

    @Serializable
    data class DataObject(
        val token: String,
        @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
        val endpoint: String?
    )

}

class VoiceStateUpdateEvent
internal constructor(override val data: VoiceStateObject) : Event<VoiceStateObject>

class WebhooksUpdateEvent
internal constructor(override val data: DataObject) : Event<WebhooksUpdateEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
        @SerialName("channel_id") val channelId: Snowflake,
    )

}