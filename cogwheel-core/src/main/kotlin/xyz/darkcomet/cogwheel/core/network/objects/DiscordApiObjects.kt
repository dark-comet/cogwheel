package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.ImageData
import xyz.darkcomet.cogwheel.core.primitives.PermissionSet
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class ActivityAssetsObject(
    @SerialName("large_image") val largeImage: String? = null,
    @SerialName("large_text") val largeText: String? = null,
    @SerialName("small_image") val smallImage: String? = null,
    @SerialName("small_text") val smallText: String? = null,
)

@Serializable
data class ActivityButtonObject(
    val label: String,
    val url: String
)

@Serializable
data class ActivityEmojiObject(
    val name: String,
    val id: Snowflake? = null,
    val animated: Boolean? = null
)

@Serializable
data class ActivityLocationObject(
    val id: String,
    val kind: String,
    @SerialName("channel_id") val channelId: Snowflake,
    @SerialName("guild_id") val guildId: Snowflake?
)

@Serializable
data class ActivityObject(
    val name: String,
    val type: Int,
    val url: String? = null,
    @SerialName("created_at") val createdAt: Int,
    val timestamps: ActivityTimestampsObject? = null,
    @SerialName("application_id") val applicationId: Snowflake? = null,
    val details: String? = null,
    val state: String? = null,
    val emoji: ActivityEmojiObject? = null,
    val party: ActivityPartyObject? = null,
    val assets: ActivityAssetsObject? = null,
    val secrets: ActivitySecretsObject? = null,
    val instance: Boolean? = null,
    val flags: Int? = null,
    val buttons: List<ActivityButtonObject>? = null
)

@Serializable
data class ActivityPartyObject(
    val id: String? = null,
    val size: List<Int>? = null
)

@Serializable
data class ActivitySecretsObject(
    val join: String? = null,
    val spectate: String? = null,
    val match: String? = null
)

@Serializable
data class ActivityTimestampsObject(
    val start: Long? = null,
    val end: Long? = null
)

@Serializable
data class AllowedMentionsObject(
    val parse: List<String>,
    val roles: List<Snowflake>,
    val users: List<Snowflake>,
    @SerialName("replied_user") val repliedUser: Boolean
)

@Serializable
data class ApplicationActivityInstanceObject(
    @SerialName("application_id") val applicationId: Snowflake,
    @SerialName("instance_id") val instanceId: String,
    @SerialName("launch_id") val launchId: Snowflake,
    val location: ActivityLocationObject,
    val users: List<Snowflake>,
)

@Serializable(with = ApplicationCommandInteractionDataOptionObject.Serializable::class)
data class ApplicationCommandInteractionDataOptionObject(
    val name: String,
    val type: Int,
    val value: Any? = null,
    val options: List<ApplicationCommandInteractionDataOptionObject>? = null,
    val focused: Boolean? = null
) {
    class Serializable : KSerializer<ApplicationCommandInteractionDataOptionObject> {
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
    val id: Snowflake,
    val type: Int? = 1,
    @SerialName("application_id") val applicationId: Snowflake,
    @SerialName("guild_id") val guildId: Snowflake? = null,
    val name: String,
    @SerialName("name_localizations") val nameLocalizations: Map<String, String>? = null,
    val description: String,
    @SerialName("description_localizations") val descriptionLocalizations: Map<String, String>? = null,
    val options: List<ApplicationCommandOptionObject>? = null,
    @SerialName("default_member_permissions") val defaultMemberPermissions: PermissionSet? = null,
    @SerialName("dm_permission") val dmPermission: Boolean? = null,
    @SerialName("default_permission") val defaultPermission: Boolean? = null,
    val nsfw: Boolean? = null,
    @SerialName("integration_types") val integrationTypes: List<Int>? = null,
    val contexts: List<Int>? = null,
    val version: Snowflake,
    val handler: Int? = null
)

@Serializable
abstract class ApplicationCommandOptionChoiceObject {
    abstract val name: String

    @SerialName("name_localizations")
    abstract val nameLocalizations: Map<String, String>?
}

@Serializable
class ApplicationCommandOptionStringChoiceObject(
    override val name: String,
    override val nameLocalizations: Map<String, String>? = null,
    val value: String
) : ApplicationCommandOptionChoiceObject()

@Serializable
class ApplicationCommandOptionIntegerChoiceObject(
    override val name: String,
    override val nameLocalizations: Map<String, String>?,
    val value: Int
) : ApplicationCommandOptionChoiceObject()

@Serializable
class ApplicationCommandOptionDoubleChoiceObject(
    override val name: String,
    override val nameLocalizations: Map<String, String>?,
    val value: Double
) : ApplicationCommandOptionChoiceObject()

@Serializable
data class ApplicationCommandOptionObject(
    val type: Int,
    val name: String,
    @SerialName("name_localizations") val nameLocalizations: Map<String, String>? = null,
    val description: String,
    @SerialName("description_localizations") val descriptionLocalizations: Map<String, String>? = null,
    val required: Boolean? = null,
    val choices: List<ApplicationCommandOptionChoiceObject>? = null,
    val options: List<ApplicationCommandOptionObject>? = null,
    @SerialName("channel_types") val channelTypes: List<Int>? = null,
    @SerialName("min_value") val minValue: Double? = null,
    @SerialName("max_value") val maxValue: Double? = null,
    @SerialName("min_length") val minLength: Int? = null,
    @SerialName("max_length") val maxLength: Int? = null,
    val autocomplete: Boolean? = null
)

@Serializable
data class ApplicationCommandPermissionObject(
    val id: Snowflake,
    val type: Int,
    val permission: Boolean
)

@Serializable
data class ApplicationInstallParamsObject(
    val scopes: List<String>,
    val permissions: String
)

@Serializable
data class ApplicationIntegrationTypeConfigurationObject(
    @SerialName("oauth2_install_params") val oauth2InstallParams: ApplicationInstallParamsObject? = null
)

@Serializable
data class ApplicationObject(
    val id: Snowflake? = null,
    val name: String? = null,
    val icon: String? = null,
    val description: String? = null,
    @SerialName("rpc_origins") val rpcOrigins: List<String>? = null,
    @SerialName("bot_public") val botPublic: Boolean? = null,
    @SerialName("bot_require_code_grant") val botRequireCodeGrant: Boolean? = null,
    val bot: UserObject? = null,
    @SerialName("terms_of_service_url") val termsOfServiceUrl: String? = null,
    @SerialName("privacy_policy_url") val privacyPolicyUrl: String? = null,
    @SerialName("owner") val owner: UserObject? = null,
    @SerialName("verify_key") val verifyKey: String? = null,
    val team: TeamObject? = null,
    @SerialName("guild_id") val guildId: Snowflake? = null,
    val guild: GuildObject? = null,
    @SerialName("primary_sku_id") val primarySkuId: Snowflake? = null,
    val slug: String? = null,
    @SerialName("cover_image") val coverImage: String? = null,
    val flags: Int? = null,
    @SerialName("approximate_guild_count") val approximateGuildCount: Int? = null,
    @SerialName("approximate_user_install_count") val approximateUserInstallCount: Int? = null,
    @SerialName("redirect_uris") val redirectUris: List<String>? = null,
    @SerialName("interactions_endpoint_url") val interactionsEndpointUrl: String? = null,
    @SerialName("role_connections_verification_url") val roleConnectionsVerificationUrl: String? = null,
    val tags: List<String>? = null,
    @SerialName("install_params") val installParams: ApplicationInstallParamsObject? = null,
    @SerialName("integration_types_config") val integrationTypesConfig: Map<String, ApplicationIntegrationTypeConfigurationObject>? = null,
    @SerialName("custom_install_url") val customInstallUrl: String? = null,
)

@Serializable
data class ApplicationRoleConnectionMetadataObject(
    val type: Int,
    val key: String,
    val name: String,
    @SerialName("name_localizations") val nameLocalizations: Map<String, String>? = null,
    val description: String,
    @SerialName("description_localizations") val descriptionLocalizations: Map<String, String>? = null
)

@Serializable
data class AttachmentObject(
    val id: Snowflake,
    val filename: String? = null,
    val title: String? = null,
    val description: String? = null,
    @SerialName("content_type") val contentType: String? = null,
    val size: Int? = null,
    val url: String? = null,
    @SerialName("proxy_url") val proxyUrl: String? = null,
    val width: Int? = null,
    val height: Int? = null,
    val ephemeral: Boolean? = null,
    @SerialName("duration_secs") val durationSecs: Float? = null,
    val waveform: String? = null,
    val flags: Int? = null,
)

@Serializable(with = AuditLogChangeObject.Serializer::class)
data class AuditLogChangeObject(
    @SerialName("new_value") val newValue: Any? = null,
    @SerialName("old_value") val oldValue: Any? = null,
    val key: String
) {
    internal class Serializer : KSerializer<AuditLogChangeObject> {
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
    @SerialName("target_id") val targetId: String?,
    val changes: List<AuditLogChangeObject>? = null,
    val userId: Snowflake?,
    val id: Snowflake,
    @SerialName("action_type") val actionType: Int,
    val options: AuditLogOptionalAuditEntryInfoObject? = null,
    val reason: String? = null,
    @SerialName("guild_id") val guildId: Snowflake? = null
)

@Serializable
data class AuditLogOptionalAuditEntryInfoObject(
    @SerialName("application_id") val applicationId: Snowflake? = null,
    @SerialName("auto_moderation_rule_name") val autoModerationRuleName: String? = null,
    @SerialName("auto_moderation_rule_trigger_type") val autoModerationRuleTriggerType: String? = null,
    @SerialName("channel_id") val channelId: Snowflake? = null,
    val count: String? = null,
    @SerialName("delete_member_days") val deleteMemberDays: String? = null,
    val id: Snowflake? = null,
    @SerialName("members_removed") val membersRemoved: String? = null,
    @SerialName("message_id") val messageId: Snowflake? = null,
    @SerialName("role_name") val roleName: String? = null,
    val type: String? = null,
    @SerialName("integration_type") val integrationType: String? = null,
)

@Serializable
data class AutoModerationActionMetadataObject(
    @SerialName("channel_id") val channelId: Snowflake,
    @SerialName("duration_seconds") val durationSeconds: Int,
    @SerialName("custom_message") val customMessage: String
)

@Serializable
data class AutoModerationActionObject(
    val type: Int,
    val metadata: AutoModerationActionMetadataObject? = null
)

@Serializable
data class AutoModerationRuleTriggerMetadataObject(
    @SerialName("keyword_filter") val keywordFilter: List<String>,
    @SerialName("regex_patterns") val regexPatterns: List<String>,
    val presets: List<Int>,
    @SerialName("allow_list") val allowList: List<String>,
    @SerialName("mention_total_limit") val mentionTotalLimit: Int,
    @SerialName("mention_raid_protection_enabled") val mentionRaidProtectionEnabled: Boolean
)

@Serializable
data class AvatarDecorationDataObject(
    val asset: String,
    @SerialName("sku_id") val skuId: Snowflake
)

@Serializable
data class ChannelDefaultReactionObject(
    @SerialName("emoji_id") val emojiId: String?,
    @SerialName("emoji_name") val emojiName: String?
)

@Serializable
data class ChannelMentionObject(
    val id: Snowflake,
    @SerialName("guild_id") val guildId: Snowflake,
    val type: Int,
    val name: String
)

@Serializable
data class ChannelObject(
    val id: Snowflake,
    val type: Int,
    @SerialName("guild_id") val guildId: Snowflake? = null,
    val position: Int? = null,
    @SerialName("permission_overwrites") val permissionOverwrites: List<ChannelPermissionOverwriteObject>? = null,
    val name: String? = null,
    val topic: String? = null,
    val nsfw: Boolean? = null,
    @SerialName("last_message_id") val lastMessageId: String? = null,
    val bitrate: Int? = null,
    @SerialName("user_limit") val userLimit: Int? = null,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: Int? = null,
    val recipients: List<UserObject>? = null,
    val icon: String? = null,
    @SerialName("owner_id") val ownerId: Snowflake? = null,
    @SerialName("application_id") val applicationId: Snowflake? = null,
    val managed: Boolean? = null,
    @SerialName("parent_id") val parentId: Snowflake? = null,
    @SerialName("last_pin_timestamp") val lastPinTimestamp: ISO8601Timestamp? = null,
    @SerialName("rtc_region") val rtcRegion: String? = null,
    @SerialName("video_quality_mode") val videoQualityMode: Int? = null,
    @SerialName("message_count") val messageCount: Int? = null,
    @SerialName("member_count") val memberCount: Int? = null,
    @SerialName("thread_metadata") val threadMetadata: ThreadMetadataObject? = null,
    @SerialName("thread_member") val member: ThreadMemberObject? = null,
    @SerialName("default_auto_archive_duration") val defaultAutoArchiveDuration: Int? = null,
    @SerialName("permissions") val permissions: PermissionSet? = null,
    val flags: Int? = null,
    @SerialName("total_message_sent") val totalMessageSent: Int? = null,
    @SerialName("available_tags") val availableTags: List<ForumTagObject>? = null,
    @SerialName("applied_tags") val appliedTags: List<Snowflake>? = null,
    @SerialName("default_reaction_emoji") val defaultReactionEmoji: ChannelDefaultReactionObject? = null,
    @SerialName("default_thread_rate_limit_per_user") val defaultThreadRateLimitPerUser: Int? = null,
    @SerialName("default_sort_order") val defaultSortOrder: Int? = null,
    @SerialName("default_forum_layout") val defaultForumLayout: Int? = null,
    val message: MessageObject? = null, // from starting a thread in forum / media channel
)

@Serializable
data class ChannelPermissionOverwriteObject(
    val id: Snowflake,
    val type: Int,
    val allow: PermissionSet,
    val deny: PermissionSet
)

@Serializable
data class ClientStatusObject(
    val desktop: String? = null,
    val mobile: String? = null,
    val web: String? = null
)

@Serializable
data class EmbedAuthorObject(
    val name: String,
    val url: String? = null,
    @SerialName("icon_url") val iconUrl: String? = null,
    @SerialName("proxy_icon_url") val proxyIconUrl: String? = null,
)

@Serializable
data class EmbedFieldObject(
    val name: String,
    val value: String,
    val inline: Boolean? = null
)

@Serializable
data class EmbedFooterObject(
    val text: String,
    @SerialName("icon_url") val iconUrl: String,
    @SerialName("proxy_icon_url") val proxyIconUrl: String
)

@Serializable
data class EmbedImageObject(
    val url: String,
    @SerialName("proxy_url") val proxyUrl: String? = null,
    val width: Int? = null,
    val height: Int? = null
)

@Serializable
data class EmbedObject(
    val title: String? = null,
    val type: String? = null,
    val description: String? = null,
    val url: String? = null,
    val timestamp: ISO8601Timestamp? = null,
    val color: Int? = null,
    val footer: EmbedFooterObject? = null,
    val image: EmbedImageObject? = null,
    val thumbnail: EmbedThumbnailObject? = null,
    val video: EmbedVideoObject? = null,
    val provider: EmbedProviderObject? = null,
    val author: EmbedAuthorObject? = null,
    val fields: List<EmbedFieldObject>? = null
)

@Serializable
data class EmbedProviderObject(
    val name: String? = null,
    val url: String? = null
)

@Serializable
data class EmbedThumbnailObject(
    val url: String,
    @SerialName("proxy_url") val proxyUrl: String? = null,
    val width: Int? = null,
    val height: Int? = null
)

@Serializable
data class EmbedVideoObject(
    val url: String? = null,
    @SerialName("proxy_url") val proxyUrl: String? = null,
    val width: String? = null,
    val height: String? = null
)

@Serializable
data class EmojiObject(
    val id: Snowflake?,
    val name: String?,
    val roles: List<RoleObject>? = null,
    val user: UserObject? = null,
    @SerialName("require_colons") val requireColons: Boolean? = null,
    val managed: Boolean? = null,
    val animated: Boolean? = null,
    val available: Boolean? = null
)

@Serializable
data class EntitlementObject(
    val id: Snowflake,
    @SerialName("sku_id") val skuId: Snowflake,
    @SerialName("application_id") val applicationId: Snowflake,
    @SerialName("user_id") val userId: Snowflake? = null,
    val type: Int,
    val deleted: Boolean,
    @SerialName("starts_at") val startsAt: ISO8601Timestamp? = null,
    @SerialName("ends_at") val endsAt: ISO8601Timestamp? = null,
    @SerialName("guild_id") val guildId: Snowflake? = null,
    val consumed: Boolean? = null
)

@Serializable
data class FollowedChannelObject(
    @SerialName("channel_id") val channelId: Snowflake,
    @SerialName("webhook_id") val webhookId: Snowflake
)

@Serializable
data class ForumTagObject(
    val id: Snowflake,
    val name: String,
    val moderated: Boolean,
    @SerialName("emoji_id") val emojiId: Snowflake? = null,
    @SerialName("emoji_name") val emojiName: String? = null
)

@Serializable
data class ForumThreadMessageParametersObject(
    val content: String?,
    val embeds: List<EmbedObject>,
    val allowedMentions: AllowedMentionsObject? = null,
    val components: List<MessageComponentObject>? = null,
    val stickerIds: List<Snowflake>? = null,
    val attachments: List<AttachmentObject>? = null,
    val flags: Int? = null
)

@Serializable
data class GuildApplicationCommandPermissionsObject(
    val id: Snowflake,
    @SerialName("application_id") val applicationId: Snowflake,
    @SerialName("guild_id") val guildId: Snowflake,
    val permissions: List<ApplicationCommandPermissionObject>
)

@Serializable
data class GuildAuditLogObject(
    @SerialName("application_commands") val applicationCommands: List<ApplicationCommandObject>,
    @SerialName("audit_log_entries") val auditLogEntries: List<AuditLogEntryObject>,
    @SerialName("autoModerationRules") val autoModerationRules: List<GuildAutoModerationRuleObject>,
    @SerialName("guild_scheduled_events") val guildScheduledEvents: List<GuildScheduledEventObject>,
    val integrations: List<GuildIntegrationObject>,
    val threads: List<ChannelObject>,
    val users: List<UserObject>,
    val webhooks: List<WebhookObject>
)

@Serializable
data class GuildAutoModerationRuleObject(
    val id: Snowflake,
    @SerialName("guild_id") val guildId: Snowflake,
    val name: String,
    @SerialName("creator_id") val creatorId: Snowflake,
    @SerialName("event_type") val eventType: Int,
    @SerialName("trigger_type") val triggerType: Int,
    @SerialName("trigger_metadata") val triggerMetadata: AutoModerationRuleTriggerMetadataObject,
    val actions: List<AutoModerationActionObject>,
    val enabled: Boolean,
    @SerialName("exempt_roles") val exemptRoles: List<Snowflake>,
    @SerialName("exempt_channels") val exemptChannels: List<Snowflake>
)

@Serializable
data class GuildBanObject(
    val reason: String?,
    val user: UserObject
)

@Serializable
data class GuildIntegrationObject(
    val id: Snowflake,
    val name: String,
    val type: String,
    val enabled: Boolean,
    val syncing: Boolean? = null,
    @SerialName("role_id") val roleId: Snowflake,
    @SerialName("enable_emoticons") val enableEmoticons: Boolean? = null,
    @SerialName("expire_behaviour") val expireBehaviour: Int? = null,
    @SerialName("expire_grace_period") val expireGracePeriod: Int? = null,
    val user: UserObject? = null,
    val account: IntegrationAccountObject? = null,
    @SerialName("synced_at") val syncedAt: ISO8601Timestamp? = null,
    @SerialName("subscriber_count") val subscriberCount: Int? = null,
    val revoked: Boolean? = null,
    val application: IntegrationApplicationObject? = null,
    val scopes: List<String>,
    @SerialName("guild_id") val guildId: Snowflake? = null
)

@Serializable
data class GuildMemberObject(
    val user: UserObject? = null,
    val nick: String? = null,
    val avatar: ImageData? = null,
    val banner: ImageData? = null,
    val roles: List<Snowflake>,
    @SerialName("joined_at") val joinedAt: ISO8601Timestamp,
    @SerialName("premium_since") val premiumSince: ISO8601Timestamp? = null,
    val deaf: Boolean,
    val mute: Boolean,
    val flags: Int,
    val pending: Boolean? = null,
    val permissions: PermissionSet? = null,
    @SerialName("communication_disabled_until") val communicationDisabledUntil: ISO8601Timestamp? = null,
    @SerialName("avatar_decoration_data") val avatarDecorationData: AvatarDecorationDataObject? = null,
    @SerialName("guild_id") val guildId: Snowflake? = null
)

/**
 * Low-level representation of Discord API Guild object and UnavailableGuild object.
 *
 * @see <a href="https://discord.com/developers/docs/resources/guild">Discord API Reference: Guild</a>
 */
@Serializable
data class GuildObject(
    val id: Snowflake,
    val name: String? = null,
    val icon: String? = null,
    @SerialName("icon_hash") val iconHash: String? = null,
    val splash: String? = null,
    @SerialName("discovery_splash") val discoverySplash: String? = null,
    val owner: String? = null, //Only set when using the GET Current User Guilds endpoint, and are relative to the requested user
    @SerialName("owner_id") val ownerId: Snowflake? = null,
    val permissions: PermissionSet? = null,
    @Deprecated("replaced by channel.rtc_region") val region: String? = null,
    @SerialName("afk_channel_id") val afkChannelId: Snowflake? = null,
    @SerialName("afk_timeout") val afkTimeout: Int? = null,
    @SerialName("widget_enabled") val widgetEnabled: Boolean? = null,
    @SerialName("widget_channel_id") val widgetChannelId: Snowflake? = null,
    @SerialName("verification_level") val verificationLevel: Int? = null,
    @SerialName("default_message_notifications") val defaultMessageNotifications: Int? = null,
    @SerialName("explicit_content_filter") val explicitContentFilter: Int? = null,
    val roles: List<RoleObject>? = null,
    val emojis: List<EmojiObject>? = null,
    val features: List<String>? = null,
    @SerialName("mfa_level") val mfaLevel: Int? = null,
    @SerialName("application_id") val applicationId: Snowflake? = null,
    @SerialName("system_channel_id") val systemChannelId: Snowflake? = null,
    @SerialName("system_channel_flags") val systemChannelFlags: Int? = null,
    @SerialName("rules_channel_id") val rulesChannelId: Snowflake? = null,
    @SerialName("max_presences") val maxPresences: Int? = null,
    @SerialName("max_members") val maxMembers: Int? = null,
    @SerialName("vanity_url_code") val vanityUrlCode: String? = null,
    val description: String? = null,
    val banner: ImageData? = null,
    @SerialName("premium_tier") val premiumTier: Int? = null,
    @SerialName("premium_subscription_count") val premiumSubscriptionCount: Int? = null,
    @SerialName("preferred_locale") val preferredLocale: String? = null,
    @SerialName("public_updates_channel_id") val publicUpdatesChannelId: Snowflake? = null,
    @SerialName("max_video_channel_users") val maxVideoChannelUsers: Int? = null,
    @SerialName("max_stage_video_channel_users") val maxStageVideoChannelUsers: Int? = null,
    @SerialName("approximate_member_count") val approximateMemberCount: Int? = null,
    @SerialName("approximate_presence_count") val approximatePresenceCount: Int? = null,
    @SerialName("welcome_screen") val welcomeScreen: GuildWelcomeScreenObject? = null,
    @SerialName("nsfw_level") val nsfwLevel: Int? = null,
    val stickers: List<StickerObject>? = null,
    @SerialName("premium_progress_bar_enabled") val premiumProgressBarEnabled: Boolean? = null,
    @SerialName("safety_alerts_channel_id") val safetyAlertsChannelId: Snowflake? = null,
    val unavailable: Boolean? = null,
    // From Gateway Guild Create Event
    @SerialName("joined_at") val joinedAt: ISO8601Timestamp? = null,
    @SerialName("large") val large: Boolean? = null,
    @SerialName("member_count") val memberCount: Int? = null,
    @SerialName("voice_states") val voiceStates: List<VoiceStateObject>? = null,
    @SerialName("members") val members: List<GuildMemberObject>? = null,
    @SerialName("channels") val channels: List<ChannelObject>? = null,
    @SerialName("threads") val threads: List<ChannelObject>? = null,
    @SerialName("presences") val presences: List<UpdatePresenceObject>? = null,
    @SerialName("stage_instances") val stageInstances: List<StageInstanceObject>? = null,
    @SerialName("guild_scheduled_events") val guildScheduledEvents: List<GuildScheduledEventObject>? = null,
    @SerialName("soundboard_sounds") val soundboardSounds: List<SoundboardSoundObject>? = null,
)

@Serializable
data class GuildOnboardingObject(
    @SerialName("guild_id") val guildId: Snowflake,
    val prompts: List<GuildOnboardingPromptObject>,
    @SerialName("default_channel_ids") val defaultChannelIds: List<Snowflake>,
    val enabled: Boolean,
    val mode: Int
)

@Serializable
data class GuildOnboardingPromptObject(
    val id: Snowflake,
    val options: List<GuildOnboardingPromptOptionObject>,
    val title: String,
    @SerialName("single_select") val singleSelect: Boolean,
    val required: Boolean,
    @SerialName("in_onboarding") val inOnboarding: Boolean
)

@Serializable
data class GuildOnboardingPromptOptionObject(
    val id: Snowflake,
    @SerialName("channel_ids") val channelIds: List<Snowflake>,
    @SerialName("role_ids") val roleIds: List<Snowflake>,
    val emoji: EmojiObject? = null,
    @SerialName("emoji_id") val emojiId: Snowflake? = null,
    @SerialName("emoji_name") val emojiName: String? = null,
    @SerialName("emoji_animated") val emojiAnimated: Boolean? = null,
    val title: String,
    val description: String? = null
)

@Serializable
data class GuildPreviewObject(
    val id: Snowflake,
    val name: String,
    val icon: String?,
    val splash: String?,
    @SerialName("discovery_splash") val discoverySplash: String?,
    val emojis: List<EmojiObject>,
    val features: List<String>,
    @SerialName("approximate_member_count") val approximateMemberCount: Int,
    @SerialName("approximate_presence_count") val approximatePresenceCount: Int,
    val description: String? = null,
    val stickers: List<StickerObject>
)

@Serializable
data class GuildScheduledEventEntityMetadataObject(
    val location: String? = null
)

@Serializable
data class GuildScheduledEventObject(
    val id: Snowflake,
    @SerialName("guild_id") val guildId: Snowflake,
    @SerialName("channel_id") val channelId: Snowflake?,
    @SerialName("creator_id") val creatorId: Snowflake? = null,
    val name: String,
    val description: String? = null,
    @SerialName("scheduled_start_time") val scheduledStartTime: ISO8601Timestamp,
    @SerialName("scheduled_end_time") val scheduledEndTime: ISO8601Timestamp? = null,
    @SerialName("privacy_level") val privacyLevel: Int,
    val status: Int,
    @SerialName("entity_type") val entityType: Int,
    @SerialName("entity_id") val entityId: Snowflake?,
    @SerialName("entity_metadata") val entityMetadata: GuildScheduledEventEntityMetadataObject? = null,
    val creator: UserObject? = null,
    @SerialName("user_count") val userCount: Int? = null,
    val image: ImageData? = null,
    @SerialName("recurrence_rule") val recurrenceRule: GuildScheduledEventRecurrenceRuleObject? = null,
)

@Serializable
data class GuildScheduledEventRecurrenceRuleObject(
    val start: ISO8601Timestamp,
    val end: ISO8601Timestamp,
    val frequency: Int,
    val interval: Int,
    @SerialName("by_weekday") val byWeekday: List<Int>? = null,
    @SerialName("by_n_weekday") val byNWeekday: List<RecurrenceRuleNWeekdayObject>? = null,
    @SerialName("by_month") val byMonth: List<Int>? = null,
    @SerialName("by_month_day") val byMonthDay: List<Int>? = null,
    @SerialName("by_year_day") val byYearDay: List<Int>? = null,
    val count: Int? = null
)

@Serializable
data class GuildScheduledEventUserObject(
    val guildScheduledEventId: Snowflake,
    val user: UserObject,
    val member: GuildMemberObject? = null
)

@Serializable
data class GuildTemplateObject(
    val code: String,
    val name: String,
    val description: String?,
    @SerialName("usage_count") val usageCount: Int,
    @SerialName("creator_id") val creatorId: Snowflake,
    val creator: UserObject,
    @SerialName("created_at") val createdAt: ISO8601Timestamp,
    @SerialName("updated_at") val updatedAt: ISO8601Timestamp,
    @SerialName("source_guild_id") val sourceGuildId: Snowflake,
    @SerialName("serialized_source_guild") val serializedSourceGuild: GuildObject,
    @SerialName("is_dirty") val isDirty: Boolean
)

@Serializable
data class GuildWelcomeScreenChannelObject(
    @SerialName("channel_id") val channelId: Snowflake,
    val description: String,
    @SerialName("emoji_id") val emojiId: Snowflake?,
    @SerialName("emoji_name") val emojiName: String?
)

@Serializable
data class GuildWelcomeScreenObject(
    val description: String?,
    @SerialName("welcome_channels") val welcomeChannels: List<GuildWelcomeScreenChannelObject>
)

@Serializable
data class GuildWidgetObject(
    val id: Snowflake,
    val name: String,
    @SerialName("instant_invite") val instantInvite: String,
    val channels: List<ChannelObject>,
    val members: List<GuildMemberObject>,
    @SerialName("presence_count") val presenceCount: Int
)

@Serializable
data class GuildWidgetSettingsObject(
    val enabled: Boolean,
    @SerialName("channel_id") val channelId: Snowflake? = null
)

@Serializable
data class IntegrationAccountObject(
    val id: String,
    val name: String
)

@Serializable
data class IntegrationApplicationObject(
    val id: Snowflake,
    val name: String,
    val icon: String?,
    val description: String,
    val bot: UserObject? = null
)

@Serializable
data class InteractionDataObject(
    val id: Snowflake,
    val name: String,
    val type: Int,
    val resolved: ResolvedDataObject? = null,
    val options: List<ApplicationCommandInteractionDataOptionObject>? = null,
    @SerialName("guild_id") val guildId: Snowflake? = null,
    @SerialName("target_id") val targetId: Snowflake? = null
)

@Serializable
data class InteractionObject(
    val id: Snowflake,
    @SerialName("application_id") val applicationId: Snowflake,
    val type: Int,
    val data: InteractionDataObject? = null,
    val guild: GuildObject? = null,
    @SerialName("guild_id") val guildId: Snowflake? = null,
    val channel: ChannelObject? = null,
    @SerialName("channel_id") val channelId: Snowflake,
    val member: GuildMemberObject? = null,
    val user: UserObject? = null,
    val token: String,
    val version: Int,
    val message: MessageObject? = null,
    @SerialName("app_permissions") val appPermissions: String,
    val locale: String? = null,
    @SerialName("guild_locale") val guildLocale: String? = null,
    val entitlements: List<EntitlementObject>,
    @SerialName("authorizing_integration_owners") val authorizingIntegrationOwners: Map<Int, String>
)

@Serializable
data class InviteObject(
    val type: Int? = null,
    val code: String,
    val uses: Int? = null,
    val guild: GuildObject? = null,
    val channel: ChannelObject? = null,
    val inviter: UserObject? = null,
    @SerialName("target_type") val targetType: Int? = null,
    @SerialName("target_user") val targetUser: UserObject? = null,
    @SerialName("target_application") val targetApplication: ApplicationObject? = null,
    @SerialName("approximate_presence_count") val approximatePresenceCount: Int? = null,
    @SerialName("approximate_member_count") val approximateMemberCount: Int? = null,
    @SerialName("expires_at") val expiresAt: ISO8601Timestamp? = null,
    @SerialName("stage_instance") val stageInstance: InviteStageInstanceObject? = null,
    @SerialName("guild_scheduled_event") val guildScheduledEvent: GuildScheduledEventObject? = null,
)

@Serializable
data class InviteStageInstanceObject(
    val members: List<GuildMemberObject>,
    @SerialName("participation_count") val participationCount: Int,
    @SerialName("speaker_count") val speakerCount: Int,
    val topic: String
)

@Serializable
data class MessageActivityObject(
    val type: Int,
    @SerialName("party_id") val partyId: String
)

@Serializable
data class MessageCallObject(
    val participants: List<Snowflake>,
    val endedTimestamp: ISO8601Timestamp? = null
)

@Serializable
class MessageComponentObject(
    // TODO: Implement me
)

@Serializable
abstract class MessageInteractionMetadataObject {
    abstract val id: Snowflake
    abstract val type: Int
    abstract val user: UserObject

    @SerialName("authorizing_integration_owners")
    abstract val authorizingIntegrationOwners: Map<Int, Snowflake>

    @SerialName("original_response_message_id")
    abstract val originalResponseMessageId: Snowflake?
}

@Serializable
data class ApplicationCommandInteractionMetadataObject(
    override val id: Snowflake,
    override val type: Int,
    override val user: UserObject,
    override val authorizingIntegrationOwners: Map<Int, Snowflake>,
    override val originalResponseMessageId: Snowflake? = null,
    @SerialName("target_user") val targetUser: UserObject? = null,
    @SerialName("target_message_id") val targetMessageId: Snowflake? = null,
) : MessageInteractionMetadataObject()

@Serializable
data class MessageComponentInteractionMetadataObject(
    override val id: Snowflake,
    override val type: Int,
    override val user: UserObject,
    override val authorizingIntegrationOwners: Map<Int, Snowflake>,
    override val originalResponseMessageId: Snowflake? = null,
    @SerialName("interacted_message_id") val interactedMessageId: Snowflake,
) : MessageInteractionMetadataObject()

@Serializable
data class ModalSubmitInteractionMetadataObject(
    override val id: Snowflake,
    override val type: Int,
    override val user: UserObject,
    override val authorizingIntegrationOwners: Map<Int, Snowflake>,
    override val originalResponseMessageId: Snowflake? = null,
    @SerialName("trigger_interaction_metadata") val triggerInteractionMetadata: MessageInteractionMetadataObject,
) : MessageInteractionMetadataObject()

@Serializable
data class MessageObject(
    val id: Snowflake,
    val channelId: Snowflake,
    val author: UserObject,
    val content: String,
    val timestamp: ISO8601Timestamp,
    val editedTimestamp: ISO8601Timestamp?,
    val tts: Boolean,
    val mentionEveryone: Boolean,
    val mentions: List<UserObject>,
    val mentionRoles: List<RoleObject>,
    val mentionChannels: List<ChannelMentionObject>,
    val attachments: List<AttachmentObject>,
    val embeds: List<EmbedObject>,
    val reactions: List<ReactionObject>? = null,
    val nonce: String? = null,
    val pinned: Boolean,
    val webhookId: Snowflake? = null,
    val type: Int,
    val activity: MessageActivityObject? = null,
    val application: ApplicationObject? = null,
    val applicationId: Snowflake? = null,
    val flags: Int? = null,
    val messageReference: MessageReferenceObject? = null,
    val messageSnapshots: MessageSnapshotObject? = null,
    val referencedMessage: MessageReferenceObject? = null,
    val interactionMetadata: MessageInteractionMetadataObject? = null,
    val interaction: InteractionObject? = null,
    val thread: ChannelObject? = null,
    val components: List<MessageComponentObject>? = null,
    val stickerItems: List<StickerObject>? = null,
    val stickers: List<StickerObject>? = null,
    val position: Int? = null,
    val roleSubscriptionData: RoleSubscriptionDataObject? = null,
    val resolved: ResolvedDataObject? = null,
    val poll: PollObject? = null,
    val call: MessageCallObject? = null,
    val guildId: Snowflake? = null,
    val member: GuildMemberObject? = null
)

@Serializable
data class MessageReferenceObject(
    val type: Int? = null,
    @SerialName("message_id") val messageId: Snowflake? = null,
    @SerialName("channel_id") val channelId: Snowflake? = null,
    @SerialName("guild_id") val guildId: Snowflake? = null,
    @SerialName("fail_if_not_exists") val failIfNotExists: Boolean? = null
)

@Serializable
data class MessageSnapshotObject(
    val message: MessageObject?
)

@Serializable
data class PollAnswerCountObject(
    val id: Int,
    val count: Int,
    @SerialName("me_voted") val meVoted: Boolean
)

@Serializable
data class PollAnswerObject(
    @SerialName("answer_id") val answerId: Int,
    @SerialName("poll_media") val pollMedia: PollMediaObject,
)

@Serializable
data class PollMediaObject(
    val text: String? = null,
    val emoji: EmojiObject? = null
)

@Serializable
data class PollObject(
    val question: PollMediaObject,
    val answers: List<PollAnswerObject>,
    val expiry: ISO8601Timestamp? = null,
    @SerialName("allow_multiselect") val allowMultiselect: Boolean,
    @SerialName("layout_type") val layoutType: Int,
    val results: PollResultsObject? = null
)

@Serializable
data class PollResultsObject(
    @SerialName("is_finalized") val isFinalized: Boolean,
    @SerialName("answer_counts") val answerCounts: List<PollAnswerCountObject>
)

@Serializable
data class ReactionCountDetailsObject(
    val burst: Int,
    val normal: Int
)

@Serializable
data class ReactionObject(
    val count: Int,
    @SerialName("count_details") val countDetails: ReactionCountDetailsObject,
    val me: Boolean,
    @SerialName("me_burst") val meBurst: Boolean,
    val emoji: EmojiObject,
    @SerialName("burst_colors") val burstColors: List<Int>
)

@Serializable
data class RecurrenceRuleNWeekdayObject(
    val n: Int,
    val day: Int
)

@Serializable
data class ResolvedDataObject(
    val users: Map<Snowflake, UserObject>? = null,
    val members: Map<Snowflake, GuildMemberObject>? = null,
    val roles: Map<Snowflake, RoleObject>? = null,
    val channels: Map<Snowflake, ChannelObject>? = null,
    val messages: Map<Snowflake, MessageObject>? = null,
    val attachments: Map<Snowflake, AttachmentObject>? = null,
)

@Serializable
data class RoleObject(
    val id: Snowflake,
    val name: String,
    val color: Int,
    val hoist: Boolean,
    val icon: String? = null,
    @SerialName("unicode_emoji") val unicodeEmoji: String? = null,
    val position: Int,
    val permissions: PermissionSet,
    val managed: Boolean,
    val mentionable: Boolean,
    val tags: RoleTagsObject? = null,
    val flags: Int
)

@Serializable
data class RoleSubscriptionDataObject(
    @SerialName("role_subscription_listing_id") val roleSubscriptionListingId: Snowflake,
    @SerialName("tier_name") val tierName: String,
    @SerialName("total_months_subscribed") val totalMonthsSubscribed: Int,
    @SerialName("is_renewal") val isRenewal: Boolean
)

@Serializable
data class RoleTagsObject(
    val botId: Snowflake? = null,
    @SerialName("integration_id") val integrationId: Snowflake? = null,
    @SerialName("premium_subscriber") val premiumSubscriber: Boolean? = null,
    @SerialName("subscription_listing_id") val subscriptionListingId: Snowflake? = null,
    @SerialName("available_for_purchase") val availableForPurchase: Boolean? = null,
    @SerialName("guild_connections") val guildConnections: Boolean? = null
)

@Serializable
data class SkuObject(
    val id: Snowflake,
    val type: Int,
    @SerialName("application_id") val applicationId: Snowflake,
    val name: String,
    val slug: String,
    val flags: Int
)

@Serializable
data class SoundboardSoundObject(
    val name: String,
    @SerialName("sound_id") val soundId: Snowflake,
    val volume: Double,
    @SerialName("emoji_id") val emojiId: Snowflake?,
    @SerialName("emoji_name") val emojiName: String?,
    @SerialName("guild_id") val guildId: Snowflake? = null,
    val available: Boolean,
    val user: UserObject? = null
)

@Serializable
data class StageInstanceObject(
    val id: Snowflake,
    @SerialName("guild_id") val guildId: Snowflake,
    @SerialName("channel_id") val channelId: Snowflake,
    val topic: String,
    @SerialName("privacy_level") val privacyLevel: Int,
    @SerialName("discoverable_disabled") val discoverableDisabled: Boolean,
    @SerialName("guild_scheduled_event_id") val guildScheduledEventId: Snowflake? = null,
)

@Serializable
data class StickerObject(
    val id: Snowflake,
    @SerialName("pack_id") val packId: Snowflake? = null,
    val name: String,
    val description: String,
    val tags: String,
    val type: Int,
    @SerialName("format_type") val formatType: Int,
    val available: Boolean? = null,
    @SerialName("guild_id") val guildId: Snowflake? = null,
    val user: UserObject? = null,
    @SerialName("sort_value") val sortValue: Int? = null,
)

@Serializable
data class StickerPackObject(
    val id: Snowflake,
    val stickers: List<StickerObject>,
    val name: String,
    @SerialName("sku_id") val skuId: Snowflake,
    @SerialName("cover_sticker_id") val coverStickerId: Snowflake? = null,
    val description: String,
    @SerialName("banner_asset_id") val bannerAssetId: Snowflake? = null
)

@Serializable
data class SubscriptionObject(
    val id: Snowflake,
    @SerialName("user_id") val userId: Snowflake,
    @SerialName("sku_ids") val skuIds: List<Snowflake>,
    @SerialName("entitlement_ids") val entitlementIds: List<Snowflake>,
    @SerialName("renewal_sku_ids") val renewalSkuIds: List<Snowflake>,
    @SerialName("current_period_start") val currentPeriodStart: ISO8601Timestamp,
    @SerialName("current_period_end") val currentPeriodEnd: ISO8601Timestamp,
    val status: Int,
    @SerialName("canceled_at") val canceledAt: ISO8601Timestamp?,
    val country: String?
)

@Serializable
data class TeamMemberObject(
    @SerialName("membership_state") val membershipState: Int,
    @SerialName("team_id") val teamId: Int,
    val user: UserObject,
    val role: String
)

@Serializable
data class TeamObject(
    val icon: String?,
    val id: Snowflake,
    val members: List<TeamMemberObject>,
    val name: String,
    @SerialName("owner_user_id") val ownerUserId: Snowflake,
)

@Serializable
data class ThreadMemberObject(
    val id: Snowflake? = null,
    @SerialName("user_id") val userId: Snowflake? = null,
    @SerialName("join_timestamp") val joinTimestamp: ISO8601Timestamp,
    val flags: Int,
    val member: GuildMemberObject? = null,
    @SerialName("guild_id") val guildId: Snowflake? = null
)

@Serializable
data class ThreadMetadataObject(
    val archived: Boolean,
    @SerialName("auto_archive_duration") val autoArchiveDuration: Int,
    @SerialName("archive_timestamp") val archiveTimestamp: ISO8601Timestamp,
    val locked: Boolean,
    val invitable: Boolean? = null,
    @SerialName("create_timestamp") val createTimestamp: ISO8601Timestamp? = null,
)

@Serializable
data class UpdatePresenceObject(
    val since: Int?,
    val activities: ActivityObject,
    val status: String,
    val afk: Boolean
)

@Serializable
data class UserApplicationRoleConnectionObject(
    @SerialName("platform_name") val platformName: String?,
    @SerialName("platform_username") val platformUsername: String?,
    val metadata: Map<String, ApplicationRoleConnectionMetadataObject>
)

@Serializable
data class UserConnectionObject(
    val id: String,
    val name: String,
    val type: String,
    val revoked: Boolean? = null,
    val integrations: List<GuildIntegrationObject>? = null,
    val verified: Boolean,
    @SerialName("friend_sync") val friendSync: Boolean,
    @SerialName("show_activity") val showActivity: Boolean,
    @SerialName("two_way_link") val twoWayLink: Boolean,
    val visibility: Int
)

@Serializable
data class UserObject(
    val id: Snowflake,
    val username: String,
    val discriminator: String,
    @SerialName("global_name") val globalName: String?,
    val avatar: String?,
    val bot: Boolean? = null,
    val system: Boolean? = null,
    @SerialName("mfa_enabled") val mfaEnabled: Boolean? = null,
    val banner: String? = null,
    @SerialName("accent_color") val accentColor: Int? = null,
    val locale: String? = null,
    val verified: Boolean? = null,
    val email: String? = null,
    val flags: Int? = null,
    @SerialName("premium_type") val premiumType: Int? = null,
    @SerialName("public_flags") val publicFlags: Int? = null,
    @SerialName("avatar_decoration_data") val avatarDecorationData: AvatarDecorationDataObject? = null
)

@Serializable
data class VoiceRegionObject(
    @SerialName("guild_id") val guildId: Snowflake? = null,
    @SerialName("channel_id") val channelId: Snowflake?,
    @SerialName("user_id") val userId: Snowflake,
    val member: GuildMemberObject? = null,
    @SerialName("session_id") val sessionId: String,
    val deaf: Boolean,
    val mute: Boolean,
    @SerialName("self_deaf") val selfDeaf: Boolean,
    @SerialName("self_mute") val selfMute: Boolean,
    @SerialName("self_stream") val selfStream: Boolean? = null,
    @SerialName("self_video") val selfVideo: Boolean,
    val suppress: Boolean,
    @SerialName("request_to_speak_timestamp") val requestToSpeakTimestamp: ISO8601Timestamp?
)

@Serializable
data class VoiceStateObject(
    @SerialName("guild_id") val guildId: Snowflake?,
    @SerialName("channel_id") val channelId: Snowflake,
    @SerialName("user_id") val userId: Snowflake,
    val member: GuildMemberObject? = null,
    @SerialName("session_id") val sessionId: String,
    val deaf: Boolean,
    val mute: Boolean,
    @SerialName("self_deaf") val selfDeaf: Boolean,
    @SerialName("self_mute") val selfMute: Boolean,
    @SerialName("self_stream") val selfStream: Boolean? = null,
    @SerialName("self_video") val selfVideo: Boolean,
    val suppress: Boolean,
    @SerialName("request_to_speak_timestamp") val requestToSpeakTimestamp: ISO8601Timestamp?
)

@Serializable
data class WebhookObject(
    val id: Snowflake,
    val type: Int,
    @SerialName("guild_id") val guildId: Snowflake? = null,
    @SerialName("channel_id") val channelId: Snowflake? = null,
    val user: UserObject? = null,
    val name: String? = null,
    val avatar: ImageData? = null,
    val token: String? = null,
    @SerialName("application_id") val applicationId: Snowflake? = null,
    @SerialName("source_guild") val sourceGuild: GuildObject? = null,
    @SerialName("source_channel") val sourceChannel: ChannelObject? = null,
    val url: String? = null
)