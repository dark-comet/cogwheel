package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class AddGuildMemberRequestParameters(
    @SerialName("access_token") val accessToken: String,
    val nick: MaybeAbsent<String>? = null,
    val roles: MaybeAbsent<List<Snowflake>>? = null,
    val mute: MaybeAbsent<Boolean>? = null,
    val deaf: MaybeAbsent<Boolean>? = null
)

@Serializable
data class BeginGuildPruneRequestParameters(
    val days: Int = 7,
    val computePruneCount: Boolean = true,
    val includeRoles: MutableList<Snowflake> = mutableListOf(),
    val reason: MaybeAbsent<String>? = null
)

@Serializable
data class BulkDeleteMessagesRequestParameters(
    val messages: List<Snowflake>
)

@Serializable
data class BulkGuildBanRequestParameters(
    @SerialName("user_ids") val userIds: List<Snowflake>,
    @SerialName("delete_message_seconds") val deleteMessageSeconds: MaybeAbsent<Int>? = null
)

@Serializable
data class CreateApplicationEmojiRequestParameters(
    val name: String,
    val image: String
)

@Serializable
data class CreateChannelInviteRequestParameters(
    @SerialName("max_age") val maxAge: MaybeAbsent<Int>? = null,
    @SerialName("max_uses") val maxUses: MaybeAbsent<Int>? = null,
    val temporary: MaybeAbsent<Boolean>? = null,
    val unique: MaybeAbsent<Boolean>? = null,
    @SerialName("target_type") val targetType: MaybeAbsent<Int>? = null,
    @SerialName("target_user_id") val targetUserId: MaybeAbsent<Snowflake>? = null,
    @SerialName("target_application_id") val targetApplicationId: MaybeAbsent<Snowflake>? = null
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
    @SerialName("trigger_metadata") val triggerMetadata: MaybeAbsent<AutoModerationRuleTriggerMetadataObject>? = null,
    val actions: List<AutoModerationActionObject>,
    val enabled: MaybeAbsent<Boolean>? = null,
    @SerialName("exempt_roles") val exemptRoles: MaybeAbsent<List<Snowflake>>? = null,
    @SerialName("exempt_channels") val exemptChannels: MaybeAbsent<List<Snowflake>>? = null,
)

@Serializable
data class CreateGuildBanRequestParameters(
    @SerialName("delete_message_days") val deleteMessageDays: MaybeAbsent<Int>? = null,
    @SerialName("delete_message_seconds") val deleteMessageSeconds: MaybeAbsent<Int>? = null,
)

@Serializable
data class CreateGuildChannelRequestParameters(
    val name: String,
    val type: MaybeAbsent<Int>? = null,
    val topic: MaybeAbsent<String>? = null,
    val bitrate: MaybeAbsent<Int>? = null,
    @SerialName("user_limit") val userLimit: MaybeAbsent<Int>? = null,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: MaybeAbsent<Int>? = null,
    val position: MaybeAbsent<Int>? = null,
    @SerialName("permission_overwrites") val permissionOverwrites: MaybeAbsent<List<ChannelPermissionOverwriteObject>>? = null,
    @SerialName("parent_id") val parentId: MaybeAbsent<Snowflake>? = null,
    val nsfw: MaybeAbsent<Boolean>? = null,
    @SerialName("rtc_region") val rtcRegion: MaybeAbsent<String>? = null,
    @SerialName("video_quality_mode") val videoQualityMode: MaybeAbsent<Int>? = null,
    @SerialName("default_auto_archive_duration") val defaultAutoArchiveDuration: MaybeAbsent<Int>? = null,
    @SerialName("default_reaction_emoji") val defaultReactionEmoji: MaybeAbsent<ChannelDefaultReactionObject>? = null,
    @SerialName("available_tags") val availableTags: MaybeAbsent<List<ForumTagObject>>? = null,
    @SerialName("default_sort_order") val defaultSortOrder: MaybeAbsent<Int>? = null,
    @SerialName("default_forum_layout") val defaultForumLayout: MaybeAbsent<Int>? = null,
    @SerialName("default_thread_rate_limit_per_user") val defaultThreadRateLimitPerUser: MaybeAbsent<Int>? = null,
)

@Serializable
data class CreateGuildEmojiRequestParameters(
    val name: String,
    val image: String,
    val roles: List<Snowflake>
)

@Serializable
data class CreateGuildFromGuildTemplateRequestParameters(
    val name: String,
    val icon: MaybeAbsent<String>? = null
)

@Serializable
data class CreateGuildRequestParameters(
    val name: String,
    val region: MaybeAbsent<String>? = null, // deprecated
    val icon: MaybeAbsent<String>? = null,
    @SerialName("verification_level") val verificationLevel: MaybeAbsent<Int>? = null,
    @SerialName("default_message_notifications") val defaultMessageNotifications: MaybeAbsent<Int>? = null,
    @SerialName("explicit_content_filter") val explicitContentFilter: MaybeAbsent<Int>? = null,
    val roles: MaybeAbsent<List<RoleObject>>? = null,
    val channels: MaybeAbsent<List<ChannelObject>>? = null,
    @SerialName("afk_channel_id") val afkChannelId: MaybeAbsent<Snowflake>? = null,
    @SerialName("afk_timeout") val afkTimeout: MaybeAbsent<Int>? = null,
    @SerialName("system_channel_id") val systemChannelId: MaybeAbsent<Snowflake>? = null,
    @SerialName("system_channel_flags") val systemChannelFlags: MaybeAbsent<Int>? = null,
)

@Serializable
data class CreateGuildRoleRequestParameters(
    val name: String,
    val permissions: String,
    val color: Int,
    val hoist: Boolean,
    val icon: String?,
    @SerialName("unicode_emoji") val unicodeEmoji: String?,
    val mentionable: Boolean
)

@Serializable
data class CreateGuildScheduledEventRequestParameters(
    val channelId: MaybeAbsent<Snowflake>? = null,
    @SerialName("entity_metadata") val entityMetadata: MaybeAbsent<GuildScheduledEventEntityMetadataObject>? = null,
    val name: String,
    @SerialName("privacy_level") val privacyLevel: Int? = null,
    @SerialName("scheduled_start_time") val scheduledStartTime: ISO8601Timestamp,
    @SerialName("scheduled_end_time") val scheduledEndTime: MaybeAbsent<ISO8601Timestamp>? = null,
    val description: MaybeAbsent<String>? = null,
    @SerialName("entity_type") val entityType: Int,
    val image: MaybeAbsent<String>? = null,
    @SerialName("recurrence_rule") val recurrenceRule: MaybeAbsent<GuildScheduledEventRecurrenceRuleObject>? = null
)

@Serializable
data class CreateGuildSoundboardSoundRequestParameters(
    val name: String,
    val sound: String,
    val volume: MaybeAbsent<Double?>? = null,
    @SerialName("emoji_id") val emojiId: MaybeAbsent<Snowflake?>? = null,
    @SerialName("emoji_name") val emojiName: MaybeAbsent<String?>? = null
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
    val description: MaybeAbsent<String>? = null
)

@Serializable
data class CreateMessageRequestParameters(
    val content: MaybeAbsent<String>? = null,
    val nonce: MaybeAbsent<String>? = null,
    val tts: MaybeAbsent<Boolean>? = null,
    val embeds: MaybeAbsent<List<EmbedObject>>? = null,
    @SerialName("allowed_mentions") val allowedMentions: MaybeAbsent<AllowedMentionsObject>? = null,
    @SerialName("message_reference") val messageReference: MaybeAbsent<MessageReferenceObject>? = null,
    val components: MaybeAbsent<List<MessageComponentObject>>? = null,
    @SerialName("sticker_ids") val stickerIds: MaybeAbsent<List<Snowflake>>? = null,
//    val filesN
    @SerialName("payload_json") val payloadJson: MaybeAbsent<String>? = null,
    val attachments: MaybeAbsent<List<AttachmentObject>>? = null,
    val flags: MaybeAbsent<Int>? = null,
    @SerialName("enforce_nonce") val enforceNonce: MaybeAbsent<Boolean>? = null,
    val poll: MaybeAbsent<PollObject>? = null
)

@Serializable
data class CreateStageInstanceRequestParameters(
    @SerialName("channel_id") val channelId: Snowflake,
    val topic: String,
    @SerialName("privacy_level") val privacyLevel: MaybeAbsent<Int>? = null,
    @SerialName("send_start_notification") val sendStartNotification: MaybeAbsent<Boolean>? = null,
    @SerialName("guild_scheduled_event_id") val guildScheduledEventId: MaybeAbsent<Snowflake>? = null
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
    val avatar: MaybeAbsent<String>? = null,
)

@Serializable
data class EditChannelPermissionsParameters(
    val allow: MaybeAbsent<String?>? = null,
    val deny: MaybeAbsent<String?>? = null,
    val type: Int
)

@Serializable
data class EditMessageRequestParameters(
    val content: MaybeAbsent<String>? = null,
    val embeds: MaybeAbsent<List<EmbedObject>>? = null,
    val flags: MaybeAbsent<Int>? = null,
    @SerialName("allowed_mentions") val allowedMentions: MaybeAbsent<AllowedMentionsObject>? = null,
    val components: MaybeAbsent<List<MessageComponentObject>>? = null,
//    val filesN
    @SerialName("payload_json") val payloadJson: MaybeAbsent<String>? = null,
    val attachments: MaybeAbsent<List<AttachmentObject>>? = null,
)

@Serializable
data class EditWebhookMessageRequestParameters(
    val content: MaybeAbsent<String>? = null,
    val embeds: MaybeAbsent<List<EmbedObject>>? = null,
    @SerialName("allowed_mentions") val allowedMentions: MaybeAbsent<AllowedMentionsObject>? = null,
    val components: MaybeAbsent<List<MessageComponentObject>>? = null,
//    val filesN
    @SerialName("payload_json") val payloadJson: MaybeAbsent<String>? = null,
    val attachments: MaybeAbsent<List<AttachmentObject>>? = null,
    val poll: MaybeAbsent<PollObject>? = null
)

@Serializable
data class ExecuteWebhookRequestParameters(
    val content: MaybeAbsent<String>? = null,
    val username: MaybeAbsent<String>? = null,
    @SerialName("avatar_url") val avatarUrl: MaybeAbsent<String>? = null,
    val tts: MaybeAbsent<Boolean>? = null,
    val embeds: MaybeAbsent<List<EmbedObject>>? = null,
    @SerialName("allowed_mentions") val allowedMentions: MaybeAbsent<AllowedMentionsObject>? = null,
    val components: MaybeAbsent<List<MessageComponentObject>>? = null,
//    val filesN
    @SerialName("payload_json") val payloadJson: MaybeAbsent<String>? = null,
    val attachments: MaybeAbsent<List<AttachmentObject>>? = null,
    val flags: MaybeAbsent<Int>? = null,
    @SerialName("thread_name") val threadName: MaybeAbsent<String>? = null,
    @SerialName("applied_tags") val appliedTags: MaybeAbsent<List<Snowflake>>? = null,
    val poll: MaybeAbsent<PollObject>? = null
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
    val query: MaybeAbsent<String>? = null,
    val limit: Int,
    val presences: MaybeAbsent<Boolean>? = null,
    @SerialName("user_ids") val userIds: MaybeAbsent<List<Snowflake>>? = null,
    val nonce: MaybeAbsent<String>? = null
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
    val name: String,
    val image: String
)

@Serializable
data class EditCurrentApplicationRequestParameters(
    @SerialName("custom_install_url") val customInstallUrl: MaybeAbsent<String>? = null,
    val description: MaybeAbsent<String>? = null,
    @SerialName("role_connection_verification_url") val roleConnectionVerificationUrl: MaybeAbsent<String>? = null,
    @SerialName("install_params") val installParams: MaybeAbsent<ApplicationInstallParamsObject>? = null,
    @SerialName("integration_types_config") val integrationTypesConfig: MaybeAbsent<Map<String, ApplicationIntegrationTypeConfigurationObject>>? = null,
    val flags: MaybeAbsent<Int>? = null,
    val icon: MaybeAbsent<String?>? = null,
    @SerialName("cover_image") val coverImage: MaybeAbsent<String?>? = null,
    @SerialName("interactions_endpoint_url") val interactionsEndpointUrl: MaybeAbsent<String>? = null,
    val tags: MaybeAbsent<List<String>>? = null,
    @SerialName("event_webhooks_url") val eventWebhooksUrl: MaybeAbsent<String>? = null,
    @SerialName("event_webhooks_status") val eventWebhooksStatus: MaybeAbsent<Int>? = null,
    @SerialName("event_webhook_types") val eventWebhookTypes: MaybeAbsent<List<String>>? = null,
)

@Serializable
data class ModifyCurrentUserRequestParameters(
    val username: String,
    val avatar: String?,
    val banner: String?
)

@Serializable
data class ModifyCurrentUserVoiceStateRequestParameters(
    @SerialName("channel_id") val channelId: MaybeAbsent<Snowflake>? = null,
    val suppress: MaybeAbsent<Boolean>? = null,
    @SerialName("request_to_speak_timestamp") val requestToSpeakTimestamp: MaybeAbsent<ISO8601Timestamp?>? = null
)

@Serializable
data class ModifyDmChannelRequestParameters(
    val name: MaybeAbsent<String>? = null,
    val icon: MaybeAbsent<String>? = null
)

@Serializable
data class ModifyGuildAutoModerationRuleRequestParameters(
    val name: MaybeAbsent<String>? = null,
    @SerialName("event_type") val eventType: MaybeAbsent<Int>? = null,
    @SerialName("trigger_metadata") val triggerMetadata: MaybeAbsent<AutoModerationRuleTriggerMetadataObject>? = null,
    val actions: MaybeAbsent<List<AutoModerationActionObject>>? = null,
    val enabled: MaybeAbsent<Boolean>? = null,
    @SerialName("exempt_roles") val exemptRoles: MaybeAbsent<List<Snowflake>>? = null,
    @SerialName("exempt_channels") val exemptChannels: MaybeAbsent<List<Snowflake>>? = null,
)

@Serializable
data class ModifyGuildChannelPositionsRequestParameters(
    val id: Snowflake,
    val position: MaybeAbsent<Int>? = null,
    @SerialName("lock_permissions") val lockPermissions: MaybeAbsent<Boolean>? = null,
    @SerialName("parent_id") val parentId: MaybeAbsent<Snowflake>? = null
)


@Serializable
data class ModifyGuildChannelRequestParameters(
    val name: MaybeAbsent<String>? = null,
    val type: MaybeAbsent<Int>? = null,
    val position: MaybeAbsent<Int?>? = null,
    val topic: MaybeAbsent<String?>? = null,
    val nsfw: MaybeAbsent<Boolean?>? = null,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: MaybeAbsent<Int?>? = null,
    val bitrate: MaybeAbsent<Int?>? = null,
    @SerialName("user_limit") val userLimit: MaybeAbsent<Int?>? = null,
    @SerialName("permission_overwrites") val permissionOverwrites: MaybeAbsent<List<ChannelPermissionOverwriteObject>?>? = null,
    @SerialName("parent_id") val parentId: MaybeAbsent<Snowflake?>? = null,
    @SerialName("rtc_region") val rtcRegion: MaybeAbsent<String?>? = null,
    @SerialName("video_quality_mode") val videoQualityMode: MaybeAbsent<Int?>? = null,
    @SerialName("default_auto_archive_duration") val defaultAutoArchiveDuration: MaybeAbsent<Int?>? = null,
    val flags: MaybeAbsent<Int>? = null,
    @SerialName("available_tags") val availableTags: MaybeAbsent<List<ForumTagObject>>? = null,
    @SerialName("default_reaction_emoji") val defaultReactionEmoji: MaybeAbsent<ChannelDefaultReactionObject?>? = null,
    @SerialName("default_thread_rate_limit_per_user") val defaultThreadRateLimitPerUser: MaybeAbsent<Int>? = null,
    @SerialName("default_sort_order") val defaultSortOrder: MaybeAbsent<Int?>? = null,
    @SerialName("default_forum_layout") val defaultForumLayout: MaybeAbsent<Int>? = null,
)

@Serializable
data class ModifyGuildCurrentMemberRequestParameters(
    val nick: MaybeAbsent<String>? = null
)

@Serializable
data class ModifyGuildEmojiRequestParameters(
    val name: MaybeAbsent<String>? = null,
    val roles: MaybeAbsent<List<Snowflake>>? = null,
)

@Serializable
data class ModifyGuildMemberRequestParameters(
    val nick: MaybeAbsent<String>? = null,
    val roles: MaybeAbsent<List<Snowflake>>? = null,
    val mute: MaybeAbsent<Boolean>? = null,
    val deaf: MaybeAbsent<Boolean>? = null,
    @SerialName("channel_id") val channelId: MaybeAbsent<Snowflake>? = null,
    @SerialName("communication_disabled_until") val communicationDisabledUntil: MaybeAbsent<ISO8601Timestamp>? = null,
    val flags: MaybeAbsent<Int>? = null
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
    val name: MaybeAbsent<String>? = null,
    val region: MaybeAbsent<String>? = null,
    @SerialName("verification_level") val verificationLevel: MaybeAbsent<Int>? = null,
    @SerialName("default_message_notifications") val defaultMessageNotifications: MaybeAbsent<Int>? = null,
    @SerialName("explicit_content_filter") val explicitContentFilter: MaybeAbsent<Int>? = null,
    @SerialName("afk_channel_id") val afkChannelId: MaybeAbsent<Snowflake>? = null,
    @SerialName("afk_timeout") val afkTimeout: MaybeAbsent<Int>? = null,
    val icon: MaybeAbsent<String>? = null,
    @SerialName("owner_id") val ownerId: MaybeAbsent<Snowflake>? = null,
    val splash: MaybeAbsent<String>? = null,
    @SerialName("discovery_splash") val discoverySplash: MaybeAbsent<String>? = null,
    val banner: MaybeAbsent<String>? = null,
    @SerialName("system_channel_id") val systemChannelId: MaybeAbsent<Snowflake>? = null,
    @SerialName("system_channel_flags") val systemChannelFlags: MaybeAbsent<Int>? = null,
    @SerialName("rules_channel_id") val rulesChannelId: MaybeAbsent<Snowflake>? = null,
    @SerialName("public_updates_channel_id") val publicUpdatesChannelId: MaybeAbsent<Snowflake>? = null,
    @SerialName("preferred_locale") val preferredLocale: MaybeAbsent<String>? = null,
    val features: MaybeAbsent<List<String>>? = null,
    val description: MaybeAbsent<String>? = null,
    @SerialName("premium_progress_bar_enabled") val premiumProgressBarEnabled: MaybeAbsent<Boolean>? = null,
    @SerialName("safety_alerts_channel_id") val safetyAlertsChannelId: MaybeAbsent<Snowflake>? = null
)

@Serializable
data class ModifyGuildRolePositionRequestParameters(
    val id: Snowflake,
    val position: MaybeAbsent<Int>? = null
)

@Serializable
data class ModifyGuildRoleRequestParameters(
    val name: MaybeAbsent<String>? = null,
    val permissions: MaybeAbsent<String>? = null,
    val color: MaybeAbsent<Int>? = null,
    val hoist: MaybeAbsent<Boolean>? = null,
    val icon: MaybeAbsent<String>? = null,
    @SerialName("unicode_emoji") val unicodeEmoji: MaybeAbsent<String>? = null,
    val mentionable: MaybeAbsent<Boolean>? = null
)

@Serializable
data class ModifyGuildScheduledEventRequestParameters(
    val channelId: MaybeAbsent<Snowflake?>? = null,
    @SerialName("entity_metadata") val entityMetadata: MaybeAbsent<GuildScheduledEventEntityMetadataObject?>? = null,
    val name: MaybeAbsent<String>? = null,
    @SerialName("privacy_level") val privacyLevel: MaybeAbsent<Int>? = null,
    @SerialName("scheduled_start_time") val scheduledStartTime: MaybeAbsent<ISO8601Timestamp>? = null,
    @SerialName("scheduled_end_time") val scheduledEndTime: MaybeAbsent<ISO8601Timestamp>? = null,
    val description: MaybeAbsent<String?>? = null,
    @SerialName("entity_type") val entityType: MaybeAbsent<Int>? = null,
    val status: MaybeAbsent<Int>? = null,
    val image: MaybeAbsent<String>? = null,
    @SerialName("recurrence_rule") val recurrenceRule: MaybeAbsent<GuildScheduledEventRecurrenceRuleObject?>? = null
)

@Serializable
data class ModifyGuildSoundboardSoundRequestParameters(
    val name: MaybeAbsent<String>? = null,
    val sound: MaybeAbsent<String>? = null,
    val volume: MaybeAbsent<Double?>? = null,
    @SerialName("emoji_id") val emojiId: MaybeAbsent<Snowflake?>? = null,
    @SerialName("emoji_name") val emojiName: MaybeAbsent<String?>? = null
)

@Serializable
data class ModifyGuildStickerRequestParameters(
    val name: MaybeAbsent<String>? = null,
    val description: MaybeAbsent<String?>? = null,
    val tags: MaybeAbsent<String>? = null
)

@Serializable
data class ModifyGuildTemplateRequestParameters(
    val name: MaybeAbsent<String>? = null,
    val description: MaybeAbsent<String>? = null
)

@Serializable
data class ModifyGuildWelcomeScreenRequestParameters(
    val enabled: MaybeAbsent<Boolean>? = null,
    @SerialName("welcome_channels") val welcomeChannels: MaybeAbsent<List<GuildWelcomeScreenChannelObject>>? = null,
    val description: MaybeAbsent<String>? = null
)

@Serializable
data class ModifyGuildWidgetSettingsRequestParameters(
    val enabled: MaybeAbsent<Boolean>? = null,
    @SerialName("channel_id") val channelId: MaybeAbsent<Snowflake>? = null
)

@Serializable
data class ModifyStageInstanceRequestParameters(
    val topic: MaybeAbsent<String>? = null,
    @SerialName("privacy_level") val privacyLevel: MaybeAbsent<Int>? = null,
)

@Serializable
data class ModifyThreadChannelRequestParameters(
    val name: MaybeAbsent<String>? = null,
    val archived: MaybeAbsent<Boolean>? = null,
    @SerialName("auto_archive_duration") val autoArchiveDuration: MaybeAbsent<Int>? = null,
    val locked: MaybeAbsent<Boolean>? = null,
    val invitable: MaybeAbsent<Boolean>? = null,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: MaybeAbsent<Int?>? = null,
    val flags: MaybeAbsent<Int>? = null,
    @SerialName("applied_tags") val appliedTags: MaybeAbsent<List<Snowflake>>? = null
)

@Serializable
data class ModifyUserVoiceStateRequestParameters(
    @SerialName("channel_id") val channelId: Snowflake,
    val suppress: MaybeAbsent<Boolean>? = null
)

@Serializable
data class ModifyWebhookRequestParameters(
    val name: MaybeAbsent<String>? = null,
    val avatar: MaybeAbsent<String>? = null,
    @SerialName("channel_id") val channelId: MaybeAbsent<Snowflake>? = null,
)

@Serializable
data class SendSoundboardSoundRequestParameters(
    @SerialName("sound_id") val soundId: Snowflake,
    @SerialName("source_guild_id") val sourceGuildId: MaybeAbsent<Snowflake>? = null,
)

@Serializable
data class StartThreadFromMessageRequestParameters(
    val name: String,
    @SerialName("auto_archive_duration") val autoArchiveDuration: MaybeAbsent<Int>? = null,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: MaybeAbsent<Int?>? = null,
)

@Serializable
data class StartThreadInForumOrMediaChannelRequestParameters(
    val name: String,
    val autoArchiveDuration: MaybeAbsent<Int>? = null,
    val rateLimitPerUser: MaybeAbsent<Int>? = null,
    val message: MaybeAbsent<ForumThreadMessageParametersObject>? = null,
    val appliedTags: MaybeAbsent<List<Snowflake>>? = null,
    val files: MaybeAbsent<List<String>>? = null,
    val payloadJson: MaybeAbsent<String>? = null
)

@Serializable
data class StartThreadWithoutMessageRequestParameters(
    val name: String,
    @SerialName("auto_archive_duration") val autoArchiveDuration: MaybeAbsent<Int>? = null,
    val type: MaybeAbsent<Int>? = null,
    val invitable: MaybeAbsent<Boolean>? = null,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: MaybeAbsent<Int?>? = null
)

@Serializable
data class UpdateCurrentUserApplicationRoleConnectionRequestParameters(
    @SerialName("platform_name") val platformName: MaybeAbsent<String>? = null,
    @SerialName("platform_username") val platformUsername: MaybeAbsent<String>? = null,
    val metadata: MaybeAbsent<Map<String, ApplicationRoleConnectionMetadataObject>>? = null
)