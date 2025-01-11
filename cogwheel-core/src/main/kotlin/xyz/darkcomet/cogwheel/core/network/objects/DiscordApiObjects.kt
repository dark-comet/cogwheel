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
    @SerialName("large_image") val largeImage: Optional<String>? = null,
    @SerialName("large_text") val largeText: Optional<String>? = null,
    @SerialName("small_image") val smallImage: Optional<String>? = null,
    @SerialName("small_text") val smallText: Optional<String>? = null,
)

@Serializable
data class ActivityButtonObject(
    val label: Optional<String>? = null,
    val url: Optional<String>? = null
)

@Serializable
data class ActivityEmojiObject(
    val name: Optional<String>? = null,
    val id: Optional<Snowflake>? = null,
    val animated: Optional<Boolean>? = null
)

@Serializable
data class ActivityLocationObject(
    val id: Optional<String>? = null,
    val kind: Optional<String>? = null,
    @SerialName("channel_id") val channelId: Optional<Snowflake>? = null,
    @SerialName("guild_id") val guildId: Optional<Snowflake>? = null
)

@Serializable
data class ActivityObject(
    val name: Optional<String>? = null,
    val type: Optional<Int>? = null,
    val url: Optional<String>? = null,
    @SerialName("created_at") val createdAt: Optional<Int>? = null,
    val timestamps: Optional<ActivityTimestampsObject>? = null,
    @SerialName("application_id") val applicationId: Optional<Snowflake>? = null,
    val details: Optional<String>? = null,
    val state: Optional<String>? = null,
    val emoji: Optional<ActivityEmojiObject>? = null,
    val party: Optional<ActivityPartyObject>? = null,
    val assets: Optional<ActivityAssetsObject>? = null,
    val secrets: Optional<ActivitySecretsObject>? = null,
    val instance: Optional<Boolean>? = null,
    val flags: Optional<Int>? = null,
    val buttons: Optional<List<ActivityButtonObject>>? = null
)

@Serializable
data class ActivityPartyObject(
    val id: Optional<String>? = null,
    val size: Optional<List<Int>>? = null
)

@Serializable
data class ActivitySecretsObject(
    val join: Optional<String>? = null,
    val spectate: Optional<String>? = null,
    val match: Optional<String>? = null
)

@Serializable
data class ActivityTimestampsObject(
    val start: Optional<Long>? = null,
    val end: Optional<Long>? = null
)

@Serializable
data class AllowedMentionsObject(
    val parse: Optional<List<String>>? = null,
    val roles: Optional<List<Snowflake>>? = null,
    val users: Optional<List<Snowflake>>? = null,
    @SerialName("replied_user") val repliedUser: Optional<Boolean>? = null
)

@Serializable
data class ApplicationActivityInstanceObject(
    @SerialName("application_id") val applicationId: Optional<Snowflake>? = null,
    @SerialName("instance_id") val instanceId: Optional<String>? = null,
    @SerialName("launch_id") val launchId: Optional<Snowflake>? = null,
    val location: Optional<ActivityLocationObject>? = null,
    val users: Optional<List<Snowflake>>? = null,
)

@Serializable(with = ApplicationCommandInteractionDataOptionObject.Serializer::class)
data class ApplicationCommandInteractionDataOptionObject(
    val name: Optional<String>? = null,
    val type: Optional<Int>? = null,
    val value: Optional<Any>? = null,
    val options: Optional<List<ApplicationCommandInteractionDataOptionObject>>? = null,
    val focused: Optional<Boolean>? = null
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
    val id: Optional<Snowflake>? = null,
    val type: Optional<Int>? = null,
    @SerialName("application_id") val applicationId: Optional<Snowflake>? = null,
    @SerialName("guild_id") val guildId: Optional<Snowflake>? = null,
    val name: Optional<String>? = null,
    @SerialName("name_localizations") val nameLocalizations: Optional<Map<String, String>>? = null,
    val description: Optional<String>? = null,
    @SerialName("description_localizations") val descriptionLocalizations: Optional<Map<String, String>>? = null,
    val options: Optional<List<ApplicationCommandOptionObject>>? = null,
    @SerialName("default_member_permissions") val defaultMemberPermissions: Optional<PermissionSet>? = null,
    @SerialName("dm_permission") val dmPermission: Optional<Boolean>? = null,
    @SerialName("default_permission") val defaultPermission: Optional<Boolean>? = null,
    val nsfw: Optional<Boolean>? = null,
    @SerialName("integration_types") val integrationTypes: Optional<List<Int>>? = null,
    val contexts: Optional<List<Int>>? = null,
    val version: Optional<Snowflake>? = null,
    val handler: Optional<Int>? = null
)

@Serializable
abstract class ApplicationCommandOptionChoiceObject {
    abstract val name: Optional<String>?

    @SerialName("name_localizations")
    abstract val nameLocalizations: Optional<Map<String, String>>?
}

@Serializable
class ApplicationCommandOptionStringChoiceObject(
    override val name: Optional<String>? = null,
    override val nameLocalizations: Optional<Map<String, String>>? = null,
    val value: Optional<String>? = null
) : ApplicationCommandOptionChoiceObject()

@Serializable
class ApplicationCommandOptionIntegerChoiceObject(
    override val name: Optional<String>? = null,
    override val nameLocalizations: Optional<Map<String, String>>? = null,
    val value: Optional<Int>? = null
) : ApplicationCommandOptionChoiceObject()

@Serializable
class ApplicationCommandOptionDoubleChoiceObject(
    override val name: Optional<String>? = null,
    override val nameLocalizations: Optional<Map<String, String>>? = null,
    val value: Optional<Double>? = null
) : ApplicationCommandOptionChoiceObject()

@Serializable
data class ApplicationCommandOptionObject(
    val type: Optional<Int>? = null,
    val name: Optional<String>? = null,
    @SerialName("name_localizations") val nameLocalizations: Optional<Map<String, String>>? = null,
    val description: Optional<String>? = null,
    @SerialName("description_localizations") val descriptionLocalizations: Optional<Map<String, String>>? = null,
    val required: Optional<Boolean>? = null,
    val choices: Optional<List<ApplicationCommandOptionChoiceObject>>? = null,
    val options: Optional<List<ApplicationCommandOptionObject>>? = null,
    @SerialName("channel_types") val channelTypes: Optional<List<Int>>? = null,
    @SerialName("min_value") val minValue: Optional<Double>? = null,
    @SerialName("max_value") val maxValue: Optional<Double>? = null,
    @SerialName("min_length") val minLength: Optional<Int>? = null,
    @SerialName("max_length") val maxLength: Optional<Int>? = null,
    val autocomplete: Optional<Boolean>? = null
)

@Serializable
data class ApplicationCommandPermissionObject(
    val id: Optional<Snowflake>? = null,
    val type: Optional<Int>? = null,
    val permission: Optional<Boolean>? = null
)

@Serializable
data class ApplicationInstallParamsObject(
    val scopes: Optional<List<String>>? = null,
    val permissions: Optional<String>? = null
)

@Serializable
data class ApplicationIntegrationTypeConfigurationObject(
    @SerialName("oauth2_install_params") val oauth2InstallParams: Optional<ApplicationInstallParamsObject>? = null
)

@Serializable
data class ApplicationObject(
    val id: Optional<Snowflake>? = null,
    val name: Optional<String>? = null,
    val icon: Optional<String>? = null,
    val description: Optional<String>? = null,
    @SerialName("rpc_origins") val rpcOrigins: Optional<List<String>>? = null,
    @SerialName("bot_public") val botPublic: Optional<Boolean?>? = null,
    @SerialName("bot_require_code_grant") val botRequireCodeGrant: Optional<Boolean>? = null,
    val bot: Optional<UserObject>? = null,
    @SerialName("terms_of_service_url") val termsOfServiceUrl: Optional<String>? = null,
    @SerialName("privacy_policy_url") val privacyPolicyUrl: Optional<String>? = null,
    @SerialName("owner") val owner: Optional<UserObject>? = null,
    @SerialName("verify_key") val verifyKey: Optional<String>? = null,
    val team: Optional<TeamObject>? = null,
    @SerialName("guild_id") val guildId: Optional<Snowflake>? = null,
    val guild: Optional<GuildObject>? = null,
    @SerialName("primary_sku_id") val primarySkuId: Optional<Snowflake>? = null,
    val slug: Optional<String>? = null,
    @SerialName("cover_image") val coverImage: Optional<String>? = null,
    val flags: Optional<Int>? = null,
    @SerialName("approximate_guild_count") val approximateGuildCount: Optional<Int>? = null,
    @SerialName("approximate_user_install_count") val approximateUserInstallCount: Optional<Int>? = null,
    @SerialName("redirect_uris") val redirectUris: Optional<List<String>>? = null,
    @SerialName("interactions_endpoint_url") val interactionsEndpointUrl: Optional<String>? = null,
    @SerialName("role_connections_verification_url") val roleConnectionsVerificationUrl: Optional<String>? = null,
    @SerialName("event_webhooks_url") val eventWebhooksUrl: Optional<String>? = null,
    @SerialName("event_webhooks_status") val eventWebhooksStatus: Optional<Int>? = null,
    @SerialName("event_webhook_types") val eventWebhookTypes: Optional<List<String>>? = null,
    val tags: Optional<List<String>>? = null,
    @SerialName("install_params") val installParams: Optional<ApplicationInstallParamsObject>? = null,
    @SerialName("integration_types_config") val integrationTypesConfig: Optional<Map<String, ApplicationIntegrationTypeConfigurationObject>>? = null,
    @SerialName("custom_install_url") val customInstallUrl: Optional<String>? = null,
)

@Serializable
data class ApplicationRoleConnectionMetadataObject(
    val type: Optional<Int>? = null,
    val key: Optional<String>? = null,
    val name: Optional<String>? = null,
    @SerialName("name_localizations") val nameLocalizations: Optional<Map<String, String>>? = null,
    val description: Optional<String>? = null,
    @SerialName("description_localizations") val descriptionLocalizations: Optional<Map<String, String>>? = null
)

@Serializable
data class AttachmentObject(
    val id: Optional<Snowflake>? = null,
    val filename: Optional<String>? = null,
    val title: Optional<String>? = null,
    val description: Optional<String>? = null,
    @SerialName("content_type") val contentType: Optional<String>? = null,
    val size: Optional<Int>? = null,
    val url: Optional<String>? = null,
    @SerialName("proxy_url") val proxyUrl: Optional<String>? = null,
    val width: Optional<Int>? = null,
    val height: Optional<Int>? = null,
    val ephemeral: Optional<Boolean>? = null,
    @SerialName("duration_secs") val durationSecs: Optional<Float>? = null,
    val waveform: Optional<String>? = null,
    val flags: Optional<Int>? = null,
)

@Serializable(with = AuditLogChangeObject.Serializer::class)
data class AuditLogChangeObject(
    @SerialName("new_value") val newValue: Optional<Any>? = null,
    @SerialName("old_value") val oldValue: Optional<Any>? = null,
    val key: Optional<String>? = null
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
    @SerialName("target_id") val targetId: Optional<String>? = null,
    val changes: Optional<List<AuditLogChangeObject>>? = null,
    val userId: Optional<Snowflake?>? = null,
    val id: Optional<Snowflake>? = null,
    @SerialName("action_type") val actionType: Optional<Int>? = null,
    val options: Optional<AuditLogOptionalAuditEntryInfoObject>? = null,
    val reason: Optional<String>? = null,
    @SerialName("guild_id") val guildId: Optional<Snowflake>? = null
)

@Serializable
data class AuditLogOptionalAuditEntryInfoObject(
    @SerialName("application_id") val applicationId: Optional<Snowflake>? = null,
    @SerialName("auto_moderation_rule_name") val autoModerationRuleName: Optional<String>? = null,
    @SerialName("auto_moderation_rule_trigger_type") val autoModerationRuleTriggerType: Optional<String>? = null,
    @SerialName("channel_id") val channelId: Optional<Snowflake>? = null,
    val count: Optional<String>? = null,
    @SerialName("delete_member_days") val deleteMemberDays: Optional<String>? = null,
    val id: Optional<Snowflake>? = null,
    @SerialName("members_removed") val membersRemoved: Optional<String>? = null,
    @SerialName("message_id") val messageId: Optional<Snowflake>? = null,
    @SerialName("role_name") val roleName: Optional<String>? = null,
    val type: Optional<String>? = null,
    @SerialName("integration_type") val integrationType: Optional<String>? = null,
)

@Serializable
data class AutoModerationActionMetadataObject(
    @SerialName("channel_id") val channelId: Optional<Snowflake>? = null,
    @SerialName("duration_seconds") val durationSeconds: Optional<Int>? = null,
    @SerialName("custom_message") val customMessage: Optional<String>? = null
)

@Serializable
data class AutoModerationActionObject(
    val type: Optional<Int>? = null,
    val metadata: Optional<AutoModerationActionMetadataObject>? = null
)

@Serializable
data class AutoModerationRuleTriggerMetadataObject(
    @SerialName("keyword_filter") val keywordFilter: Optional<List<String>>? = null,
    @SerialName("regex_patterns") val regexPatterns: Optional<List<String>>? = null,
    val presets: Optional<List<Int>>? = null,
    @SerialName("allow_list") val allowList: Optional<List<String>>? = null,
    @SerialName("mention_total_limit") val mentionTotalLimit: Optional<Int>? = null,
    @SerialName("mention_raid_protection_enabled") val mentionRaidProtectionEnabled: Optional<Boolean>? = null
)

@Serializable
data class AvatarDecorationDataObject(
    val asset: Optional<String>? = null,
    @SerialName("sku_id") val skuId: Optional<Snowflake>? = null
)

@Serializable
data class ChannelDefaultReactionObject(
    @SerialName("emoji_id") val emojiId: Optional<String>? = null,
    @SerialName("emoji_name") val emojiName: Optional<String>? = null
)

@Serializable
data class ChannelMentionObject(
    val id: Optional<Snowflake>? = null,
    @SerialName("guild_id") val guildId: Optional<Snowflake>? = null,
    val type: Optional<Int>? = null,
    val name: Optional<String>? = null
)

@Serializable
data class ChannelObject(
    val id: Optional<Snowflake>? = null,
    val type: Optional<Int>? = null,
    @SerialName("guild_id") val guildId: Optional<Snowflake>? = null,
    val position: Optional<Int>? = null,
    @SerialName("permission_overwrites") val permissionOverwrites: Optional<List<ChannelPermissionOverwriteObject>>? = null,
    val name: Optional<String>? = null,
    val topic: Optional<String>? = null,
    val nsfw: Optional<Boolean>? = null,
    @SerialName("last_message_id") val lastMessageId: Optional<String>? = null,
    val bitrate: Optional<Int>? = null,
    @SerialName("user_limit") val userLimit: Optional<Int>? = null,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: Optional<Int>? = null,
    val recipients: Optional<List<UserObject>>? = null,
    val icon: Optional<String>? = null,
    @SerialName("owner_id") val ownerId: Optional<Snowflake>? = null,
    @SerialName("application_id") val applicationId: Optional<Snowflake>? = null,
    val managed: Optional<Boolean>? = null,
    @SerialName("parent_id") val parentId: Optional<Snowflake>? = null,
    @SerialName("last_pin_timestamp") val lastPinTimestamp: Optional<ISO8601Timestamp>? = null,
    @SerialName("rtc_region") val rtcRegion: Optional<String>? = null,
    @SerialName("video_quality_mode") val videoQualityMode: Optional<Int>? = null,
    @SerialName("message_count") val messageCount: Optional<Int>? = null,
    @SerialName("member_count") val memberCount: Optional<Int>? = null,
    @SerialName("thread_metadata") val threadMetadata: Optional<ThreadMetadataObject>? = null,
    @SerialName("thread_member") val member: Optional<ThreadMemberObject>? = null,
    @SerialName("default_auto_archive_duration") val defaultAutoArchiveDuration: Optional<Int>? = null,
    @SerialName("permissions") val permissions: Optional<PermissionSet>? = null,
    val flags: Optional<Int>? = null,
    @SerialName("total_message_sent") val totalMessageSent: Optional<Int>? = null,
    @SerialName("available_tags") val availableTags: Optional<List<ForumTagObject>>? = null,
    @SerialName("applied_tags") val appliedTags: Optional<List<Snowflake>>? = null,
    @SerialName("default_reaction_emoji") val defaultReactionEmoji: Optional<ChannelDefaultReactionObject>? = null,
    @SerialName("default_thread_rate_limit_per_user") val defaultThreadRateLimitPerUser: Optional<Int>? = null,
    @SerialName("default_sort_order") val defaultSortOrder: Optional<Int>? = null,
    @SerialName("default_forum_layout") val defaultForumLayout: Optional<Int>? = null,
    val message: Optional<MessageObject>? = null, // from starting a thread in forum / media channel
)

@Serializable
data class ChannelPermissionOverwriteObject(
    val id: Optional<Snowflake>? = null,
    val type: Optional<Int>? = null,
    val allow: Optional<PermissionSet>? = null,
    val deny: Optional<PermissionSet>? = null
)

@Serializable
data class ClientStatusObject(
    val desktop: Optional<String>? = null,
    val mobile: Optional<String>? = null,
    val web: Optional<String>? = null
)

@Serializable
data class EmbedAuthorObject(
    val name: Optional<String>? = null,
    val url: Optional<String>? = null,
    @SerialName("icon_url") val iconUrl: Optional<String>? = null,
    @SerialName("proxy_icon_url") val proxyIconUrl: Optional<String>? = null,
)

@Serializable
data class EmbedFieldObject(
    val name: Optional<String>? = null,
    val value: Optional<String>? = null,
    val inline: Optional<Boolean>? = null
)

@Serializable
data class EmbedFooterObject(
    val text: Optional<String>? = null,
    @SerialName("icon_url") val iconUrl: Optional<String>? = null,
    @SerialName("proxy_icon_url") val proxyIconUrl: Optional<String>? = null
)

@Serializable
data class EmbedImageObject(
    val url: Optional<String>? = null,
    @SerialName("proxy_url") val proxyUrl: Optional<String>? = null,
    val width: Optional<Int>? = null,
    val height: Optional<Int>? = null
)

@Serializable
data class EmbedObject(
    val title: Optional<String>? = null,
    val type: Optional<String>? = null,
    val description: Optional<String>? = null,
    val url: Optional<String>? = null,
    val timestamp: Optional<ISO8601Timestamp>? = null,
    val color: Optional<Int>? = null,
    val footer: Optional<EmbedFooterObject>? = null,
    val image: Optional<EmbedImageObject>? = null,
    val thumbnail: Optional<EmbedThumbnailObject>? = null,
    val video: Optional<EmbedVideoObject>? = null,
    val provider: Optional<EmbedProviderObject>? = null,
    val author: Optional<EmbedAuthorObject>? = null,
    val fields: Optional<List<EmbedFieldObject>>? = null
)

@Serializable
data class EmbedProviderObject(
    val name: Optional<String>? = null,
    val url: Optional<String>? = null
)

@Serializable
data class EmbedThumbnailObject(
    val url: Optional<String>? = null,
    @SerialName("proxy_url") val proxyUrl: Optional<String>? = null,
    val width: Optional<Int>? = null,
    val height: Optional<Int>? = null
)

@Serializable
data class EmbedVideoObject(
    val url: Optional<String>? = null,
    @SerialName("proxy_url") val proxyUrl: Optional<String>? = null,
    val width: Optional<String>? = null,
    val height: Optional<String>? = null
)

@Serializable
data class EmojiObject(
    val id: Optional<Snowflake?>? = null,
    val name: Optional<String?>? = null,
    val roles: Optional<List<RoleObject>>? = null,
    val user: Optional<UserObject>? = null,
    @SerialName("require_colons") val requireColons: Optional<Boolean>? = null,
    val managed: Optional<Boolean>? = null,
    val animated: Optional<Boolean>? = null,
    val available: Optional<Boolean>? = null
)

@Serializable
data class EntitlementObject(
    val id: Optional<Snowflake>? = null,
    @SerialName("sku_id") val skuId: Optional<Snowflake>? = null,
    @SerialName("application_id") val applicationId: Optional<Snowflake>? = null,
    @SerialName("user_id") val userId: Optional<Snowflake>? = null,
    val type: Optional<Int>? = null,
    val deleted: Optional<Boolean>? = null,
    @SerialName("starts_at") val startsAt: Optional<ISO8601Timestamp>? = null,
    @SerialName("ends_at") val endsAt: Optional<ISO8601Timestamp>? = null,
    @SerialName("guild_id") val guildId: Optional<Snowflake>? = null,
    val consumed: Optional<Boolean>? = null
)

@Serializable
data class FollowedChannelObject(
    @SerialName("channel_id") val channelId: Optional<Snowflake>? = null,
    @SerialName("webhook_id") val webhookId: Optional<Snowflake>? = null
)

@Serializable
data class ForumTagObject(
    val id: Optional<Snowflake>? = null,
    val name: Optional<String>? = null,
    val moderated: Optional<Boolean>? = null,
    @SerialName("emoji_id") val emojiId: Optional<Snowflake>? = null,
    @SerialName("emoji_name") val emojiName: Optional<String>? = null
)

@Serializable
data class ForumThreadMessageParametersObject(
    val content: Optional<String>? = null,
    val embeds: Optional<List<EmbedObject>>? = null,
    val allowedMentions: Optional<AllowedMentionsObject>? = null,
    val components: Optional<List<MessageComponentObject>>? = null,
    val stickerIds: Optional<List<Snowflake>>? = null,
    val attachments: Optional<List<AttachmentObject>>? = null,
    val flags: Optional<Int>? = null
)

@Serializable
data class GuildApplicationCommandPermissionsObject(
    val id: Optional<Snowflake>? = null,
    @SerialName("application_id") val applicationId: Optional<Snowflake>? = null,
    @SerialName("guild_id") val guildId: Optional<Snowflake>? = null,
    val permissions: Optional<List<ApplicationCommandPermissionObject>>? = null
)

@Serializable
data class GuildAuditLogObject(
    @SerialName("application_commands") val applicationCommands: Optional<List<ApplicationCommandObject>>? = null,
    @SerialName("audit_log_entries") val auditLogEntries: Optional<List<AuditLogEntryObject>>? = null,
    @SerialName("autoModerationRules") val autoModerationRules: Optional<List<GuildAutoModerationRuleObject>>? = null,
    @SerialName("guild_scheduled_events") val guildScheduledEvents: Optional<List<GuildScheduledEventObject>>? = null,
    val integrations: Optional<List<GuildIntegrationObject>>? = null,
    val threads: Optional<List<ChannelObject>>? = null,
    val users: Optional<List<UserObject>>? = null,
    val webhooks: Optional<List<WebhookObject>>? = null
)

@Serializable
data class GuildAutoModerationRuleObject(
    val id: Optional<Snowflake>? = null,
    @SerialName("guild_id") val guildId: Optional<Snowflake>? = null,
    val name: Optional<String>? = null,
    @SerialName("creator_id") val creatorId: Optional<Snowflake>? = null,
    @SerialName("event_type") val eventType: Optional<Int>? = null,
    @SerialName("trigger_type") val triggerType: Optional<Int>? = null,
    @SerialName("trigger_metadata") val triggerMetadata: Optional<AutoModerationRuleTriggerMetadataObject>? = null,
    val actions: Optional<List<AutoModerationActionObject>>? = null,
    val enabled: Optional<Boolean>? = null,
    @SerialName("exempt_roles") val exemptRoles: Optional<List<Snowflake>>? = null,
    @SerialName("exempt_channels") val exemptChannels: Optional<List<Snowflake>>? = null
)

@Serializable
data class GuildBanObject(
    val reason: Optional<String>? = null,
    val user: Optional<UserObject>? = null
)

@Serializable
data class GuildIntegrationObject(
    val id: Optional<Snowflake>? = null,
    val name: Optional<String>? = null,
    val type: Optional<String>? = null,
    val enabled: Optional<Boolean>? = null,
    val syncing: Optional<Boolean>? = null,
    @SerialName("role_id") val roleId: Optional<Snowflake>? = null,
    @SerialName("enable_emoticons") val enableEmoticons: Optional<Boolean>? = null,
    @SerialName("expire_behaviour") val expireBehaviour: Optional<Int>? = null,
    @SerialName("expire_grace_period") val expireGracePeriod: Optional<Int>? = null,
    val user: Optional<UserObject>? = null,
    val account: Optional<IntegrationAccountObject>? = null,
    @SerialName("synced_at") val syncedAt: Optional<ISO8601Timestamp>? = null,
    @SerialName("subscriber_count") val subscriberCount: Optional<Int>? = null,
    val revoked: Optional<Boolean>? = null,
    val application: Optional<IntegrationApplicationObject>? = null,
    val scopes: Optional<List<String>>? = null,
    @SerialName("guild_id") val guildId: Optional<Snowflake>? = null
)

@Serializable
data class GuildMemberObject(
    val user: Optional<UserObject>? = null,
    val nick: Optional<String>? = null,
    val avatar: Optional<ImageData>? = null,
    val banner: Optional<ImageData>? = null,
    val roles: Optional<List<Snowflake>>? = null,
    @SerialName("joined_at") val joinedAt: Optional<ISO8601Timestamp>? = null,
    @SerialName("premium_since") val premiumSince: Optional<ISO8601Timestamp>? = null,
    val deaf: Optional<Boolean>? = null,
    val mute: Optional<Boolean>? = null,
    val flags: Optional<Int>? = null,
    val pending: Optional<Boolean>? = null,
    val permissions: Optional<PermissionSet>? = null,
    @SerialName("communication_disabled_until") val communicationDisabledUntil: Optional<ISO8601Timestamp>? = null,
    @SerialName("avatar_decoration_data") val avatarDecorationData: Optional<AvatarDecorationDataObject>? = null,
    @SerialName("guild_id") val guildId: Optional<Snowflake>? = null
)

/**
 * Low-level representation of Discord API Guild object and UnavailableGuild object.
 *
 * @see <a href="https://discord.com/developers/docs/resources/guild">Discord API Reference: Value<Guild</a>
 */
@Serializable
data class GuildObject(
    val id: Optional<Snowflake>? = null,
    val name: Optional<String>? = null,
    val icon: Optional<String>? = null,
    @SerialName("icon_hash") val iconHash: Optional<String>? = null,
    val splash: Optional<String>? = null,
    @SerialName("discovery_splash") val discoverySplash: Optional<String>? = null,
    val owner: Optional<String>? = null, //Only set when using the GET Current User Guilds endpoint, and are relative to the requested user
    @SerialName("owner_id") val ownerId: Optional<Snowflake>? = null,
    val permissions: Optional<PermissionSet>? = null,
    @Deprecated("replaced by channel.rtc_region") val region: Optional<String>? = null,
    @SerialName("afk_channel_id") val afkChannelId: Optional<Snowflake>? = null,
    @SerialName("afk_timeout") val afkTimeout: Optional<Int>? = null,
    @SerialName("widget_enabled") val widgetEnabled: Optional<Boolean>? = null,
    @SerialName("widget_channel_id") val widgetChannelId: Optional<Snowflake>? = null,
    @SerialName("verification_level") val verificationLevel: Optional<Int>? = null,
    @SerialName("default_message_notifications") val defaultMessageNotifications: Optional<Int>? = null,
    @SerialName("explicit_content_filter") val explicitContentFilter: Optional<Int>? = null,
    val roles: Optional<List<RoleObject>>? = null,
    val emojis: Optional<List<EmojiObject>>? = null,
    val features: Optional<List<String>>? = null,
    @SerialName("mfa_level") val mfaLevel: Optional<Int>? = null,
    @SerialName("application_id") val applicationId: Optional<Snowflake>? = null,
    @SerialName("system_channel_id") val systemChannelId: Optional<Snowflake>? = null,
    @SerialName("system_channel_flags") val systemChannelFlags: Optional<Int>? = null,
    @SerialName("rules_channel_id") val rulesChannelId: Optional<Snowflake>? = null,
    @SerialName("max_presences") val maxPresences: Optional<Int>? = null,
    @SerialName("max_members") val maxMembers: Optional<Int>? = null,
    @SerialName("vanity_url_code") val vanityUrlCode: Optional<String>? = null,
    val description: Optional<String>? = null,
    val banner: Optional<ImageData>? = null,
    @SerialName("premium_tier") val premiumTier: Optional<Int>? = null,
    @SerialName("premium_subscription_count") val premiumSubscriptionCount: Optional<Int>? = null,
    @SerialName("preferred_locale") val preferredLocale: Optional<String>? = null,
    @SerialName("public_updates_channel_id") val publicUpdatesChannelId: Optional<Snowflake>? = null,
    @SerialName("max_video_channel_users") val maxVideoChannelUsers: Optional<Int>? = null,
    @SerialName("max_stage_video_channel_users") val maxStageVideoChannelUsers: Optional<Int>? = null,
    @SerialName("approximate_member_count") val approximateMemberCount: Optional<Int>? = null,
    @SerialName("approximate_presence_count") val approximatePresenceCount: Optional<Int>? = null,
    @SerialName("welcome_screen") val welcomeScreen: Optional<GuildWelcomeScreenObject>? = null,
    @SerialName("nsfw_level") val nsfwLevel: Optional<Int>? = null,
    val stickers: Optional<List<StickerObject>>? = null,
    @SerialName("premium_progress_bar_enabled") val premiumProgressBarEnabled: Optional<Boolean>? = null,
    @SerialName("safety_alerts_channel_id") val safetyAlertsChannelId: Optional<Snowflake>? = null,
    val unavailable: Optional<Boolean>? = null,
    // From Gateway Guild Create Event
    @SerialName("joined_at") val joinedAt: Optional<ISO8601Timestamp>? = null,
    @SerialName("large") val large: Optional<Boolean>? = null,
    @SerialName("member_count") val memberCount: Optional<Int>? = null,
    @SerialName("voice_states") val voiceStates: Optional<List<VoiceStateObject>>? = null,
    @SerialName("members") val members: Optional<List<GuildMemberObject>>? = null,
    @SerialName("channels") val channels: Optional<List<ChannelObject>>? = null,
    @SerialName("threads") val threads: Optional<List<ChannelObject>>? = null,
    @SerialName("presences") val presences: Optional<List<UpdatePresenceObject>>? = null,
    @SerialName("stage_instances") val stageInstances: Optional<List<StageInstanceObject>>? = null,
    @SerialName("guild_scheduled_events") val guildScheduledEvents: Optional<List<GuildScheduledEventObject>>? = null,
    @SerialName("soundboard_sounds") val soundboardSounds: Optional<List<SoundboardSoundObject>>? = null,
)

@Serializable
data class GuildOnboardingObject(
    @SerialName("guild_id") val guildId: Optional<Snowflake>? = null,
    val prompts: Optional<List<GuildOnboardingPromptObject>>? = null,
    @SerialName("default_channel_ids") val defaultChannelIds: Optional<List<Snowflake>>? = null,
    val enabled: Optional<Boolean>? = null,
    val mode: Optional<Int>? = null
)

@Serializable
data class GuildOnboardingPromptObject(
    val id: Optional<Snowflake>? = null,
    val options: Optional<List<GuildOnboardingPromptOptionObject>>? = null,
    val title: Optional<String>? = null,
    @SerialName("single_select") val singleSelect: Optional<Boolean>? = null,
    val required: Optional<Boolean>? = null,
    @SerialName("in_onboarding") val inOnboarding: Optional<Boolean>? = null
)

@Serializable
data class GuildOnboardingPromptOptionObject(
    val id: Optional<Snowflake>? = null,
    @SerialName("channel_ids") val channelIds: Optional<List<Snowflake>>? = null,
    @SerialName("role_ids") val roleIds: Optional<List<Snowflake>>? = null,
    val emoji: Optional<EmojiObject>? = null,
    @SerialName("emoji_id") val emojiId: Optional<Snowflake>? = null,
    @SerialName("emoji_name") val emojiName: Optional<String>? = null,
    @SerialName("emoji_animated") val emojiAnimated: Optional<Boolean>? = null,
    val title: Optional<String>? = null,
    val description: Optional<String>? = null
)

@Serializable
data class GuildPreviewObject(
    val id: Optional<Snowflake>? = null,
    val name: Optional<String>? = null,
    val icon: Optional<String>? = null,
    val splash: Optional<String>? = null,
    @SerialName("discovery_splash") val discoverySplash: Optional<String>? = null,
    val emojis: Optional<List<EmojiObject>>? = null,
    val features: Optional<List<String>>? = null,
    @SerialName("approximate_member_count") val approximateMemberCount: Optional<Int>? = null,
    @SerialName("approximate_presence_count") val approximatePresenceCount: Optional<Int>? = null,
    val description: Optional<String>? = null,
    val stickers: Optional<List<StickerObject>>? = null
)

@Serializable
data class GuildScheduledEventEntityMetadataObject(
    val location: Optional<String>? = null
)

@Serializable
data class GuildScheduledEventObject(
    val id: Optional<Snowflake>? = null,
    @SerialName("guild_id") val guildId: Optional<Snowflake>? = null,
    @SerialName("channel_id") val channelId: Optional<Snowflake>? = null,
    @SerialName("creator_id") val creatorId: Optional<Snowflake>? = null,
    val name: Optional<String>? = null,
    val description: Optional<String>? = null,
    @SerialName("scheduled_start_time") val scheduledStartTime: Optional<ISO8601Timestamp>? = null,
    @SerialName("scheduled_end_time") val scheduledEndTime: Optional<ISO8601Timestamp>? = null,
    @SerialName("privacy_level") val privacyLevel: Optional<Int>? = null,
    val status: Optional<Int>? = null,
    @SerialName("entity_type") val entityType: Optional<Int>? = null,
    @SerialName("entity_id") val entityId: Optional<Snowflake>? = null,
    @SerialName("entity_metadata") val entityMetadata: Optional<GuildScheduledEventEntityMetadataObject>? = null,
    val creator: Optional<UserObject>? = null,
    @SerialName("user_count") val userCount: Optional<Int>? = null,
    val image: Optional<ImageData>? = null,
    @SerialName("recurrence_rule") val recurrenceRule: Optional<GuildScheduledEventRecurrenceRuleObject>? = null,
)

@Serializable
data class GuildScheduledEventRecurrenceRuleObject(
    val start: Optional<ISO8601Timestamp>? = null,
    val end: Optional<ISO8601Timestamp>? = null,
    val frequency: Optional<Int>? = null,
    val interval: Optional<Int>? = null,
    @SerialName("by_weekday") val byWeekday: Optional<List<Int>>? = null,
    @SerialName("by_n_weekday") val byNWeekday: Optional<List<RecurrenceRuleNWeekdayObject>>? = null,
    @SerialName("by_month") val byMonth: Optional<List<Int>>? = null,
    @SerialName("by_month_day") val byMonthDay: Optional<List<Int>>? = null,
    @SerialName("by_year_day") val byYearDay: Optional<List<Int>>? = null,
    val count: Optional<Int>? = null
)

@Serializable
data class GuildScheduledEventUserObject(
    val guildScheduledEventId: Optional<Snowflake>? = null,
    val user: Optional<UserObject>? = null,
    val member: Optional<GuildMemberObject>? = null
)

@Serializable
data class GuildTemplateObject(
    val code: Optional<String>? = null,
    val name: Optional<String>? = null,
    val description: Optional<String>? = null,
    @SerialName("usage_count") val usageCount: Optional<Int>? = null,
    @SerialName("creator_id") val creatorId: Optional<Snowflake>? = null,
    val creator: Optional<UserObject>? = null,
    @SerialName("created_at") val createdAt: Optional<ISO8601Timestamp>? = null,
    @SerialName("updated_at") val updatedAt: Optional<ISO8601Timestamp>? = null,
    @SerialName("source_guild_id") val sourceGuildId: Optional<Snowflake>? = null,
    @SerialName("serialized_source_guild") val serializedSourceGuild: Optional<GuildObject>? = null,
    @SerialName("is_dirty") val isDirty: Optional<Boolean>? = null
)

@Serializable
data class GuildWelcomeScreenChannelObject(
    @SerialName("channel_id") val channelId: Optional<Snowflake>? = null,
    val description: Optional<String>? = null,
    @SerialName("emoji_id") val emojiId: Optional<Snowflake>? = null,
    @SerialName("emoji_name") val emojiName: Optional<String>? = null
)

@Serializable
data class GuildWelcomeScreenObject(
    val description: Optional<String>? = null,
    @SerialName("welcome_channels") val welcomeChannels: Optional<List<GuildWelcomeScreenChannelObject>>? = null
)

@Serializable
data class GuildWidgetObject(
    val id: Optional<Snowflake>? = null,
    val name: Optional<String>? = null,
    @SerialName("instant_invite") val instantInvite: Optional<String>? = null,
    val channels: Optional<List<ChannelObject>>? = null,
    val members: Optional<List<GuildMemberObject>>? = null,
    @SerialName("presence_count") val presenceCount: Optional<Int>? = null
)

@Serializable
data class GuildWidgetSettingsObject(
    val enabled: Optional<Boolean>? = null,
    @SerialName("channel_id") val channelId: Optional<Snowflake>? = null
)

@Serializable
data class IntegrationAccountObject(
    val id: Optional<String>? = null,
    val name: Optional<String>? = null
)

@Serializable
data class IntegrationApplicationObject(
    val id: Optional<Snowflake>? = null,
    val name: Optional<String>? = null,
    val icon: Optional<String>? = null,
    val description: Optional<String>? = null,
    val bot: Optional<UserObject>? = null
)

@Serializable
data class InteractionDataObject(
    val id: Optional<Snowflake>? = null,
    val name: Optional<String>? = null,
    val type: Optional<Int>? = null,
    val resolved: Optional<ResolvedDataObject>? = null,
    val options: Optional<List<ApplicationCommandInteractionDataOptionObject>>? = null,
    @SerialName("guild_id") val guildId: Optional<Snowflake>? = null,
    @SerialName("target_id") val targetId: Optional<Snowflake>? = null
)

@Serializable
data class InteractionObject(
    val id: Optional<Snowflake>? = null,
    @SerialName("application_id") val applicationId: Optional<Snowflake>? = null,
    val type: Optional<Int>? = null,
    val data: Optional<InteractionDataObject>? = null,
    val guild: Optional<GuildObject>? = null,
    @SerialName("guild_id") val guildId: Optional<Snowflake>? = null,
    val channel: Optional<ChannelObject>? = null,
    @SerialName("channel_id") val channelId: Optional<Snowflake>? = null,
    val member: Optional<GuildMemberObject>? = null,
    val user: Optional<UserObject>? = null,
    val token: Optional<String>? = null,
    val version: Optional<Int>? = null,
    val message: Optional<MessageObject>? = null,
    @SerialName("app_permissions") val appPermissions: Optional<String>? = null,
    val locale: Optional<String>? = null,
    @SerialName("guild_locale") val guildLocale: Optional<String>? = null,
    val entitlements: Optional<List<EntitlementObject>>? = null,
    @SerialName("authorizing_integration_owners") val authorizingIntegrationOwners: Optional<Map<Int, String>>? = null
)

@Serializable
data class InviteObject(
    val type: Optional<Int>? = null,
    val code: Optional<String>? = null,
    val uses: Optional<Int>? = null,
    val guild: Optional<GuildObject>? = null,
    val channel: Optional<ChannelObject>? = null,
    val inviter: Optional<UserObject>? = null,
    @SerialName("target_type") val targetType: Optional<Int>? = null,
    @SerialName("target_user") val targetUser: Optional<UserObject>? = null,
    @SerialName("target_application") val targetApplication: Optional<ApplicationObject>? = null,
    @SerialName("approximate_presence_count") val approximatePresenceCount: Optional<Int>? = null,
    @SerialName("approximate_member_count") val approximateMemberCount: Optional<Int>? = null,
    @SerialName("expires_at") val expiresAt: Optional<ISO8601Timestamp>? = null,
    @SerialName("stage_instance") val stageInstance: Optional<InviteStageInstanceObject>? = null,
    @SerialName("guild_scheduled_event") val guildScheduledEvent: Optional<GuildScheduledEventObject>? = null,
)

@Serializable
data class InviteStageInstanceObject(
    val members: Optional<List<GuildMemberObject>>? = null,
    @SerialName("participation_count") val participationCount: Optional<Int>? = null,
    @SerialName("speaker_count") val speakerCount: Optional<Int>? = null,
    val topic: Optional<String>? = null
)

@Serializable
data class MessageActivityObject(
    val type: Optional<Int>? = null,
    @SerialName("party_id") val partyId: Optional<String>? = null
)

@Serializable
data class MessageCallObject(
    val participants: Optional<List<Snowflake>>? = null,
    val endedTimestamp: Optional<ISO8601Timestamp>? = null
)

@Serializable
class MessageComponentObject(
    // TODO: Value<Implement me
)

@Serializable
abstract class MessageInteractionMetadataObject {
    abstract val id: Optional<Snowflake>?
    abstract val type: Optional<Int>?
    abstract val user: Optional<UserObject>?

    @SerialName("authorizing_integration_owners")
    abstract val authorizingIntegrationOwners: Optional<Map<Int, Snowflake>>?
    
    @SerialName("original_response_message_id")
    abstract val originalResponseMessageId: Optional<Snowflake>?
}

@Serializable
data class ApplicationCommandInteractionMetadataObject(
    override val id: Optional<Snowflake>? = null,
    override val type: Optional<Int>? = null,
    override val user: Optional<UserObject>? = null,
    override val authorizingIntegrationOwners: Optional<Map<Int, Snowflake>>? = null,
    override val originalResponseMessageId: Optional<Snowflake>? = null,
    @SerialName("target_user") val targetUser: Optional<UserObject>? = null,
    @SerialName("target_message_id") val targetMessageId: Optional<Snowflake>? = null,
) : MessageInteractionMetadataObject()

@Serializable
data class MessageComponentInteractionMetadataObject(
    override val id: Optional<Snowflake>? = null,
    override val type: Optional<Int>? = null,
    override val user: Optional<UserObject>? = null,
    override val authorizingIntegrationOwners: Optional<Map<Int, Snowflake>>? = null,
    override val originalResponseMessageId: Optional<Snowflake>? = null,
    @SerialName("interacted_message_id") val interactedMessageId: Optional<Snowflake>? = null,
) : MessageInteractionMetadataObject()

@Serializable
data class ModalSubmitInteractionMetadataObject(
    override val id: Optional<Snowflake>? = null,
    override val type: Optional<Int>? = null,
    override val user: Optional<UserObject>? = null,
    override val authorizingIntegrationOwners: Optional<Map<Int, Snowflake>>? = null,
    override val originalResponseMessageId: Optional<Snowflake>? = null,
    @SerialName("trigger_interaction_metadata") val triggerInteractionMetadata: Optional<MessageInteractionMetadataObject>? = null,
) : MessageInteractionMetadataObject()

@Serializable
data class MessageObject(
    val id: Optional<Snowflake>? = null,
    val channelId: Optional<Snowflake>? = null,
    val author: Optional<UserObject>? = null,
    val content: Optional<String>? = null,
    val timestamp: Optional<ISO8601Timestamp>? = null,
    val editedTimestamp: Optional<ISO8601Timestamp>? = null,
    val tts: Optional<Boolean>? = null,
    val mentionEveryone: Optional<Boolean>? = null,
    val mentions: Optional<List<UserObject>>? = null,
    val mentionRoles: Optional<List<RoleObject>>? = null,
    val mentionChannels: Optional<List<ChannelMentionObject>>? = null,
    val attachments: Optional<List<AttachmentObject>>? = null,
    val embeds: Optional<List<EmbedObject>>? = null,
    val reactions: Optional<List<ReactionObject>>? = null,
    val nonce: Optional<String>? = null,
    val pinned: Optional<Boolean>? = null,
    val webhookId: Optional<Snowflake>? = null,
    val type: Optional<Int>? = null,
    val activity: Optional<MessageActivityObject>? = null,
    val application: Optional<ApplicationObject>? = null,
    val applicationId: Optional<Snowflake>? = null,
    val flags: Optional<Int>? = null,
    val messageReference: Optional<MessageReferenceObject>? = null,
    val messageSnapshots: Optional<MessageSnapshotObject>? = null,
    val referencedMessage: Optional<MessageReferenceObject>? = null,
    val interactionMetadata: Optional<MessageInteractionMetadataObject>? = null,
    val interaction: Optional<InteractionObject>? = null,
    val thread: Optional<ChannelObject>? = null,
    val components: Optional<List<MessageComponentObject>>? = null,
    val stickerItems: Optional<List<StickerObject>>? = null,
    val stickers: Optional<List<StickerObject>>? = null,
    val position: Optional<Int>? = null,
    val roleSubscriptionData: Optional<RoleSubscriptionDataObject>? = null,
    val resolved: Optional<ResolvedDataObject>? = null,
    val poll: Optional<PollObject>? = null,
    val call: Optional<MessageCallObject>? = null,
    val guildId: Optional<Snowflake>? = null,
    val member: Optional<GuildMemberObject>? = null
)

@Serializable
data class MessageReferenceObject(
    val type: Optional<Int>? = null,
    @SerialName("message_id") val messageId: Optional<Snowflake>? = null,
    @SerialName("channel_id") val channelId: Optional<Snowflake>? = null,
    @SerialName("guild_id") val guildId: Optional<Snowflake>? = null,
    @SerialName("fail_if_not_exists") val failIfNotExists: Optional<Boolean>? = null
)

@Serializable
data class MessageSnapshotObject(
    val message: Optional<MessageObject>? = null
)

@Serializable
data class PollAnswerCountObject(
    val id: Optional<Int>? = null,
    val count: Optional<Int>? = null,
    @SerialName("me_voted") val meVoted: Optional<Boolean>? = null
)

@Serializable
data class PollAnswerObject(
    @SerialName("answer_id") val answerId: Optional<Int>? = null,
    @SerialName("poll_media") val pollMedia: Optional<PollMediaObject>? = null,
)

@Serializable
data class PollMediaObject(
    val text: Optional<String>? = null,
    val emoji: Optional<EmojiObject>? = null
)

@Serializable
data class PollObject(
    val question: Optional<PollMediaObject>? = null,
    val answers: Optional<List<PollAnswerObject>>? = null,
    val expiry: Optional<ISO8601Timestamp>? = null,
    @SerialName("allow_multiselect") val allowMultiselect: Optional<Boolean>? = null,
    @SerialName("layout_type") val layoutType: Optional<Int>? = null,
    val results: Optional<PollResultsObject>? = null
)

@Serializable
data class PollResultsObject(
    @SerialName("is_finalized") val isFinalized: Optional<Boolean>? = null,
    @SerialName("answer_counts") val answerCounts: Optional<List<PollAnswerCountObject>>? = null
)

@Serializable
data class ReactionCountDetailsObject(
    val burst: Optional<Int>? = null,
    val normal: Optional<Int>? = null
)

@Serializable
data class ReactionObject(
    val count: Optional<Int>? = null,
    @SerialName("count_details") val countDetails: Optional<ReactionCountDetailsObject>? = null,
    val me: Optional<Boolean>? = null,
    @SerialName("me_burst") val meBurst: Optional<Boolean>? = null,
    val emoji: Optional<EmojiObject>? = null,
    @SerialName("burst_colors") val burstColors: Optional<List<Int>>? = null
)

@Serializable
data class RecurrenceRuleNWeekdayObject(
    val n: Optional<Int>? = null,
    val day: Optional<Int>? = null
)

@Serializable
data class ResolvedDataObject(
    val users: Optional<Map<Snowflake, UserObject>>? = null,
    val members: Optional<Map<Snowflake, GuildMemberObject>>? = null,
    val roles: Optional<Map<Snowflake, RoleObject>>? = null,
    val channels: Optional<Map<Snowflake, ChannelObject>>? = null,
    val messages: Optional<Map<Snowflake, MessageObject>>? = null,
    val attachments: Optional<Map<Snowflake, AttachmentObject>>? = null,
)

@Serializable
data class RoleObject(
    val id: Optional<Snowflake>? = null,
    val name: Optional<String>? = null,
    val color: Optional<Int>? = null,
    val hoist: Optional<Boolean>? = null,
    val icon: Optional<String>? = null,
    @SerialName("unicode_emoji") val unicodeEmoji: Optional<String>? = null,
    val position: Optional<Int>? = null,
    val permissions: Optional<PermissionSet>? = null,
    val managed: Optional<Boolean>? = null,
    val mentionable: Optional<Boolean>? = null,
    val tags: Optional<RoleTagsObject>? = null,
    val flags: Optional<Int>? = null
)

@Serializable
data class RoleSubscriptionDataObject(
    @SerialName("role_subscription_listing_id") val roleSubscriptionListingId: Optional<Snowflake>? = null,
    @SerialName("tier_name") val tierName: Optional<String>? = null,
    @SerialName("total_months_subscribed") val totalMonthsSubscribed: Optional<Int>? = null,
    @SerialName("is_renewal") val isRenewal: Optional<Boolean>? = null
)

@Serializable
data class RoleTagsObject(
    val botId: Optional<Snowflake>? = null,
    @SerialName("integration_id") val integrationId: Optional<Snowflake>? = null,
    @SerialName("premium_subscriber") val premiumSubscriber: Optional<Boolean>? = null,
    @SerialName("subscription_listing_id") val subscriptionListingId: Optional<Snowflake>? = null,
    @SerialName("available_for_purchase") val availableForPurchase: Optional<Boolean>? = null,
    @SerialName("guild_connections") val guildConnections: Optional<Boolean>? = null
)

@Serializable
data class SkuObject(
    val id: Optional<Snowflake>? = null,
    val type: Optional<Int>? = null,
    @SerialName("application_id") val applicationId: Optional<Snowflake>? = null,
    val name: Optional<String>? = null,
    val slug: Optional<String>? = null,
    val flags: Optional<Int>? = null
)

@Serializable
data class SoundboardSoundObject(
    val name: Optional<String>? = null,
    @SerialName("sound_id") val soundId: Optional<Snowflake>? = null,
    val volume: Optional<Double>? = null,
    @SerialName("emoji_id") val emojiId: Optional<Snowflake>? = null,
    @SerialName("emoji_name") val emojiName: Optional<String>? = null,
    @SerialName("guild_id") val guildId: Optional<Snowflake>? = null,
    val available: Optional<Boolean>? = null,
    val user: Optional<UserObject>? = null
)

@Serializable
data class StageInstanceObject(
    val id: Optional<Snowflake>? = null,
    @SerialName("guild_id") val guildId: Optional<Snowflake>? = null,
    @SerialName("channel_id") val channelId: Optional<Snowflake>? = null,
    val topic: Optional<String>? = null,
    @SerialName("privacy_level") val privacyLevel: Optional<Int>? = null,
    @SerialName("discoverable_disabled") val discoverableDisabled: Optional<Boolean>? = null,
    @SerialName("guild_scheduled_event_id") val guildScheduledEventId: Optional<Snowflake>? = null,
)

@Serializable
data class StickerObject(
    val id: Optional<Snowflake>? = null,
    @SerialName("pack_id") val packId: Optional<Snowflake>? = null,
    val name: Optional<String>? = null,
    val description: Optional<String>? = null,
    val tags: Optional<String>? = null,
    val type: Optional<Int>? = null,
    @SerialName("format_type") val formatType: Optional<Int>? = null,
    val available: Optional<Boolean>? = null,
    @SerialName("guild_id") val guildId: Optional<Snowflake>? = null,
    val user: Optional<UserObject>? = null,
    @SerialName("sort_value") val sortValue: Optional<Int>? = null,
)

@Serializable
data class StickerPackObject(
    val id: Optional<Snowflake>? = null,
    val stickers: Optional<List<StickerObject>>? = null,
    val name: Optional<String>? = null,
    @SerialName("sku_id") val skuId: Optional<Snowflake>? = null,
    @SerialName("cover_sticker_id") val coverStickerId: Optional<Snowflake>? = null,
    val description: Optional<String>? = null,
    @SerialName("banner_asset_id") val bannerAssetId: Optional<Snowflake>? = null
)

@Serializable
data class SubscriptionObject(
    val id: Optional<Snowflake>? = null,
    @SerialName("user_id") val userId: Optional<Snowflake>? = null,
    @SerialName("sku_ids") val skuIds: Optional<List<Snowflake>>? = null,
    @SerialName("entitlement_ids") val entitlementIds: Optional<List<Snowflake>>? = null,
    @SerialName("renewal_sku_ids") val renewalSkuIds: Optional<List<Snowflake>>? = null,
    @SerialName("current_period_start") val currentPeriodStart: Optional<ISO8601Timestamp>? = null,
    @SerialName("current_period_end") val currentPeriodEnd: Optional<ISO8601Timestamp>? = null,
    val status: Optional<Int>? = null,
    @SerialName("canceled_at") val canceledAt: Optional<ISO8601Timestamp>? = null,
    val country: Optional<String>? = null
)

@Serializable
data class TeamMemberObject(
    @SerialName("membership_state") val membershipState: Optional<Int>? = null,
    @SerialName("team_id") val teamId: Optional<Int>? = null,
    val user: Optional<UserObject>? = null,
    val role: Optional<String>? = null
)

@Serializable
data class TeamObject(
    val icon: Optional<String>? = null,
    val id: Optional<Snowflake>? = null,
    val members: Optional<List<TeamMemberObject>>? = null,
    val name: Optional<String>? = null,
    @SerialName("owner_user_id") val ownerUserId: Optional<Snowflake>? = null,
)

@Serializable
data class ThreadMemberObject(
    val id: Optional<Snowflake>? = null,
    @SerialName("user_id") val userId: Optional<Snowflake>? = null,
    @SerialName("join_timestamp") val joinTimestamp: Optional<ISO8601Timestamp>? = null,
    val flags: Optional<Int>? = null,
    val member: Optional<GuildMemberObject>? = null,
    @SerialName("guild_id") val guildId: Optional<Snowflake>? = null
)

@Serializable
data class ThreadMetadataObject(
    val archived: Optional<Boolean>? = null,
    @SerialName("auto_archive_duration") val autoArchiveDuration: Optional<Int>? = null,
    @SerialName("archive_timestamp") val archiveTimestamp: Optional<ISO8601Timestamp>? = null,
    val locked: Optional<Boolean>? = null,
    val invitable: Optional<Boolean>? = null,
    @SerialName("create_timestamp") val createTimestamp: Optional<ISO8601Timestamp>? = null,
)

@Serializable
data class UpdatePresenceObject(
    val since: Optional<Int>? = null,
    val activities: Optional<ActivityObject>? = null,
    val status: Optional<String>? = null,
    val afk: Optional<Boolean>? = null
)

@Serializable
data class UserApplicationRoleConnectionObject(
    @SerialName("platform_name") val platformName: Optional<String>? = null,
    @SerialName("platform_username") val platformUsername: Optional<String>? = null,
    val metadata: Optional<Map<String, ApplicationRoleConnectionMetadataObject>>? = null
)

@Serializable
data class UserConnectionObject(
    val id: Optional<String>? = null,
    val name: Optional<String>? = null,
    val type: Optional<String>? = null,
    val revoked: Optional<Boolean>? = null,
    val integrations: Optional<List<GuildIntegrationObject>>? = null,
    val verified: Optional<Boolean>? = null,
    @SerialName("friend_sync") val friendSync: Optional<Boolean>? = null,
    @SerialName("show_activity") val showActivity: Optional<Boolean>? = null,
    @SerialName("two_way_link") val twoWayLink: Optional<Boolean>? = null,
    val visibility: Optional<Int>? = null
)

@Serializable
data class UserObject(
    val id: Optional<Snowflake>? = null,
    val username: Optional<String>? = null,
    val discriminator: Optional<String>? = null,
    @SerialName("global_name") val globalName: Optional<String>? = null,
    val avatar: Optional<String>? = null,
    val bot: Optional<Boolean>? = null,
    val system: Optional<Boolean>? = null,
    @SerialName("mfa_enabled") val mfaEnabled: Optional<Boolean>? = null,
    val banner: Optional<String>? = null,
    @SerialName("accent_color") val accentColor: Optional<Int>? = null,
    val locale: Optional<String>? = null,
    val verified: Optional<Boolean>? = null,
    val email: Optional<String>? = null,
    val flags: Optional<Int>? = null,
    @SerialName("premium_type") val premiumType: Optional<Int>? = null,
    @SerialName("public_flags") val publicFlags: Optional<Int>? = null,
    @SerialName("avatar_decoration_data") val avatarDecorationData: Optional<AvatarDecorationDataObject>? = null
)

@Serializable
data class VoiceRegionObject(
    @SerialName("guild_id") val guildId: Optional<Snowflake>? = null,
    @SerialName("channel_id") val channelId: Optional<Snowflake>? = null,
    @SerialName("user_id") val userId: Optional<Snowflake>? = null,
    val member: Optional<GuildMemberObject>? = null,
    @SerialName("session_id") val sessionId: Optional<String>? = null,
    val deaf: Optional<Boolean>? = null,
    val mute: Optional<Boolean>? = null,
    @SerialName("self_deaf") val selfDeaf: Optional<Boolean>? = null,
    @SerialName("self_mute") val selfMute: Optional<Boolean>? = null,
    @SerialName("self_stream") val selfStream: Optional<Boolean>? = null,
    @SerialName("self_video") val selfVideo: Optional<Boolean>? = null,
    val suppress: Optional<Boolean>? = null,
    @SerialName("request_to_speak_timestamp") val requestToSpeakTimestamp: Optional<ISO8601Timestamp>? = null
)

@Serializable
data class VoiceStateObject(
    @SerialName("guild_id") val guildId: Optional<Snowflake>? = null,
    @SerialName("channel_id") val channelId: Optional<Snowflake>? = null,
    @SerialName("user_id") val userId: Optional<Snowflake>? = null,
    val member: Optional<GuildMemberObject>? = null,
    @SerialName("session_id") val sessionId: Optional<String>? = null,
    val deaf: Optional<Boolean>? = null,
    val mute: Optional<Boolean>? = null,
    @SerialName("self_deaf") val selfDeaf: Optional<Boolean>? = null,
    @SerialName("self_mute") val selfMute: Optional<Boolean>? = null,
    @SerialName("self_stream") val selfStream: Optional<Boolean>? = null,
    @SerialName("self_video") val selfVideo: Optional<Boolean>? = null,
    val suppress: Optional<Boolean>? = null,
    @SerialName("request_to_speak_timestamp") val requestToSpeakTimestamp: Optional<ISO8601Timestamp>? = null
)

@Serializable
data class WebhookObject(
    val id: Optional<Snowflake>? = null,
    val type: Optional<Int>? = null,
    @SerialName("guild_id") val guildId: Optional<Snowflake>? = null,
    @SerialName("channel_id") val channelId: Optional<Snowflake>? = null,
    val user: Optional<UserObject>? = null,
    val name: Optional<String>? = null,
    val avatar: Optional<ImageData>? = null,
    val token: Optional<String>? = null,
    @SerialName("application_id") val applicationId: Optional<Snowflake>? = null,
    @SerialName("source_guild") val sourceGuild: Optional<GuildObject>? = null,
    @SerialName("source_channel") val sourceChannel: Optional<ChannelObject>? = null,
    val url: Optional<String>? = null
)