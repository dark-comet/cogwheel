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
        
        fun decode(payload: GatewayPayload, jsonSerializer: Json): Event<*>? {
            return when (payload.op) {
                GatewayOpCode.DISPATCH.code -> decodeDispatchEvent(payload, jsonSerializer)
                GatewayOpCode.IDENTIFY.code -> unsupportedDecode(payload)
                GatewayOpCode.HELLO.code -> decode<GatewayHelloEvent.DataObject>(payload, jsonSerializer) { GatewayHelloEvent(it) }
                GatewayOpCode.RECONNECT.code -> GatewayReconnectEvent()
                GatewayOpCode.INVALID_SESSION.code -> GatewayInvalidSessionEvent(isResumeRecommended = jsonSerializer.decodeFromJsonElement(payload.d!!))
                GatewayOpCode.HEARTBEAT.code -> GatewayHeartbeatEvent()
                GatewayOpCode.HEARTBEAT_ACK.code -> GatewayHeartbeatAckEvent()
                else -> null
            }
        }

        private fun decodeDispatchEvent(
            payload: GatewayPayload, 
            jsonSerializer: Json
        ): Event<*>? {
            return when (payload.t) {
                READY.name -> decode<GatewayReadyEvent.DataObject>(payload, jsonSerializer) { GatewayReadyEvent(it) }
                RESUMED.name -> GatewayResumedEvent()
                APPLICATION_COMMAND_PERMISSIONS_UPDATE.name -> decode<ApplicationCommandPermissionObject>(payload, jsonSerializer) { ApplicationCommandPermissionsUpdateEvent(it) }
                AUTO_MODERATION_RULE_CREATE.name -> decode<GuildAutoModerationRuleObject>(payload, jsonSerializer) { AutoModerationRuleCreateEvent(it) }
                AUTO_MODERATION_RULE_UPDATE.name -> decode<GuildAutoModerationRuleObject>(payload, jsonSerializer) { AutoModerationRuleUpdateEvent(it) }
                AUTO_MODERATION_RULE_DELETE.name -> decode<GuildAutoModerationRuleObject>(payload, jsonSerializer) { AutoModerationRuleDeleteEvent(it) }
                AUTO_MODERATION_ACTION_EXECUTION.name -> decode<AutoModerationActionExecutionEvent.DataObject>(payload, jsonSerializer) { AutoModerationActionExecutionEvent(it) }
                CHANNEL_CREATE.name -> decode<ChannelObject>(payload, jsonSerializer) { ChannelCreateEvent(it) }
                CHANNEL_UPDATE.name -> decode<ChannelObject>(payload, jsonSerializer) { ChannelUpdateEvent(it) }
                CHANNEL_DELETE.name -> decode<ChannelObject>(payload, jsonSerializer) { ChannelDeleteEvent(it) }
                CHANNEL_PINS_UPDATE.name -> decode<ChannelPinsUpdateEvent.DataObject>(payload, jsonSerializer) { ChannelPinsUpdateEvent(it) }
                THREAD_CREATE.name -> decode<ChannelObject>(payload, jsonSerializer) { ThreadCreateEvent(it) }
                THREAD_UPDATE.name -> decode<ChannelObject>(payload, jsonSerializer) { ThreadUpdateEvent(it) }
                THREAD_DELETE.name -> decode<ChannelObject>(payload, jsonSerializer) { ThreadDeleteEvent(it) }
                THREAD_LIST_SYNC.name -> decode<ThreadListSyncEvent.DataObject>(payload, jsonSerializer) { ThreadListSyncEvent(it) }
                THREAD_MEMBER_UPDATE.name -> decode<ThreadMemberObject>(payload, jsonSerializer) { ThreadMemberUpdateEvent(it) }
                THREAD_MEMBERS_UPDATE.name -> decode<ThreadMembersUpdateEvent.DataObject>(payload, jsonSerializer) { ThreadMembersUpdateEvent(it) }
                ENTITLEMENT_CREATE.name -> decode<EntitlementObject>(payload, jsonSerializer) { EntitlementCreateEvent(it) }
                ENTITLEMENT_UPDATE.name -> decode<EntitlementObject>(payload, jsonSerializer) { EntitlementUpdateEvent(it) }
                ENTITLEMENT_DELETE.name -> decode<EntitlementObject>(payload, jsonSerializer) { EntitlementDeleteEvent(it) }
                GUILD_CREATE.name -> decode<GuildObject>(payload, jsonSerializer) { GuildCreateEvent(it) }
                GUILD_UPDATE.name -> decode<GuildObject>(payload, jsonSerializer) { GuildUpdateEvent(it) }
                GUILD_DELETE.name -> decode<GuildObject>(payload, jsonSerializer) { GuildDeleteEvent(it) }
                GUILD_AUDIT_LOG_ENTRY_CREATE.name -> decode<AuditLogEntryObject>(payload, jsonSerializer) { GuildAuditLogEntryCreateEvent(it) }
                GUILD_BAN_ADD.name -> decode<GuildBanAddEvent.DataObject>(payload, jsonSerializer) { GuildBanAddEvent(it) }
                GUILD_BAN_REMOVE.name -> decode<GuildBanRemoveEvent.DataObject>(payload, jsonSerializer) { GuildBanRemoveEvent(it) }
                GUILD_EMOJIS_UPDATE.name -> decode<GuildEmojisUpdateEvent.DataObject>(payload, jsonSerializer) { GuildEmojisUpdateEvent(it) }
                GUILD_STICKERS_UPDATE.name -> decode<GuildStickersUpdateEvent.DataObject>(payload, jsonSerializer) { GuildStickersUpdateEvent(it) }
                GUILD_INTEGRATIONS_UPDATE.name -> decode<GuildIntegrationsUpdateEvent.DataObject>(payload, jsonSerializer) { GuildIntegrationsUpdateEvent(it) }
                GUILD_MEMBER_ADD.name -> decode<GuildMemberObject>(payload, jsonSerializer) { GuildMemberAddEvent(it) }
                GUILD_MEMBER_REMOVE.name -> decode<GuildMemberRemoveEvent.DataObject>(payload, jsonSerializer) { GuildMemberRemoveEvent(it) }
                GUILD_MEMBER_UPDATE.name -> decode<GuildMemberUpdateEvent.DataObject>(payload, jsonSerializer) { GuildMemberUpdateEvent(it) }
                GUILD_MEMBERS_CHUNK.name -> decode<GuildMembersChunkEvent.DataObject>(payload, jsonSerializer) { GuildMembersChunkEvent(it) }
                GUILD_ROLE_CREATE.name -> decode<GuildRoleCreateEvent.DataObject>(payload, jsonSerializer) { GuildRoleCreateEvent(it) }
                GUILD_ROLE_UPDATE.name -> decode<GuildRoleUpdateEvent.DataObject>(payload, jsonSerializer) { GuildRoleUpdateEvent(it) }
                GUILD_ROLE_DELETE.name -> decode<GuildRoleDeleteEvent.DataObject>(payload, jsonSerializer) { GuildRoleDeleteEvent(it) }
                GUILD_SCHEDULED_EVENT_CREATE.name -> decode<GuildScheduledEventObject>(payload, jsonSerializer) { GuildScheduledEventCreateEvent(it) }
                GUILD_SCHEDULED_EVENT_UPDATE.name -> decode<GuildScheduledEventObject>(payload, jsonSerializer) { GuildScheduledEventUpdateEvent(it) }
                GUILD_SCHEDULED_EVENT_DELETE.name -> decode<GuildScheduledEventObject>(payload, jsonSerializer) { GuildScheduledEventDeleteEvent(it) }
                GUILD_SCHEDULED_EVENT_USER_ADD.name -> decode<GuildScheduledEventUserAddEvent.DataObject>(payload, jsonSerializer) { GuildScheduledEventUserAddEvent(it) }
                GUILD_SCHEDULED_EVENT_USER_REMOVE.name -> decode<GuildScheduledEventUserRemoveEvent.DataObject>(payload, jsonSerializer) { GuildScheduledEventUserRemoveEvent(it) }
                GUILD_SOUNDBOARD_SOUND_CREATE.name -> decode<SoundboardSoundObject>(payload, jsonSerializer) { GuildSoundboardSoundCreateEvent(it) }
                GUILD_SOUNDBOARD_SOUND_UPDATE.name -> decode<SoundboardSoundObject>(payload, jsonSerializer) { GuildSoundboardSoundUpdateEvent(it) }
                GUILD_SOUNDBOARD_SOUND_DELETE.name -> decode<GuildSoundboardSoundDeleteEvent.DataObject>(payload, jsonSerializer) { GuildSoundboardSoundDeleteEvent(it) }
                GUILD_SOUNDBOARD_SOUNDS_UPDATE.name -> decode<GuildSoundboardSoundsUpdateEvent.DataObject>(payload, jsonSerializer) { GuildSoundboardSoundsUpdateEvent(it) }
                SOUNDBOARD_SOUNDS.name -> decode<SoundboardSoundsEvent.DataObject>(payload, jsonSerializer) { SoundboardSoundsEvent(it) }
                INTEGRATION_CREATE.name -> decode<GuildIntegrationObject>(payload, jsonSerializer) { IntegrationCreateEvent(it) }
                INTEGRATION_UPDATE.name -> decode<GuildIntegrationObject>(payload, jsonSerializer) { IntegrationUpdateEvent(it) }
                INTEGRATION_DELETE.name -> decode<IntegrationDeleteEvent.DataObject>(payload, jsonSerializer) { IntegrationDeleteEvent(it) }
                INTERACTION_CREATE.name -> decode<InteractionObject>(payload, jsonSerializer) { InteractionCreateEvent(it) }
                INVITE_CREATE.name -> decode<InviteCreateEvent.DataObject>(payload, jsonSerializer) { InviteCreateEvent(it) }
                INVITE_DELETE.name -> decode<InviteDeleteEvent.DataObject>(payload, jsonSerializer) { InviteDeleteEvent(it) }
                MESSAGE_CREATE.name -> decode<MessageObject>(payload, jsonSerializer) { MessageCreateEvent(it) }
                MESSAGE_UPDATE.name -> decode<MessageObject>(payload, jsonSerializer) { MessageUpdateEvent(it) }
                MESSAGE_DELETE.name -> decode<MessageDeleteEvent.DataObject>(payload, jsonSerializer) { MessageDeleteEvent(it) }
                MESSAGE_DELETE_BULK.name -> decode<MessageDeleteBulkEvent.DataObject>(payload, jsonSerializer) { MessageDeleteBulkEvent(it) }
                MESSAGE_REACTION_ADD.name -> decode<MessageReactionAddEvent.DataObject>(payload, jsonSerializer) { MessageReactionAddEvent(it) }
                MESSAGE_REACTION_REMOVE.name -> decode<MessageReactionRemoveEvent.DataObject>(payload, jsonSerializer) { MessageReactionRemoveEvent(it) }
                MESSAGE_REACTION_REMOVE_ALL.name -> decode<MessageReactionRemoveAllEvent.DataObject>(payload, jsonSerializer) { MessageReactionRemoveAllEvent(it) }
                MESSAGE_REACTION_REMOVE_EMOJI.name -> decode<MessageReactionRemoveEmojiEvent.DataObject>(payload, jsonSerializer) { MessageReactionRemoveEmojiEvent(it) }
                PRESENCE_UPDATE.name -> decode<PresenceUpdateEvent.DataObject>(payload, jsonSerializer) { PresenceUpdateEvent(it) }
                STAGE_INSTANCE_CREATE.name -> decode<StageInstanceObject>(payload, jsonSerializer) { StageInstanceCreateEvent(it) }
                STAGE_INSTANCE_UPDATE.name -> decode<StageInstanceObject>(payload, jsonSerializer) { StageInstanceUpdateEvent(it) }
                STAGE_INSTANCE_DELETE.name -> decode<StageInstanceObject>(payload, jsonSerializer) { StageInstanceDeleteEvent(it) }
                SUBSCRIPTION_CREATE.name -> decode<SubscriptionObject>(payload, jsonSerializer) { SubscriptionCreateEvent(it) }
                SUBSCRIPTION_UPDATE.name -> decode<SubscriptionObject>(payload, jsonSerializer) { SubscriptionUpdateEvent(it) }
                SUBSCRIPTION_DELETE.name -> decode<SubscriptionObject>(payload, jsonSerializer) { SubscriptionDeleteEvent(it) }
                TYPING_START.name -> decode<TypingStartEvent.DataObject>(payload, jsonSerializer) { TypingStartEvent(it) }
                USER_UPDATE.name -> decode<UserObject>(payload, jsonSerializer) { UserUpdateEvent(it) }
                VOICE_CHANNEL_EFFECT_SEND.name -> decode<VoiceChannelEffectSendEvent.DataObject>(payload, jsonSerializer) { VoiceChannelEffectSendEvent(it) }
                VOICE_STATE_UPDATE.name -> decode<VoiceStateObject>(payload, jsonSerializer) { VoiceStateUpdateEvent(it) }
                VOICE_SERVER_UPDATE.name -> decode<VoiceServerUpdateEvent.DataObject>(payload, jsonSerializer) { VoiceServerUpdateEvent(it) }
                WEBHOOKS_UPDATE.name -> decode<WebhooksUpdateEvent.DataObject>(payload, jsonSerializer) { WebhooksUpdateEvent(it) }
                MESSAGE_POLL_VOTE_ADD.name -> decode<MessagePollVoteAddEvent.DataObject>(payload, jsonSerializer) { MessagePollVoteAddEvent(it) }
                MESSAGE_POLL_VOTE_REMOVE.name -> decode<MessagePollVoteRemoveEvent.DataObject>(payload, jsonSerializer) { MessagePollVoteRemoveEvent(it) }
                else -> null
            }
        }

        private fun unsupportedDecode(payload: GatewayPayload): Event<*>? {
            throw IllegalArgumentException("Not expected to receive event with op: ${payload.op}")
        }

        private inline fun <reified TData> decode(
            payload: GatewayPayload,
            jsonSerializer: Json,
            resultFactory: (TData) -> Event<*>
        ): Event<*>? {
            if (payload.d == null) {
                LOGGER.warn("Error parsing payload (op: {}, t: {}) to type {} because 'd' == null", payload.op, payload.t, typeOf<TData>())
                return null;
            }

            val eventData = jsonSerializer.decodeFromJsonElement<TData>(payload.d)
            return resultFactory.invoke(eventData)
        }
    }
}