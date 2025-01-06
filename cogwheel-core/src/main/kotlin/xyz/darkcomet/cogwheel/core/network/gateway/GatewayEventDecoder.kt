package xyz.darkcomet.cogwheel.core.network.gateway

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import xyz.darkcomet.cogwheel.core.events.*
import xyz.darkcomet.cogwheel.core.network.gateway.codes.GatewayOpCode
import xyz.darkcomet.cogwheel.core.network.gateway.codes.GatewayReceivePayloadName.*
import xyz.darkcomet.cogwheel.core.network.objects.*
import kotlin.reflect.typeOf

internal class GatewayEventDecoder private constructor() {
    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(GatewayEventDecoder::class.java)
        private val JSON_DESERIALIZER = Json { ignoreUnknownKeys = true }
        
        fun decode(payload: GatewayPayload): Event<*>? {
            return when (payload.op) {
                GatewayOpCode.DISPATCH.code -> decodeDispatchEvent(payload)
                GatewayOpCode.IDENTIFY.code -> unsupportedDecode(payload)
                GatewayOpCode.HELLO.code -> decode<GatewayHelloEvent.DataObject>(payload) { GatewayHelloEvent(it) }
                GatewayOpCode.RECONNECT.code -> GatewayReconnectEvent()
                GatewayOpCode.INVALID_SESSION.code -> GatewayInvalidSessionEvent(isResumeRecommended = JSON_DESERIALIZER.decodeFromJsonElement(payload.d!!))
                GatewayOpCode.HEARTBEAT.code -> GatewayHeartbeatEvent()
                GatewayOpCode.HEARTBEAT_ACK.code -> GatewayHeartbeatAckEvent()
                else -> null
            }
        }

        private fun decodeDispatchEvent(payload: GatewayPayload): Event<*>? {
            return when (payload.t) {
                READY.name -> decode<GatewayReadyEvent.DataObject>(payload) { GatewayReadyEvent(it) }
                RESUMED.name -> GatewayResumedEvent()
                APPLICATION_COMMAND_PERMISSIONS_UPDATE.name -> decode<ApplicationCommandPermissionObject>(payload) { ApplicationCommandPermissionsUpdateEvent(it) }
                AUTO_MODERATION_RULE_CREATE.name -> decode<GuildAutoModerationRuleObject>(payload) { AutoModerationRuleCreateEvent(it) }
                AUTO_MODERATION_RULE_UPDATE.name -> decode<GuildAutoModerationRuleObject>(payload) { AutoModerationRuleUpdateEvent(it) }
                AUTO_MODERATION_RULE_DELETE.name -> decode<GuildAutoModerationRuleObject>(payload) { AutoModerationRuleDeleteEvent(it) }
                AUTO_MODERATION_ACTION_EXECUTION.name -> decode<AutoModerationActionExecutionEvent.DataObject>(payload) { AutoModerationActionExecutionEvent(it) }
                CHANNEL_CREATE.name -> decode<ChannelObject>(payload) { ChannelCreateEvent(it) }
                CHANNEL_UPDATE.name -> decode<ChannelObject>(payload) { ChannelUpdateEvent(it) }
                CHANNEL_DELETE.name -> decode<ChannelObject>(payload) { ChannelDeleteEvent(it) }
                CHANNEL_PINS_UPDATE.name -> decode<ChannelPinsUpdateEvent.DataObject>(payload) { ChannelPinsUpdateEvent(it) }
                THREAD_CREATE.name -> decode<ChannelObject>(payload) { ThreadCreateEvent(it) }
                THREAD_UPDATE.name -> decode<ChannelObject>(payload) { ThreadUpdateEvent(it) }
                THREAD_DELETE.name -> decode<ChannelObject>(payload) { ThreadDeleteEvent(it) }
                THREAD_LIST_SYNC.name -> decode<ThreadListSyncEvent.DataObject>(payload) { ThreadListSyncEvent(it) }
                THREAD_MEMBER_UPDATE.name -> decode<ThreadMemberObject>(payload) { ThreadMemberUpdateEvent(it) }
                THREAD_MEMBERS_UPDATE.name -> decode<ThreadMembersUpdateEvent.DataObject>(payload) { ThreadMembersUpdateEvent(it) }
                ENTITLEMENT_CREATE.name -> decode<EntitlementObject>(payload) { EntitlementCreateEvent(it) }
                ENTITLEMENT_UPDATE.name -> decode<EntitlementObject>(payload) { EntitlementUpdateEvent(it) }
                ENTITLEMENT_DELETE.name -> decode<EntitlementObject>(payload) { EntitlementDeleteEvent(it) }
                GUILD_CREATE.name -> decode<GuildObject>(payload) { GuildCreateEvent(it) }
                GUILD_UPDATE.name -> decode<GuildObject>(payload) { GuildUpdateEvent(it) }
                GUILD_DELETE.name -> decode<GuildObject>(payload) { GuildDeleteEvent(it) }
                GUILD_AUDIT_LOG_ENTRY_CREATE.name -> decode<AuditLogEntryObject>(payload) { GuildAuditLogEntryCreateEvent(it) }
                GUILD_BAN_ADD.name -> decode<GuildBanAddEvent.DataObject>(payload) { GuildBanAddEvent(it) }
                GUILD_BAN_REMOVE.name -> decode<GuildBanRemoveEvent.DataObject>(payload) { GuildBanRemoveEvent(it) }
                GUILD_EMOJIS_UPDATE.name -> decode<GuildEmojisUpdateEvent.DataObject>(payload) { GuildEmojisUpdateEvent(it) }
                GUILD_STICKERS_UPDATE.name -> decode<GuildStickersUpdateEvent.DataObject>(payload) { GuildStickersUpdateEvent(it) }
                GUILD_INTEGRATIONS_UPDATE.name -> decode<GuildIntegrationsUpdateEvent.DataObject>(payload) { GuildIntegrationsUpdateEvent(it) }
                GUILD_MEMBER_ADD.name -> decode<GuildMemberObject>(payload) { GuildMemberAddEvent(it) }
                GUILD_MEMBER_REMOVE.name -> decode<GuildMemberRemoveEvent.DataObject>(payload) { GuildMemberRemoveEvent(it) }
                GUILD_MEMBER_UPDATE.name -> decode<GuildMemberUpdateEvent.DataObject>(payload) { GuildMemberUpdateEvent(it) }
                GUILD_MEMBERS_CHUNK.name -> decode<GuildMembersChunkEvent.DataObject>(payload) { GuildMembersChunkEvent(it) }
                GUILD_ROLE_CREATE.name -> decode<GuildRoleCreateEvent.DataObject>(payload) { GuildRoleCreateEvent(it) }
                GUILD_ROLE_UPDATE.name -> decode<GuildRoleUpdateEvent.DataObject>(payload) { GuildRoleUpdateEvent(it) }
                GUILD_ROLE_DELETE.name -> decode<GuildRoleDeleteEvent.DataObject>(payload) { GuildRoleDeleteEvent(it) }
                GUILD_SCHEDULED_EVENT_CREATE.name -> decode<GuildScheduledEventObject>(payload) { GuildScheduledEventCreateEvent(it) }
                GUILD_SCHEDULED_EVENT_UPDATE.name -> decode<GuildScheduledEventObject>(payload) { GuildScheduledEventUpdateEvent(it) }
                GUILD_SCHEDULED_EVENT_DELETE.name -> decode<GuildScheduledEventObject>(payload) { GuildScheduledEventDeleteEvent(it) }
                GUILD_SCHEDULED_EVENT_USER_ADD.name -> decode<GuildScheduledEventUserAddEvent.DataObject>(payload) { GuildScheduledEventUserAddEvent(it) }
                GUILD_SCHEDULED_EVENT_USER_REMOVE.name -> decode<GuildScheduledEventUserRemoveEvent.DataObject>(payload) { GuildScheduledEventUserRemoveEvent(it) }
                GUILD_SOUNDBOARD_SOUND_CREATE.name -> decode<SoundboardSoundObject>(payload) { GuildSoundboardSoundCreateEvent(it) }
                GUILD_SOUNDBOARD_SOUND_UPDATE.name -> decode<SoundboardSoundObject>(payload) { GuildSoundboardSoundUpdateEvent(it) }
                GUILD_SOUNDBOARD_SOUND_DELETE.name -> decode<GuildSoundboardSoundDeleteEvent.DataObject>(payload) { GuildSoundboardSoundDeleteEvent(it) }
                GUILD_SOUNDBOARD_SOUNDS_UPDATE.name -> decode<GuildSoundboardSoundsUpdateEvent.DataObject>(payload) { GuildSoundboardSoundsUpdateEvent(it) }
                SOUNDBOARD_SOUNDS.name -> decode<SoundboardSoundsEvent.DataObject>(payload) { SoundboardSoundsEvent(it) }
                INTEGRATION_CREATE.name -> decode<GuildIntegrationObject>(payload) { IntegrationCreateEvent(it) }
                INTEGRATION_UPDATE.name -> decode<GuildIntegrationObject>(payload) { IntegrationUpdateEvent(it) }
                INTEGRATION_DELETE.name -> decode<IntegrationDeleteEvent.DataObject>(payload) { IntegrationDeleteEvent(it) }
                INTERACTION_CREATE.name -> decode<InteractionObject>(payload) { InteractionCreateEvent(it) }
                INVITE_CREATE.name -> decode<InviteCreateEvent.DataObject>(payload) { InviteCreateEvent(it) }
                INVITE_DELETE.name -> decode<InviteDeleteEvent.DataObject>(payload) { InviteDeleteEvent(it) }
                MESSAGE_CREATE.name -> decode<MessageObject>(payload) { MessageCreateEvent(it) }
                MESSAGE_UPDATE.name -> decode<MessageObject>(payload) { MessageUpdateEvent(it) }
                MESSAGE_DELETE.name -> decode<MessageDeleteEvent.DataObject>(payload) { MessageDeleteEvent(it) }
                MESSAGE_DELETE_BULK.name -> decode<MessageDeleteBulkEvent.DataObject>(payload) { MessageDeleteBulkEvent(it) }
                MESSAGE_REACTION_ADD.name -> decode<MessageReactionAddEvent.DataObject>(payload) { MessageReactionAddEvent(it) }
                MESSAGE_REACTION_REMOVE.name -> decode<MessageReactionRemoveEvent.DataObject>(payload) { MessageReactionRemoveEvent(it) }
                MESSAGE_REACTION_REMOVE_ALL.name -> decode<MessageReactionRemoveAllEvent.DataObject>(payload) { MessageReactionRemoveAllEvent(it) }
                MESSAGE_REACTION_REMOVE_EMOJI.name -> decode<MessageReactionRemoveEmojiEvent.DataObject>(payload) { MessageReactionRemoveEmojiEvent(it) }
                PRESENCE_UPDATE.name -> decode<PresenceUpdateEvent.DataObject>(payload) { PresenceUpdateEvent(it) }
                STAGE_INSTANCE_CREATE.name -> decode<StageInstanceObject>(payload) { StageInstanceCreateEvent(it) }
                STAGE_INSTANCE_UPDATE.name -> decode<StageInstanceObject>(payload) { StageInstanceUpdateEvent(it) }
                STAGE_INSTANCE_DELETE.name -> decode<StageInstanceObject>(payload) { StageInstanceDeleteEvent(it) }
                SUBSCRIPTION_CREATE.name -> decode<SubscriptionObject>(payload) { SubscriptionCreateEvent(it) }
                SUBSCRIPTION_UPDATE.name -> decode<SubscriptionObject>(payload) { SubscriptionUpdateEvent(it) }
                SUBSCRIPTION_DELETE.name -> decode<SubscriptionObject>(payload) { SubscriptionDeleteEvent(it) }
                TYPING_START.name -> decode<TypingStartEvent.DataObject>(payload) { TypingStartEvent(it) }
                USER_UPDATE.name -> decode<UserObject>(payload) { UserUpdateEvent(it) }
                VOICE_CHANNEL_EFFECT_SEND.name -> decode<VoiceChannelEffectSendEvent.DataObject>(payload) { VoiceChannelEffectSendEvent(it) }
                VOICE_STATE_UPDATE.name -> decode<VoiceStateObject>(payload) { VoiceStateUpdateEvent(it) }
                VOICE_SERVER_UPDATE.name -> decode<VoiceServerUpdateEvent.DataObject>(payload) { VoiceServerUpdateEvent(it) }
                WEBHOOKS_UPDATE.name -> decode<WebhooksUpdateEvent.DataObject>(payload) { WebhooksUpdateEvent(it) }
                MESSAGE_POLL_VOTE_ADD.name -> decode<MessagePollVoteAddEvent.DataObject>(payload) { MessagePollVoteAddEvent(it) }
                MESSAGE_POLL_VOTE_REMOVE.name -> decode<MessagePollVoteRemoveEvent.DataObject>(payload) { MessagePollVoteRemoveEvent(it) }
                else -> null
            }
        }

        private fun unsupportedDecode(payload: GatewayPayload): Event<*>? {
            throw IllegalArgumentException("Not expected to receive event with op: ${payload.op}")
        }

        private inline fun <reified TData> decode(
            payload: GatewayPayload,
            resultFactory: (TData) -> Event<*>
        ): Event<*>? {
            if (payload.d == null) {
                LOGGER.warn("Error parsing payload (op: {}, t: {}) to type {} because 'd' == null", payload.op, payload.t, typeOf<TData>())
                return null;
            }

            val eventData = JSON_DESERIALIZER.decodeFromJsonElement<TData>(payload.d)
            return resultFactory.invoke(eventData)
        }
    }
}