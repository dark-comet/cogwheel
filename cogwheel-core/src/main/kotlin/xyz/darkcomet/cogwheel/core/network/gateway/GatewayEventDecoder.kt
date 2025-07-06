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
                GatewayOpCode.HELLO.code -> decode<GatewayEvent.Hello.DataObject>(payload, jsonSerializer) { GatewayEvent.Hello(it) }
                GatewayOpCode.RECONNECT.code -> GatewayEvent.Reconnect()
                GatewayOpCode.INVALID_SESSION.code -> GatewayEvent.InvalidSession(isResumeRecommended = jsonSerializer.decodeFromJsonElement(payload.d!!))
                GatewayOpCode.HEARTBEAT.code -> GatewayEvent.Heartbeat()
                GatewayOpCode.HEARTBEAT_ACK.code -> GatewayEvent.HeartbeatAck()
                else -> null
            }
        }

        private fun decodeDispatchEvent(
            payload: GatewayPayload, 
            jsonSerializer: Json
        ): Event<*>? {
            return when (payload.t) {
                READY.name -> decode<GatewayEvent.Ready.DataObject>(payload, jsonSerializer) { GatewayEvent.Ready(it) }
                RESUMED.name -> GatewayEvent.Resumed()
                APPLICATION_COMMAND_PERMISSIONS_UPDATE.name -> decode<ApplicationCommandPermissionObject>(payload, jsonSerializer) { GatewayEvent.ApplicationCommandPermissionsUpdate(it) }
                AUTO_MODERATION_RULE_CREATE.name -> decode<GuildAutoModerationRuleObject>(payload, jsonSerializer) { GatewayEvent.AutoModerationRuleCreate(it) }
                AUTO_MODERATION_RULE_UPDATE.name -> decode<GuildAutoModerationRuleObject>(payload, jsonSerializer) { GatewayEvent.AutoModerationRuleUpdate(it) }
                AUTO_MODERATION_RULE_DELETE.name -> decode<GuildAutoModerationRuleObject>(payload, jsonSerializer) { GatewayEvent.AutoModerationRuleDelete(it) }
                AUTO_MODERATION_ACTION_EXECUTION.name -> decode<GatewayEvent.AutoModerationActionExecution.DataObject>(payload, jsonSerializer) { GatewayEvent.AutoModerationActionExecution(it) }
                CHANNEL_CREATE.name -> decode<ChannelObject>(payload, jsonSerializer) { GatewayEvent.ChannelCreate(it) }
                CHANNEL_UPDATE.name -> decode<ChannelObject>(payload, jsonSerializer) { GatewayEvent.ChannelUpdate(it) }
                CHANNEL_DELETE.name -> decode<ChannelObject>(payload, jsonSerializer) { GatewayEvent.ChannelDelete(it) }
                CHANNEL_PINS_UPDATE.name -> decode<GatewayEvent.ChannelPinsUpdate.DataObject>(payload, jsonSerializer) { GatewayEvent.ChannelPinsUpdate(it) }
                THREAD_CREATE.name -> decode<ChannelObject>(payload, jsonSerializer) { GatewayEvent.ThreadCreate(it) }
                THREAD_UPDATE.name -> decode<ChannelObject>(payload, jsonSerializer) { GatewayEvent.ThreadUpdate(it) }
                THREAD_DELETE.name -> decode<ChannelObject>(payload, jsonSerializer) { GatewayEvent.ThreadDelete(it) }
                THREAD_LIST_SYNC.name -> decode<GatewayEvent.ThreadListSync.DataObject>(payload, jsonSerializer) { GatewayEvent.ThreadListSync(it) }
                THREAD_MEMBER_UPDATE.name -> decode<ThreadMemberObject>(payload, jsonSerializer) { GatewayEvent.ThreadMemberUpdate(it) }
                THREAD_MEMBERS_UPDATE.name -> decode<GatewayEvent.ThreadMembersUpdate.DataObject>(payload, jsonSerializer) { GatewayEvent.ThreadMembersUpdate(it) }
                ENTITLEMENT_CREATE.name -> decode<EntitlementObject>(payload, jsonSerializer) { GatewayEvent.EntitlementCreate(it) }
                ENTITLEMENT_UPDATE.name -> decode<EntitlementObject>(payload, jsonSerializer) { GatewayEvent.EntitlementUpdate(it) }
                ENTITLEMENT_DELETE.name -> decode<EntitlementObject>(payload, jsonSerializer) { GatewayEvent.EntitlementDelete(it) }
                GUILD_CREATE.name -> decode<GuildObject>(payload, jsonSerializer) { GatewayEvent.GuildCreate(it) }
                GUILD_UPDATE.name -> decode<GuildObject>(payload, jsonSerializer) { GatewayEvent.GuildUpdate(it) }
                GUILD_DELETE.name -> decode<GuildObject>(payload, jsonSerializer) { GatewayEvent.GuildDelete(it) }
                GUILD_AUDIT_LOG_ENTRY_CREATE.name -> decode<AuditLogEntryObject>(payload, jsonSerializer) { GatewayEvent.GuildAuditLogEntryCreate(it) }
                GUILD_BAN_ADD.name -> decode<GatewayEvent.GuildBanAdd.DataObject>(payload, jsonSerializer) { GatewayEvent.GuildBanAdd(it) }
                GUILD_BAN_REMOVE.name -> decode<GatewayEvent.GuildBanRemove.DataObject>(payload, jsonSerializer) { GatewayEvent.GuildBanRemove(it) }
                GUILD_EMOJIS_UPDATE.name -> decode<GatewayEvent.GuildEmojisUpdate.DataObject>(payload, jsonSerializer) { GatewayEvent.GuildEmojisUpdate(it) }
                GUILD_STICKERS_UPDATE.name -> decode<GatewayEvent.GuildStickersUpdate.DataObject>(payload, jsonSerializer) { GatewayEvent.GuildStickersUpdate(it) }
                GUILD_INTEGRATIONS_UPDATE.name -> decode<GatewayEvent.GuildIntegrationsUpdate.DataObject>(payload, jsonSerializer) { GatewayEvent.GuildIntegrationsUpdate(it) }
                GUILD_MEMBER_ADD.name -> decode<GuildMemberObject>(payload, jsonSerializer) { GatewayEvent.GuildMemberAdd(it) }
                GUILD_MEMBER_REMOVE.name -> decode<GatewayEvent.GuildMemberRemove.DataObject>(payload, jsonSerializer) { GatewayEvent.GuildMemberRemove(it) }
                GUILD_MEMBER_UPDATE.name -> decode<GatewayEvent.GuildMemberUpdate.DataObject>(payload, jsonSerializer) { GatewayEvent.GuildMemberUpdate(it) }
                GUILD_MEMBERS_CHUNK.name -> decode<GatewayEvent.GuildMembersChunk.DataObject>(payload, jsonSerializer) { GatewayEvent.GuildMembersChunk(it) }
                GUILD_ROLE_CREATE.name -> decode<GatewayEvent.GuildRoleCreate.DataObject>(payload, jsonSerializer) { GatewayEvent.GuildRoleCreate(it) }
                GUILD_ROLE_UPDATE.name -> decode<GatewayEvent.GuildRoleUpdate.DataObject>(payload, jsonSerializer) { GatewayEvent.GuildRoleUpdate(it) }
                GUILD_ROLE_DELETE.name -> decode<GatewayEvent.GuildRoleDelete.DataObject>(payload, jsonSerializer) { GatewayEvent.GuildRoleDelete(it) }
                GUILD_SCHEDULED_EVENT_CREATE.name -> decode<GuildScheduledEventObject>(payload, jsonSerializer) { GatewayEvent.GuildScheduledEventCreate(it) }
                GUILD_SCHEDULED_EVENT_UPDATE.name -> decode<GuildScheduledEventObject>(payload, jsonSerializer) { GatewayEvent.GuildScheduledEventUpdate(it) }
                GUILD_SCHEDULED_EVENT_DELETE.name -> decode<GuildScheduledEventObject>(payload, jsonSerializer) { GatewayEvent.GuildScheduledEventDelete(it) }
                GUILD_SCHEDULED_EVENT_USER_ADD.name -> decode<GatewayEvent.GuildScheduledEventUserAdd.DataObject>(payload, jsonSerializer) { GatewayEvent.GuildScheduledEventUserAdd(it) }
                GUILD_SCHEDULED_EVENT_USER_REMOVE.name -> decode<GatewayEvent.GuildScheduledEventUserRemove.DataObject>(payload, jsonSerializer) { GatewayEvent.GuildScheduledEventUserRemove(it) }
                GUILD_SOUNDBOARD_SOUND_CREATE.name -> decode<SoundboardSoundObject>(payload, jsonSerializer) { GatewayEvent.GuildSoundboardSoundCreate(it) }
                GUILD_SOUNDBOARD_SOUND_UPDATE.name -> decode<SoundboardSoundObject>(payload, jsonSerializer) { GatewayEvent.GuildSoundboardSoundUpdate(it) }
                GUILD_SOUNDBOARD_SOUND_DELETE.name -> decode<GatewayEvent.GuildSoundboardSoundDelete.DataObject>(payload, jsonSerializer) { GatewayEvent.GuildSoundboardSoundDelete(it) }
                GUILD_SOUNDBOARD_SOUNDS_UPDATE.name -> decode<GatewayEvent.GuildSoundboardSoundsUpdate.DataObject>(payload, jsonSerializer) { GatewayEvent.GuildSoundboardSoundsUpdate(it) }
                SOUNDBOARD_SOUNDS.name -> decode<GatewayEvent.SoundboardSounds.DataObject>(payload, jsonSerializer) { GatewayEvent.SoundboardSounds(it) }
                INTEGRATION_CREATE.name -> decode<GuildIntegrationObject>(payload, jsonSerializer) { GatewayEvent.IntegrationCreate(it) }
                INTEGRATION_UPDATE.name -> decode<GuildIntegrationObject>(payload, jsonSerializer) { GatewayEvent.IntegrationUpdate(it) }
                INTEGRATION_DELETE.name -> decode<GatewayEvent.IntegrationDelete.DataObject>(payload, jsonSerializer) { GatewayEvent.IntegrationDelete(it) }
                INTERACTION_CREATE.name -> decode<InteractionObject>(payload, jsonSerializer) { GatewayEvent.InteractionCreate(it) }
                INVITE_CREATE.name -> decode<GatewayEvent.InviteCreate.DataObject>(payload, jsonSerializer) { GatewayEvent.InviteCreate(it) }
                INVITE_DELETE.name -> decode<GatewayEvent.InviteDelete.DataObject>(payload, jsonSerializer) { GatewayEvent.InviteDelete(it) }
                MESSAGE_CREATE.name -> decode<MessageObject>(payload, jsonSerializer) { GatewayEvent.MessageCreate(it) }
                MESSAGE_UPDATE.name -> decode<MessageObject>(payload, jsonSerializer) { GatewayEvent.MessageUpdate(it) }
                MESSAGE_DELETE.name -> decode<GatewayEvent.MessageDelete.DataObject>(payload, jsonSerializer) { GatewayEvent.MessageDelete(it) }
                MESSAGE_DELETE_BULK.name -> decode<GatewayEvent.MessageDeleteBulk.DataObject>(payload, jsonSerializer) { GatewayEvent.MessageDeleteBulk(it) }
                MESSAGE_REACTION_ADD.name -> decode<GatewayEvent.MessageReactionAdd.DataObject>(payload, jsonSerializer) { GatewayEvent.MessageReactionAdd(it) }
                MESSAGE_REACTION_REMOVE.name -> decode<GatewayEvent.MessageReactionRemove.DataObject>(payload, jsonSerializer) { GatewayEvent.MessageReactionRemove(it) }
                MESSAGE_REACTION_REMOVE_ALL.name -> decode<GatewayEvent.MessageReactionRemoveAll.DataObject>(payload, jsonSerializer) { GatewayEvent.MessageReactionRemoveAll(it) }
                MESSAGE_REACTION_REMOVE_EMOJI.name -> decode<GatewayEvent.MessageReactionRemoveEmoji.DataObject>(payload, jsonSerializer) { GatewayEvent.MessageReactionRemoveEmoji(it) }
                PRESENCE_UPDATE.name -> decode<GatewayEvent.PresenceUpdate.DataObject>(payload, jsonSerializer) { GatewayEvent.PresenceUpdate(it) }
                STAGE_INSTANCE_CREATE.name -> decode<StageInstanceObject>(payload, jsonSerializer) { GatewayEvent.StageInstanceCreate(it) }
                STAGE_INSTANCE_UPDATE.name -> decode<StageInstanceObject>(payload, jsonSerializer) { GatewayEvent.StageInstanceUpdate(it) }
                STAGE_INSTANCE_DELETE.name -> decode<StageInstanceObject>(payload, jsonSerializer) { GatewayEvent.StageInstanceDelete(it) }
                SUBSCRIPTION_CREATE.name -> decode<SubscriptionObject>(payload, jsonSerializer) { GatewayEvent.SubscriptionCreate(it) }
                SUBSCRIPTION_UPDATE.name -> decode<SubscriptionObject>(payload, jsonSerializer) { GatewayEvent.SubscriptionUpdate(it) }
                SUBSCRIPTION_DELETE.name -> decode<SubscriptionObject>(payload, jsonSerializer) { GatewayEvent.SubscriptionDelete(it) }
                TYPING_START.name -> decode<GatewayEvent.TypingStart.DataObject>(payload, jsonSerializer) { GatewayEvent.TypingStart(it) }
                USER_UPDATE.name -> decode<UserObject>(payload, jsonSerializer) { GatewayEvent.UserUpdate(it) }
                VOICE_CHANNEL_EFFECT_SEND.name -> decode<GatewayEvent.VoiceChannelEffectSend.DataObject>(payload, jsonSerializer) { GatewayEvent.VoiceChannelEffectSend(it) }
                VOICE_STATE_UPDATE.name -> decode<VoiceStateObject>(payload, jsonSerializer) { GatewayEvent.VoiceStateUpdate(it) }
                VOICE_SERVER_UPDATE.name -> decode<GatewayEvent.VoiceServerUpdate.DataObject>(payload, jsonSerializer) { GatewayEvent.VoiceServerUpdate(it) }
                WEBHOOKS_UPDATE.name -> decode<GatewayEvent.WebhooksUpdate.DataObject>(payload, jsonSerializer) { GatewayEvent.WebhooksUpdate(it) }
                MESSAGE_POLL_VOTE_ADD.name -> decode<GatewayEvent.MessagePollVoteAdd.DataObject>(payload, jsonSerializer) { GatewayEvent.MessagePollVoteAdd(it) }
                MESSAGE_POLL_VOTE_REMOVE.name -> decode<GatewayEvent.MessagePollVoteRemove.DataObject>(payload, jsonSerializer) { GatewayEvent.MessagePollVoteRemove(it) }
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