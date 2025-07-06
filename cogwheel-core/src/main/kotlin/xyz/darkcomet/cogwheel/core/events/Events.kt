package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.*
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

interface Event<TData> {
    val type: EventType<*>
    val data: TData?
}

interface EventType<TEvent : Event<*>>

/**
 * Defines the exhaustive list of events in the Discord Gateway API that the client can subscribe to.
 */
open class Gateway protected constructor() {

    // This scoped namespace is purely for developer ergonomics

    class ApplicationCommandPermissionsUpdate
    internal constructor(
        override val data: ApplicationCommandPermissionObject,
        override val type: EventType<ApplicationCommandPermissionsUpdate> = Event
    ) : Event<ApplicationCommandPermissionObject> {
        companion object Event : EventType<ApplicationCommandPermissionsUpdate>
    }

    class AutoModerationActionExecution
    internal constructor(
        override val data: DataObject,
        override val type: EventType<AutoModerationActionExecution> = Event
    ): Event<AutoModerationActionExecution.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: Snowflake,
            val action: AutoModerationActionObject,
            @SerialName("rule_id") val ruleId: Snowflake,
            @SerialName("rule_trigger_type") val ruleTriggerType: Int,
            @SerialName("user_id") val userId: Snowflake,
            @SerialName("channel_id") val channelId: MaybeAbsent<Snowflake>? = null,
            @SerialName("message_id") val messageId: MaybeAbsent<Snowflake>? = null,
            @SerialName("alert_system_message_id") val alertSystemMessageId: MaybeAbsent<Snowflake>? = null,
            val content: String,
            @SerialName("matched_keyword") val matchedKeyword: MaybeAbsent<String>? = null,
            @SerialName("matched_content") val matchedContent: MaybeAbsent<String>? = null,
        )

        companion object Event : EventType<AutoModerationActionExecution>
    }

    class AutoModerationRuleCreate
    internal constructor(
        override val data: GuildAutoModerationRuleObject,
        override val type: EventType<AutoModerationRuleCreate> = Event
    ): Event<GuildAutoModerationRuleObject> {
        companion object Event : EventType<AutoModerationRuleCreate>
    }

    class AutoModerationRuleDelete
    internal constructor(
        override val data: GuildAutoModerationRuleObject,
        override val type: EventType<AutoModerationRuleDelete> = Event
    ): Event<GuildAutoModerationRuleObject> {
        companion object Event : EventType<AutoModerationRuleDelete>
    }

    class AutoModerationRuleUpdate
    internal constructor(
        override val data: GuildAutoModerationRuleObject,
        override val type: EventType<AutoModerationRuleUpdate> = Event
    ): Event<GuildAutoModerationRuleObject> {
        companion object Event : EventType<AutoModerationRuleUpdate>
    }

    class ChannelCreate
    internal constructor(
        override val data: ChannelObject,
        override val type: EventType<ChannelCreate> = Event
    ): Event<ChannelObject> {
        companion object Event : EventType<ChannelCreate>
    }

    class ChannelDelete
    internal constructor(
        override val data: ChannelObject,
        override val type: EventType<ChannelDelete> = Event
    ): Event<ChannelObject> {
        companion object Event : EventType<ChannelDelete>
    }

    class ChannelPinsUpdate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<ChannelPinsUpdate> = Event
    ) : Event<ChannelPinsUpdate.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: Snowflake,
            @SerialName("channel_id") val channelId: Snowflake,
            @SerialName("last_pin_timestamp") val lastPinTimestamp: MaybeAbsent<ISO8601Timestamp>? = null,
        )

        companion object Event : EventType<ChannelPinsUpdate>
        
    }

    class ChannelUpdate
    internal constructor(
        override val data: ChannelObject,
        override val type: EventType<ChannelUpdate> = Event
    ): Event<ChannelObject> {
        companion object Event : EventType<ChannelUpdate>
    }

    class EntitlementCreate
    internal constructor(
        override val data: EntitlementObject,
        override val type: EventType<EntitlementCreate> = Event
    ) : Event<EntitlementObject> {
        companion object Event : EventType<EntitlementCreate>
    }

    class EntitlementDelete
    internal constructor(
        override val data: EntitlementObject,
        override val type: EventType<EntitlementDelete> = Event
    ) : Event<EntitlementObject> {
        companion object Event : EventType<EntitlementDelete>
    }

    class EntitlementUpdate
    internal constructor(
        override val data: EntitlementObject,
        override val type: EventType<EntitlementUpdate> = Event
    ) : Event<EntitlementObject> {
        companion object Event : EventType<EntitlementUpdate>
    }

    class HeartbeatAck(
        override val type: EventType<HeartbeatAck> = Event
    ) : Event<Unit> {

        override val data: Unit?
            get() = null

        companion object Event : EventType<HeartbeatAck>
    }

    class Heartbeat(
        override val type: EventType<Heartbeat> = Event
    ) : Event<Unit> {

        override val data: Unit?
            get() = null

        companion object Event : EventType<Heartbeat>
    }

    class Hello
    internal constructor(
        override val data: DataObject,
        override val type: EventType<Hello> = Event
    ) : Event<Hello.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("heartbeat_interval") val heartbeatInterval: Long,
        )

        companion object Event : EventType<Hello>
    }

    class InvalidSession(
        val isResumeRecommended: Boolean,
        override val type: EventType<InvalidSession> = Event
    ) : Event<Boolean> {

        override val data: Boolean
            get() = isResumeRecommended

        companion object Event : EventType<InvalidSession>
    }

    class Ready
    internal constructor(
        override val data: DataObject,
        override val type: EventType<Ready> = Event
    ) : Event<Ready.DataObject> {

        @Serializable
        data class DataObject(
            val v: Int,
            val user: UserObject,
            val guilds: List<GuildObject>,
            @SerialName("session_id") val sessionId: String,
            @SerialName("resume_gateway_url") val resumeGatewayUrl: String,
            val shard: MaybeAbsent<List<Int>>? = null,
            val application: ApplicationObject,
        )

        companion object Event : EventType<Ready>
    }

    class Reconnect(
        override val type: EventType<Reconnect> = Event
    ) : Event<Unit> {

        override val data: Unit?
            get() = null

        companion object Event : EventType<Reconnect>
    }

    class Resumed(
        override val type: EventType<Resumed> = Event
    ) : Event<Unit> {
        override val data: Unit?
            get() = null

        companion object Event : EventType<Resumed>
    }

    class GuildAuditLogEntryCreate
    internal constructor(
        override val data: AuditLogEntryObject,
        override val type: EventType<GuildAuditLogEntryCreate> = Event
    ) : Event<AuditLogEntryObject> {
        companion object Event : EventType<GuildAuditLogEntryCreate>
    }

    class GuildBanAdd
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildBanAdd> = Event
    ) : Event<GuildBanAdd.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: Snowflake,
            val user: UserObject
        )

        companion object Event : EventType<GuildBanAdd>
    }

    class GuildBanRemove
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildBanRemove> = Event
    ) : Event<GuildBanRemove.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: Snowflake,
            val user: UserObject
        )

        companion object Event : EventType<GuildBanRemove>
    }

    class GuildCreate
    internal constructor(
        override val data: GuildObject,
        override val type: EventType<GuildCreate> = Event
    ) : Event<GuildObject> {
        companion object Event : EventType<GuildCreate>
    }

    class GuildDelete
    internal constructor(
        override val data: GuildObject,
        override val type: EventType<GuildDelete> = Event
    ) : Event<GuildObject> {
        companion object Event : EventType<GuildDelete>
    }

    class GuildEmojisUpdate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildEmojisUpdate> = Event
    ) : Event<GuildEmojisUpdate.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: Snowflake,
            val emojis: List<EmojiObject>
        )

        companion object Event : EventType<GuildEmojisUpdate>
    }

    class GuildIntegrationsUpdate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildIntegrationsUpdate> = Event
    ) : Event<GuildIntegrationsUpdate.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: Snowflake
        )

        companion object Event : EventType<GuildIntegrationsUpdate>
    }

    class GuildMemberAdd
    internal constructor(
        override val data: GuildMemberObject,
        override val type: EventType<GuildMemberAdd> = Event
    ) : Event<GuildMemberObject> {
        companion object Event : EventType<GuildMemberAdd>
    }

    class GuildMemberRemove
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildMemberRemove> = Event
    ) : Event<GuildMemberRemove.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: Snowflake,
            val user: UserObject
        )

        companion object Event : EventType<GuildMemberRemove>
    }

    class GuildMembersChunk
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildMembersChunk> = Event
    ) : Event<GuildMembersChunk.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: Snowflake,
            val members: List<GuildMemberObject>,
            @SerialName("chunk_index") val chunkIndex: Int,
            @SerialName("chunk_count") val chunkCount: Int,
            @SerialName("not_found") val notFound: MaybeAbsent<List<Snowflake>>? = null,
            val presences: MaybeAbsent<List<UpdatePresenceObject>>? = null,
            val nonce: MaybeAbsent<String>? = null,
        )
        
        companion object Event : EventType<GuildMembersChunk>
    }

    class GuildMemberUpdate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildMemberUpdate> = Event
    ) : Event<GuildMemberUpdate.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: Snowflake,
            val roles: List<Snowflake>,
            val user: UserObject,
            val nick: MaybeAbsent<String>? = null,
            val avatar: MaybeAbsent<String>? = null,
            val banner: MaybeAbsent<String>? = null,
            @SerialName("joined_at") val joinedAt: MaybeAbsent<ISO8601Timestamp>? = null,
            @SerialName("premium_since") val premiumSince: MaybeAbsent<ISO8601Timestamp>? = null,
            val deaf: MaybeAbsent<Boolean>? = null,
            val mute: MaybeAbsent<Boolean>? = null,
            val pending: MaybeAbsent<Boolean>? = null,
            @SerialName("communication_disabled_until") val communicationDisabledUntil: MaybeAbsent<ISO8601Timestamp>? = null,
            val flags: MaybeAbsent<Int>? = null,
            @SerialName("avatar_decoration_data") val avatarDecorationData: MaybeAbsent<UserAvatarDecorationDataObject>? = null
        )

        companion object Event : EventType<GuildMemberUpdate>
    }

    class GuildRoleCreate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildRoleCreate> = Event
    ) : Event<GuildRoleCreate.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: Snowflake,
            val role: RoleObject
        )

        companion object Event : EventType<GuildRoleCreate>
    }

    class GuildRoleDelete
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildRoleDelete> = Event
    ) : Event<GuildRoleDelete.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: Snowflake,
            @SerialName("role_id") val roleId: Snowflake
        )

        companion object Event : EventType<GuildRoleDelete>
    }

    class GuildRoleUpdate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildRoleUpdate> = Event
    ) : Event<GuildRoleUpdate.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: Snowflake,
            val role: RoleObject
        )

        companion object Event : EventType<GuildRoleUpdate>
    }

    class GuildScheduledEventCreate
    internal constructor(
        override val data: GuildScheduledEventObject,
        override val type: EventType<GuildScheduledEventCreate> = Event
    ) : Event<GuildScheduledEventObject> {
        companion object Event : EventType<GuildScheduledEventCreate>
    }

    class GuildScheduledEventDelete
    internal constructor(
        override val data: GuildScheduledEventObject,
        override val type: EventType<GuildScheduledEventDelete> = Event
    ) : Event<GuildScheduledEventObject> {
        companion object Event : EventType<GuildScheduledEventDelete>
    }

    class GuildScheduledEventUpdate
    internal constructor(
        override val data: GuildScheduledEventObject,
        override val type: EventType<GuildScheduledEventUpdate> = Event
    ) : Event<GuildScheduledEventObject> {
        companion object Event : EventType<GuildScheduledEventUpdate>
    }

    class GuildScheduledEventUserAdd
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildScheduledEventUserAdd> = Event
    ) : Event<GuildScheduledEventUserAdd.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_scheduled_event_id") val guildScheduledEventId: Snowflake,
            @SerialName("user_id") val userId: Snowflake,
            @SerialName("guild_id") val guildId: Snowflake,
        )

        companion object Event : EventType<GuildScheduledEventUserAdd>
    }

    class GuildScheduledEventUserRemove
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildScheduledEventUserRemove> = Event
    ) : Event<GuildScheduledEventUserRemove.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_scheduled_event_id") val guildScheduledEventId: Snowflake,
            @SerialName("user_id") val userId: Snowflake,
            @SerialName("guild_id") val guildId: Snowflake,
        )

        companion object Event : EventType<GuildScheduledEventUserRemove>
    }

    class GuildSoundboardSoundCreate
    internal constructor(
        override val data: SoundboardSoundObject,
        override val type: EventType<GuildSoundboardSoundCreate> = Event
    ) : Event<SoundboardSoundObject> {
        companion object Event : EventType<GuildSoundboardSoundCreate>
    }

    class GuildSoundboardSoundDelete
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildSoundboardSoundDelete> = Event
    ) : Event<GuildSoundboardSoundDelete.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("sound_id") val soundId: Snowflake,
            @SerialName("guild_id") val guildId: Snowflake
        )

        companion object Event : EventType<GuildSoundboardSoundDelete>
    }

    class GuildSoundboardSoundsUpdate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildSoundboardSoundsUpdate> = Event
    ) : Event<GuildSoundboardSoundsUpdate.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("soundboard_sounds") val soundboardSounds: List<SoundboardSoundObject>,
            @SerialName("guild_id") val guildId: Snowflake
        )

        companion object Event : EventType<GuildSoundboardSoundsUpdate>
    }

    class GuildSoundboardSoundUpdate
    internal constructor(
        override val data: SoundboardSoundObject,
        override val type: EventType<GuildSoundboardSoundUpdate> = Event
    ) : Event<SoundboardSoundObject> {
        companion object Event : EventType<GuildSoundboardSoundUpdate>
    }

    class GuildStickersUpdate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildStickersUpdate> = Event
    ) : Event<GuildStickersUpdate.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: Snowflake,
            val stickers: List<StickerObject>
        )

        companion object Event : EventType<GuildStickersUpdate>
    }

    class GuildUpdate
    internal constructor(
        override val data: GuildObject,
        override val type: EventType<GuildUpdate> = Event
    ) : Event<GuildObject> {
        companion object Event : EventType<GuildUpdate>
    }

    class IntegrationCreate
    internal constructor(
        override val data: GuildIntegrationObject,
        override val type: EventType<IntegrationCreate> = Event
    ) : Event<GuildIntegrationObject> {
        companion object Event : EventType<IntegrationCreate>
    }

    class IntegrationDelete
    internal constructor(
        override val data: DataObject,
        override val type: EventType<IntegrationDelete> = Event
    ) : Event<IntegrationDelete.DataObject> {

        @Serializable
        data class DataObject(
            val id: Snowflake,
            @SerialName("guild_id") val guildId: Snowflake,
            @SerialName("application_id") val applicationId: MaybeAbsent<Snowflake>? = null
        )

        companion object Event : EventType<IntegrationDelete>
    }

    class IntegrationUpdate
    internal constructor(
        override val data: GuildIntegrationObject,
        override val type: EventType<IntegrationUpdate> = Event
    ) : Event<GuildIntegrationObject> {
        companion object Event : EventType<IntegrationUpdate>
    }

    class InteractionCreate
    internal constructor(
        override val data: InteractionObject,
        override val type: EventType<InteractionCreate> = Event
    ) : Event<InteractionObject> {
        companion object Event : EventType<InteractionCreate>
    }

    class InviteCreate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<InviteCreate> = Event
    ) : Event<InviteCreate.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("channel_id") val channelId: Snowflake,
            val code: String,
            @SerialName("created_at") val createdAt: ISO8601Timestamp,
            @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
            val inviter: MaybeAbsent<UserObject>? = null,
            @SerialName("max_age") val maxAge: Int,
            @SerialName("max_uses") val maxUses: Int,
            @SerialName("target_type") val targetType: MaybeAbsent<Int>? = null,
            @SerialName("target_user") val targetUser: MaybeAbsent<UserObject>? = null,
            @SerialName("target_application") val targetApplication: MaybeAbsent<ApplicationObject>? = null,
            val temporary: Boolean,
            val uses: Int,
        )

        companion object Event : EventType<InviteCreate>
    }

    class InviteDelete
    internal constructor(
        override val data: DataObject,
        override val type: EventType<InviteDelete> = Event
    ) : Event<InviteDelete.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("channel_id") val channelId: Snowflake,
            @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
            val code: String,
        )

        companion object Event : EventType<InviteDelete>
    }

    class MessageCreate
    internal constructor(
        override val data: MessageObject,
        override val type: EventType<MessageCreate> = Event
    ) : Event<MessageObject> {
        companion object Event : EventType<MessageCreate>
    }

    class MessageDeleteBulk
    internal constructor(
        override val data: DataObject,
        override val type: EventType<MessageDeleteBulk> = Event
    ) : Event<MessageDeleteBulk.DataObject> {

        @Serializable
        data class DataObject(
            val ids: List<Snowflake>,
            @SerialName("channel_id") val channelId: Snowflake,
            @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null
        )

        companion object Event : EventType<MessageDeleteBulk>
    }

    class MessageDelete
    internal constructor(
        override val data: DataObject,
        override val type: EventType<MessageDelete> = Event
    ) : Event<MessageDelete.DataObject> {

        @Serializable
        data class DataObject(
            val id: Snowflake,
            @SerialName("channel_id") val channelId: Snowflake,
            @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null
        )

        companion object Event : EventType<MessageDelete>
    }

    class MessagePollVoteAdd
    internal constructor(
        override val data: DataObject,
        override val type: EventType<MessagePollVoteAdd> = Event
    ) : Event<MessagePollVoteAdd.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("user_id") val userId: Snowflake,
            @SerialName("channel_id") val channelId: Snowflake,
            @SerialName("message_id") val messageId: Snowflake,
            @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
            @SerialName("answer_id") val answerId: Int,
        )

        companion object Event : EventType<MessagePollVoteAdd>
    }

    class MessagePollVoteRemove
    internal constructor(
        override val data: DataObject,
        override val type: EventType<MessagePollVoteRemove> = Event
    ) : Event<MessagePollVoteRemove.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("user_id") val userId: Snowflake,
            @SerialName("channel_id") val channelId: Snowflake,
            @SerialName("message_id") val messageId: Snowflake,
            @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
            @SerialName("answer_id") val answerId: Int,
        )
        
        companion object Event : EventType<MessagePollVoteRemove>
    }

    class MessageReactionAdd
    internal constructor(
        override val data: DataObject,
        override val type: EventType<MessageReactionAdd> = Event
    ) : Event<MessageReactionAdd.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("user_id") val userId: Snowflake,
            @SerialName("channel_id") val channelId: Snowflake,
            @SerialName("message_id") val messageId: Snowflake,
            @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
            val member: MaybeAbsent<GuildMemberObject>? = null,
            val emoji: EmojiObject,
            @SerialName("message_author_id") val messageAuthorId: MaybeAbsent<Snowflake>? = null,
            val burst: Boolean,
            @SerialName("burst_colors") val burstColors: List<String>,
            val type: Int
        )

        companion object Event : EventType<MessageReactionAdd>
    }

    class MessageReactionRemoveAll
    internal constructor(
        override val data: DataObject,
        override val type: EventType<MessageReactionRemoveAll> = Event
    ) : Event<MessageReactionRemoveAll.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("channel_id") val channelId: Snowflake,
            @SerialName("message_id") val messageId: Snowflake,
            @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
        )

        companion object Event : EventType<MessageReactionRemoveAll>
    }

    class MessageReactionRemoveEmoji
    internal constructor(
        override val data: DataObject,
        override val type: EventType<MessageReactionRemoveEmoji> = Event
    ) : Event<MessageReactionRemoveEmoji.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("channel_id") val channelId: Snowflake,
            @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
            @SerialName("message_id") val messageId: Snowflake,
            val emoji: EmojiObject
        )

        companion object Event : EventType<MessageReactionRemoveEmoji>
    }

    class MessageReactionRemove
    internal constructor(
        override val data: DataObject,
        override val type: EventType<MessageReactionRemove> = Event
    ) : Event<MessageReactionRemove.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("user_id") val userId: Snowflake,
            @SerialName("channel_id") val channelId: Snowflake,
            @SerialName("message_id") val messageId: Snowflake,
            @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
            val emoji: EmojiObject,
            val burst: Boolean,
            val type: Int
        )

        companion object Event : EventType<MessageReactionRemove>
    }

    class MessageUpdate
    internal constructor(
        override val data: MessageObject,
        override val type: EventType<MessageUpdate> = Event
    ) : Event<MessageObject> {
        companion object Event : EventType<MessageUpdate>
    }

    class PresenceUpdate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<PresenceUpdate> = Event
    ) : Event<PresenceUpdate.DataObject> {

        @Serializable
        data class DataObject(
            val user: UserObject,
            @SerialName("guild_id") val guildId: Snowflake,
            val status: String,
            val activities: List<ActivityObject>,
            @SerialName("client_status") val clientStatus: ClientStatusObject
        )

        companion object Event : EventType<PresenceUpdate>
    }

    class SoundboardSounds
    internal constructor(
        override val data: DataObject,
        override val type: EventType<SoundboardSounds> = Event
    ) : Event<SoundboardSounds.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("soundboard_sounds") val soundboardSounds: List<SoundboardSoundObject>,
            @SerialName("guild_id") val guildId: Snowflake,
        )

        companion object Event : EventType<SoundboardSounds>
    }

    class StageInstanceCreate
    internal constructor(
        override val data: StageInstanceObject,
        override val type: EventType<StageInstanceCreate> = Event
    ) : Event<StageInstanceObject> {
        companion object Event : EventType<StageInstanceCreate>
    }

    class StageInstanceDelete
    internal constructor(
        override val data: StageInstanceObject,
        override val type: EventType<StageInstanceDelete> = Event
    ) : Event<StageInstanceObject> {
        companion object Event : EventType<StageInstanceDelete>
    }

    class StageInstanceUpdate
    internal constructor(
        override val data: StageInstanceObject,
        override val type: EventType<StageInstanceUpdate> = Event
    ) : Event<StageInstanceObject> {
        companion object Event : EventType<StageInstanceUpdate>
    }

    class SubscriptionCreate
    internal constructor(
        override val data: SubscriptionObject,
        override val type: EventType<SubscriptionCreate> = Event
    ) : Event<SubscriptionObject> {
        companion object Event : EventType<SubscriptionCreate>
    }

    class SubscriptionDelete
    internal constructor(
        override val data: SubscriptionObject,
        override val type: EventType<SubscriptionDelete> = Event
    ) : Event<SubscriptionObject> {
        companion object Event : EventType<SubscriptionDelete>
    }

    class SubscriptionUpdate
    internal constructor(
        override val data: SubscriptionObject,
        override val type: EventType<SubscriptionUpdate> = Event
    ) : Event<SubscriptionObject> {
        companion object Event : EventType<SubscriptionUpdate>
    }

    class ThreadCreate
    internal constructor(
        override val data: ChannelObject,
        override val type: EventType<ThreadCreate> = Event
    ): Event<ChannelObject> {
        companion object Event : EventType<ThreadCreate>
    }

    class ThreadDelete
    internal constructor(
        override val data: ChannelObject,
        override val type: EventType<ThreadDelete> = Event
    ): Event<ChannelObject> {
        companion object Event : EventType<ThreadDelete>
    }

    class ThreadListSync
    internal constructor(
        override val data: DataObject,
        override val type: EventType<ThreadListSync> = Event
    ): Event<ThreadListSync.DataObject> {

        @Serializable
        data class DataObject(
            val guildId: Snowflake,
            val channelIds: MaybeAbsent<List<Snowflake>>? = null,
            val threads: List<ChannelObject>,
            val members: List<ThreadMemberObject>
        )

        companion object Event : EventType<ThreadListSync>
    }

    class ThreadMembersUpdate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<ThreadMembersUpdate> = Event
    ): Event<ThreadMembersUpdate.DataObject> {

        @Serializable
        data class DataObject(
            val id: Snowflake,
            @SerialName("guild_id") val guildId: Snowflake,
            @SerialName("member_count") val memberCount: Int,
            @SerialName("added_members") val addedMembers: MaybeAbsent<List<ThreadMemberObject>>? = null,
            @SerialName("removed_member_ids") val removedMemberIds: MaybeAbsent<List<Snowflake>>? = null
        )

        companion object Event : EventType<ThreadMembersUpdate>
    }

    class ThreadMemberUpdate
    internal constructor(
        override val data: ThreadMemberObject,
        override val type: EventType<ThreadMemberUpdate> = Event
    ): Event<ThreadMemberObject> {
        companion object Event : EventType<ThreadMemberUpdate>
    }

    class ThreadUpdate
    internal constructor(
        override val data: ChannelObject,
        override val type: EventType<ThreadUpdate> = Event
    ): Event<ChannelObject> {
        companion object Event : EventType<ThreadUpdate>
    }

    class TypingStart
    internal constructor(
        override val data: DataObject,
        override val type: EventType<TypingStart> = Event
    ) : Event<TypingStart.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("channel_id") val channelId: Snowflake,
            @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
            @SerialName("user_id") val userId: Snowflake,
            val timestamp: Long,
            val member: MaybeAbsent<GuildMemberObject>? = null,
        )

        companion object Event : EventType<TypingStart>
    }

    class UserUpdate
    internal constructor(
        override val data: UserObject,
        override val type: EventType<UserUpdate> = Event
    ) : Event<UserObject> {
        companion object Event : EventType<UserUpdate>
    }

    class VoiceChannelEffectSend
    internal constructor(
        override val data: DataObject,
        override val type: EventType<VoiceChannelEffectSend> = Event
    ) : Event<VoiceChannelEffectSend.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("channel_id") val channelId: Snowflake,
            @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
            @SerialName("user_id") val userId: Snowflake,
            val emoji: MaybeAbsent<EmojiObject>? = null,
            @SerialName("animation_type") val animationType: MaybeAbsent<Int>? = null,
            @SerialName("animation_id") val animationId: MaybeAbsent<Int>? = null,
            @SerialName("sound_id") val soundId: Snowflake,
            @SerialName("sound_volume") val soundVolume: Double
        )

        companion object Event : EventType<VoiceChannelEffectSend>
    }

    class VoiceServerUpdate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<VoiceServerUpdate> = Event
    ) : Event<VoiceServerUpdate.DataObject> {

        @Serializable
        data class DataObject(
            val token: String,
            @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
            val endpoint: String?
        )

        companion object Event : EventType<VoiceServerUpdate>
    }

    class VoiceStateUpdate
    internal constructor(
        override val data: VoiceStateObject,
        override val type: EventType<VoiceStateUpdate> = Event
    ) : Event<VoiceStateObject> {
        companion object Event : EventType<VoiceStateUpdate>
    }

    class WebhooksUpdate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<WebhooksUpdate> = Event
    ) : Event<WebhooksUpdate.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
            @SerialName("channel_id") val channelId: Snowflake,
        )

        companion object Event : EventType<WebhooksUpdate>
    }
}