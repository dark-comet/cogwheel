package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import xyz.darkcomet.cogwheel.core.primitives.*

@Serializable
data class ActivityAssetsObject(
    @SerialName("large_image") val largeImage: Possible<String>? = null,
    @SerialName("large_text") val largeText: Possible<String>? = null,
    @SerialName("small_image") val smallImage: Possible<String>? = null,
    @SerialName("small_text") val smallText: Possible<String>? = null,
)

@Serializable
data class ActivityButtonObject(
    val label: Possible<String>? = null,
    val url: Possible<String>? = null
)

@Serializable
data class ActivityEmojiObject(
    val name: Possible<String>? = null,
    val id: Possible<Snowflake>? = null,
    val animated: Possible<Boolean>? = null
)

@Serializable
data class ActivityLocationObject(
    val id: Possible<String>? = null,
    val kind: Possible<String>? = null,
    @SerialName("channel_id") val channelId: Possible<Snowflake>? = null,
    @SerialName("guild_id") val guildId: Possible<Snowflake>? = null
)

@Serializable
data class ActivityObject(
    val name: Possible<String>? = null,
    val type: Possible<Int>? = null,
    val url: Possible<String>? = null,
    @SerialName("created_at") val createdAt: Possible<Int>? = null,
    val timestamps: Possible<ActivityTimestampsObject>? = null,
    @SerialName("application_id") val applicationId: Possible<Snowflake>? = null,
    val details: Possible<String>? = null,
    val state: Possible<String>? = null,
    val emoji: Possible<ActivityEmojiObject>? = null,
    val party: Possible<ActivityPartyObject>? = null,
    val assets: Possible<ActivityAssetsObject>? = null,
    val secrets: Possible<ActivitySecretsObject>? = null,
    val instance: Possible<Boolean>? = null,
    val flags: Possible<Int>? = null,
    val buttons: Possible<List<ActivityButtonObject>>? = null
)

@Serializable
data class ActivityPartyObject(
    val id: Possible<String>? = null,
    val size: Possible<List<Int>>? = null
)

@Serializable
data class ActivitySecretsObject(
    val join: Possible<String>? = null,
    val spectate: Possible<String>? = null,
    val match: Possible<String>? = null
)

@Serializable
data class ActivityTimestampsObject(
    val start: Possible<Long>? = null,
    val end: Possible<Long>? = null
)

@Serializable
data class AllowedMentionsObject(
    val parse: Possible<List<String>>? = null,
    val roles: Possible<List<Snowflake>>? = null,
    val users: Possible<List<Snowflake>>? = null,
    @SerialName("replied_user") val repliedUser: Possible<Boolean>? = null
)

@Serializable
data class ApplicationActivityInstanceObject(
    @SerialName("application_id") val applicationId: Possible<Snowflake>? = null,
    @SerialName("instance_id") val instanceId: Possible<String>? = null,
    @SerialName("launch_id") val launchId: Possible<Snowflake>? = null,
    val location: Possible<ActivityLocationObject>? = null,
    val users: Possible<List<Snowflake>>? = null,
)

@Serializable(with = ApplicationCommandInteractionDataOptionObject.Serializer::class)
data class ApplicationCommandInteractionDataOptionObject(
    val name: Possible<String>? = null,
    val type: Possible<Int>? = null,
    val value: Possible<Any>? = null,
    val options: Possible<List<ApplicationCommandInteractionDataOptionObject>>? = null,
    val focused: Possible<Boolean>? = null
) {
    internal object Serializer : KSerializer<ApplicationCommandInteractionDataOptionObject> {
        override val descriptor: SerialDescriptor
            get() = TODO("Not yet implemented")

        override fun deserialize(decoder: Decoder): ApplicationCommandInteractionDataOptionObject {
            TODO("Not yet implemented")
        }

        override fun serialize(
            encoder: Encoder,
            value: ApplicationCommandInteractionDataOptionObject
        ) {
            TODO("Not yet implemented")
        }
    }
}

@Serializable
data class ApplicationCommandObject(
    val id: Possible<Snowflake>? = null,
    val type: Possible<Int>? = null,
    @SerialName("application_id") val applicationId: Possible<Snowflake>? = null,
    @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
    val name: Possible<String>? = null,
    @SerialName("name_localizations") val nameLocalizations: Possible<Map<String, String>>? = null,
    val description: Possible<String>? = null,
    @SerialName("description_localizations") val descriptionLocalizations: Possible<Map<String, String>>? = null,
    val options: Possible<List<ApplicationCommandOptionObject>>? = null,
    @SerialName("default_member_permissions") val defaultMemberPermissions: Possible<PermissionSet>? = null,
    @SerialName("dm_permission") val dmPermission: Possible<Boolean>? = null,
    @SerialName("default_permission") val defaultPermission: Possible<Boolean>? = null,
    val nsfw: Possible<Boolean>? = null,
    @SerialName("integration_types") val integrationTypes: Possible<List<Int>>? = null,
    val contexts: Possible<List<Int>>? = null,
    val version: Possible<Snowflake>? = null,
    val handler: Possible<Int>? = null
)

@Serializable
abstract class ApplicationCommandOptionChoiceObject {
    abstract val name: Possible<String>?

    @SerialName("name_localizations")
    abstract val nameLocalizations: Possible<Map<String, String>>?
}

@Serializable
class ApplicationCommandOptionStringChoiceObject(
    override val name: Possible<String>? = null,
    override val nameLocalizations: Possible<Map<String, String>>? = null,
    val value: Possible<String>? = null
) : ApplicationCommandOptionChoiceObject()

@Serializable
class ApplicationCommandOptionIntegerChoiceObject(
    override val name: Possible<String>? = null,
    override val nameLocalizations: Possible<Map<String, String>>? = null,
    val value: Possible<Int>? = null
) : ApplicationCommandOptionChoiceObject()

@Serializable
class ApplicationCommandOptionDoubleChoiceObject(
    override val name: Possible<String>? = null,
    override val nameLocalizations: Possible<Map<String, String>>? = null,
    val value: Possible<Double>? = null
) : ApplicationCommandOptionChoiceObject()

@Serializable
data class ApplicationCommandOptionObject(
    val type: Possible<Int>? = null,
    val name: Possible<String>? = null,
    @SerialName("name_localizations") val nameLocalizations: Possible<Map<String, String>>? = null,
    val description: Possible<String>? = null,
    @SerialName("description_localizations") val descriptionLocalizations: Possible<Map<String, String>>? = null,
    val required: Possible<Boolean>? = null,
    val choices: Possible<List<ApplicationCommandOptionChoiceObject>>? = null,
    val options: Possible<List<ApplicationCommandOptionObject>>? = null,
    @SerialName("channel_types") val channelTypes: Possible<List<Int>>? = null,
    @SerialName("min_value") val minValue: Possible<Double>? = null,
    @SerialName("max_value") val maxValue: Possible<Double>? = null,
    @SerialName("min_length") val minLength: Possible<Int>? = null,
    @SerialName("max_length") val maxLength: Possible<Int>? = null,
    val autocomplete: Possible<Boolean>? = null
)

@Serializable
data class ApplicationCommandPermissionObject(
    val id: Possible<Snowflake>? = null,
    val type: Possible<Int>? = null,
    val permission: Possible<Boolean>? = null
)

@Serializable
data class ApplicationInstallParamsObject(
    val scopes: Possible<List<String>>? = null,
    val permissions: Possible<String>? = null
)

@Serializable
data class ApplicationIntegrationTypeConfigurationObject(
    @SerialName("oauth2_install_params") val oauth2InstallParams: Possible<ApplicationInstallParamsObject>? = null
)

@Serializable
data class ApplicationObject(
    val id: Possible<Snowflake>? = null,
    val name: Possible<String>? = null,
    val icon: Possible<String>? = null,
    val description: Possible<String>? = null,
    @SerialName("rpc_origins") val rpcOrigins: Possible<List<String>>? = null,
    @SerialName("bot_public") val botPublic: Possible<Boolean?>? = null,
    @SerialName("bot_require_code_grant") val botRequireCodeGrant: Possible<Boolean>? = null,
    val bot: Possible<UserObject>? = null,
    @SerialName("terms_of_service_url") val termsOfServiceUrl: Possible<String>? = null,
    @SerialName("privacy_policy_url") val privacyPolicyUrl: Possible<String>? = null,
    @SerialName("owner") val owner: Possible<UserObject>? = null,
    @SerialName("verify_key") val verifyKey: Possible<String>? = null,
    val team: Possible<TeamObject>? = null,
    @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
    val guild: Possible<GuildObject>? = null,
    @SerialName("primary_sku_id") val primarySkuId: Possible<Snowflake>? = null,
    val slug: Possible<String>? = null,
    @SerialName("cover_image") val coverImage: Possible<String>? = null,
    val flags: Possible<Int>? = null,
    @SerialName("approximate_guild_count") val approximateGuildCount: Possible<Int>? = null,
    @SerialName("approximate_user_install_count") val approximateUserInstallCount: Possible<Int>? = null,
    @SerialName("redirect_uris") val redirectUris: Possible<List<String>>? = null,
    @SerialName("interactions_endpoint_url") val interactionsEndpointUrl: Possible<String>? = null,
    @SerialName("role_connections_verification_url") val roleConnectionsVerificationUrl: Possible<String>? = null,
    @SerialName("event_webhooks_url") val eventWebhooksUrl: Possible<String>? = null,
    @SerialName("event_webhooks_status") val eventWebhooksStatus: Possible<Int>? = null,
    @SerialName("event_webhook_types") val eventWebhookTypes: Possible<List<String>>? = null,
    val tags: Possible<List<String>>? = null,
    @SerialName("install_params") val installParams: Possible<ApplicationInstallParamsObject>? = null,
    @SerialName("integration_types_config") val integrationTypesConfig: Possible<Map<String, ApplicationIntegrationTypeConfigurationObject>>? = null,
    @SerialName("custom_install_url") val customInstallUrl: Possible<String>? = null,
)

@Serializable
data class ApplicationRoleConnectionMetadataObject(
    val type: Possible<Int>? = null,
    val key: Possible<String>? = null,
    val name: Possible<String>? = null,
    @SerialName("name_localizations") val nameLocalizations: Possible<Map<String, String>>? = null,
    val description: Possible<String>? = null,
    @SerialName("description_localizations") val descriptionLocalizations: Possible<Map<String, String>>? = null
)

@Serializable
data class AttachmentObject(
    val id: Possible<Snowflake>? = null,
    val filename: Possible<String>? = null,
    val title: Possible<String>? = null,
    val description: Possible<String>? = null,
    @SerialName("content_type") val contentType: Possible<String>? = null,
    val size: Possible<Int>? = null,
    val url: Possible<String>? = null,
    @SerialName("proxy_url") val proxyUrl: Possible<String>? = null,
    val width: Possible<Int>? = null,
    val height: Possible<Int>? = null,
    val ephemeral: Possible<Boolean>? = null,
    @SerialName("duration_secs") val durationSecs: Possible<Float>? = null,
    val waveform: Possible<String>? = null,
    val flags: Possible<Int>? = null,
)

@Serializable(with = AuditLogChangeObject.Serializer::class)
data class AuditLogChangeObject(
    @SerialName("new_value") val newValue: Possible<Any>? = null,
    @SerialName("old_value") val oldValue: Possible<Any>? = null,
    val key: Possible<String>? = null
) {
    internal object Serializer : KSerializer<AuditLogChangeObject> {
        
        override val descriptor: SerialDescriptor
            get() = TODO("Not yet implemented")

        override fun deserialize(decoder: Decoder): AuditLogChangeObject {
            TODO("Not yet implemented")
        }

        override fun serialize(encoder: Encoder, value: AuditLogChangeObject) {
            TODO("Not yet implemented")
        }
        
    }
}

@Serializable
data class AuditLogEntryObject(
    @SerialName("target_id") val targetId: Possible<String>? = null,
    val changes: Possible<List<AuditLogChangeObject>>? = null,
    val userId: Possible<Snowflake?>? = null,
    val id: Possible<Snowflake>? = null,
    @SerialName("action_type") val actionType: Possible<Int>? = null,
    val options: Possible<AuditLogOptionalAuditEntryInfoObject>? = null,
    val reason: Possible<String>? = null,
    @SerialName("guild_id") val guildId: Possible<Snowflake>? = null
)

@Serializable
data class AuditLogOptionalAuditEntryInfoObject(
    @SerialName("application_id") val applicationId: Possible<Snowflake>? = null,
    @SerialName("auto_moderation_rule_name") val autoModerationRuleName: Possible<String>? = null,
    @SerialName("auto_moderation_rule_trigger_type") val autoModerationRuleTriggerType: Possible<String>? = null,
    @SerialName("channel_id") val channelId: Possible<Snowflake>? = null,
    val count: Possible<String>? = null,
    @SerialName("delete_member_days") val deleteMemberDays: Possible<String>? = null,
    val id: Possible<Snowflake>? = null,
    @SerialName("members_removed") val membersRemoved: Possible<String>? = null,
    @SerialName("message_id") val messageId: Possible<Snowflake>? = null,
    @SerialName("role_name") val roleName: Possible<String>? = null,
    val type: Possible<String>? = null,
    @SerialName("integration_type") val integrationType: Possible<String>? = null,
)

@Serializable
data class AutoModerationActionMetadataObject(
    @SerialName("channel_id") val channelId: Possible<Snowflake>? = null,
    @SerialName("duration_seconds") val durationSeconds: Possible<Int>? = null,
    @SerialName("custom_message") val customMessage: Possible<String>? = null
)

@Serializable
data class AutoModerationActionObject(
    val type: Possible<Int>? = null,
    val metadata: Possible<AutoModerationActionMetadataObject>? = null
)

@Serializable
data class AutoModerationRuleTriggerMetadataObject(
    @SerialName("keyword_filter") val keywordFilter: Possible<List<String>>? = null,
    @SerialName("regex_patterns") val regexPatterns: Possible<List<String>>? = null,
    val presets: Possible<List<Int>>? = null,
    @SerialName("allow_list") val allowList: Possible<List<String>>? = null,
    @SerialName("mention_total_limit") val mentionTotalLimit: Possible<Int>? = null,
    @SerialName("mention_raid_protection_enabled") val mentionRaidProtectionEnabled: Possible<Boolean>? = null
)

@Serializable
data class AvatarDecorationDataObject(
    val asset: Possible<String>? = null,
    @SerialName("sku_id") val skuId: Possible<Snowflake>? = null
)

@Serializable
data class ChannelDefaultReactionObject(
    @SerialName("emoji_id") val emojiId: Possible<String>? = null,
    @SerialName("emoji_name") val emojiName: Possible<String>? = null
)

@Serializable
data class ChannelMentionObject(
    val id: Possible<Snowflake>? = null,
    @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
    val type: Possible<Int>? = null,
    val name: Possible<String>? = null
)

@Serializable
data class ChannelObject(
    val id: Possible<Snowflake>? = null,
    val type: Possible<Int>? = null,
    @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
    val position: Possible<Int>? = null,
    @SerialName("permission_overwrites") val permissionOverwrites: Possible<List<ChannelPermissionOverwriteObject>>? = null,
    val name: Possible<String>? = null,
    val topic: Possible<String>? = null,
    val nsfw: Possible<Boolean>? = null,
    @SerialName("last_message_id") val lastMessageId: Possible<String>? = null,
    val bitrate: Possible<Int>? = null,
    @SerialName("user_limit") val userLimit: Possible<Int>? = null,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: Possible<Int>? = null,
    val recipients: Possible<List<UserObject>>? = null,
    val icon: Possible<String>? = null,
    @SerialName("owner_id") val ownerId: Possible<Snowflake>? = null,
    @SerialName("application_id") val applicationId: Possible<Snowflake>? = null,
    val managed: Possible<Boolean>? = null,
    @SerialName("parent_id") val parentId: Possible<Snowflake>? = null,
    @SerialName("last_pin_timestamp") val lastPinTimestamp: Possible<ISO8601Timestamp>? = null,
    @SerialName("rtc_region") val rtcRegion: Possible<String>? = null,
    @SerialName("video_quality_mode") val videoQualityMode: Possible<Int>? = null,
    @SerialName("message_count") val messageCount: Possible<Int>? = null,
    @SerialName("member_count") val memberCount: Possible<Int>? = null,
    @SerialName("thread_metadata") val threadMetadata: Possible<ThreadMetadataObject>? = null,
    @SerialName("thread_member") val member: Possible<ThreadMemberObject>? = null,
    @SerialName("default_auto_archive_duration") val defaultAutoArchiveDuration: Possible<Int>? = null,
    @SerialName("permissions") val permissions: Possible<PermissionSet>? = null,
    val flags: Possible<Int>? = null,
    @SerialName("total_message_sent") val totalMessageSent: Possible<Int>? = null,
    @SerialName("available_tags") val availableTags: Possible<List<ForumTagObject>>? = null,
    @SerialName("applied_tags") val appliedTags: Possible<List<Snowflake>>? = null,
    @SerialName("default_reaction_emoji") val defaultReactionEmoji: Possible<ChannelDefaultReactionObject>? = null,
    @SerialName("default_thread_rate_limit_per_user") val defaultThreadRateLimitPerUser: Possible<Int>? = null,
    @SerialName("default_sort_order") val defaultSortOrder: Possible<Int>? = null,
    @SerialName("default_forum_layout") val defaultForumLayout: Possible<Int>? = null,
    val message: Possible<MessageObject>? = null, // from starting a thread in forum / media channel
)

@Serializable
data class ChannelPermissionOverwriteObject(
    val id: Possible<Snowflake>? = null,
    val type: Possible<Int>? = null,
    val allow: Possible<PermissionSet>? = null,
    val deny: Possible<PermissionSet>? = null
)

@Serializable
data class ClientStatusObject(
    val desktop: Possible<String>? = null,
    val mobile: Possible<String>? = null,
    val web: Possible<String>? = null
)

@Serializable
data class EmbedAuthorObject(
    val name: Possible<String>? = null,
    val url: Possible<String>? = null,
    @SerialName("icon_url") val iconUrl: Possible<String>? = null,
    @SerialName("proxy_icon_url") val proxyIconUrl: Possible<String>? = null,
)

@Serializable
data class EmbedFieldObject(
    val name: Possible<String>? = null,
    val value: Possible<String>? = null,
    val inline: Possible<Boolean>? = null
)

@Serializable
data class EmbedFooterObject(
    val text: Possible<String>? = null,
    @SerialName("icon_url") val iconUrl: Possible<String>? = null,
    @SerialName("proxy_icon_url") val proxyIconUrl: Possible<String>? = null
)

@Serializable
data class EmbedImageObject(
    val url: Possible<String>? = null,
    @SerialName("proxy_url") val proxyUrl: Possible<String>? = null,
    val width: Possible<Int>? = null,
    val height: Possible<Int>? = null
)

@Serializable
data class EmbedObject(
    val title: Possible<String>? = null,
    val type: Possible<String>? = null,
    val description: Possible<String>? = null,
    val url: Possible<String>? = null,
    val timestamp: Possible<ISO8601Timestamp>? = null,
    val color: Possible<Int>? = null,
    val footer: Possible<EmbedFooterObject>? = null,
    val image: Possible<EmbedImageObject>? = null,
    val thumbnail: Possible<EmbedThumbnailObject>? = null,
    val video: Possible<EmbedVideoObject>? = null,
    val provider: Possible<EmbedProviderObject>? = null,
    val author: Possible<EmbedAuthorObject>? = null,
    val fields: Possible<List<EmbedFieldObject>>? = null
)

@Serializable
data class EmbedProviderObject(
    val name: Possible<String>? = null,
    val url: Possible<String>? = null
)

@Serializable
data class EmbedThumbnailObject(
    val url: Possible<String>? = null,
    @SerialName("proxy_url") val proxyUrl: Possible<String>? = null,
    val width: Possible<Int>? = null,
    val height: Possible<Int>? = null
)

@Serializable
data class EmbedVideoObject(
    val url: Possible<String>? = null,
    @SerialName("proxy_url") val proxyUrl: Possible<String>? = null,
    val width: Possible<String>? = null,
    val height: Possible<String>? = null
)

@Serializable
data class EmojiObject(
    val id: Possible<Snowflake?>? = null,
    val name: Possible<String?>? = null,
    val roles: Possible<List<RoleObject>>? = null,
    val user: Possible<UserObject>? = null,
    @SerialName("require_colons") val requireColons: Possible<Boolean>? = null,
    val managed: Possible<Boolean>? = null,
    val animated: Possible<Boolean>? = null,
    val available: Possible<Boolean>? = null
)

@Serializable
data class EntitlementObject(
    val id: Possible<Snowflake>? = null,
    @SerialName("sku_id") val skuId: Possible<Snowflake>? = null,
    @SerialName("application_id") val applicationId: Possible<Snowflake>? = null,
    @SerialName("user_id") val userId: Possible<Snowflake>? = null,
    val type: Possible<Int>? = null,
    val deleted: Possible<Boolean>? = null,
    @SerialName("starts_at") val startsAt: Possible<ISO8601Timestamp>? = null,
    @SerialName("ends_at") val endsAt: Possible<ISO8601Timestamp>? = null,
    @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
    val consumed: Possible<Boolean>? = null
)

@Serializable
data class FollowedChannelObject(
    @SerialName("channel_id") val channelId: Possible<Snowflake>? = null,
    @SerialName("webhook_id") val webhookId: Possible<Snowflake>? = null
)

@Serializable
data class ForumTagObject(
    val id: Possible<Snowflake>? = null,
    val name: Possible<String>? = null,
    val moderated: Possible<Boolean>? = null,
    @SerialName("emoji_id") val emojiId: Possible<Snowflake>? = null,
    @SerialName("emoji_name") val emojiName: Possible<String>? = null
)

@Serializable
data class ForumThreadMessageParametersObject(
    val content: Possible<String>? = null,
    val embeds: Possible<List<EmbedObject>>? = null,
    val allowedMentions: Possible<AllowedMentionsObject>? = null,
    val components: Possible<List<MessageComponentObject>>? = null,
    val stickerIds: Possible<List<Snowflake>>? = null,
    val attachments: Possible<List<AttachmentObject>>? = null,
    val flags: Possible<Int>? = null
)

@Serializable
data class GuildApplicationCommandPermissionsObject(
    val id: Possible<Snowflake>? = null,
    @SerialName("application_id") val applicationId: Possible<Snowflake>? = null,
    @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
    val permissions: Possible<List<ApplicationCommandPermissionObject>>? = null
)

@Serializable
data class GuildAuditLogObject(
    @SerialName("application_commands") val applicationCommands: Possible<List<ApplicationCommandObject>>? = null,
    @SerialName("audit_log_entries") val auditLogEntries: Possible<List<AuditLogEntryObject>>? = null,
    @SerialName("autoModerationRules") val autoModerationRules: Possible<List<GuildAutoModerationRuleObject>>? = null,
    @SerialName("guild_scheduled_events") val guildScheduledEvents: Possible<List<GuildScheduledEventObject>>? = null,
    val integrations: Possible<List<GuildIntegrationObject>>? = null,
    val threads: Possible<List<ChannelObject>>? = null,
    val users: Possible<List<UserObject>>? = null,
    val webhooks: Possible<List<WebhookObject>>? = null
)

@Serializable
data class GuildAutoModerationRuleObject(
    val id: Possible<Snowflake>? = null,
    @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
    val name: Possible<String>? = null,
    @SerialName("creator_id") val creatorId: Possible<Snowflake>? = null,
    @SerialName("event_type") val eventType: Possible<Int>? = null,
    @SerialName("trigger_type") val triggerType: Possible<Int>? = null,
    @SerialName("trigger_metadata") val triggerMetadata: Possible<AutoModerationRuleTriggerMetadataObject>? = null,
    val actions: Possible<List<AutoModerationActionObject>>? = null,
    val enabled: Possible<Boolean>? = null,
    @SerialName("exempt_roles") val exemptRoles: Possible<List<Snowflake>>? = null,
    @SerialName("exempt_channels") val exemptChannels: Possible<List<Snowflake>>? = null
)

@Serializable
data class GuildBanObject(
    val reason: Possible<String>? = null,
    val user: Possible<UserObject>? = null
)

@Serializable
data class GuildIntegrationObject(
    val id: Possible<Snowflake>? = null,
    val name: Possible<String>? = null,
    val type: Possible<String>? = null,
    val enabled: Possible<Boolean>? = null,
    val syncing: Possible<Boolean>? = null,
    @SerialName("role_id") val roleId: Possible<Snowflake>? = null,
    @SerialName("enable_emoticons") val enableEmoticons: Possible<Boolean>? = null,
    @SerialName("expire_behaviour") val expireBehaviour: Possible<Int>? = null,
    @SerialName("expire_grace_period") val expireGracePeriod: Possible<Int>? = null,
    val user: Possible<UserObject>? = null,
    val account: Possible<IntegrationAccountObject>? = null,
    @SerialName("synced_at") val syncedAt: Possible<ISO8601Timestamp>? = null,
    @SerialName("subscriber_count") val subscriberCount: Possible<Int>? = null,
    val revoked: Possible<Boolean>? = null,
    val application: Possible<IntegrationApplicationObject>? = null,
    val scopes: Possible<List<String>>? = null,
    @SerialName("guild_id") val guildId: Possible<Snowflake>? = null
)

@Serializable
data class GuildMemberObject(
    val user: Possible<UserObject>? = null,
    val nick: Possible<String>? = null,
    val avatar: Possible<ImageData>? = null,
    val banner: Possible<ImageData>? = null,
    val roles: Possible<List<Snowflake>>? = null,
    @SerialName("joined_at") val joinedAt: Possible<ISO8601Timestamp>? = null,
    @SerialName("premium_since") val premiumSince: Possible<ISO8601Timestamp>? = null,
    val deaf: Possible<Boolean>? = null,
    val mute: Possible<Boolean>? = null,
    val flags: Possible<Int>? = null,
    val pending: Possible<Boolean>? = null,
    val permissions: Possible<PermissionSet>? = null,
    @SerialName("communication_disabled_until") val communicationDisabledUntil: Possible<ISO8601Timestamp>? = null,
    @SerialName("avatar_decoration_data") val avatarDecorationData: Possible<AvatarDecorationDataObject>? = null,
    @SerialName("guild_id") val guildId: Possible<Snowflake>? = null
)

/**
 * Low-level representation of Discord API Guild object and UnavailableGuild object.
 *
 * @see <a href="https://discord.com/developers/docs/resources/guild">Discord API Reference: Value<Guild</a>
 */
@Serializable
data class GuildObject(
    val id: Possible<Snowflake>? = null,
    val name: Possible<String>? = null,
    val icon: Possible<String>? = null,
    @SerialName("icon_hash") val iconHash: Possible<String>? = null,
    val splash: Possible<String>? = null,
    @SerialName("discovery_splash") val discoverySplash: Possible<String>? = null,
    val owner: Possible<String>? = null, //Only set when using the GET Current User Guilds endpoint, and are relative to the requested user
    @SerialName("owner_id") val ownerId: Possible<Snowflake>? = null,
    val permissions: Possible<PermissionSet>? = null,
    @Deprecated("replaced by channel.rtc_region") val region: Possible<String>? = null,
    @SerialName("afk_channel_id") val afkChannelId: Possible<Snowflake>? = null,
    @SerialName("afk_timeout") val afkTimeout: Possible<Int>? = null,
    @SerialName("widget_enabled") val widgetEnabled: Possible<Boolean>? = null,
    @SerialName("widget_channel_id") val widgetChannelId: Possible<Snowflake>? = null,
    @SerialName("verification_level") val verificationLevel: Possible<Int>? = null,
    @SerialName("default_message_notifications") val defaultMessageNotifications: Possible<Int>? = null,
    @SerialName("explicit_content_filter") val explicitContentFilter: Possible<Int>? = null,
    val roles: Possible<List<RoleObject>>? = null,
    val emojis: Possible<List<EmojiObject>>? = null,
    val features: Possible<List<String>>? = null,
    @SerialName("mfa_level") val mfaLevel: Possible<Int>? = null,
    @SerialName("application_id") val applicationId: Possible<Snowflake>? = null,
    @SerialName("system_channel_id") val systemChannelId: Possible<Snowflake>? = null,
    @SerialName("system_channel_flags") val systemChannelFlags: Possible<Int>? = null,
    @SerialName("rules_channel_id") val rulesChannelId: Possible<Snowflake>? = null,
    @SerialName("max_presences") val maxPresences: Possible<Int>? = null,
    @SerialName("max_members") val maxMembers: Possible<Int>? = null,
    @SerialName("vanity_url_code") val vanityUrlCode: Possible<String>? = null,
    val description: Possible<String>? = null,
    val banner: Possible<ImageData>? = null,
    @SerialName("premium_tier") val premiumTier: Possible<Int>? = null,
    @SerialName("premium_subscription_count") val premiumSubscriptionCount: Possible<Int>? = null,
    @SerialName("preferred_locale") val preferredLocale: Possible<String>? = null,
    @SerialName("public_updates_channel_id") val publicUpdatesChannelId: Possible<Snowflake>? = null,
    @SerialName("max_video_channel_users") val maxVideoChannelUsers: Possible<Int>? = null,
    @SerialName("max_stage_video_channel_users") val maxStageVideoChannelUsers: Possible<Int>? = null,
    @SerialName("approximate_member_count") val approximateMemberCount: Possible<Int>? = null,
    @SerialName("approximate_presence_count") val approximatePresenceCount: Possible<Int>? = null,
    @SerialName("welcome_screen") val welcomeScreen: Possible<GuildWelcomeScreenObject>? = null,
    @SerialName("nsfw_level") val nsfwLevel: Possible<Int>? = null,
    val stickers: Possible<List<StickerObject>>? = null,
    @SerialName("premium_progress_bar_enabled") val premiumProgressBarEnabled: Possible<Boolean>? = null,
    @SerialName("safety_alerts_channel_id") val safetyAlertsChannelId: Possible<Snowflake>? = null,
    val unavailable: Possible<Boolean>? = null,
    // From Gateway Guild Create Event
    @SerialName("joined_at") val joinedAt: Possible<ISO8601Timestamp>? = null,
    @SerialName("large") val large: Possible<Boolean>? = null,
    @SerialName("member_count") val memberCount: Possible<Int>? = null,
    @SerialName("voice_states") val voiceStates: Possible<List<VoiceStateObject>>? = null,
    @SerialName("members") val members: Possible<List<GuildMemberObject>>? = null,
    @SerialName("channels") val channels: Possible<List<ChannelObject>>? = null,
    @SerialName("threads") val threads: Possible<List<ChannelObject>>? = null,
    @SerialName("presences") val presences: Possible<List<UpdatePresenceObject>>? = null,
    @SerialName("stage_instances") val stageInstances: Possible<List<StageInstanceObject>>? = null,
    @SerialName("guild_scheduled_events") val guildScheduledEvents: Possible<List<GuildScheduledEventObject>>? = null,
    @SerialName("soundboard_sounds") val soundboardSounds: Possible<List<SoundboardSoundObject>>? = null,
)

@Serializable
data class GuildOnboardingObject(
    @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
    val prompts: Possible<List<GuildOnboardingPromptObject>>? = null,
    @SerialName("default_channel_ids") val defaultChannelIds: Possible<List<Snowflake>>? = null,
    val enabled: Possible<Boolean>? = null,
    val mode: Possible<Int>? = null
)

@Serializable
data class GuildOnboardingPromptObject(
    val id: Possible<Snowflake>? = null,
    val options: Possible<List<GuildOnboardingPromptOptionObject>>? = null,
    val title: Possible<String>? = null,
    @SerialName("single_select") val singleSelect: Possible<Boolean>? = null,
    val required: Possible<Boolean>? = null,
    @SerialName("in_onboarding") val inOnboarding: Possible<Boolean>? = null
)

@Serializable
data class GuildOnboardingPromptOptionObject(
    val id: Possible<Snowflake>? = null,
    @SerialName("channel_ids") val channelIds: Possible<List<Snowflake>>? = null,
    @SerialName("role_ids") val roleIds: Possible<List<Snowflake>>? = null,
    val emoji: Possible<EmojiObject>? = null,
    @SerialName("emoji_id") val emojiId: Possible<Snowflake>? = null,
    @SerialName("emoji_name") val emojiName: Possible<String>? = null,
    @SerialName("emoji_animated") val emojiAnimated: Possible<Boolean>? = null,
    val title: Possible<String>? = null,
    val description: Possible<String>? = null
)

@Serializable
data class GuildPreviewObject(
    val id: Possible<Snowflake>? = null,
    val name: Possible<String>? = null,
    val icon: Possible<String>? = null,
    val splash: Possible<String>? = null,
    @SerialName("discovery_splash") val discoverySplash: Possible<String>? = null,
    val emojis: Possible<List<EmojiObject>>? = null,
    val features: Possible<List<String>>? = null,
    @SerialName("approximate_member_count") val approximateMemberCount: Possible<Int>? = null,
    @SerialName("approximate_presence_count") val approximatePresenceCount: Possible<Int>? = null,
    val description: Possible<String>? = null,
    val stickers: Possible<List<StickerObject>>? = null
)

@Serializable
data class GuildScheduledEventEntityMetadataObject(
    val location: Possible<String>? = null
)

@Serializable
data class GuildScheduledEventObject(
    val id: Possible<Snowflake>? = null,
    @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
    @SerialName("channel_id") val channelId: Possible<Snowflake>? = null,
    @SerialName("creator_id") val creatorId: Possible<Snowflake>? = null,
    val name: Possible<String>? = null,
    val description: Possible<String>? = null,
    @SerialName("scheduled_start_time") val scheduledStartTime: Possible<ISO8601Timestamp>? = null,
    @SerialName("scheduled_end_time") val scheduledEndTime: Possible<ISO8601Timestamp>? = null,
    @SerialName("privacy_level") val privacyLevel: Possible<Int>? = null,
    val status: Possible<Int>? = null,
    @SerialName("entity_type") val entityType: Possible<Int>? = null,
    @SerialName("entity_id") val entityId: Possible<Snowflake>? = null,
    @SerialName("entity_metadata") val entityMetadata: Possible<GuildScheduledEventEntityMetadataObject>? = null,
    val creator: Possible<UserObject>? = null,
    @SerialName("user_count") val userCount: Possible<Int>? = null,
    val image: Possible<ImageData>? = null,
    @SerialName("recurrence_rule") val recurrenceRule: Possible<GuildScheduledEventRecurrenceRuleObject>? = null,
)

@Serializable
data class GuildScheduledEventRecurrenceRuleObject(
    val start: Possible<ISO8601Timestamp>? = null,
    val end: Possible<ISO8601Timestamp>? = null,
    val frequency: Possible<Int>? = null,
    val interval: Possible<Int>? = null,
    @SerialName("by_weekday") val byWeekday: Possible<List<Int>>? = null,
    @SerialName("by_n_weekday") val byNWeekday: Possible<List<RecurrenceRuleNWeekdayObject>>? = null,
    @SerialName("by_month") val byMonth: Possible<List<Int>>? = null,
    @SerialName("by_month_day") val byMonthDay: Possible<List<Int>>? = null,
    @SerialName("by_year_day") val byYearDay: Possible<List<Int>>? = null,
    val count: Possible<Int>? = null
)

@Serializable
data class GuildScheduledEventUserObject(
    val guildScheduledEventId: Possible<Snowflake>? = null,
    val user: Possible<UserObject>? = null,
    val member: Possible<GuildMemberObject>? = null
)

@Serializable
data class GuildTemplateObject(
    val code: Possible<String>? = null,
    val name: Possible<String>? = null,
    val description: Possible<String>? = null,
    @SerialName("usage_count") val usageCount: Possible<Int>? = null,
    @SerialName("creator_id") val creatorId: Possible<Snowflake>? = null,
    val creator: Possible<UserObject>? = null,
    @SerialName("created_at") val createdAt: Possible<ISO8601Timestamp>? = null,
    @SerialName("updated_at") val updatedAt: Possible<ISO8601Timestamp>? = null,
    @SerialName("source_guild_id") val sourceGuildId: Possible<Snowflake>? = null,
    @SerialName("serialized_source_guild") val serializedSourceGuild: Possible<GuildObject>? = null,
    @SerialName("is_dirty") val isDirty: Possible<Boolean>? = null
)

@Serializable
data class GuildWelcomeScreenChannelObject(
    @SerialName("channel_id") val channelId: Possible<Snowflake>? = null,
    val description: Possible<String>? = null,
    @SerialName("emoji_id") val emojiId: Possible<Snowflake>? = null,
    @SerialName("emoji_name") val emojiName: Possible<String>? = null
)

@Serializable
data class GuildWelcomeScreenObject(
    val description: Possible<String>? = null,
    @SerialName("welcome_channels") val welcomeChannels: Possible<List<GuildWelcomeScreenChannelObject>>? = null
)

@Serializable
data class GuildWidgetObject(
    val id: Possible<Snowflake>? = null,
    val name: Possible<String>? = null,
    @SerialName("instant_invite") val instantInvite: Possible<String>? = null,
    val channels: Possible<List<ChannelObject>>? = null,
    val members: Possible<List<GuildMemberObject>>? = null,
    @SerialName("presence_count") val presenceCount: Possible<Int>? = null
)

@Serializable
data class GuildWidgetSettingsObject(
    val enabled: Possible<Boolean>? = null,
    @SerialName("channel_id") val channelId: Possible<Snowflake>? = null
)

@Serializable
data class IntegrationAccountObject(
    val id: Possible<String>? = null,
    val name: Possible<String>? = null
)

@Serializable
data class IntegrationApplicationObject(
    val id: Possible<Snowflake>? = null,
    val name: Possible<String>? = null,
    val icon: Possible<String>? = null,
    val description: Possible<String>? = null,
    val bot: Possible<UserObject>? = null
)

@Serializable
data class InteractionDataObject(
    val id: Possible<Snowflake>? = null,
    val name: Possible<String>? = null,
    val type: Possible<Int>? = null,
    val resolved: Possible<ResolvedDataObject>? = null,
    val options: Possible<List<ApplicationCommandInteractionDataOptionObject>>? = null,
    @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
    @SerialName("target_id") val targetId: Possible<Snowflake>? = null
)

@Serializable
data class InteractionObject(
    val id: Possible<Snowflake>? = null,
    @SerialName("application_id") val applicationId: Possible<Snowflake>? = null,
    val type: Possible<Int>? = null,
    val data: Possible<InteractionDataObject>? = null,
    val guild: Possible<GuildObject>? = null,
    @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
    val channel: Possible<ChannelObject>? = null,
    @SerialName("channel_id") val channelId: Possible<Snowflake>? = null,
    val member: Possible<GuildMemberObject>? = null,
    val user: Possible<UserObject>? = null,
    val token: Possible<String>? = null,
    val version: Possible<Int>? = null,
    val message: Possible<MessageObject>? = null,
    @SerialName("app_permissions") val appPermissions: Possible<String>? = null,
    val locale: Possible<String>? = null,
    @SerialName("guild_locale") val guildLocale: Possible<String>? = null,
    val entitlements: Possible<List<EntitlementObject>>? = null,
    @SerialName("authorizing_integration_owners") val authorizingIntegrationOwners: Possible<Map<Int, String>>? = null
)

@Serializable
data class InviteObject(
    val type: Possible<Int>? = null,
    val code: Possible<String>? = null,
    val uses: Possible<Int>? = null,
    val guild: Possible<GuildObject>? = null,
    val channel: Possible<ChannelObject>? = null,
    val inviter: Possible<UserObject>? = null,
    @SerialName("target_type") val targetType: Possible<Int>? = null,
    @SerialName("target_user") val targetUser: Possible<UserObject>? = null,
    @SerialName("target_application") val targetApplication: Possible<ApplicationObject>? = null,
    @SerialName("approximate_presence_count") val approximatePresenceCount: Possible<Int>? = null,
    @SerialName("approximate_member_count") val approximateMemberCount: Possible<Int>? = null,
    @SerialName("expires_at") val expiresAt: Possible<ISO8601Timestamp>? = null,
    @SerialName("stage_instance") val stageInstance: Possible<InviteStageInstanceObject>? = null,
    @SerialName("guild_scheduled_event") val guildScheduledEvent: Possible<GuildScheduledEventObject>? = null,
)

@Serializable
data class InviteStageInstanceObject(
    val members: Possible<List<GuildMemberObject>>? = null,
    @SerialName("participation_count") val participationCount: Possible<Int>? = null,
    @SerialName("speaker_count") val speakerCount: Possible<Int>? = null,
    val topic: Possible<String>? = null
)

interface MessageComponentObject {
    val type: Possible<Int>?
    val id: Possible<Int>?
    val customId: Possible<String>?
}

@Serializable
data class ActionRowComponentObject(
    override val type: Possible<Int>? = null,
    override val id: Possible<Int>? = null,
    @SerialName("custom_id") override val customId: Possible<String>? = null,
    val components: Possible<List<MessageComponentObject>>? = null,
) : MessageComponentObject

@Serializable
data class ButtonComponentObject(
    override val type: Possible<Int>? = null,
    override val id: Possible<Int>? = null,
    @SerialName("custom_id") override val customId: Possible<String>? = null,
    val style: Possible<Int>? = null,
    val label: Possible<String>? = null,
    val emoji: Possible<EmojiObject>? = null,
    @SerialName("sku_id") val skuId: Possible<Snowflake>? = null,
    val url: Possible<String>? = null,
    val disabled: Possible<Boolean>? = null,
) : MessageComponentObject

@Serializable
data class StringSelectComponentObject(
    override val type: Possible<Int>? = null,
    override val id: Possible<Int>? = null,
    @SerialName("custom_id") override val customId: Possible<String>? = null,
    val options: Possible<List<SelectOptionObject>>? = null,
    val placeholder: Possible<String>? = null,
    @SerialName("min_values") val minValues: Possible<Int>? = null,
    val disabled: Possible<Boolean>? = null,
) : MessageComponentObject

@Serializable
data class SelectOptionObject(
    val label: Possible<String>? = null,
    val value: Possible<String>? = null,
    val description: Possible<String>? = null,
    val emoji: Possible<EmojiObject>? = null,
    val default: Possible<Boolean>? = null,
)

@Serializable
data class TextInputComponentObject(
    override val type: Possible<Int>? = null,
    override val id: Possible<Int>? = null,
    @SerialName("custom_id") override val customId: Possible<String>? = null,
    val style: Possible<Int>? = null,
    val label: Possible<String>? = null,
    @SerialName("min_length") val minLength: Possible<Int>? = null,
    @SerialName("max_length") val maxLength: Possible<Int>? = null,
    val required: Possible<Boolean>? = null,
    val value: Possible<String>? = null,
    val placeholder: Possible<String>? = null,
) : MessageComponentObject

@Serializable
data class UserSelectComponentObject(
    override val type: Possible<Int>? = null,
    override val id: Possible<Int>? = null,
    @SerialName("custom_id") override val customId: Possible<String>? = null,
    val placeholder: Possible<String>? = null,
    @SerialName("default_values") val defaultValues: Possible<List<SelectDefaultValueObject>>? = null,
    @SerialName("min_values") val minValues: Possible<Int>? = null,
    @SerialName("max_values") val maxValues: Possible<Int>? = null,
    val disabled: Possible<Boolean>? = null,
) : MessageComponentObject

@Serializable
data class SelectDefaultValueObject(
    val id: Possible<Snowflake>? = null,
    val type: Possible<String>? = null
)

@Serializable
data class RoleSelectComponentObject(
    override val type: Possible<Int>? = null,
    override val id: Possible<Int>? = null,
    @SerialName("custom_id") override val customId: Possible<String>? = null,
    val placeholder: Possible<String>? = null,
    @SerialName("default_values") val defaultValues: Possible<List<SelectDefaultValueObject>>? = null,
    @SerialName("min_values") val minValues: Possible<Int>? = null,
    @SerialName("max_values") val maxValues: Possible<Int>? = null,
    val disabled: Possible<Boolean>? = null,
) : MessageComponentObject

@Serializable
data class MentionableSelectComponentObject(
    override val type: Possible<Int>? = null,
    override val id: Possible<Int>? = null,
    @SerialName("custom_id") override val customId: Possible<String>? = null,
    val placeholder: Possible<String>? = null,
    @SerialName("default_values") val defaultValues: Possible<List<SelectDefaultValueObject>>? = null,
    @SerialName("min_values") val minValues: Possible<Int>? = null,
    @SerialName("max_values") val maxValues: Possible<Int>? = null,
    val disabled: Possible<Boolean>? = null,
) : MessageComponentObject

@Serializable
data class ChannelSelectComponentObject(
    override val type: Possible<Int>? = null,
    override val id: Possible<Int>? = null,
    @SerialName("custom_id") override val customId: Possible<String>? = null,
    @SerialName("channel_types") val channelTypes: Possible<List<Int>>? = null,
    val placeholder: Possible<String>? = null,
    @SerialName("default_values") val defaultValues: Possible<List<SelectDefaultValueObject>>? = null,
    @SerialName("min_values") val minValues: Possible<Int>? = null,
    @SerialName("max_values") val maxValues: Possible<Int>? = null,
    val disabled: Possible<Boolean>? = null
) : MessageComponentObject

@Serializable
data class SectionComponentObject(
    override val type: Possible<Int>? = null,
    override val id: Possible<Int>? = null,
    @SerialName("custom_id") override val customId: Possible<String>? = null,
    val components: Possible<List<TextDisplayComponentObject>>? = null,
    val accessory: Possible<MessageComponentObject>? = null,
) : MessageComponentObject

@Serializable
data class TextDisplayComponentObject(
    override val type: Possible<Int>? = null,
    override val id: Possible<Int>? = null,
    @SerialName("custom_id") override val customId: Possible<String>? = null,
    val content: Possible<String>? = null
) : MessageComponentObject

@Serializable
data class ThumbnailComponentObject(
    override val type: Possible<Int>? = null,
    override val id: Possible<Int>? = null,
    @SerialName("custom_id") override val customId: Possible<String>? = null,
    val media: Possible<UnfurledMediaItemObject>? = null,
    val description: Possible<String>? = null,
    val spoiler: Possible<Boolean>? = null,
) : MessageComponentObject

@Serializable
data class UnfurledMediaItemObject(
    val url: Possible<String>? = null,
    @SerialName("proxy_url") val proxyUrl: Possible<String>? = null,
    val height: Possible<Int>? = null,
    val width: Possible<Int>? = null,
    @SerialName("content_type") val contentType: Possible<String>? = null,
)

@Serializable
data class FileComponentObject(
    override val type: Possible<Int>? = null,
    override val id: Possible<Int>? = null,
    @SerialName("custom_id") override val customId: Possible<String>? = null,
    val file: Possible<UnfurledMediaItemObject>? = null,
    val spoiler: Possible<Boolean>? = null
) : MessageComponentObject

@Serializable
data class SeparatorComponentObject(
    override val type: Possible<Int>? = null,
    override val id: Possible<Int>? = null,
    @SerialName("custom_id") override val customId: Possible<String>? = null,
    val divider: Possible<Boolean>? = null,
    val spacing: Possible<Int>? = null,
) : MessageComponentObject

@Serializable
data class ContainerComponentObject(
    override val type: Possible<Int>,
    override val id: Possible<Int>? = null,
    @SerialName("custom_id") override val customId: Possible<String>? = null,
    val components: Possible<List<MessageComponentObject>>? = null,
    @SerialName("accent_color") val accentColor: Possible<Int>? = null,
    val spoiler: Possible<Boolean>? = null,
) : MessageComponentObject

@Serializable
data class MessageActivityObject(
    val type: Possible<Int>? = null,
    @SerialName("party_id") val partyId: Possible<String>? = null
)

@Serializable
data class MessageCallObject(
    val participants: Possible<List<Snowflake>>? = null,
    val endedTimestamp: Possible<ISO8601Timestamp>? = null
)

@Serializable
abstract class MessageInteractionMetadataObject {
    abstract val id: Possible<Snowflake>?
    abstract val type: Possible<Int>?
    abstract val user: Possible<UserObject>?

    @SerialName("authorizing_integration_owners")
    abstract val authorizingIntegrationOwners: Possible<Map<Int, Snowflake>>?
    
    @SerialName("original_response_message_id")
    abstract val originalResponseMessageId: Possible<Snowflake>?
}

@Serializable
data class ApplicationCommandInteractionMetadataObject(
    override val id: Possible<Snowflake>? = null,
    override val type: Possible<Int>? = null,
    override val user: Possible<UserObject>? = null,
    override val authorizingIntegrationOwners: Possible<Map<Int, Snowflake>>? = null,
    override val originalResponseMessageId: Possible<Snowflake>? = null,
    @SerialName("target_user") val targetUser: Possible<UserObject>? = null,
    @SerialName("target_message_id") val targetMessageId: Possible<Snowflake>? = null,
) : MessageInteractionMetadataObject()

@Serializable
data class MessageComponentInteractionMetadataObject(
    override val id: Possible<Snowflake>? = null,
    override val type: Possible<Int>? = null,
    override val user: Possible<UserObject>? = null,
    override val authorizingIntegrationOwners: Possible<Map<Int, Snowflake>>? = null,
    override val originalResponseMessageId: Possible<Snowflake>? = null,
    @SerialName("interacted_message_id") val interactedMessageId: Possible<Snowflake>? = null,
) : MessageInteractionMetadataObject()

@Serializable
data class ModalSubmitInteractionMetadataObject(
    override val id: Possible<Snowflake>? = null,
    override val type: Possible<Int>? = null,
    override val user: Possible<UserObject>? = null,
    override val authorizingIntegrationOwners: Possible<Map<Int, Snowflake>>? = null,
    override val originalResponseMessageId: Possible<Snowflake>? = null,
    @SerialName("trigger_interaction_metadata") val triggerInteractionMetadata: Possible<MessageInteractionMetadataObject>? = null,
) : MessageInteractionMetadataObject()

@Serializable
data class MessageObject(
    val id: Possible<Snowflake>? = null,
    val channelId: Possible<Snowflake>? = null,
    val author: Possible<UserObject>? = null,
    val content: Possible<String>? = null,
    val timestamp: Possible<ISO8601Timestamp>? = null,
    @SerialName("edited_timestamp") val editedTimestamp: Possible<ISO8601Timestamp>? = null,
    val tts: Possible<Boolean>? = null,
    @SerialName("mention_everyone") val mentionEveryone: Possible<Boolean>? = null,
    val mentions: Possible<List<UserObject>>? = null,
    @SerialName("mention_roles") val mentionRoles: Possible<List<RoleObject>>? = null,
    @SerialName("mention_channels") val mentionChannels: Possible<List<ChannelMentionObject>>? = null,
    val attachments: Possible<List<AttachmentObject>>? = null,
    val embeds: Possible<List<EmbedObject>>? = null,
    val reactions: Possible<List<ReactionObject>>? = null,
    val nonce: Possible<String>? = null,
    val pinned: Possible<Boolean>? = null,
    @SerialName("webhook_id") val webhookId: Possible<Snowflake>? = null,
    val type: Possible<Int>? = null,
    val activity: Possible<MessageActivityObject>? = null,
    val application: Possible<ApplicationObject>? = null,
    @SerialName("application_id") val applicationId: Possible<Snowflake>? = null,
    val flags: Possible<Int>? = null,
    @SerialName("message_reference") val messageReference: Possible<MessageReferenceObject>? = null,
    @SerialName("message_snapshots") val messageSnapshots: Possible<MessageSnapshotObject>? = null,
    @SerialName("referenced_message") val referencedMessage: Possible<MessageReferenceObject>? = null,
    @SerialName("interaction_metadata") val interactionMetadata: Possible<MessageInteractionMetadataObject>? = null,
    val interaction: Possible<InteractionObject>? = null,
    val thread: Possible<ChannelObject>? = null,
    val components: Possible<List<MessageComponentObject>>? = null,
    @SerialName("sticker_items") val stickerItems: Possible<List<StickerObject>>? = null,
    val stickers: Possible<List<StickerObject>>? = null,
    val position: Possible<Int>? = null,
    @SerialName("role_subscription_data") val roleSubscriptionData: Possible<RoleSubscriptionDataObject>? = null,
    val resolved: Possible<ResolvedDataObject>? = null,
    val poll: Possible<PollObject>? = null,
    val call: Possible<MessageCallObject>? = null,
    @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
    val member: Possible<GuildMemberObject>? = null
)

@Serializable
data class MessageReferenceObject(
    val type: Possible<Int>? = null,
    @SerialName("message_id") val messageId: Possible<Snowflake>? = null,
    @SerialName("channel_id") val channelId: Possible<Snowflake>? = null,
    @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
    @SerialName("fail_if_not_exists") val failIfNotExists: Possible<Boolean>? = null
)

@Serializable
data class MessageSnapshotObject(
    val message: Possible<MessageObject>? = null
)

@Serializable
data class PollAnswerCountObject(
    val id: Possible<Int>? = null,
    val count: Possible<Int>? = null,
    @SerialName("me_voted") val meVoted: Possible<Boolean>? = null
)

@Serializable
data class PollAnswerObject(
    @SerialName("answer_id") val answerId: Possible<Int>? = null,
    @SerialName("poll_media") val pollMedia: Possible<PollMediaObject>? = null,
)

@Serializable
data class PollMediaObject(
    val text: Possible<String>? = null,
    val emoji: Possible<EmojiObject>? = null
)

@Serializable
data class PollObject(
    val question: Possible<PollMediaObject>? = null,
    val answers: Possible<List<PollAnswerObject>>? = null,
    val expiry: Possible<ISO8601Timestamp>? = null,
    @SerialName("allow_multiselect") val allowMultiselect: Possible<Boolean>? = null,
    @SerialName("layout_type") val layoutType: Possible<Int>? = null,
    val results: Possible<PollResultsObject>? = null
)

@Serializable
data class PollResultsObject(
    @SerialName("is_finalized") val isFinalized: Possible<Boolean>? = null,
    @SerialName("answer_counts") val answerCounts: Possible<List<PollAnswerCountObject>>? = null
)

@Serializable
data class ReactionCountDetailsObject(
    val burst: Possible<Int>? = null,
    val normal: Possible<Int>? = null
)

@Serializable
data class ReactionObject(
    val count: Possible<Int>? = null,
    @SerialName("count_details") val countDetails: Possible<ReactionCountDetailsObject>? = null,
    val me: Possible<Boolean>? = null,
    @SerialName("me_burst") val meBurst: Possible<Boolean>? = null,
    val emoji: Possible<EmojiObject>? = null,
    @SerialName("burst_colors") val burstColors: Possible<List<Int>>? = null
)

@Serializable
data class RecurrenceRuleNWeekdayObject(
    val n: Possible<Int>? = null,
    val day: Possible<Int>? = null
)

@Serializable
data class ResolvedDataObject(
    val users: Possible<Map<Snowflake, UserObject>>? = null,
    val members: Possible<Map<Snowflake, GuildMemberObject>>? = null,
    val roles: Possible<Map<Snowflake, RoleObject>>? = null,
    val channels: Possible<Map<Snowflake, ChannelObject>>? = null,
    val messages: Possible<Map<Snowflake, MessageObject>>? = null,
    val attachments: Possible<Map<Snowflake, AttachmentObject>>? = null,
)

@Serializable
data class RoleObject(
    val id: Possible<Snowflake>? = null,
    val name: Possible<String>? = null,
    val color: Possible<Int>? = null,
    val hoist: Possible<Boolean>? = null,
    val icon: Possible<String>? = null,
    @SerialName("unicode_emoji") val unicodeEmoji: Possible<String>? = null,
    val position: Possible<Int>? = null,
    val permissions: Possible<PermissionSet>? = null,
    val managed: Possible<Boolean>? = null,
    val mentionable: Possible<Boolean>? = null,
    val tags: Possible<RoleTagsObject>? = null,
    val flags: Possible<Int>? = null
)

@Serializable
data class RoleSubscriptionDataObject(
    @SerialName("role_subscription_listing_id") val roleSubscriptionListingId: Possible<Snowflake>? = null,
    @SerialName("tier_name") val tierName: Possible<String>? = null,
    @SerialName("total_months_subscribed") val totalMonthsSubscribed: Possible<Int>? = null,
    @SerialName("is_renewal") val isRenewal: Possible<Boolean>? = null
)

@Serializable
data class RoleTagsObject(
    val botId: Possible<Snowflake>? = null,
    @SerialName("integration_id") val integrationId: Possible<Snowflake>? = null,
    @SerialName("premium_subscriber") val premiumSubscriber: Possible<Boolean>? = null,
    @SerialName("subscription_listing_id") val subscriptionListingId: Possible<Snowflake>? = null,
    @SerialName("available_for_purchase") val availableForPurchase: Possible<Boolean>? = null,
    @SerialName("guild_connections") val guildConnections: Possible<Boolean>? = null
)

@Serializable
data class SkuObject(
    val id: Possible<Snowflake>? = null,
    val type: Possible<Int>? = null,
    @SerialName("application_id") val applicationId: Possible<Snowflake>? = null,
    val name: Possible<String>? = null,
    val slug: Possible<String>? = null,
    val flags: Possible<Int>? = null
)

@Serializable
data class SoundboardSoundObject(
    val name: Possible<String>? = null,
    @SerialName("sound_id") val soundId: Possible<Snowflake>? = null,
    val volume: Possible<Double>? = null,
    @SerialName("emoji_id") val emojiId: Possible<Snowflake>? = null,
    @SerialName("emoji_name") val emojiName: Possible<String>? = null,
    @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
    val available: Possible<Boolean>? = null,
    val user: Possible<UserObject>? = null
)

@Serializable
data class StageInstanceObject(
    val id: Possible<Snowflake>? = null,
    @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
    @SerialName("channel_id") val channelId: Possible<Snowflake>? = null,
    val topic: Possible<String>? = null,
    @SerialName("privacy_level") val privacyLevel: Possible<Int>? = null,
    @SerialName("discoverable_disabled") val discoverableDisabled: Possible<Boolean>? = null,
    @SerialName("guild_scheduled_event_id") val guildScheduledEventId: Possible<Snowflake>? = null,
)

@Serializable
data class StickerObject(
    val id: Possible<Snowflake>? = null,
    @SerialName("pack_id") val packId: Possible<Snowflake>? = null,
    val name: Possible<String>? = null,
    val description: Possible<String>? = null,
    val tags: Possible<String>? = null,
    val type: Possible<Int>? = null,
    @SerialName("format_type") val formatType: Possible<Int>? = null,
    val available: Possible<Boolean>? = null,
    @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
    val user: Possible<UserObject>? = null,
    @SerialName("sort_value") val sortValue: Possible<Int>? = null,
)

@Serializable
data class StickerPackObject(
    val id: Possible<Snowflake>? = null,
    val stickers: Possible<List<StickerObject>>? = null,
    val name: Possible<String>? = null,
    @SerialName("sku_id") val skuId: Possible<Snowflake>? = null,
    @SerialName("cover_sticker_id") val coverStickerId: Possible<Snowflake>? = null,
    val description: Possible<String>? = null,
    @SerialName("banner_asset_id") val bannerAssetId: Possible<Snowflake>? = null
)

@Serializable
data class SubscriptionObject(
    val id: Possible<Snowflake>? = null,
    @SerialName("user_id") val userId: Possible<Snowflake>? = null,
    @SerialName("sku_ids") val skuIds: Possible<List<Snowflake>>? = null,
    @SerialName("entitlement_ids") val entitlementIds: Possible<List<Snowflake>>? = null,
    @SerialName("renewal_sku_ids") val renewalSkuIds: Possible<List<Snowflake>>? = null,
    @SerialName("current_period_start") val currentPeriodStart: Possible<ISO8601Timestamp>? = null,
    @SerialName("current_period_end") val currentPeriodEnd: Possible<ISO8601Timestamp>? = null,
    val status: Possible<Int>? = null,
    @SerialName("canceled_at") val canceledAt: Possible<ISO8601Timestamp>? = null,
    val country: Possible<String>? = null
)

@Serializable
data class TeamMemberObject(
    @SerialName("membership_state") val membershipState: Possible<Int>? = null,
    @SerialName("team_id") val teamId: Possible<Int>? = null,
    val user: Possible<UserObject>? = null,
    val role: Possible<String>? = null
)

@Serializable
data class TeamObject(
    val icon: Possible<String>? = null,
    val id: Possible<Snowflake>? = null,
    val members: Possible<List<TeamMemberObject>>? = null,
    val name: Possible<String>? = null,
    @SerialName("owner_user_id") val ownerUserId: Possible<Snowflake>? = null,
)

@Serializable
data class ThreadMemberObject(
    val id: Possible<Snowflake>? = null,
    @SerialName("user_id") val userId: Possible<Snowflake>? = null,
    @SerialName("join_timestamp") val joinTimestamp: Possible<ISO8601Timestamp>? = null,
    val flags: Possible<Int>? = null,
    val member: Possible<GuildMemberObject>? = null,
    @SerialName("guild_id") val guildId: Possible<Snowflake>? = null
)

@Serializable
data class ThreadMetadataObject(
    val archived: Possible<Boolean>? = null,
    @SerialName("auto_archive_duration") val autoArchiveDuration: Possible<Int>? = null,
    @SerialName("archive_timestamp") val archiveTimestamp: Possible<ISO8601Timestamp>? = null,
    val locked: Possible<Boolean>? = null,
    val invitable: Possible<Boolean>? = null,
    @SerialName("create_timestamp") val createTimestamp: Possible<ISO8601Timestamp>? = null,
)

@Serializable
data class UpdatePresenceObject(
    val since: Possible<Int>? = null,
    val activities: Possible<ActivityObject>? = null,
    val status: Possible<String>? = null,
    val afk: Possible<Boolean>? = null
)

@Serializable
data class UserApplicationRoleConnectionObject(
    @SerialName("platform_name") val platformName: Possible<String>? = null,
    @SerialName("platform_username") val platformUsername: Possible<String>? = null,
    val metadata: Possible<Map<String, ApplicationRoleConnectionMetadataObject>>? = null
)

@Serializable
data class UserConnectionObject(
    val id: Possible<String>? = null,
    val name: Possible<String>? = null,
    val type: Possible<String>? = null,
    val revoked: Possible<Boolean>? = null,
    val integrations: Possible<List<GuildIntegrationObject>>? = null,
    val verified: Possible<Boolean>? = null,
    @SerialName("friend_sync") val friendSync: Possible<Boolean>? = null,
    @SerialName("show_activity") val showActivity: Possible<Boolean>? = null,
    @SerialName("two_way_link") val twoWayLink: Possible<Boolean>? = null,
    val visibility: Possible<Int>? = null
)

@Serializable
data class UserObject(
    val id: Possible<Snowflake>? = null,
    val username: Possible<String>? = null,
    val discriminator: Possible<String>? = null,
    @SerialName("global_name") val globalName: Possible<String>? = null,
    val avatar: Possible<String>? = null,
    val bot: Possible<Boolean>? = null,
    val system: Possible<Boolean>? = null,
    @SerialName("mfa_enabled") val mfaEnabled: Possible<Boolean>? = null,
    val banner: Possible<String>? = null,
    @SerialName("accent_color") val accentColor: Possible<Int>? = null,
    val locale: Possible<String>? = null,
    val verified: Possible<Boolean>? = null,
    val email: Possible<String>? = null,
    val flags: Possible<Int>? = null,
    @SerialName("premium_type") val premiumType: Possible<Int>? = null,
    @SerialName("public_flags") val publicFlags: Possible<Int>? = null,
    @SerialName("avatar_decoration_data") val avatarDecorationData: Possible<AvatarDecorationDataObject>? = null
)

@Serializable
data class VoiceRegionObject(
    @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
    @SerialName("channel_id") val channelId: Possible<Snowflake>? = null,
    @SerialName("user_id") val userId: Possible<Snowflake>? = null,
    val member: Possible<GuildMemberObject>? = null,
    @SerialName("session_id") val sessionId: Possible<String>? = null,
    val deaf: Possible<Boolean>? = null,
    val mute: Possible<Boolean>? = null,
    @SerialName("self_deaf") val selfDeaf: Possible<Boolean>? = null,
    @SerialName("self_mute") val selfMute: Possible<Boolean>? = null,
    @SerialName("self_stream") val selfStream: Possible<Boolean>? = null,
    @SerialName("self_video") val selfVideo: Possible<Boolean>? = null,
    val suppress: Possible<Boolean>? = null,
    @SerialName("request_to_speak_timestamp") val requestToSpeakTimestamp: Possible<ISO8601Timestamp>? = null
)

@Serializable
data class VoiceStateObject(
    @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
    @SerialName("channel_id") val channelId: Possible<Snowflake>? = null,
    @SerialName("user_id") val userId: Possible<Snowflake>? = null,
    val member: Possible<GuildMemberObject>? = null,
    @SerialName("session_id") val sessionId: Possible<String>? = null,
    val deaf: Possible<Boolean>? = null,
    val mute: Possible<Boolean>? = null,
    @SerialName("self_deaf") val selfDeaf: Possible<Boolean>? = null,
    @SerialName("self_mute") val selfMute: Possible<Boolean>? = null,
    @SerialName("self_stream") val selfStream: Possible<Boolean>? = null,
    @SerialName("self_video") val selfVideo: Possible<Boolean>? = null,
    val suppress: Possible<Boolean>? = null,
    @SerialName("request_to_speak_timestamp") val requestToSpeakTimestamp: Possible<ISO8601Timestamp>? = null
)

@Serializable
data class WebhookObject(
    val id: Possible<Snowflake>? = null,
    val type: Possible<Int>? = null,
    @SerialName("guild_id") val guildId: Possible<Snowflake>? = null,
    @SerialName("channel_id") val channelId: Possible<Snowflake>? = null,
    val user: Possible<UserObject>? = null,
    val name: Possible<String>? = null,
    val avatar: Possible<ImageData>? = null,
    val token: Possible<String>? = null,
    @SerialName("application_id") val applicationId: Possible<Snowflake>? = null,
    @SerialName("source_guild") val sourceGuild: Possible<GuildObject>? = null,
    @SerialName("source_channel") val sourceChannel: Possible<ChannelObject>? = null,
    val url: Possible<String>? = null
)