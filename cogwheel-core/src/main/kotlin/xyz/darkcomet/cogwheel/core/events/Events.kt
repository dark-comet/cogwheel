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
open class GatewayEvent protected constructor() {

    // This scoped namespace is purely for developer ergonomics

    class ApplicationCommandPermissionsUpdate
    internal constructor(
        override val data: ApplicationCommandPermissionObject,
        override val type: EventType<ApplicationCommandPermissionsUpdate> = Type
    ) : Event<ApplicationCommandPermissionObject> {
        companion object Type : EventType<ApplicationCommandPermissionsUpdate>
    }

    class AutoModerationActionExecution
    internal constructor(
        override val data: DataObject,
        override val type: EventType<AutoModerationActionExecution> = Type
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

        companion object Type : EventType<AutoModerationActionExecution>
    }

    class AutoModerationRuleCreate
    internal constructor(
        override val data: GuildAutoModerationRuleObject,
        override val type: EventType<AutoModerationRuleCreate> = Type
    ): Event<GuildAutoModerationRuleObject> {
        companion object Type : EventType<AutoModerationRuleCreate>
    }

    class AutoModerationRuleDelete
    internal constructor(
        override val data: GuildAutoModerationRuleObject,
        override val type: EventType<AutoModerationRuleDelete> = Type
    ): Event<GuildAutoModerationRuleObject> {
        companion object Type : EventType<AutoModerationRuleDelete>
    }

    class AutoModerationRuleUpdate
    internal constructor(
        override val data: GuildAutoModerationRuleObject,
        override val type: EventType<AutoModerationRuleUpdate> = Type
    ): Event<GuildAutoModerationRuleObject> {
        companion object Type : EventType<AutoModerationRuleUpdate>
    }

    class ChannelCreate
    internal constructor(
        override val data: ChannelObject,
        override val type: EventType<ChannelCreate> = Type
    ): Event<ChannelObject> {
        companion object Type : EventType<ChannelCreate>
    }

    class ChannelDelete
    internal constructor(
        override val data: ChannelObject,
        override val type: EventType<ChannelDelete> = Type
    ): Event<ChannelObject> {
        companion object Type : EventType<ChannelDelete>
    }

    class ChannelPinsUpdate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<ChannelPinsUpdate> = Type
    ) : Event<ChannelPinsUpdate.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: Snowflake,
            @SerialName("channel_id") val channelId: Snowflake,
            @SerialName("last_pin_timestamp") val lastPinTimestamp: MaybeAbsent<ISO8601Timestamp>? = null,
        )

        companion object Type : EventType<ChannelPinsUpdate>
        
    }

    class ChannelUpdate
    internal constructor(
        override val data: ChannelObject,
        override val type: EventType<ChannelUpdate> = Type
    ): Event<ChannelObject> {
        companion object Type : EventType<ChannelUpdate>
    }

    class EntitlementCreate
    internal constructor(
        override val data: EntitlementObject,
        override val type: EventType<EntitlementCreate> = Type
    ) : Event<EntitlementObject> {
        companion object Type : EventType<EntitlementCreate>
    }

    class EntitlementDelete
    internal constructor(
        override val data: EntitlementObject,
        override val type: EventType<EntitlementDelete> = Type
    ) : Event<EntitlementObject> {
        companion object Type : EventType<EntitlementDelete>
    }

    class EntitlementUpdate
    internal constructor(
        override val data: EntitlementObject,
        override val type: EventType<EntitlementUpdate> = Type
    ) : Event<EntitlementObject> {
        companion object Type : EventType<EntitlementUpdate>
    }

    class HeartbeatAck(
        override val type: EventType<HeartbeatAck> = Type
    ) : Event<Unit> {

        override val data: Unit?
            get() = null

        companion object Type : EventType<HeartbeatAck>
    }

    class Heartbeat(
        override val type: EventType<Heartbeat> = Type
    ) : Event<Unit> {

        override val data: Unit?
            get() = null

        companion object Type : EventType<Heartbeat>
    }

    class Hello
    internal constructor(
        override val data: DataObject,
        override val type: EventType<Hello> = Type
    ) : Event<Hello.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("heartbeat_interval") val heartbeatInterval: Long,
        )

        companion object Type : EventType<Hello>
    }

    class InvalidSession(
        val isResumeRecommended: Boolean,
        override val type: EventType<InvalidSession> = Type
    ) : Event<Boolean> {

        override val data: Boolean
            get() = isResumeRecommended

        companion object Type : EventType<InvalidSession>
    }

    class Ready
    internal constructor(
        override val data: DataObject,
        override val type: EventType<Ready> = Type
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

        companion object Type : EventType<Ready>
    }

    class Reconnect(
        override val type: EventType<Reconnect> = Type
    ) : Event<Unit> {

        override val data: Unit?
            get() = null

        companion object Type : EventType<Reconnect>
    }

    class Resumed(
        override val type: EventType<Resumed> = Type
    ) : Event<Unit> {
        override val data: Unit?
            get() = null

        companion object Type : EventType<Resumed>
    }

    class GuildAuditLogEntryCreate
    internal constructor(
        override val data: AuditLogEntryObject,
        override val type: EventType<GuildAuditLogEntryCreate> = Type
    ) : Event<AuditLogEntryObject> {
        companion object Type : EventType<GuildAuditLogEntryCreate>
    }

    class GuildBanAdd
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildBanAdd> = Type
    ) : Event<GuildBanAdd.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: Snowflake,
            val user: UserObject
        )

        companion object Type : EventType<GuildBanAdd>
    }

    class GuildBanRemove
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildBanRemove> = Type
    ) : Event<GuildBanRemove.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: Snowflake,
            val user: UserObject
        )

        companion object Type : EventType<GuildBanRemove>
    }

    class GuildCreate
    internal constructor(
        override val data: GuildObject,
        override val type: EventType<GuildCreate> = Type
    ) : Event<GuildObject> {
        companion object Type : EventType<GuildCreate>
    }

    class GuildDelete
    internal constructor(
        override val data: GuildObject,
        override val type: EventType<GuildDelete> = Type
    ) : Event<GuildObject> {
        companion object Type : EventType<GuildDelete>
    }

    class GuildEmojisUpdate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildEmojisUpdate> = Type
    ) : Event<GuildEmojisUpdate.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: Snowflake,
            val emojis: List<EmojiObject>
        )

        companion object Type : EventType<GuildEmojisUpdate>
    }

    class GuildIntegrationsUpdate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildIntegrationsUpdate> = Type
    ) : Event<GuildIntegrationsUpdate.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: Snowflake
        )

        companion object Type : EventType<GuildIntegrationsUpdate>
    }

    class GuildMemberAdd
    internal constructor(
        override val data: GuildMemberObject,
        override val type: EventType<GuildMemberAdd> = Type
    ) : Event<GuildMemberObject> {
        companion object Type : EventType<GuildMemberAdd>
    }

    class GuildMemberRemove
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildMemberRemove> = Type
    ) : Event<GuildMemberRemove.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: Snowflake,
            val user: UserObject
        )

        companion object Type : EventType<GuildMemberRemove>
    }

    class GuildMembersChunk
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildMembersChunk> = Type
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
        
        companion object Type : EventType<GuildMembersChunk>
    }

    class GuildMemberUpdate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildMemberUpdate> = Type
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

        companion object Type : EventType<GuildMemberUpdate>
    }

    class GuildRoleCreate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildRoleCreate> = Type
    ) : Event<GuildRoleCreate.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: Snowflake,
            val role: RoleObject
        )

        companion object Type : EventType<GuildRoleCreate>
    }

    class GuildRoleDelete
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildRoleDelete> = Type
    ) : Event<GuildRoleDelete.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: Snowflake,
            @SerialName("role_id") val roleId: Snowflake
        )

        companion object Type : EventType<GuildRoleDelete>
    }

    class GuildRoleUpdate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildRoleUpdate> = Type
    ) : Event<GuildRoleUpdate.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: Snowflake,
            val role: RoleObject
        )

        companion object Type : EventType<GuildRoleUpdate>
    }

    class GuildScheduledEventCreate
    internal constructor(
        override val data: GuildScheduledEventObject,
        override val type: EventType<GuildScheduledEventCreate> = Type
    ) : Event<GuildScheduledEventObject> {
        companion object Type : EventType<GuildScheduledEventCreate>
    }

    class GuildScheduledEventDelete
    internal constructor(
        override val data: GuildScheduledEventObject,
        override val type: EventType<GuildScheduledEventDelete> = Type
    ) : Event<GuildScheduledEventObject> {
        companion object Type : EventType<GuildScheduledEventDelete>
    }

    class GuildScheduledEventUpdate
    internal constructor(
        override val data: GuildScheduledEventObject,
        override val type: EventType<GuildScheduledEventUpdate> = Type
    ) : Event<GuildScheduledEventObject> {
        companion object Type : EventType<GuildScheduledEventUpdate>
    }

    class GuildScheduledEventUserAdd
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildScheduledEventUserAdd> = Type
    ) : Event<GuildScheduledEventUserAdd.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_scheduled_event_id") val guildScheduledEventId: Snowflake,
            @SerialName("user_id") val userId: Snowflake,
            @SerialName("guild_id") val guildId: Snowflake,
        )

        companion object Type : EventType<GuildScheduledEventUserAdd>
    }

    class GuildScheduledEventUserRemove
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildScheduledEventUserRemove> = Type
    ) : Event<GuildScheduledEventUserRemove.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_scheduled_event_id") val guildScheduledEventId: Snowflake,
            @SerialName("user_id") val userId: Snowflake,
            @SerialName("guild_id") val guildId: Snowflake,
        )

        companion object Type : EventType<GuildScheduledEventUserRemove>
    }

    class GuildSoundboardSoundCreate
    internal constructor(
        override val data: SoundboardSoundObject,
        override val type: EventType<GuildSoundboardSoundCreate> = Type
    ) : Event<SoundboardSoundObject> {
        companion object Type : EventType<GuildSoundboardSoundCreate>
    }

    class GuildSoundboardSoundDelete
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildSoundboardSoundDelete> = Type
    ) : Event<GuildSoundboardSoundDelete.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("sound_id") val soundId: Snowflake,
            @SerialName("guild_id") val guildId: Snowflake
        )

        companion object Type : EventType<GuildSoundboardSoundDelete>
    }

    class GuildSoundboardSoundsUpdate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildSoundboardSoundsUpdate> = Type
    ) : Event<GuildSoundboardSoundsUpdate.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("soundboard_sounds") val soundboardSounds: List<SoundboardSoundObject>,
            @SerialName("guild_id") val guildId: Snowflake
        )

        companion object Type : EventType<GuildSoundboardSoundsUpdate>
    }

    class GuildSoundboardSoundUpdate
    internal constructor(
        override val data: SoundboardSoundObject,
        override val type: EventType<GuildSoundboardSoundUpdate> = Type
    ) : Event<SoundboardSoundObject> {
        companion object Type : EventType<GuildSoundboardSoundUpdate>
    }

    class GuildStickersUpdate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<GuildStickersUpdate> = Type
    ) : Event<GuildStickersUpdate.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: Snowflake,
            val stickers: List<StickerObject>
        )

        companion object Type : EventType<GuildStickersUpdate>
    }

    class GuildUpdate
    internal constructor(
        override val data: GuildObject,
        override val type: EventType<GuildUpdate> = Type
    ) : Event<GuildObject> {
        companion object Type : EventType<GuildUpdate>
    }

    class IntegrationCreate
    internal constructor(
        override val data: GuildIntegrationObject,
        override val type: EventType<IntegrationCreate> = Type
    ) : Event<GuildIntegrationObject> {
        companion object Type : EventType<IntegrationCreate>
    }

    class IntegrationDelete
    internal constructor(
        override val data: DataObject,
        override val type: EventType<IntegrationDelete> = Type
    ) : Event<IntegrationDelete.DataObject> {

        @Serializable
        data class DataObject(
            val id: Snowflake,
            @SerialName("guild_id") val guildId: Snowflake,
            @SerialName("application_id") val applicationId: MaybeAbsent<Snowflake>? = null
        )

        companion object Type : EventType<IntegrationDelete>
    }

    class IntegrationUpdate
    internal constructor(
        override val data: GuildIntegrationObject,
        override val type: EventType<IntegrationUpdate> = Type
    ) : Event<GuildIntegrationObject> {
        companion object Type : EventType<IntegrationUpdate>
    }

    class InteractionCreate
    internal constructor(
        override val data: InteractionObject,
        override val type: EventType<InteractionCreate> = Type
    ) : Event<InteractionObject> {
        companion object Type : EventType<InteractionCreate>
    }

    class InviteCreate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<InviteCreate> = Type
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

        companion object Type : EventType<InviteCreate>
    }

    class InviteDelete
    internal constructor(
        override val data: DataObject,
        override val type: EventType<InviteDelete> = Type
    ) : Event<InviteDelete.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("channel_id") val channelId: Snowflake,
            @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
            val code: String,
        )

        companion object Type : EventType<InviteDelete>
    }

    class MessageCreate
    internal constructor(
        override val data: MessageObject,
        override val type: EventType<MessageCreate> = Type
    ) : Event<MessageObject> {
        companion object Type : EventType<MessageCreate>
    }

    class MessageDeleteBulk
    internal constructor(
        override val data: DataObject,
        override val type: EventType<MessageDeleteBulk> = Type
    ) : Event<MessageDeleteBulk.DataObject> {

        @Serializable
        data class DataObject(
            val ids: List<Snowflake>,
            @SerialName("channel_id") val channelId: Snowflake,
            @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null
        )

        companion object Type : EventType<MessageDeleteBulk>
    }

    class MessageDelete
    internal constructor(
        override val data: DataObject,
        override val type: EventType<MessageDelete> = Type
    ) : Event<MessageDelete.DataObject> {

        @Serializable
        data class DataObject(
            val id: Snowflake,
            @SerialName("channel_id") val channelId: Snowflake,
            @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null
        )

        companion object Type : EventType<MessageDelete>
    }

    class MessagePollVoteAdd
    internal constructor(
        override val data: DataObject,
        override val type: EventType<MessagePollVoteAdd> = Type
    ) : Event<MessagePollVoteAdd.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("user_id") val userId: Snowflake,
            @SerialName("channel_id") val channelId: Snowflake,
            @SerialName("message_id") val messageId: Snowflake,
            @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
            @SerialName("answer_id") val answerId: Int,
        )

        companion object Type : EventType<MessagePollVoteAdd>
    }

    class MessagePollVoteRemove
    internal constructor(
        override val data: DataObject,
        override val type: EventType<MessagePollVoteRemove> = Type
    ) : Event<MessagePollVoteRemove.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("user_id") val userId: Snowflake,
            @SerialName("channel_id") val channelId: Snowflake,
            @SerialName("message_id") val messageId: Snowflake,
            @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
            @SerialName("answer_id") val answerId: Int,
        )
        
        companion object Type : EventType<MessagePollVoteRemove>
    }

    class MessageReactionAdd
    internal constructor(
        override val data: DataObject,
        override val type: EventType<MessageReactionAdd> = Type
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

        companion object Type : EventType<MessageReactionAdd>
    }

    class MessageReactionRemoveAll
    internal constructor(
        override val data: DataObject,
        override val type: EventType<MessageReactionRemoveAll> = Type
    ) : Event<MessageReactionRemoveAll.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("channel_id") val channelId: Snowflake,
            @SerialName("message_id") val messageId: Snowflake,
            @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
        )

        companion object Type : EventType<MessageReactionRemoveAll>
    }

    class MessageReactionRemoveEmoji
    internal constructor(
        override val data: DataObject,
        override val type: EventType<MessageReactionRemoveEmoji> = Type
    ) : Event<MessageReactionRemoveEmoji.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("channel_id") val channelId: Snowflake,
            @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
            @SerialName("message_id") val messageId: Snowflake,
            val emoji: EmojiObject
        )

        companion object Type : EventType<MessageReactionRemoveEmoji>
    }

    class MessageReactionRemove
    internal constructor(
        override val data: DataObject,
        override val type: EventType<MessageReactionRemove> = Type
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

        companion object Type : EventType<MessageReactionRemove>
    }

    class MessageUpdate
    internal constructor(
        override val data: MessageObject,
        override val type: EventType<MessageUpdate> = Type
    ) : Event<MessageObject> {
        companion object Type : EventType<MessageUpdate>
    }

    class PresenceUpdate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<PresenceUpdate> = Type
    ) : Event<PresenceUpdate.DataObject> {

        @Serializable
        data class DataObject(
            val user: UserObject,
            @SerialName("guild_id") val guildId: Snowflake,
            val status: String,
            val activities: List<ActivityObject>,
            @SerialName("client_status") val clientStatus: ClientStatusObject
        )

        companion object Type : EventType<PresenceUpdate>
    }

    class SoundboardSounds
    internal constructor(
        override val data: DataObject,
        override val type: EventType<SoundboardSounds> = Type
    ) : Event<SoundboardSounds.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("soundboard_sounds") val soundboardSounds: List<SoundboardSoundObject>,
            @SerialName("guild_id") val guildId: Snowflake,
        )

        companion object Type : EventType<SoundboardSounds>
    }

    class StageInstanceCreate
    internal constructor(
        override val data: StageInstanceObject,
        override val type: EventType<StageInstanceCreate> = Type
    ) : Event<StageInstanceObject> {
        companion object Type : EventType<StageInstanceCreate>
    }

    class StageInstanceDelete
    internal constructor(
        override val data: StageInstanceObject,
        override val type: EventType<StageInstanceDelete> = Type
    ) : Event<StageInstanceObject> {
        companion object Type : EventType<StageInstanceDelete>
    }

    class StageInstanceUpdate
    internal constructor(
        override val data: StageInstanceObject,
        override val type: EventType<StageInstanceUpdate> = Type
    ) : Event<StageInstanceObject> {
        companion object Type : EventType<StageInstanceUpdate>
    }

    class SubscriptionCreate
    internal constructor(
        override val data: SubscriptionObject,
        override val type: EventType<SubscriptionCreate> = Type
    ) : Event<SubscriptionObject> {
        companion object Type : EventType<SubscriptionCreate>
    }

    class SubscriptionDelete
    internal constructor(
        override val data: SubscriptionObject,
        override val type: EventType<SubscriptionDelete> = Type
    ) : Event<SubscriptionObject> {
        companion object Type : EventType<SubscriptionDelete>
    }

    class SubscriptionUpdate
    internal constructor(
        override val data: SubscriptionObject,
        override val type: EventType<SubscriptionUpdate> = Type
    ) : Event<SubscriptionObject> {
        companion object Type : EventType<SubscriptionUpdate>
    }

    class ThreadCreate
    internal constructor(
        override val data: ChannelObject,
        override val type: EventType<ThreadCreate> = Type
    ): Event<ChannelObject> {
        companion object Type : EventType<ThreadCreate>
    }

    class ThreadDelete
    internal constructor(
        override val data: ChannelObject,
        override val type: EventType<ThreadDelete> = Type
    ): Event<ChannelObject> {
        companion object Type : EventType<ThreadDelete>
    }

    class ThreadListSync
    internal constructor(
        override val data: DataObject,
        override val type: EventType<ThreadListSync> = Type
    ): Event<ThreadListSync.DataObject> {

        @Serializable
        data class DataObject(
            val guildId: Snowflake,
            val channelIds: MaybeAbsent<List<Snowflake>>? = null,
            val threads: List<ChannelObject>,
            val members: List<ThreadMemberObject>
        )

        companion object Type : EventType<ThreadListSync>
    }

    class ThreadMembersUpdate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<ThreadMembersUpdate> = Type
    ): Event<ThreadMembersUpdate.DataObject> {

        @Serializable
        data class DataObject(
            val id: Snowflake,
            @SerialName("guild_id") val guildId: Snowflake,
            @SerialName("member_count") val memberCount: Int,
            @SerialName("added_members") val addedMembers: MaybeAbsent<List<ThreadMemberObject>>? = null,
            @SerialName("removed_member_ids") val removedMemberIds: MaybeAbsent<List<Snowflake>>? = null
        )

        companion object Type : EventType<ThreadMembersUpdate>
    }

    class ThreadMemberUpdate
    internal constructor(
        override val data: ThreadMemberObject,
        override val type: EventType<ThreadMemberUpdate> = Type
    ): Event<ThreadMemberObject> {
        companion object Type : EventType<ThreadMemberUpdate>
    }

    class ThreadUpdate
    internal constructor(
        override val data: ChannelObject,
        override val type: EventType<ThreadUpdate> = Type
    ): Event<ChannelObject> {
        companion object Type : EventType<ThreadUpdate>
    }

    class TypingStart
    internal constructor(
        override val data: DataObject,
        override val type: EventType<TypingStart> = Type
    ) : Event<TypingStart.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("channel_id") val channelId: Snowflake,
            @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
            @SerialName("user_id") val userId: Snowflake,
            val timestamp: Long,
            val member: MaybeAbsent<GuildMemberObject>? = null,
        )

        companion object Type : EventType<TypingStart>
    }

    class UserUpdate
    internal constructor(
        override val data: UserObject,
        override val type: EventType<UserUpdate> = Type
    ) : Event<UserObject> {
        companion object Type : EventType<UserUpdate>
    }

    class VoiceChannelEffectSend
    internal constructor(
        override val data: DataObject,
        override val type: EventType<VoiceChannelEffectSend> = Type
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

        companion object Type : EventType<VoiceChannelEffectSend>
    }

    class VoiceServerUpdate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<VoiceServerUpdate> = Type
    ) : Event<VoiceServerUpdate.DataObject> {

        @Serializable
        data class DataObject(
            val token: String,
            @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
            val endpoint: String?
        )

        companion object Type : EventType<VoiceServerUpdate>
    }

    class VoiceStateUpdate
    internal constructor(
        override val data: VoiceStateObject,
        override val type: EventType<VoiceStateUpdate> = Type
    ) : Event<VoiceStateObject> {
        companion object Type : EventType<VoiceStateUpdate>
    }

    class WebhooksUpdate
    internal constructor(
        override val data: DataObject,
        override val type: EventType<WebhooksUpdate> = Type
    ) : Event<WebhooksUpdate.DataObject> {

        @Serializable
        data class DataObject(
            @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
            @SerialName("channel_id") val channelId: Snowflake,
        )

        companion object Type : EventType<WebhooksUpdate>
    }
}