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
                GatewayOpCode.HELLO.code -> decode<Gateway.Hello.DataObject>(payload, jsonSerializer) { Gateway.Hello(it) }
                GatewayOpCode.RECONNECT.code -> Gateway.Reconnect()
                GatewayOpCode.INVALID_SESSION.code -> Gateway.InvalidSession(isResumeRecommended = jsonSerializer.decodeFromJsonElement(payload.d!!))
                GatewayOpCode.HEARTBEAT.code -> Gateway.Heartbeat()
                GatewayOpCode.HEARTBEAT_ACK.code -> Gateway.HeartbeatAck()
                else -> null
            }
        }

        private fun decodeDispatchEvent(
            payload: GatewayPayload, 
            jsonSerializer: Json
        ): Event<*>? {
            return when (payload.t) {
                READY.name -> decode<Gateway.Ready.DataObject>(payload, jsonSerializer) { Gateway.Ready(it) }
                RESUMED.name -> Gateway.Resumed()
                APPLICATION_COMMAND_PERMISSIONS_UPDATE.name -> decode<ApplicationCommandPermissionObject>(payload, jsonSerializer) { Gateway.ApplicationCommandPermissionsUpdate(it) }
                AUTO_MODERATION_RULE_CREATE.name -> decode<GuildAutoModerationRuleObject>(payload, jsonSerializer) { Gateway.AutoModerationRuleCreate(it) }
                AUTO_MODERATION_RULE_UPDATE.name -> decode<GuildAutoModerationRuleObject>(payload, jsonSerializer) { Gateway.AutoModerationRuleUpdate(it) }
                AUTO_MODERATION_RULE_DELETE.name -> decode<GuildAutoModerationRuleObject>(payload, jsonSerializer) { Gateway.AutoModerationRuleDelete(it) }
                AUTO_MODERATION_ACTION_EXECUTION.name -> decode<Gateway.AutoModerationActionExecution.DataObject>(payload, jsonSerializer) { Gateway.AutoModerationActionExecution(it) }
                CHANNEL_CREATE.name -> decode<ChannelObject>(payload, jsonSerializer) { Gateway.ChannelCreate(it) }
                CHANNEL_UPDATE.name -> decode<ChannelObject>(payload, jsonSerializer) { Gateway.ChannelUpdate(it) }
                CHANNEL_DELETE.name -> decode<ChannelObject>(payload, jsonSerializer) { Gateway.ChannelDelete(it) }
                CHANNEL_PINS_UPDATE.name -> decode<Gateway.ChannelPinsUpdate.DataObject>(payload, jsonSerializer) { Gateway.ChannelPinsUpdate(it) }
                THREAD_CREATE.name -> decode<ChannelObject>(payload, jsonSerializer) { Gateway.ThreadCreate(it) }
                THREAD_UPDATE.name -> decode<ChannelObject>(payload, jsonSerializer) { Gateway.ThreadUpdate(it) }
                THREAD_DELETE.name -> decode<ChannelObject>(payload, jsonSerializer) { Gateway.ThreadDelete(it) }
                THREAD_LIST_SYNC.name -> decode<Gateway.ThreadListSync.DataObject>(payload, jsonSerializer) { Gateway.ThreadListSync(it) }
                THREAD_MEMBER_UPDATE.name -> decode<ThreadMemberObject>(payload, jsonSerializer) { Gateway.ThreadMemberUpdate(it) }
                THREAD_MEMBERS_UPDATE.name -> decode<Gateway.ThreadMembersUpdate.DataObject>(payload, jsonSerializer) { Gateway.ThreadMembersUpdate(it) }
                ENTITLEMENT_CREATE.name -> decode<EntitlementObject>(payload, jsonSerializer) { Gateway.EntitlementCreate(it) }
                ENTITLEMENT_UPDATE.name -> decode<EntitlementObject>(payload, jsonSerializer) { Gateway.EntitlementUpdate(it) }
                ENTITLEMENT_DELETE.name -> decode<EntitlementObject>(payload, jsonSerializer) { Gateway.EntitlementDelete(it) }
                GUILD_CREATE.name -> decode<GuildObject>(payload, jsonSerializer) { Gateway.GuildCreate(it) }
                GUILD_UPDATE.name -> decode<GuildObject>(payload, jsonSerializer) { Gateway.GuildUpdate(it) }
                GUILD_DELETE.name -> decode<GuildObject>(payload, jsonSerializer) { Gateway.GuildDelete(it) }
                GUILD_AUDIT_LOG_ENTRY_CREATE.name -> decode<AuditLogEntryObject>(payload, jsonSerializer) { Gateway.GuildAuditLogEntryCreate(it) }
                GUILD_BAN_ADD.name -> decode<Gateway.GuildBanAdd.DataObject>(payload, jsonSerializer) { Gateway.GuildBanAdd(it) }
                GUILD_BAN_REMOVE.name -> decode<Gateway.GuildBanRemove.DataObject>(payload, jsonSerializer) { Gateway.GuildBanRemove(it) }
                GUILD_EMOJIS_UPDATE.name -> decode<Gateway.GuildEmojisUpdate.DataObject>(payload, jsonSerializer) { Gateway.GuildEmojisUpdate(it) }
                GUILD_STICKERS_UPDATE.name -> decode<Gateway.GuildStickersUpdate.DataObject>(payload, jsonSerializer) { Gateway.GuildStickersUpdate(it) }
                GUILD_INTEGRATIONS_UPDATE.name -> decode<Gateway.GuildIntegrationsUpdate.DataObject>(payload, jsonSerializer) { Gateway.GuildIntegrationsUpdate(it) }
                GUILD_MEMBER_ADD.name -> decode<GuildMemberObject>(payload, jsonSerializer) { Gateway.GuildMemberAdd(it) }
                GUILD_MEMBER_REMOVE.name -> decode<Gateway.GuildMemberRemove.DataObject>(payload, jsonSerializer) { Gateway.GuildMemberRemove(it) }
                GUILD_MEMBER_UPDATE.name -> decode<Gateway.GuildMemberUpdate.DataObject>(payload, jsonSerializer) { Gateway.GuildMemberUpdate(it) }
                GUILD_MEMBERS_CHUNK.name -> decode<Gateway.GuildMembersChunk.DataObject>(payload, jsonSerializer) { Gateway.GuildMembersChunk(it) }
                GUILD_ROLE_CREATE.name -> decode<Gateway.GuildRoleCreate.DataObject>(payload, jsonSerializer) { Gateway.GuildRoleCreate(it) }
                GUILD_ROLE_UPDATE.name -> decode<Gateway.GuildRoleUpdate.DataObject>(payload, jsonSerializer) { Gateway.GuildRoleUpdate(it) }
                GUILD_ROLE_DELETE.name -> decode<Gateway.GuildRoleDelete.DataObject>(payload, jsonSerializer) { Gateway.GuildRoleDelete(it) }
                GUILD_SCHEDULED_EVENT_CREATE.name -> decode<GuildScheduledEventObject>(payload, jsonSerializer) { Gateway.GuildScheduledEventCreate(it) }
                GUILD_SCHEDULED_EVENT_UPDATE.name -> decode<GuildScheduledEventObject>(payload, jsonSerializer) { Gateway.GuildScheduledEventUpdate(it) }
                GUILD_SCHEDULED_EVENT_DELETE.name -> decode<GuildScheduledEventObject>(payload, jsonSerializer) { Gateway.GuildScheduledEventDelete(it) }
                GUILD_SCHEDULED_EVENT_USER_ADD.name -> decode<Gateway.GuildScheduledEventUserAdd.DataObject>(payload, jsonSerializer) { Gateway.GuildScheduledEventUserAdd(it) }
                GUILD_SCHEDULED_EVENT_USER_REMOVE.name -> decode<Gateway.GuildScheduledEventUserRemove.DataObject>(payload, jsonSerializer) { Gateway.GuildScheduledEventUserRemove(it) }
                GUILD_SOUNDBOARD_SOUND_CREATE.name -> decode<SoundboardSoundObject>(payload, jsonSerializer) { Gateway.GuildSoundboardSoundCreate(it) }
                GUILD_SOUNDBOARD_SOUND_UPDATE.name -> decode<SoundboardSoundObject>(payload, jsonSerializer) { Gateway.GuildSoundboardSoundUpdate(it) }
                GUILD_SOUNDBOARD_SOUND_DELETE.name -> decode<Gateway.GuildSoundboardSoundDelete.DataObject>(payload, jsonSerializer) { Gateway.GuildSoundboardSoundDelete(it) }
                GUILD_SOUNDBOARD_SOUNDS_UPDATE.name -> decode<Gateway.GuildSoundboardSoundsUpdate.DataObject>(payload, jsonSerializer) { Gateway.GuildSoundboardSoundsUpdate(it) }
                SOUNDBOARD_SOUNDS.name -> decode<Gateway.SoundboardSounds.DataObject>(payload, jsonSerializer) { Gateway.SoundboardSounds(it) }
                INTEGRATION_CREATE.name -> decode<GuildIntegrationObject>(payload, jsonSerializer) { Gateway.IntegrationCreate(it) }
                INTEGRATION_UPDATE.name -> decode<GuildIntegrationObject>(payload, jsonSerializer) { Gateway.IntegrationUpdate(it) }
                INTEGRATION_DELETE.name -> decode<Gateway.IntegrationDelete.DataObject>(payload, jsonSerializer) { Gateway.IntegrationDelete(it) }
                INTERACTION_CREATE.name -> decode<InteractionObject>(payload, jsonSerializer) { Gateway.InteractionCreate(it) }
                INVITE_CREATE.name -> decode<Gateway.InviteCreate.DataObject>(payload, jsonSerializer) { Gateway.InviteCreate(it) }
                INVITE_DELETE.name -> decode<Gateway.InviteDelete.DataObject>(payload, jsonSerializer) { Gateway.InviteDelete(it) }
                MESSAGE_CREATE.name -> decode<MessageObject>(payload, jsonSerializer) { Gateway.MessageCreate(it) }
                MESSAGE_UPDATE.name -> decode<MessageObject>(payload, jsonSerializer) { Gateway.MessageUpdate(it) }
                MESSAGE_DELETE.name -> decode<Gateway.MessageDelete.DataObject>(payload, jsonSerializer) { Gateway.MessageDelete(it) }
                MESSAGE_DELETE_BULK.name -> decode<Gateway.MessageDeleteBulk.DataObject>(payload, jsonSerializer) { Gateway.MessageDeleteBulk(it) }
                MESSAGE_REACTION_ADD.name -> decode<Gateway.MessageReactionAdd.DataObject>(payload, jsonSerializer) { Gateway.MessageReactionAdd(it) }
                MESSAGE_REACTION_REMOVE.name -> decode<Gateway.MessageReactionRemove.DataObject>(payload, jsonSerializer) { Gateway.MessageReactionRemove(it) }
                MESSAGE_REACTION_REMOVE_ALL.name -> decode<Gateway.MessageReactionRemoveAll.DataObject>(payload, jsonSerializer) { Gateway.MessageReactionRemoveAll(it) }
                MESSAGE_REACTION_REMOVE_EMOJI.name -> decode<Gateway.MessageReactionRemoveEmoji.DataObject>(payload, jsonSerializer) { Gateway.MessageReactionRemoveEmoji(it) }
                PRESENCE_UPDATE.name -> decode<Gateway.PresenceUpdate.DataObject>(payload, jsonSerializer) { Gateway.PresenceUpdate(it) }
                STAGE_INSTANCE_CREATE.name -> decode<StageInstanceObject>(payload, jsonSerializer) { Gateway.StageInstanceCreate(it) }
                STAGE_INSTANCE_UPDATE.name -> decode<StageInstanceObject>(payload, jsonSerializer) { Gateway.StageInstanceUpdate(it) }
                STAGE_INSTANCE_DELETE.name -> decode<StageInstanceObject>(payload, jsonSerializer) { Gateway.StageInstanceDelete(it) }
                SUBSCRIPTION_CREATE.name -> decode<SubscriptionObject>(payload, jsonSerializer) { Gateway.SubscriptionCreate(it) }
                SUBSCRIPTION_UPDATE.name -> decode<SubscriptionObject>(payload, jsonSerializer) { Gateway.SubscriptionUpdate(it) }
                SUBSCRIPTION_DELETE.name -> decode<SubscriptionObject>(payload, jsonSerializer) { Gateway.SubscriptionDelete(it) }
                TYPING_START.name -> decode<Gateway.TypingStart.DataObject>(payload, jsonSerializer) { Gateway.TypingStart(it) }
                USER_UPDATE.name -> decode<UserObject>(payload, jsonSerializer) { Gateway.UserUpdate(it) }
                VOICE_CHANNEL_EFFECT_SEND.name -> decode<Gateway.VoiceChannelEffectSend.DataObject>(payload, jsonSerializer) { Gateway.VoiceChannelEffectSend(it) }
                VOICE_STATE_UPDATE.name -> decode<VoiceStateObject>(payload, jsonSerializer) { Gateway.VoiceStateUpdate(it) }
                VOICE_SERVER_UPDATE.name -> decode<Gateway.VoiceServerUpdate.DataObject>(payload, jsonSerializer) { Gateway.VoiceServerUpdate(it) }
                WEBHOOKS_UPDATE.name -> decode<Gateway.WebhooksUpdate.DataObject>(payload, jsonSerializer) { Gateway.WebhooksUpdate(it) }
                MESSAGE_POLL_VOTE_ADD.name -> decode<Gateway.MessagePollVoteAdd.DataObject>(payload, jsonSerializer) { Gateway.MessagePollVoteAdd(it) }
                MESSAGE_POLL_VOTE_REMOVE.name -> decode<Gateway.MessagePollVoteRemove.DataObject>(payload, jsonSerializer) { Gateway.MessagePollVoteRemove(it) }
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