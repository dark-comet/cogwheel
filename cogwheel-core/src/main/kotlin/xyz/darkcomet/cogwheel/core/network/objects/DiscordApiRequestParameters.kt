package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.*

@Serializable
data class AddGuildMemberRequestParameters(
    @SerialName("access_token") val accessToken: String,
    val nick: Optional<String>? = null,
    val roles: Optional<List<Snowflake>>? = null,
    val mute: Optional<Boolean>? = null,
    val deaf: Optional<Boolean>? = null
)

@Serializable
data class BeginGuildPruneRequestParameters(
    val days: Int = 7,
    val computePruneCount: Boolean = true,
    val includeRoles: MutableList<Snowflake> = mutableListOf(),
    val reason: Optional<String>? = null
)

@Serializable
data class BulkDeleteMessagesRequestParameters(
    val messages: List<Snowflake>
)

@Serializable
data class BulkGuildBanRequestParameters(
    @SerialName("user_ids") val userIds: List<Snowflake>,
    @SerialName("delete_message_seconds") val deleteMessageSeconds: Optional<Int>? = null
)

@Serializable
data class CreateApplicationEmojiRequestParameters(
    val name: String,
    val image: ImageData
)

@Serializable
data class CreateChannelInviteRequestParameters(
    @SerialName("max_age") val maxAge: Int,
    @SerialName("max_uses") val maxUses: Int,
    val temporary: Boolean,
    val unique: Boolean,
    @SerialName("target_type") val targetType: Int,
    @SerialName("target_user_id") val targetUserId: Snowflake,
    @SerialName("target_application_id") val targetApplicationId: Snowflake
)

@Serializable
data class CreateGroupDmRequestParameters(
    val accessTokens: List<String>,
    val nicks: Map<Snowflake, String>
)

@Serializable
data class CreateGuildAutoModerationRuleRequestParameters(
    val name: String,
    @SerialName("event_type") val eventType: Int,
    @SerialName("trigger_type") val triggerType: Int,
    @SerialName("trigger_metadata") val triggerMetadata: Optional<AutoModerationRuleTriggerMetadataObject>? = null,
    val actions: List<AutoModerationActionObject>,
    val enabled: Optional<Boolean>? = null,
    @SerialName("exempt_roles") val exemptRoles: Optional<List<Snowflake>>? = null,
    @SerialName("exempt_channels") val exemptChannels: Optional<List<Snowflake>>? = null,
)

@Serializable
data class CreateGuildBanRequestParameters(
    @SerialName("delete_message_days") val deleteMessageDays: Optional<Int>? = null,
    @SerialName("delete_message_seconds") val deleteMessageSeconds: Optional<Int>? = null,
)

@Serializable
data class CreateGuildChannelRequestParameters(
    val name: String,
    val type: Optional<Int>? = null,
    val topic: Optional<String>? = null,
    val bitrate: Optional<Int>? = null,
    @SerialName("user_limit") val userLimit: Optional<Int>? = null,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: Optional<Int>? = null,
    val position: Optional<Int>? = null,
    @SerialName("permission_overwrites") val permissionOverwrites: Optional<List<ChannelPermissionOverwriteObject>>? = null,
    @SerialName("parent_id") val parentId: Optional<Snowflake>? = null,
    val nsfw: Optional<Boolean>? = null,
    @SerialName("rtc_region") val rtcRegion: Optional<String>? = null,
    @SerialName("video_quality_mode") val videoQualityMode: Optional<Int>? = null,
    @SerialName("default_auto_archive_duration") val defaultAutoArchiveDuration: Optional<Int>? = null,
    @SerialName("default_reaction_emoji") val defaultReactionEmoji: Optional<ChannelDefaultReactionObject>? = null,
    @SerialName("available_tags") val availableTags: Optional<List<ForumTagObject>>? = null,
    @SerialName("default_sort_order") val defaultSortOrder: Optional<Int>? = null,
    @SerialName("default_forum_layout") val defaultForumLayout: Optional<Int>? = null,
    @SerialName("default_thread_rate_limit_per_user") val defaultThreadRateLimitPerUser: Optional<Int>? = null,
)

@Serializable
data class CreateGuildEmojiRequestParameters(
    val name: String,
    val image: ImageData,
    val roles: List<Snowflake>
)

@Serializable
data class CreateGuildFromGuildTemplateRequestParameters(
    val name: String,
    val icon: Optional<ImageData>? = null
)

@Serializable
data class CreateGuildRequestParameters(
    val name: String,
    val region: Optional<String>? = null, // deprecated
    val icon: Optional<ImageData>? = null,
    @SerialName("verification_level") val verificationLevel: Optional<Int>? = null,
    @SerialName("default_message_notifications") val defaultMessageNotifications: Optional<Int>? = null,
    @SerialName("explicit_content_filter") val explicitContentFilter: Optional<Int>? = null,
    val roles: Optional<List<RoleObject>>? = null,
    val channels: Optional<List<ChannelObject>>? = null,
    @SerialName("afk_channel_id") val afkChannelId: Optional<Snowflake>? = null,
    @SerialName("afk_timeout") val afkTimeout: Optional<Int>? = null,
    @SerialName("system_channel_id") val systemChannelId: Optional<Snowflake>? = null,
    @SerialName("system_channel_flags") val systemChannelFlags: Optional<Int>? = null,
)

@Serializable
data class CreateGuildRoleRequestParameters(
    val name: String,
    val permissions: PermissionSet,
    val color: Int,
    val hoist: Boolean,
    val icon: ImageData?,
    @SerialName("unicode_emoji") val unicodeEmoji: String?,
    val mentionable: Boolean
)

@Serializable
data class CreateGuildScheduledEventRequestParameters(
    val channelId: Optional<Snowflake>? = null,
    @SerialName("entity_metadata") val entityMetadata: Optional<GuildScheduledEventEntityMetadataObject>? = null,
    val name: String,
    @SerialName("privacy_level") val privacyLevel: Optional<Int>? = null,
    @SerialName("scheduled_start_time") val scheduledStartTime: ISO8601Timestamp,
    @SerialName("scheduled_end_time") val scheduledEndTime: Optional<ISO8601Timestamp>? = null,
    val description: Optional<String>? = null,
    @SerialName("entity_type") val entityType: Int,
    val image: Optional<ImageData>? = null,
    @SerialName("recurrence_rule") val recurrenceRule: Optional<GuildScheduledEventRecurrenceRuleObject>? = null
)

@Serializable
data class CreateGuildSoundboardSoundRequestParameters(
    val name: String,
    val sound: String,
    val volume: Optional<Double>? = null,
    @SerialName("emoji_id") val emojiId: Optional<Snowflake>? = null,
    @SerialName("emoji_name") val emojiName: Optional<String>? = null
)

@Serializable
data class CreateGuildStickerRequestParameters(
    val name: String,
    val description: String,
    val tags: String,
    val file: String
)

@Serializable
data class CreateGuildTemplateRequestParameters(
    val name: String,
    val description: Optional<String>? = null
)

@Serializable
data class CreateMessageRequestParameters(
    val content: Optional<String>? = null,
    val nonce: Optional<String>? = null,
    val tts: Optional<Boolean>? = null,
    val embeds: Optional<List<EmbedObject>>? = null,
    @SerialName("allowed_mentions") val allowedMentions: Optional<AllowedMentionsObject>? = null,
    @SerialName("message_reference") val messageReference: Optional<MessageReferenceObject>? = null,
    val components: Optional<List<MessageComponentObject>>? = null,
    @SerialName("sticker_ids") val stickerIds: Optional<List<Snowflake>>? = null,
//    val filesN
    @SerialName("payload_json") val payloadJson: Optional<String>? = null,
    val attachments: Optional<List<AttachmentObject>>? = null,
    val flags: Optional<Int>? = null,
    @SerialName("enforce_nonce") val enforceNonce: Optional<Boolean>? = null,
    val poll: Optional<PollObject>? = null
)

@Serializable
data class CreateStageInstanceRequestParameters(
    @SerialName("channel_id") val channelId: Snowflake,
    val topic: String,
    @SerialName("privacy_level") val privacyLevel: Optional<Int>? = null,
    @SerialName("send_start_notification") val sendStartNotification: Optional<Boolean>? = null,
    @SerialName("guild_scheduled_event_id") val guildScheduledEventId: Optional<Snowflake>? = null
)

@Serializable
data class CreateTestEntitlementRequestParameters(
    @SerialName("sku_id") val skuId: String,
    @SerialName("owner_id") val ownerId: String,
    @SerialName("owner_type") val ownerType: Int
)


@Serializable
data class CreateUserDmRequestParameters(
    @SerialName("recipient_id") val recipientId: Snowflake
)

@Serializable
data class CreateWebhookRequestParameters(
    val name: String,
    val avatar: Optional<ImageData>? = null,
)

@Serializable
data class EditChannelPermissionsParameters(
    val allow: Optional<PermissionSet>? = null,
    val deny: Optional<PermissionSet>? = null,
    val type: Int
)

@Serializable
data class EditMessageRequestParameters(
    val content: Optional<String>? = null,
    val embeds: Optional<List<EmbedObject>>? = null,
    val flags: Optional<Int>? = null,
    @SerialName("allowed_mentions") val allowedMentions: Optional<AllowedMentionsObject>? = null,
    val components: Optional<List<MessageComponentObject>>? = null,
//    val filesN
    @SerialName("payload_json") val payloadJson: Optional<String>? = null,
    val attachments: Optional<List<AttachmentObject>>? = null,
)

@Serializable
data class EditWebhookMessageRequestParameters(
    val content: Optional<String>? = null,
    val embeds: Optional<List<EmbedObject>>? = null,
    @SerialName("allowed_mentions") val allowedMentions: Optional<AllowedMentionsObject>? = null,
    val components: Optional<List<MessageComponentObject>>? = null,
//    val filesN
    @SerialName("payload_json") val payloadJson: Optional<String>? = null,
    val attachments: Optional<List<AttachmentObject>>? = null,
    val poll: Optional<PollObject>? = null
)

@Serializable
data class ExecuteWebhookRequestParameters(
    val content: Optional<String>? = null,
    val username: Optional<String>? = null,
    @SerialName("avatar_url") val avatarUrl: Optional<String>? = null,
    val tts: Optional<Boolean>? = null,
    val embeds: Optional<List<EmbedObject>>? = null,
    @SerialName("allowed_mentions") val allowedMentions: Optional<AllowedMentionsObject>? = null,
    val components: Optional<List<MessageComponentObject>>? = null,
//    val filesN
    @SerialName("payload_json") val payloadJson: Optional<String>? = null,
    val attachments: Optional<List<AttachmentObject>>? = null,
    val flags: Optional<Int>? = null,
    @SerialName("thread_name") val threadName: Optional<String>? = null,
    @SerialName("applied_tags") val appliedTags: Optional<List<Snowflake>>? = null,
    val poll: Optional<PollObject>? = null
)

@Serializable
data class FollowAnnouncementChannelRequestParameters(
    @SerialName("webhook_channel_id") val webhookChannelId: Snowflake,
)

@Serializable
data class GatewayPresenceUpdateRequestParameters(
    val since: Int?,
    val activities: List<ActivityObject>,
    val status: String,
    val afk: Boolean
)

@Serializable
data class GatewayRequestGuildMembersRequestParameters(
    @SerialName("guild_id") val guildId: Snowflake,
    val query: Optional<String>? = null,
    val limit: Int,
    val presences: Optional<Boolean>? = null,
    @SerialName("user_ids") val userIds: Optional<List<Snowflake>>? = null,
    val nonce: Optional<String>? = null
)

@Serializable
data class GatewayRequestSoundboardSoundsRequestParameters(
    @SerialName("guild_ids") val guildIds: List<Snowflake>
)

@Serializable
data class GatewayVoiceStateUpdateRequestParameters(
    @SerialName("guild_id") val guildId: Snowflake,
    @SerialName("channel_id") val channelId: Snowflake?,
    @SerialName("self_mute") val selfMute: Boolean,
    @SerialName("self_deaf") val selfDeaf: Boolean
)

@Serializable
data class GroupDmAddRecipientRequestParameters(
    @SerialName("access_token") val accessToken: String,
    val nick: String
)

@Serializable
data class ModifyApplicationEmojiRequestParameters(
    val name: String
)

@Serializable
data class EditCurrentApplicationRequestParameters(
    @SerialName("custom_install_url") val customInstallUrl: Optional<String>? = null,
    val description: Optional<String>? = null,
    @SerialName("role_connection_verification_url") val roleConnectionVerificationUrl: Optional<String>? = null,
    @SerialName("install_params") val installParams: Optional<ApplicationInstallParamsObject>? = null,
    @SerialName("integration_types_config") val integrationTypesConfig: Optional<Map<String, ApplicationIntegrationTypeConfigurationObject>>? = null,
    val flags: Optional<Int?>? = null,
    val icon: Optional<ImageData>? = null,
    @SerialName("cover_image") val coverImage: Optional<ImageData>? = null,
    @SerialName("interactions_endpoint_url") val interactionsEndpointUrl: Optional<String>? = null,
    val tags: Optional<List<String>>? = null,
    @SerialName("event_webhooks_url") val eventWebhooksUrl: Optional<String>? = null,
    @SerialName("event_webhooks_status") val eventWebhooksStatus: Optional<Int>? = null,
    @SerialName("event_webhook_types") val eventWebhookTypes: Optional<List<String>>? = null,
)

@Serializable
data class ModifyCurrentUserRequestParameters(
    val username: String,
    val avatar: ImageData?,
    val banner: ImageData?
)

@Serializable
data class ModifyCurrentUserVoiceStateRequestParameters(
    @SerialName("channel_id") val channelId: Optional<Snowflake>? = null,
    val suppress: Optional<Boolean>? = null,
    @SerialName("request_to_speak_timestamp") val requestToSpeakTimestamp: Optional<ISO8601Timestamp>? = null
)

@Serializable
data class ModifyDmChannelRequestParameters(
    val name: Optional<String>? = null,
    val icon: Optional<String>? = null
)

@Serializable
data class ModifyGuildAutoModerationRuleRequestParameters(
    val name: String,
    @SerialName("event_type") val eventType: Int,
    @SerialName("trigger_metadata") val triggerMetadata: Optional<AutoModerationRuleTriggerMetadataObject>? = null,
    val actions: List<AutoModerationActionObject>,
    val enabled: Optional<Boolean>? = null,
    @SerialName("exempt_roles") val exemptRoles: Optional<List<Snowflake>>? = null,
    @SerialName("exempt_channels") val exemptChannels: Optional<List<Snowflake>>? = null,
)

@Serializable
data class ModifyGuildChannelPositionsRequestParameters(
    val id: Snowflake,
    val position: Optional<Int>? = null,
    @SerialName("lock_permissions") val lockPermissions: Optional<Boolean>? = null,
    @SerialName("parent_id") val parentId: Optional<Snowflake>? = null
)


@Serializable
data class ModifyGuildChannelRequestParameters(
    val name: Optional<String>? = null,
    val type: Optional<Int>? = null,
    val position: Optional<Int>? = null,
    val topic: Optional<String>? = null,
    val nsfw: Optional<Boolean>? = null,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: Optional<Int>? = null,
    val bitrate: Optional<Int>? = null,
    @SerialName("user_limit") val userLimit: Optional<Int>? = null,
    @SerialName("permission_overwrites") val permissionOverwrites: Optional<List<ChannelPermissionOverwriteObject>>? = null,
    @SerialName("parent_id") val parentId: Optional<Snowflake>? = null,
    @SerialName("rtc_region") val rtcRegion: Optional<String>? = null,
    @SerialName("video_quality_mode") val videoQualityMode: Optional<Int>? = null,
    @SerialName("default_auto_archive_duration") val defaultAutoArchiveDuration: Optional<Int>? = null,
    val flags: Optional<Int>? = null,
    @SerialName("available_tags") val availableTags: Optional<List<ForumTagObject>>? = null,
    @SerialName("default_reaction_emoji") val defaultReactionEmoji: Optional<ChannelDefaultReactionObject>? = null,
    @SerialName("default_thread_rate_limit_per_user") val defaultThreadRateLimitPerUser: Optional<Int>? = null,
    @SerialName("default_sort_order") val defaultSortOrder: Optional<Int>? = null,
    @SerialName("default_forum_layout") val defaultForumLayout: Optional<Int>? = null,
)

@Serializable
data class ModifyGuildCurrentMemberRequestParameters(
    val nick: Optional<String>? = null
)

@Serializable
data class ModifyGuildEmojiRequestParameters(
    val name: Optional<String>? = null,
    val roles: Optional<List<Snowflake>>? = null,
)

@Serializable
data class ModifyGuildMemberRequestParameters(
    val nick: Optional<String>? = null,
    val roles: Optional<List<Snowflake>>? = null,
    val mute: Optional<Boolean>? = null,
    val deaf: Optional<Boolean>? = null,
    @SerialName("channel_id") val channelId: Optional<Snowflake>? = null,
    @SerialName("communication_disabled_until") val communicationDisabledUntil: Optional<ISO8601Timestamp>? = null,
    val flags: Optional<Int>? = null
)

@Serializable
data class ModifyGuildMfaLevelRequestParameters(
    val level: Int
)

@Serializable
data class ModifyGuildOnboardingRequestParameters(
    val prompts: List<GuildOnboardingPromptObject>,
    @SerialName("default_channel_ids") val defaultChannelIds: List<Snowflake>,
    val enabled: Boolean,
    val mode: Int
)

@Serializable
data class ModifyGuildRequestParameters(
    val name: Optional<String>? = null,
    val region: Optional<String>? = null,
    @SerialName("verification_level") val verificationLevel: Optional<Int>? = null,
    @SerialName("default_message_notifications") val defaultMessageNotifications: Optional<Int>? = null,
    @SerialName("explicit_content_filter") val explicitContentFilter: Optional<Int>? = null,
    @SerialName("afk_channel_id") val afkChannelId: Optional<Snowflake>? = null,
    @SerialName("afk_timeout") val afkTimeout: Optional<Int>? = null,
    val icon: Optional<ImageData>? = null,
    @SerialName("owner_id") val ownerId: Optional<Snowflake>? = null,
    val splash: Optional<ImageData>? = null,
    @SerialName("discovery_splash") val discoverySplash: Optional<ImageData>? = null,
    val banner: Optional<ImageData>? = null,
    @SerialName("system_channel_id") val systemChannelId: Optional<Snowflake>? = null,
    @SerialName("system_channel_flags") val systemChannelFlags: Optional<Int>? = null,
    @SerialName("rules_channel_id") val rulesChannelId: Optional<Snowflake>? = null,
    @SerialName("public_updates_channel_id") val publicUpdatesChannelId: Optional<Snowflake>? = null,
    @SerialName("preferred_locale") val preferredLocale: Optional<String>? = null,
    val features: Optional<List<String>>? = null,
    val description: Optional<String>? = null,
    @SerialName("premium_progress_bar_enabled") val premiumProgressBarEnabled: Optional<Boolean>? = null,
    @SerialName("safety_alerts_channel_id") val safetyAlertsChannelId: Optional<Snowflake>? = null
)

@Serializable
data class ModifyGuildRolePositionRequestParameters(
    val id: Snowflake,
    val position: Optional<Int>? = null
)

@Serializable
data class ModifyGuildRoleRequestParameters(
    val name: Optional<String>? = null,
    val permissions: Optional<PermissionSet>? = null,
    val color: Optional<Int>? = null,
    val hoist: Optional<Boolean>? = null,
    val icon: Optional<ImageData>? = null,
    @SerialName("unicode_emoji") val unicodeEmoji: Optional<String>? = null,
    val mentionable: Optional<Boolean>? = null
)

@Serializable
data class ModifyGuildScheduledEventRequestParameters(
    val channelId: Optional<Snowflake>? = null,
    @SerialName("entity_metadata") val entityMetadata: Optional<GuildScheduledEventEntityMetadataObject>? = null,
    val name: Optional<String>? = null,
    @SerialName("privacy_level") val privacyLevel: Optional<Int>? = null,
    @SerialName("scheduled_start_time") val scheduledStartTime: Optional<ISO8601Timestamp>? = null,
    @SerialName("scheduled_end_time") val scheduledEndTime: Optional<ISO8601Timestamp>? = null,
    val description: Optional<String>? = null,
    @SerialName("entity_type") val entityType: Optional<Int>? = null,
    val status: Optional<Int>? = null,
    val image: Optional<ImageData>? = null,
    @SerialName("recurrence_rule") val recurrenceRule: Optional<GuildScheduledEventRecurrenceRuleObject>? = null
)

@Serializable
data class ModifyGuildSoundboardSoundRequestParameters(
    val name: Optional<String>? = null,
    val volume: Optional<Double>? = null,
    @SerialName("emoji_id") val emojiId: Optional<Snowflake>? = null,
    @SerialName("emoji_name") val emojiName: Optional<String>? = null
)

@Serializable
data class ModifyGuildStickerRequestParameters(
    val name: Optional<String>? = null,
    val description: Optional<String>? = null,
    val tags: Optional<String>? = null
)

@Serializable
data class ModifyGuildTemplateRequestParameters(
    val name: Optional<String>? = null,
    val description: Optional<String>? = null
)

@Serializable
data class ModifyGuildWelcomeScreenRequestParameters(
    val enabled: Optional<Boolean>? = null,
    @SerialName("welcome_channels") val welcomeChannels: Optional<List<GuildWelcomeScreenChannelObject>>? = null,
    val description: Optional<String>? = null
)

@Serializable
data class ModifyGuildWidgetSettingsRequestParameters(
    val enabled: Optional<Boolean>? = null,
    @SerialName("channel_id") val channelId: Optional<Snowflake>? = null
)

@Serializable
data class ModifyStageInstanceRequestParameters(
    val topic: Optional<String>? = null,
    @SerialName("privacy_level") val privacyLevel: Optional<Int>? = null,
)

@Serializable
data class ModifyThreadChannelRequestParameters(
    val name: Optional<String>? = null,
    val archived: Optional<Boolean>? = null,
    @SerialName("auto_archive_duration") val autoArchiveDuration: Optional<Int>? = null,
    val locked: Optional<Boolean>? = null,
    val invitable: Optional<Boolean>? = null,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: Optional<Int>? = null,
    val flags: Optional<Int>? = null,
    @SerialName("applied_tags") val appliedTags: Optional<List<Snowflake>>? = null
)

@Serializable
data class ModifyUserVoiceStateRequestParameters(
    @SerialName("channel_id") val channelId: Snowflake,
    val suppress: Optional<Boolean>? = null
)

@Serializable
data class ModifyWebhookRequestParameters(
    val name: Optional<String>? = null,
    val avatar: Optional<ImageData>? = null,
    @SerialName("channel_id") val channelId: Optional<Snowflake>? = null,
)

@Serializable
data class SendSoundboardSoundRequestParameters(
    @SerialName("sound_id") val soundId: Snowflake,
    @SerialName("source_guild_id") val sourceGuildId: Snowflake,
)

@Serializable
data class StartThreadFromMessageRequestParameters(
    val name: String,
    @SerialName("auto_archive_duration") val autoArchiveDuration: Optional<Int>? = null,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: Optional<Int>? = null,
)

@Serializable
data class StartThreadInForumOrMediaChannelRequestParameters(
    val name: String,
    val autoArchiveDuration: Optional<Int>? = null,
    val rateLimitPerUser: Optional<Int>? = null,
    val message: Optional<ForumThreadMessageParametersObject>? = null,
    val appliedTags: Optional<List<Snowflake>>? = null,
    val files: Optional<List<String>>? = null,
    val payloadJson: Optional<String>? = null
)

@Serializable
data class StartThreadWithoutMessageRequestParameters(
    val name: String,
    @SerialName("auto_archive_duration") val autoArchiveDuration: Optional<Int>? = null,
    val type: Optional<Int>? = null,
    val invitable: Boolean,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: Optional<Int>? = null
)

@Serializable
data class UpdateCurrentUserApplicationRoleConnectionRequestParameters(
    @SerialName("platform_name") val platformName: Optional<String>? = null,
    @SerialName("platform_username") val platformUsername: Optional<String>? = null,
    val metadata: Optional<Map<String, ApplicationRoleConnectionMetadataObject>>? = null
)