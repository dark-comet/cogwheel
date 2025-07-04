package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.*

@Serializable
data class AddGuildMemberRequestParameters(
    @SerialName("access_token") val accessToken: String,
    val nick: Possible<String>? = null,
    val roles: Possible<List<Snowflake>>? = null,
    val mute: Possible<Boolean>? = null,
    val deaf: Possible<Boolean>? = null
)

@Serializable
data class BeginGuildPruneRequestParameters(
    val days: Int = 7,
    val computePruneCount: Boolean = true,
    val includeRoles: MutableList<Snowflake> = mutableListOf(),
    val reason: Possible<String>? = null
)

@Serializable
data class BulkDeleteMessagesRequestParameters(
    val messages: List<Snowflake>
)

@Serializable
data class BulkGuildBanRequestParameters(
    @SerialName("user_ids") val userIds: List<Snowflake>,
    @SerialName("delete_message_seconds") val deleteMessageSeconds: Possible<Int>? = null
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
    @SerialName("trigger_metadata") val triggerMetadata: Possible<AutoModerationRuleTriggerMetadataObject>? = null,
    val actions: List<AutoModerationActionObject>,
    val enabled: Possible<Boolean>? = null,
    @SerialName("exempt_roles") val exemptRoles: Possible<List<Snowflake>>? = null,
    @SerialName("exempt_channels") val exemptChannels: Possible<List<Snowflake>>? = null,
)

@Serializable
data class CreateGuildBanRequestParameters(
    @SerialName("delete_message_days") val deleteMessageDays: Possible<Int>? = null,
    @SerialName("delete_message_seconds") val deleteMessageSeconds: Possible<Int>? = null,
)

@Serializable
data class CreateGuildChannelRequestParameters(
    val name: String,
    val type: Possible<Int>? = null,
    val topic: Possible<String>? = null,
    val bitrate: Possible<Int>? = null,
    @SerialName("user_limit") val userLimit: Possible<Int>? = null,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: Possible<Int>? = null,
    val position: Possible<Int>? = null,
    @SerialName("permission_overwrites") val permissionOverwrites: Possible<List<ChannelPermissionOverwriteObject>>? = null,
    @SerialName("parent_id") val parentId: Possible<Snowflake>? = null,
    val nsfw: Possible<Boolean>? = null,
    @SerialName("rtc_region") val rtcRegion: Possible<String>? = null,
    @SerialName("video_quality_mode") val videoQualityMode: Possible<Int>? = null,
    @SerialName("default_auto_archive_duration") val defaultAutoArchiveDuration: Possible<Int>? = null,
    @SerialName("default_reaction_emoji") val defaultReactionEmoji: Possible<ChannelDefaultReactionObject>? = null,
    @SerialName("available_tags") val availableTags: Possible<List<ForumTagObject>>? = null,
    @SerialName("default_sort_order") val defaultSortOrder: Possible<Int>? = null,
    @SerialName("default_forum_layout") val defaultForumLayout: Possible<Int>? = null,
    @SerialName("default_thread_rate_limit_per_user") val defaultThreadRateLimitPerUser: Possible<Int>? = null,
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
    val icon: Possible<ImageData>? = null
)

@Serializable
data class CreateGuildRequestParameters(
    val name: String,
    val region: Possible<String>? = null, // deprecated
    val icon: Possible<ImageData>? = null,
    @SerialName("verification_level") val verificationLevel: Possible<Int>? = null,
    @SerialName("default_message_notifications") val defaultMessageNotifications: Possible<Int>? = null,
    @SerialName("explicit_content_filter") val explicitContentFilter: Possible<Int>? = null,
    val roles: Possible<List<RoleObject>>? = null,
    val channels: Possible<List<ChannelObject>>? = null,
    @SerialName("afk_channel_id") val afkChannelId: Possible<Snowflake>? = null,
    @SerialName("afk_timeout") val afkTimeout: Possible<Int>? = null,
    @SerialName("system_channel_id") val systemChannelId: Possible<Snowflake>? = null,
    @SerialName("system_channel_flags") val systemChannelFlags: Possible<Int>? = null,
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
    val channelId: Possible<Snowflake>? = null,
    @SerialName("entity_metadata") val entityMetadata: Possible<GuildScheduledEventEntityMetadataObject>? = null,
    val name: String,
    @SerialName("privacy_level") val privacyLevel: Possible<Int>? = null,
    @SerialName("scheduled_start_time") val scheduledStartTime: ISO8601Timestamp,
    @SerialName("scheduled_end_time") val scheduledEndTime: Possible<ISO8601Timestamp>? = null,
    val description: Possible<String>? = null,
    @SerialName("entity_type") val entityType: Int,
    val image: Possible<ImageData>? = null,
    @SerialName("recurrence_rule") val recurrenceRule: Possible<GuildScheduledEventRecurrenceRuleObject>? = null
)

@Serializable
data class CreateGuildSoundboardSoundRequestParameters(
    val name: String,
    val sound: String,
    val volume: Possible<Double>? = null,
    @SerialName("emoji_id") val emojiId: Possible<Snowflake>? = null,
    @SerialName("emoji_name") val emojiName: Possible<String>? = null
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
    val description: Possible<String>? = null
)

@Serializable
data class CreateMessageRequestParameters(
    val content: Possible<String>? = null,
    val nonce: Possible<String>? = null,
    val tts: Possible<Boolean>? = null,
    val embeds: Possible<List<EmbedObject>>? = null,
    @SerialName("allowed_mentions") val allowedMentions: Possible<AllowedMentionsObject>? = null,
    @SerialName("message_reference") val messageReference: Possible<MessageReferenceObject>? = null,
    val components: Possible<List<MessageComponentObject>>? = null,
    @SerialName("sticker_ids") val stickerIds: Possible<List<Snowflake>>? = null,
//    val filesN
    @SerialName("payload_json") val payloadJson: Possible<String>? = null,
    val attachments: Possible<List<AttachmentObject>>? = null,
    val flags: Possible<Int>? = null,
    @SerialName("enforce_nonce") val enforceNonce: Possible<Boolean>? = null,
    val poll: Possible<PollObject>? = null
)

@Serializable
data class CreateStageInstanceRequestParameters(
    @SerialName("channel_id") val channelId: Snowflake,
    val topic: String,
    @SerialName("privacy_level") val privacyLevel: Possible<Int>? = null,
    @SerialName("send_start_notification") val sendStartNotification: Possible<Boolean>? = null,
    @SerialName("guild_scheduled_event_id") val guildScheduledEventId: Possible<Snowflake>? = null
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
    val avatar: Possible<ImageData>? = null,
)

@Serializable
data class EditChannelPermissionsParameters(
    val allow: Possible<PermissionSet>? = null,
    val deny: Possible<PermissionSet>? = null,
    val type: Int
)

@Serializable
data class EditMessageRequestParameters(
    val content: Possible<String>? = null,
    val embeds: Possible<List<EmbedObject>>? = null,
    val flags: Possible<Int>? = null,
    @SerialName("allowed_mentions") val allowedMentions: Possible<AllowedMentionsObject>? = null,
    val components: Possible<List<MessageComponentObject>>? = null,
//    val filesN
    @SerialName("payload_json") val payloadJson: Possible<String>? = null,
    val attachments: Possible<List<AttachmentObject>>? = null,
)

@Serializable
data class EditWebhookMessageRequestParameters(
    val content: Possible<String>? = null,
    val embeds: Possible<List<EmbedObject>>? = null,
    @SerialName("allowed_mentions") val allowedMentions: Possible<AllowedMentionsObject>? = null,
    val components: Possible<List<MessageComponentObject>>? = null,
//    val filesN
    @SerialName("payload_json") val payloadJson: Possible<String>? = null,
    val attachments: Possible<List<AttachmentObject>>? = null,
    val poll: Possible<PollObject>? = null
)

@Serializable
data class ExecuteWebhookRequestParameters(
    val content: Possible<String>? = null,
    val username: Possible<String>? = null,
    @SerialName("avatar_url") val avatarUrl: Possible<String>? = null,
    val tts: Possible<Boolean>? = null,
    val embeds: Possible<List<EmbedObject>>? = null,
    @SerialName("allowed_mentions") val allowedMentions: Possible<AllowedMentionsObject>? = null,
    val components: Possible<List<MessageComponentObject>>? = null,
//    val filesN
    @SerialName("payload_json") val payloadJson: Possible<String>? = null,
    val attachments: Possible<List<AttachmentObject>>? = null,
    val flags: Possible<Int>? = null,
    @SerialName("thread_name") val threadName: Possible<String>? = null,
    @SerialName("applied_tags") val appliedTags: Possible<List<Snowflake>>? = null,
    val poll: Possible<PollObject>? = null
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
    val query: Possible<String>? = null,
    val limit: Int,
    val presences: Possible<Boolean>? = null,
    @SerialName("user_ids") val userIds: Possible<List<Snowflake>>? = null,
    val nonce: Possible<String>? = null
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
    @SerialName("custom_install_url") val customInstallUrl: Possible<String>? = null,
    val description: Possible<String>? = null,
    @SerialName("role_connection_verification_url") val roleConnectionVerificationUrl: Possible<String>? = null,
    @SerialName("install_params") val installParams: Possible<ApplicationInstallParamsObject>? = null,
    @SerialName("integration_types_config") val integrationTypesConfig: Possible<Map<String, ApplicationIntegrationTypeConfigurationObject>>? = null,
    val flags: Possible<Int>? = null,
    val icon: Possible<ImageData?>? = null,
    @SerialName("cover_image") val coverImage: Possible<ImageData?>? = null,
    @SerialName("interactions_endpoint_url") val interactionsEndpointUrl: Possible<String>? = null,
    val tags: Possible<List<String>>? = null,
    @SerialName("event_webhooks_url") val eventWebhooksUrl: Possible<String>? = null,
    @SerialName("event_webhooks_status") val eventWebhooksStatus: Possible<Int>? = null,
    @SerialName("event_webhook_types") val eventWebhookTypes: Possible<List<String>>? = null,
)

@Serializable
data class ModifyCurrentUserRequestParameters(
    val username: String,
    val avatar: ImageData?,
    val banner: ImageData?
)

@Serializable
data class ModifyCurrentUserVoiceStateRequestParameters(
    @SerialName("channel_id") val channelId: Possible<Snowflake>? = null,
    val suppress: Possible<Boolean>? = null,
    @SerialName("request_to_speak_timestamp") val requestToSpeakTimestamp: Possible<ISO8601Timestamp>? = null
)

@Serializable
data class ModifyDmChannelRequestParameters(
    val name: Possible<String>? = null,
    val icon: Possible<String>? = null
)

@Serializable
data class ModifyGuildAutoModerationRuleRequestParameters(
    val name: String,
    @SerialName("event_type") val eventType: Int,
    @SerialName("trigger_metadata") val triggerMetadata: Possible<AutoModerationRuleTriggerMetadataObject>? = null,
    val actions: List<AutoModerationActionObject>,
    val enabled: Possible<Boolean>? = null,
    @SerialName("exempt_roles") val exemptRoles: Possible<List<Snowflake>>? = null,
    @SerialName("exempt_channels") val exemptChannels: Possible<List<Snowflake>>? = null,
)

@Serializable
data class ModifyGuildChannelPositionsRequestParameters(
    val id: Snowflake,
    val position: Possible<Int>? = null,
    @SerialName("lock_permissions") val lockPermissions: Possible<Boolean>? = null,
    @SerialName("parent_id") val parentId: Possible<Snowflake>? = null
)


@Serializable
data class ModifyGuildChannelRequestParameters(
    val name: Possible<String>? = null,
    val type: Possible<Int>? = null,
    val position: Possible<Int>? = null,
    val topic: Possible<String>? = null,
    val nsfw: Possible<Boolean>? = null,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: Possible<Int>? = null,
    val bitrate: Possible<Int>? = null,
    @SerialName("user_limit") val userLimit: Possible<Int>? = null,
    @SerialName("permission_overwrites") val permissionOverwrites: Possible<List<ChannelPermissionOverwriteObject>>? = null,
    @SerialName("parent_id") val parentId: Possible<Snowflake>? = null,
    @SerialName("rtc_region") val rtcRegion: Possible<String>? = null,
    @SerialName("video_quality_mode") val videoQualityMode: Possible<Int>? = null,
    @SerialName("default_auto_archive_duration") val defaultAutoArchiveDuration: Possible<Int>? = null,
    val flags: Possible<Int>? = null,
    @SerialName("available_tags") val availableTags: Possible<List<ForumTagObject>>? = null,
    @SerialName("default_reaction_emoji") val defaultReactionEmoji: Possible<ChannelDefaultReactionObject>? = null,
    @SerialName("default_thread_rate_limit_per_user") val defaultThreadRateLimitPerUser: Possible<Int>? = null,
    @SerialName("default_sort_order") val defaultSortOrder: Possible<Int>? = null,
    @SerialName("default_forum_layout") val defaultForumLayout: Possible<Int>? = null,
)

@Serializable
data class ModifyGuildCurrentMemberRequestParameters(
    val nick: Possible<String>? = null
)

@Serializable
data class ModifyGuildEmojiRequestParameters(
    val name: Possible<String>? = null,
    val roles: Possible<List<Snowflake>>? = null,
)

@Serializable
data class ModifyGuildMemberRequestParameters(
    val nick: Possible<String>? = null,
    val roles: Possible<List<Snowflake>>? = null,
    val mute: Possible<Boolean>? = null,
    val deaf: Possible<Boolean>? = null,
    @SerialName("channel_id") val channelId: Possible<Snowflake>? = null,
    @SerialName("communication_disabled_until") val communicationDisabledUntil: Possible<ISO8601Timestamp>? = null,
    val flags: Possible<Int>? = null
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
    val name: Possible<String>? = null,
    val region: Possible<String>? = null,
    @SerialName("verification_level") val verificationLevel: Possible<Int>? = null,
    @SerialName("default_message_notifications") val defaultMessageNotifications: Possible<Int>? = null,
    @SerialName("explicit_content_filter") val explicitContentFilter: Possible<Int>? = null,
    @SerialName("afk_channel_id") val afkChannelId: Possible<Snowflake>? = null,
    @SerialName("afk_timeout") val afkTimeout: Possible<Int>? = null,
    val icon: Possible<ImageData>? = null,
    @SerialName("owner_id") val ownerId: Possible<Snowflake>? = null,
    val splash: Possible<ImageData>? = null,
    @SerialName("discovery_splash") val discoverySplash: Possible<ImageData>? = null,
    val banner: Possible<ImageData>? = null,
    @SerialName("system_channel_id") val systemChannelId: Possible<Snowflake>? = null,
    @SerialName("system_channel_flags") val systemChannelFlags: Possible<Int>? = null,
    @SerialName("rules_channel_id") val rulesChannelId: Possible<Snowflake>? = null,
    @SerialName("public_updates_channel_id") val publicUpdatesChannelId: Possible<Snowflake>? = null,
    @SerialName("preferred_locale") val preferredLocale: Possible<String>? = null,
    val features: Possible<List<String>>? = null,
    val description: Possible<String>? = null,
    @SerialName("premium_progress_bar_enabled") val premiumProgressBarEnabled: Possible<Boolean>? = null,
    @SerialName("safety_alerts_channel_id") val safetyAlertsChannelId: Possible<Snowflake>? = null
)

@Serializable
data class ModifyGuildRolePositionRequestParameters(
    val id: Snowflake,
    val position: Possible<Int>? = null
)

@Serializable
data class ModifyGuildRoleRequestParameters(
    val name: Possible<String>? = null,
    val permissions: Possible<PermissionSet>? = null,
    val color: Possible<Int>? = null,
    val hoist: Possible<Boolean>? = null,
    val icon: Possible<ImageData>? = null,
    @SerialName("unicode_emoji") val unicodeEmoji: Possible<String>? = null,
    val mentionable: Possible<Boolean>? = null
)

@Serializable
data class ModifyGuildScheduledEventRequestParameters(
    val channelId: Possible<Snowflake>? = null,
    @SerialName("entity_metadata") val entityMetadata: Possible<GuildScheduledEventEntityMetadataObject>? = null,
    val name: Possible<String>? = null,
    @SerialName("privacy_level") val privacyLevel: Possible<Int>? = null,
    @SerialName("scheduled_start_time") val scheduledStartTime: Possible<ISO8601Timestamp>? = null,
    @SerialName("scheduled_end_time") val scheduledEndTime: Possible<ISO8601Timestamp>? = null,
    val description: Possible<String>? = null,
    @SerialName("entity_type") val entityType: Possible<Int>? = null,
    val status: Possible<Int>? = null,
    val image: Possible<ImageData>? = null,
    @SerialName("recurrence_rule") val recurrenceRule: Possible<GuildScheduledEventRecurrenceRuleObject>? = null
)

@Serializable
data class ModifyGuildSoundboardSoundRequestParameters(
    val name: Possible<String>? = null,
    val volume: Possible<Double>? = null,
    @SerialName("emoji_id") val emojiId: Possible<Snowflake>? = null,
    @SerialName("emoji_name") val emojiName: Possible<String>? = null
)

@Serializable
data class ModifyGuildStickerRequestParameters(
    val name: Possible<String>? = null,
    val description: Possible<String>? = null,
    val tags: Possible<String>? = null
)

@Serializable
data class ModifyGuildTemplateRequestParameters(
    val name: Possible<String>? = null,
    val description: Possible<String>? = null
)

@Serializable
data class ModifyGuildWelcomeScreenRequestParameters(
    val enabled: Possible<Boolean>? = null,
    @SerialName("welcome_channels") val welcomeChannels: Possible<List<GuildWelcomeScreenChannelObject>>? = null,
    val description: Possible<String>? = null
)

@Serializable
data class ModifyGuildWidgetSettingsRequestParameters(
    val enabled: Possible<Boolean>? = null,
    @SerialName("channel_id") val channelId: Possible<Snowflake>? = null
)

@Serializable
data class ModifyStageInstanceRequestParameters(
    val topic: Possible<String>? = null,
    @SerialName("privacy_level") val privacyLevel: Possible<Int>? = null,
)

@Serializable
data class ModifyThreadChannelRequestParameters(
    val name: Possible<String>? = null,
    val archived: Possible<Boolean>? = null,
    @SerialName("auto_archive_duration") val autoArchiveDuration: Possible<Int>? = null,
    val locked: Possible<Boolean>? = null,
    val invitable: Possible<Boolean>? = null,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: Possible<Int>? = null,
    val flags: Possible<Int>? = null,
    @SerialName("applied_tags") val appliedTags: Possible<List<Snowflake>>? = null
)

@Serializable
data class ModifyUserVoiceStateRequestParameters(
    @SerialName("channel_id") val channelId: Snowflake,
    val suppress: Possible<Boolean>? = null
)

@Serializable
data class ModifyWebhookRequestParameters(
    val name: Possible<String>? = null,
    val avatar: Possible<ImageData>? = null,
    @SerialName("channel_id") val channelId: Possible<Snowflake>? = null,
)

@Serializable
data class SendSoundboardSoundRequestParameters(
    @SerialName("sound_id") val soundId: Snowflake,
    @SerialName("source_guild_id") val sourceGuildId: Snowflake,
)

@Serializable
data class StartThreadFromMessageRequestParameters(
    val name: String,
    @SerialName("auto_archive_duration") val autoArchiveDuration: Possible<Int>? = null,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: Possible<Int>? = null,
)

@Serializable
data class StartThreadInForumOrMediaChannelRequestParameters(
    val name: String,
    val autoArchiveDuration: Possible<Int>? = null,
    val rateLimitPerUser: Possible<Int>? = null,
    val message: Possible<ForumThreadMessageParametersObject>? = null,
    val appliedTags: Possible<List<Snowflake>>? = null,
    val files: Possible<List<String>>? = null,
    val payloadJson: Possible<String>? = null
)

@Serializable
data class StartThreadWithoutMessageRequestParameters(
    val name: String,
    @SerialName("auto_archive_duration") val autoArchiveDuration: Possible<Int>? = null,
    val type: Possible<Int>? = null,
    val invitable: Boolean,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: Possible<Int>? = null
)

@Serializable
data class UpdateCurrentUserApplicationRoleConnectionRequestParameters(
    @SerialName("platform_name") val platformName: Possible<String>? = null,
    @SerialName("platform_username") val platformUsername: Possible<String>? = null,
    val metadata: Possible<Map<String, ApplicationRoleConnectionMetadataObject>>? = null
)