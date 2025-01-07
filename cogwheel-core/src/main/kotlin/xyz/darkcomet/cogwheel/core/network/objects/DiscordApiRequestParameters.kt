package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.ImageData
import xyz.darkcomet.cogwheel.core.primitives.PermissionSet
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class AddGuildMemberRequestParameters(
    @SerialName("access_token") val accessToken: String,
    val nick: String? = null,
    val roles: List<Snowflake>? = null,
    val mute: Boolean? = null,
    val deaf: Boolean? = null
)

@Serializable
data class BeginGuildPruneRequestParameters(
    val days: Int = 7,
    val computePruneCount: Boolean = true,
    val includeRoles: MutableList<Snowflake> = mutableListOf(),
    val reason: String? = null
)

@Serializable
data class BulkDeleteMessagesRequestParameters(
    val messages: List<Snowflake>
)

@Serializable
data class BulkGuildBanRequestParameters(
    @SerialName("user_ids") val userIds: List<Snowflake>,
    @SerialName("delete_message_seconds") val deleteMessageSeconds: Int? = null
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
    @SerialName("trigger_metadata") val triggerMetadata: AutoModerationRuleTriggerMetadataObject? = null,
    val actions: List<AutoModerationActionObject>,
    val enabled: Boolean? = null,
    @SerialName("exempt_roles") val exemptRoles: List<Snowflake>? = null,
    @SerialName("exempt_channels") val exemptChannels: List<Snowflake>? = null,
)

@Serializable
data class CreateGuildBanRequestParameters(
    @SerialName("delete_message_days") val deleteMessageDays: Int? = null,
    @SerialName("delete_message_seconds") val deleteMessageSeconds: Int? = null,
)

@Serializable
data class CreateGuildChannelRequestParameters(
    val name: String,
    val type: Int? = null,
    val topic: String? = null,
    val bitrate: Int? = null,
    @SerialName("user_limit") val userLimit: Int? = null,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: Int? = null,
    val position: Int? = null,
    @SerialName("permission_overwrites") val permissionOverwrites: List<ChannelPermissionOverwriteObject>? = null,
    @SerialName("parent_id") val parentId: Snowflake? = null,
    val nsfw: Boolean? = null,
    @SerialName("rtc_region") val rtcRegion: String? = null,
    @SerialName("video_quality_mode") val videoQualityMode: Int? = null,
    @SerialName("default_auto_archive_duration") val defaultAutoArchiveDuration: Int? = null,
    @SerialName("default_reaction_emoji") val defaultReactionEmoji: ChannelDefaultReactionObject? = null,
    @SerialName("available_tags") val availableTags: List<ForumTagObject>? = null,
    @SerialName("default_sort_order") val defaultSortOrder: Int? = null,
    @SerialName("default_forum_layout") val defaultForumLayout: Int? = null,
    @SerialName("default_thread_rate_limit_per_user") val defaultThreadRateLimitPerUser: Int? = null,
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
    val icon: ImageData? = null
)

@Serializable
data class CreateGuildRequestParameters(
    val name: String,
    val region: String? = null, // deprecated
    val icon: ImageData? = null,
    @SerialName("verification_level") val verificationLevel: Int? = null,
    @SerialName("default_message_notifications") val defaultMessageNotifications: Int? = null,
    @SerialName("explicit_content_filter") val explicitContentFilter: Int? = null,
    val roles: List<RoleObject>? = null,
    val channels: List<ChannelObject>? = null,
    @SerialName("afk_channel_id") val afkChannelId: Snowflake? = null,
    @SerialName("afk_timeout") val afkTimeout: Int? = null,
    @SerialName("system_channel_id") val systemChannelId: Snowflake? = null,
    @SerialName("system_channel_flags") val systemChannelFlags: Int? = null,
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
    val channelId: Snowflake? = null,
    @SerialName("entity_metadata") val entityMetadata: GuildScheduledEventEntityMetadataObject? = null,
    val name: String,
    @SerialName("privacy_level") val privacyLevel: Int? = null,
    @SerialName("scheduled_start_time") val scheduledStartTime: ISO8601Timestamp,
    @SerialName("scheduled_end_time") val scheduledEndTime: ISO8601Timestamp? = null,
    val description: String? = null,
    @SerialName("entity_type") val entityType: Int,
    val image: ImageData? = null,
    @SerialName("recurrence_rule") val recurrenceRule: GuildScheduledEventRecurrenceRuleObject? = null
)

@Serializable
data class CreateGuildSoundboardSoundRequestParameters(
    val name: String,
    val sound: String,
    val volume: Double? = null,
    @SerialName("emoji_id") val emojiId: Snowflake? = null,
    @SerialName("emoji_name") val emojiName: String? = null
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
    val description: String? = null
)

@Serializable
data class CreateMessageRequestParameters(
    val content: String? = null,
    val nonce: String? = null,
    val tts: Boolean? = null,
    val embeds: List<EmbedObject>? = null,
    @SerialName("allowed_mentions") val allowedMentions: AllowedMentionsObject? = null,
    @SerialName("message_reference") val messageReference: MessageReferenceObject? = null,
    val components: List<MessageComponentObject>? = null,
    @SerialName("sticker_ids") val stickerIds: List<Snowflake>? = null,
//    val filesN
    @SerialName("payload_json") val payloadJson: String? = null,
    val attachments: List<AttachmentObject>? = null,
    val flags: Int? = null,
    @SerialName("enforce_nonce") val enforceNonce: Boolean? = null,
    val poll: PollObject? = null
)

@Serializable
data class CreateStageInstanceRequestParameters(
    @SerialName("channel_id") val channelId: Snowflake,
    val topic: String,
    @SerialName("privacy_level") val privacyLevel: Int? = null,
    @SerialName("send_start_notification") val sendStartNotification: Boolean? = null,
    @SerialName("guild_scheduled_event_id") val guildScheduledEventId: Snowflake? = null
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
    val avatar: ImageData? = null,
)

@Serializable
data class EditChannelPermissionsParameters(
    val allow: PermissionSet? = null,
    val deny: PermissionSet? = null,
    val type: Int
)

@Serializable
data class EditMessageRequestParameters(
    val content: String? = null,
    val embeds: List<EmbedObject>? = null,
    val flags: Int? = null,
    @SerialName("allowed_mentions") val allowedMentions: AllowedMentionsObject? = null,
    val components: List<MessageComponentObject>? = null,
//    val filesN
    @SerialName("payload_json") val payloadJson: String? = null,
    val attachments: List<AttachmentObject>? = null,
)

@Serializable
data class EditWebhookMessageRequestParameters(
    val content: String? = null,
    val embeds: List<EmbedObject>? = null,
    @SerialName("allowed_mentions") val allowedMentions: AllowedMentionsObject? = null,
    val components: List<MessageComponentObject>? = null,
//    val filesN
    @SerialName("payload_json") val payloadJson: String? = null,
    val attachments: List<AttachmentObject>? = null,
    val poll: PollObject? = null
)

@Serializable
data class ExecuteWebhookRequestParameters(
    val content: String? = null,
    val username: String? = null,
    @SerialName("avatar_url") val avatarUrl: String? = null,
    val tts: Boolean? = null,
    val embeds: List<EmbedObject>? = null,
    @SerialName("allowed_mentions") val allowedMentions: AllowedMentionsObject? = null,
    val components: List<MessageComponentObject>? = null,
//    val filesN
    @SerialName("payload_json") val payloadJson: String? = null,
    val attachments: List<AttachmentObject>? = null,
    val flags: Int? = null,
    @SerialName("thread_name") val threadName: String? = null,
    @SerialName("applied_tags") val appliedTags: List<Snowflake>? = null,
    val poll: PollObject? = null
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
    val query: String? = null,
    val limit: Int,
    val presences: Boolean? = null,
    @SerialName("user_ids") val userIds: List<Snowflake>? = null,
    val nonce: String? = null
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
data class ModifyCurrentApplicationRequestParameters(
    @SerialName("custom_install_url") var customInstallUrl: String? = null,
    var description: String? = null,
    @SerialName("role_connection_verification_url") var roleConnectionVerificationUrl: String? = null,
    @SerialName("install_params") var installParams: ApplicationInstallParamsObject? = null,
    @SerialName("integration_types_config") var integrationTypesConfig: Map<String, ApplicationIntegrationTypeConfigurationObject>? = null,
    var flags: Int? = null,
    var icon: ImageData? = null, // image data
    @SerialName("cover_image") var coverImage: ImageData? = null, // image data
    @SerialName("interactions_endpoint_url") var interactionsEndpointUrl: String? = null,
    var tags: List<String>? = null
)

@Serializable
data class ModifyCurrentUserRequestParameters(
    val username: String,
    val avatar: ImageData?,
    val banner: ImageData?
)

@Serializable
data class ModifyCurrentUserVoiceStateRequestParameters(
    @SerialName("channel_id") val channelId: Snowflake? = null,
    val suppress: Boolean? = null,
    @SerialName("request_to_speak_timestamp") val requestToSpeakTimestamp: ISO8601Timestamp? = null
)

@Serializable
data class ModifyDmChannelRequestParameters(
    val name: String? = null,
    val icon: String? = null
)

@Serializable
data class ModifyGuildAutoModerationRuleRequestParameters(
    val name: String,
    @SerialName("event_type") val eventType: Int,
    @SerialName("trigger_metadata") val triggerMetadata: AutoModerationRuleTriggerMetadataObject? = null,
    val actions: List<AutoModerationActionObject>,
    val enabled: Boolean? = null,
    @SerialName("exempt_roles") val exemptRoles: List<Snowflake>? = null,
    @SerialName("exempt_channels") val exemptChannels: List<Snowflake>? = null,
)

@Serializable
data class ModifyGuildChannelPositionsRequestParameters(
    val id: Snowflake,
    val position: Int? = null,
    @SerialName("lock_permissions") val lockPermissions: Boolean? = null,
    @SerialName("parent_id") val parentId: Snowflake? = null
)


@Serializable
data class ModifyGuildChannelRequestParameters(
    val name: String? = null,
    val type: Int? = null,
    val position: Int? = null,
    val topic: String? = null,
    val nsfw: Boolean? = null,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: Int? = null,
    val bitrate: Int? = null,
    @SerialName("user_limit") val userLimit: Int? = null,
    @SerialName("permission_overwrites") val permissionOverwrites: List<ChannelPermissionOverwriteObject>? = null,
    @SerialName("parent_id") val parentId: Snowflake? = null,
    @SerialName("rtc_region") val rtcRegion: String? = null,
    @SerialName("video_quality_mode") val videoQualityMode: Int? = null,
    @SerialName("default_auto_archive_duration") val defaultAutoArchiveDuration: Int? = null,
    val flags: Int? = null,
    @SerialName("available_tags") val availableTags: List<ForumTagObject>? = null,
    @SerialName("default_reaction_emoji") val defaultReactionEmoji: ChannelDefaultReactionObject? = null,
    @SerialName("default_thread_rate_limit_per_user") val defaultThreadRateLimitPerUser: Int? = null,
    @SerialName("default_sort_order") val defaultSortOrder: Int? = null,
    @SerialName("default_forum_layout") val defaultForumLayout: Int? = null,
)

@Serializable
data class ModifyGuildCurrentMemberRequestParameters(
    val nick: String? = null
)

@Serializable
data class ModifyGuildEmojiRequestParameters(
    val name: String? = null,
    val roles: List<Snowflake>? = null,
)

@Serializable
data class ModifyGuildMemberRequestParameters(
    val nick: String? = null,
    val roles: List<Snowflake>? = null,
    val mute: Boolean? = null,
    val deaf: Boolean? = null,
    @SerialName("channel_id") val channelId: Snowflake? = null,
    @SerialName("communication_disabled_until") val communicationDisabledUntil: ISO8601Timestamp? = null,
    val flags: Int? = null
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
    val name: String? = null,
    val region: String? = null,
    @SerialName("verification_level") val verificationLevel: Int? = null,
    @SerialName("default_message_notifications") val defaultMessageNotifications: Int? = null,
    @SerialName("explicit_content_filter") val explicitContentFilter: Int? = null,
    @SerialName("afk_channel_id") val afkChannelId: Snowflake? = null,
    @SerialName("afk_timeout") val afkTimeout: Int? = null,
    val icon: ImageData? = null,
    @SerialName("owner_id") val ownerId: Snowflake? = null,
    val splash: ImageData? = null,
    @SerialName("discovery_splash") val discoverySplash: ImageData? = null,
    val banner: ImageData? = null,
    @SerialName("system_channel_id") val systemChannelId: Snowflake? = null,
    @SerialName("system_channel_flags") val systemChannelFlags: Int? = null,
    @SerialName("rules_channel_id") val rulesChannelId: Snowflake? = null,
    @SerialName("public_updates_channel_id") val publicUpdatesChannelId: Snowflake? = null,
    @SerialName("preferred_locale") val preferredLocale: String? = null,
    val features: List<String>? = null,
    val description: String? = null,
    @SerialName("premium_progress_bar_enabled") val premiumProgressBarEnabled: Boolean? = null,
    @SerialName("safety_alerts_channel_id") val safetyAlertsChannelId: Snowflake? = null
)

@Serializable
data class ModifyGuildRolePositionRequestParameters(
    val id: Snowflake,
    val position: Int? = null
)

@Serializable
data class ModifyGuildRoleRequestParameters(
    val name: String? = null,
    val permissions: PermissionSet? = null,
    val color: Int? = null,
    val hoist: Boolean? = null,
    val icon: ImageData? = null,
    @SerialName("unicode_emoji") val unicodeEmoji: String? = null,
    val mentionable: Boolean? = null
)

@Serializable
data class ModifyGuildScheduledEventRequestParameters(
    val channelId: Snowflake? = null,
    @SerialName("entity_metadata") val entityMetadata: GuildScheduledEventEntityMetadataObject? = null,
    val name: String? = null,
    @SerialName("privacy_level") val privacyLevel: Int? = null,
    @SerialName("scheduled_start_time") val scheduledStartTime: ISO8601Timestamp? = null,
    @SerialName("scheduled_end_time") val scheduledEndTime: ISO8601Timestamp? = null,
    val description: String? = null,
    @SerialName("entity_type") val entityType: Int? = null,
    val status: Int? = null,
    val image: ImageData? = null,
    @SerialName("recurrence_rule") val recurrenceRule: GuildScheduledEventRecurrenceRuleObject? = null
)

@Serializable
data class ModifyGuildSoundboardSoundRequestParameters(
    val name: String? = null,
    val volume: Double? = null,
    @SerialName("emoji_id") val emojiId: Snowflake? = null,
    @SerialName("emoji_name") val emojiName: String? = null
)

@Serializable
data class ModifyGuildStickerRequestParameters(
    val name: String? = null,
    val description: String? = null,
    val tags: String? = null
)

@Serializable
data class ModifyGuildTemplateRequestParameters(
    val name: String? = null,
    val description: String? = null
)

@Serializable
data class ModifyGuildWelcomeScreenRequestParameters(
    val enabled: Boolean? = null,
    @SerialName("welcome_channels") val welcomeChannels: List<GuildWelcomeScreenChannelObject>? = null,
    val description: String? = null
)

@Serializable
data class ModifyGuildWidgetSettingsRequestParameters(
    val enabled: Boolean? = null,
    @SerialName("channel_id") val channelId: Snowflake? = null
)

@Serializable
data class ModifyStageInstanceRequestParameters(
    val topic: String? = null,
    @SerialName("privacy_level") val privacyLevel: Int? = null,
)

@Serializable
data class ModifyThreadChannelRequestParameters(
    val name: String? = null,
    val archived: Boolean? = null,
    @SerialName("auto_archive_duration") val autoArchiveDuration: Int? = null,
    val locked: Boolean? = null,
    val invitable: Boolean? = null,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: Int? = null,
    val flags: Int? = null,
    @SerialName("applied_tags") val appliedTags: List<Snowflake>? = null
)

@Serializable
data class ModifyUserVoiceStateRequestParameters(
    @SerialName("channel_id") val channelId: Snowflake,
    val suppress: Boolean? = null
)

@Serializable
data class ModifyWebhookRequestParameters(
    val name: String? = null,
    val avatar: ImageData? = null,
    @SerialName("channel_id") val channelId: Snowflake? = null,
)

@Serializable
data class SendSoundboardSoundRequestParameters(
    @SerialName("sound_id") val soundId: Snowflake,
    @SerialName("source_guild_id") val sourceGuildId: Snowflake,
)

@Serializable
data class StartThreadFromMessageRequestParameters(
    val name: String,
    @SerialName("auto_archive_duration") val autoArchiveDuration: Int? = null,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: Int? = null,
)

@Serializable
data class StartThreadInForumOrMediaChannelRequestParameters(
    val name: String,
    val autoArchiveDuration: Int? = null,
    val rateLimitPerUser: Int? = null,
    val message: ForumThreadMessageParametersObject? = null,
    val appliedTags: List<Snowflake>? = null,
    val files: List<String>? = null,
    val payloadJson: String? = null
)

@Serializable
data class StartThreadWithoutMessageRequestParameters(
    val name: String,
    @SerialName("auto_archive_duration") val autoArchiveDuration: Int? = null,
    val type: Int? = null,
    val invitable: Boolean,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: Int? = null
)

@Serializable
data class UpdateCurrentUserApplicationRoleConnectionRequestParameters(
    @SerialName("platform_name") val platformName: String? = null,
    @SerialName("platform_username") val platformUsername: String? = null,
    val metadata: Map<String, ApplicationRoleConnectionMetadataObject>? = null
)