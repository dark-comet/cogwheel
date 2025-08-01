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
    @SerialName("large_image") val largeImage: MaybeAbsent<String>? = null,
    @SerialName("large_text") val largeText: MaybeAbsent<String>? = null,
    @SerialName("small_image") val smallImage: MaybeAbsent<String>? = null,
    @SerialName("small_text") val smallText: MaybeAbsent<String>? = null,
)

@Serializable
data class ActivityButtonObject(
    val label: MaybeAbsent<String>? = null,
    val url: MaybeAbsent<String>? = null
)

@Serializable
data class ActivityEmojiObject(
    val name: MaybeAbsent<String>? = null,
    val id: MaybeAbsent<Snowflake>? = null,
    val animated: MaybeAbsent<Boolean>? = null
)

@Serializable
data class ActivityLocationObject(
    val id: MaybeAbsent<String>? = null,
    val kind: MaybeAbsent<String>? = null,
    @SerialName("channel_id") val channelId: MaybeAbsent<Snowflake>? = null,
    @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null
)

@Serializable
data class ActivityObject(
    val name: MaybeAbsent<String>? = null,
    val type: MaybeAbsent<Int>? = null,
    val url: MaybeAbsent<String>? = null,
    @SerialName("created_at") val createdAt: MaybeAbsent<Int>? = null,
    val timestamps: MaybeAbsent<ActivityTimestampsObject>? = null,
    @SerialName("application_id") val applicationId: MaybeAbsent<Snowflake>? = null,
    val details: MaybeAbsent<String>? = null,
    val state: MaybeAbsent<String>? = null,
    val emoji: MaybeAbsent<ActivityEmojiObject>? = null,
    val party: MaybeAbsent<ActivityPartyObject>? = null,
    val assets: MaybeAbsent<ActivityAssetsObject>? = null,
    val secrets: MaybeAbsent<ActivitySecretsObject>? = null,
    val instance: MaybeAbsent<Boolean>? = null,
    val flags: MaybeAbsent<Int>? = null,
    val buttons: MaybeAbsent<List<ActivityButtonObject>>? = null
)

@Serializable
data class ActivityPartyObject(
    val id: MaybeAbsent<String>? = null,
    val size: MaybeAbsent<List<Int>>? = null
)

@Serializable
data class ActivitySecretsObject(
    val join: MaybeAbsent<String>? = null,
    val spectate: MaybeAbsent<String>? = null,
    val match: MaybeAbsent<String>? = null
)

@Serializable
data class ActivityTimestampsObject(
    val start: MaybeAbsent<Long>? = null,
    val end: MaybeAbsent<Long>? = null
)

@Serializable
data class AllowedMentionsObject(
    val parse: MaybeAbsent<List<String>>? = null,
    val roles: MaybeAbsent<List<Snowflake>>? = null,
    val users: MaybeAbsent<List<Snowflake>>? = null,
    @SerialName("replied_user") val repliedUser: MaybeAbsent<Boolean>? = null
)

@Serializable
data class ApplicationActivityInstanceObject(
    @SerialName("application_id") val applicationId: MaybeAbsent<Snowflake>? = null,
    @SerialName("instance_id") val instanceId: MaybeAbsent<String>? = null,
    @SerialName("launch_id") val launchId: MaybeAbsent<Snowflake>? = null,
    val location: MaybeAbsent<ActivityLocationObject>? = null,
    val users: MaybeAbsent<List<Snowflake>>? = null,
)

@Serializable(with = ApplicationCommandInteractionDataOptionObject.Serializer::class)
data class ApplicationCommandInteractionDataOptionObject(
    val name: MaybeAbsent<String>? = null,
    val type: MaybeAbsent<Int>? = null,
    val value: MaybeAbsent<Any>? = null,
    val options: MaybeAbsent<List<ApplicationCommandInteractionDataOptionObject>>? = null,
    val focused: MaybeAbsent<Boolean>? = null
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
    val id: MaybeAbsent<Snowflake>? = null,
    val type: MaybeAbsent<Int>? = null,
    @SerialName("application_id") val applicationId: MaybeAbsent<Snowflake>? = null,
    @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
    val name: MaybeAbsent<String>? = null,
    @SerialName("name_localizations") val nameLocalizations: MaybeAbsent<Map<String, String>>? = null,
    val description: MaybeAbsent<String>? = null,
    @SerialName("description_localizations") val descriptionLocalizations: MaybeAbsent<Map<String, String>>? = null,
    val options: MaybeAbsent<List<ApplicationCommandOptionObject>>? = null,
    @SerialName("default_member_permissions") val defaultMemberPermissions: MaybeAbsent<String>? = null,
    @SerialName("dm_permission") val dmPermission: MaybeAbsent<Boolean>? = null,
    @SerialName("default_permission") val defaultPermission: MaybeAbsent<Boolean>? = null,
    val nsfw: MaybeAbsent<Boolean>? = null,
    @SerialName("integration_types") val integrationTypes: MaybeAbsent<List<Int>>? = null,
    val contexts: MaybeAbsent<List<Int>>? = null,
    val version: MaybeAbsent<Snowflake>? = null,
    val handler: MaybeAbsent<Int>? = null
)

@Serializable
sealed class ApplicationCommandOptionChoiceObject {
    abstract val name: MaybeAbsent<String>?

    @SerialName("name_localizations")
    abstract val nameLocalizations: MaybeAbsent<Map<String, String>>?
}

@Serializable
class ApplicationCommandOptionStringChoiceObject(
    override val name: MaybeAbsent<String>? = null,
    override val nameLocalizations: MaybeAbsent<Map<String, String>>? = null,
    val value: MaybeAbsent<String>? = null
) : ApplicationCommandOptionChoiceObject()

@Serializable
class ApplicationCommandOptionIntegerChoiceObject(
    override val name: MaybeAbsent<String>? = null,
    override val nameLocalizations: MaybeAbsent<Map<String, String>>? = null,
    val value: MaybeAbsent<Int>? = null
) : ApplicationCommandOptionChoiceObject()

@Serializable
class ApplicationCommandOptionDoubleChoiceObject(
    override val name: MaybeAbsent<String>? = null,
    override val nameLocalizations: MaybeAbsent<Map<String, String>>? = null,
    val value: MaybeAbsent<Double>? = null
) : ApplicationCommandOptionChoiceObject()

@Serializable
data class ApplicationCommandOptionObject(
    val type: MaybeAbsent<Int>? = null,
    val name: MaybeAbsent<String>? = null,
    @SerialName("name_localizations") val nameLocalizations: MaybeAbsent<Map<String, String>>? = null,
    val description: MaybeAbsent<String>? = null,
    @SerialName("description_localizations") val descriptionLocalizations: MaybeAbsent<Map<String, String>>? = null,
    val required: MaybeAbsent<Boolean>? = null,
    val choices: MaybeAbsent<List<ApplicationCommandOptionChoiceObject>>? = null,
    val options: MaybeAbsent<List<ApplicationCommandOptionObject>>? = null,
    @SerialName("channel_types") val channelTypes: MaybeAbsent<List<Int>>? = null,
    @SerialName("min_value") val minValue: MaybeAbsent<Double>? = null,
    @SerialName("max_value") val maxValue: MaybeAbsent<Double>? = null,
    @SerialName("min_length") val minLength: MaybeAbsent<Int>? = null,
    @SerialName("max_length") val maxLength: MaybeAbsent<Int>? = null,
    val autocomplete: MaybeAbsent<Boolean>? = null
)

@Serializable
data class ApplicationCommandPermissionObject(
    val id: MaybeAbsent<Snowflake>? = null,
    val type: MaybeAbsent<Int>? = null,
    val permission: MaybeAbsent<Boolean>? = null
)

@Serializable
data class ApplicationInstallParamsObject(
    val scopes: MaybeAbsent<List<String>>? = null,
    val permissions: MaybeAbsent<String>? = null
)

@Serializable
data class ApplicationIntegrationTypeConfigurationObject(
    @SerialName("oauth2_install_params") val oauth2InstallParams: MaybeAbsent<ApplicationInstallParamsObject>? = null
)

@Serializable
data class ApplicationObject(
    val id: MaybeAbsent<Snowflake>? = null,
    val name: MaybeAbsent<String>? = null,
    val icon: MaybeAbsent<String>? = null,
    val description: MaybeAbsent<String>? = null,
    @SerialName("rpc_origins") val rpcOrigins: MaybeAbsent<List<String>>? = null,
    @SerialName("bot_public") val botPublic: MaybeAbsent<Boolean?>? = null,
    @SerialName("bot_require_code_grant") val botRequireCodeGrant: MaybeAbsent<Boolean>? = null,
    val bot: MaybeAbsent<UserObject>? = null,
    @SerialName("terms_of_service_url") val termsOfServiceUrl: MaybeAbsent<String>? = null,
    @SerialName("privacy_policy_url") val privacyPolicyUrl: MaybeAbsent<String>? = null,
    @SerialName("owner") val owner: MaybeAbsent<UserObject>? = null,
    @SerialName("verify_key") val verifyKey: MaybeAbsent<String>? = null,
    val team: MaybeAbsent<TeamObject>? = null,
    @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
    val guild: MaybeAbsent<GuildObject>? = null,
    @SerialName("primary_sku_id") val primarySkuId: MaybeAbsent<Snowflake>? = null,
    val slug: MaybeAbsent<String>? = null,
    @SerialName("cover_image") val coverImage: MaybeAbsent<String>? = null,
    val flags: MaybeAbsent<Int>? = null,
    @SerialName("approximate_guild_count") val approximateGuildCount: MaybeAbsent<Int>? = null,
    @SerialName("approximate_user_install_count") val approximateUserInstallCount: MaybeAbsent<Int>? = null,
    @SerialName("approximate_user_authorization_count") val approximateUserAuthorizationCount: MaybeAbsent<Int>? = null,
    @SerialName("redirect_uris") val redirectUris: MaybeAbsent<List<String>>? = null,
    @SerialName("interactions_endpoint_url") val interactionsEndpointUrl: MaybeAbsent<String>? = null,
    @SerialName("role_connections_verification_url") val roleConnectionsVerificationUrl: MaybeAbsent<String>? = null,
    @SerialName("event_webhooks_url") val eventWebhooksUrl: MaybeAbsent<String>? = null,
    @SerialName("event_webhooks_status") val eventWebhooksStatus: MaybeAbsent<Int>? = null,
    @SerialName("event_webhook_types") val eventWebhookTypes: MaybeAbsent<List<String>>? = null,
    val tags: MaybeAbsent<List<String>>? = null,
    @SerialName("install_params") val installParams: MaybeAbsent<ApplicationInstallParamsObject>? = null,
    @SerialName("integration_types_config") val integrationTypesConfig: MaybeAbsent<Map<String, ApplicationIntegrationTypeConfigurationObject>>? = null,
    @SerialName("custom_install_url") val customInstallUrl: MaybeAbsent<String>? = null,
)

@Serializable
data class ApplicationRoleConnectionMetadataObject(
    val type: MaybeAbsent<Int>? = null,
    val key: MaybeAbsent<String>? = null,
    val name: MaybeAbsent<String>? = null,
    @SerialName("name_localizations") val nameLocalizations: MaybeAbsent<Map<String, String>>? = null,
    val description: MaybeAbsent<String>? = null,
    @SerialName("description_localizations") val descriptionLocalizations: MaybeAbsent<Map<String, String>>? = null
)

@Serializable
data class AttachmentObject(
    val id: MaybeAbsent<Snowflake>? = null,
    val filename: MaybeAbsent<String>? = null,
    val title: MaybeAbsent<String>? = null,
    val description: MaybeAbsent<String>? = null,
    @SerialName("content_type") val contentType: MaybeAbsent<String>? = null,
    val size: MaybeAbsent<Int>? = null,
    val url: MaybeAbsent<String>? = null,
    @SerialName("proxy_url") val proxyUrl: MaybeAbsent<String>? = null,
    val width: MaybeAbsent<Int>? = null,
    val height: MaybeAbsent<Int>? = null,
    val ephemeral: MaybeAbsent<Boolean>? = null,
    @SerialName("duration_secs") val durationSecs: MaybeAbsent<Float>? = null,
    val waveform: MaybeAbsent<String>? = null,
    val flags: MaybeAbsent<Int>? = null,
)

@Serializable(with = AuditLogChangeObject.Serializer::class)
data class AuditLogChangeObject(
    @SerialName("new_value") val newValue: MaybeAbsent<Any?>? = null,
    @SerialName("old_value") val oldValue: MaybeAbsent<Any?>? = null,
    val key: MaybeAbsent<String>? = null,
    @SerialName("channel_id") val channelId: MaybeAbsent<Snowflake>? = null,
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
    @SerialName("target_id") val targetId: MaybeAbsent<String>? = null,
    val changes: MaybeAbsent<List<AuditLogChangeObject>>? = null,
    val userId: MaybeAbsent<Snowflake?>? = null,
    val id: MaybeAbsent<Snowflake>? = null,
    @SerialName("action_type") val actionType: MaybeAbsent<Int>? = null,
    val options: MaybeAbsent<AuditLogOptionalAuditEntryInfoObject>? = null,
    val reason: MaybeAbsent<String>? = null,
    @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null
)

@Serializable
data class AuditLogOptionalAuditEntryInfoObject(
    @SerialName("application_id") val applicationId: MaybeAbsent<Snowflake>? = null,
    @SerialName("auto_moderation_rule_name") val autoModerationRuleName: MaybeAbsent<String>? = null,
    @SerialName("auto_moderation_rule_trigger_type") val autoModerationRuleTriggerType: MaybeAbsent<String>? = null,
    @SerialName("channel_id") val channelId: MaybeAbsent<Snowflake>? = null,
    val count: MaybeAbsent<String>? = null,
    @SerialName("delete_member_days") val deleteMemberDays: MaybeAbsent<String>? = null,
    val id: MaybeAbsent<Snowflake>? = null,
    @SerialName("members_removed") val membersRemoved: MaybeAbsent<String>? = null,
    @SerialName("message_id") val messageId: MaybeAbsent<Snowflake>? = null,
    @SerialName("role_name") val roleName: MaybeAbsent<String>? = null,
    val type: MaybeAbsent<String>? = null,
    @SerialName("integration_type") val integrationType: MaybeAbsent<String>? = null,
)

@Serializable
data class AutoModerationActionMetadataObject(
    @SerialName("channel_id") val channelId: MaybeAbsent<Snowflake>? = null,
    @SerialName("duration_seconds") val durationSeconds: MaybeAbsent<Int>? = null,
    @SerialName("custom_message") val customMessage: MaybeAbsent<String>? = null
)

@Serializable
data class AutoModerationActionObject(
    val type: MaybeAbsent<Int>? = null,
    val metadata: MaybeAbsent<AutoModerationActionMetadataObject>? = null
)

@Serializable
data class AutoModerationRuleTriggerMetadataObject(
    @SerialName("keyword_filter") val keywordFilter: MaybeAbsent<List<String>>? = null,
    @SerialName("regex_patterns") val regexPatterns: MaybeAbsent<List<String>>? = null,
    val presets: MaybeAbsent<List<Int>>? = null,
    @SerialName("allow_list") val allowList: MaybeAbsent<List<String>>? = null,
    @SerialName("mention_total_limit") val mentionTotalLimit: MaybeAbsent<Int>? = null,
    @SerialName("mention_raid_protection_enabled") val mentionRaidProtectionEnabled: MaybeAbsent<Boolean>? = null
)

@Serializable
data class UserAvatarDecorationDataObject(
    val asset: MaybeAbsent<String>? = null,
    @SerialName("sku_id") val skuId: MaybeAbsent<Snowflake>? = null
)

@Serializable
data class ChannelDefaultReactionObject(
    @SerialName("emoji_id") val emojiId: MaybeAbsent<String>? = null,
    @SerialName("emoji_name") val emojiName: MaybeAbsent<String>? = null
)

@Serializable
data class ChannelMentionObject(
    val id: MaybeAbsent<Snowflake>? = null,
    @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
    val type: MaybeAbsent<Int>? = null,
    val name: MaybeAbsent<String>? = null
)

@Serializable
data class ChannelObject(
    val id: MaybeAbsent<Snowflake>? = null,
    val type: MaybeAbsent<Int>? = null,
    @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
    val position: MaybeAbsent<Int>? = null,
    @SerialName("permission_overwrites") val permissionOverwrites: MaybeAbsent<List<ChannelPermissionOverwriteObject>>? = null,
    val name: MaybeAbsent<String>? = null,
    val topic: MaybeAbsent<String>? = null,
    val nsfw: MaybeAbsent<Boolean>? = null,
    @SerialName("last_message_id") val lastMessageId: MaybeAbsent<String>? = null,
    val bitrate: MaybeAbsent<Int>? = null,
    @SerialName("user_limit") val userLimit: MaybeAbsent<Int>? = null,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: MaybeAbsent<Int>? = null,
    val recipients: MaybeAbsent<List<UserObject>>? = null,
    val icon: MaybeAbsent<String>? = null,
    @SerialName("owner_id") val ownerId: MaybeAbsent<Snowflake>? = null,
    @SerialName("application_id") val applicationId: MaybeAbsent<Snowflake>? = null,
    val managed: MaybeAbsent<Boolean>? = null,
    @SerialName("parent_id") val parentId: MaybeAbsent<Snowflake>? = null,
    @SerialName("last_pin_timestamp") val lastPinTimestamp: MaybeAbsent<ISO8601Timestamp>? = null,
    @SerialName("rtc_region") val rtcRegion: MaybeAbsent<String>? = null,
    @SerialName("video_quality_mode") val videoQualityMode: MaybeAbsent<Int>? = null,
    @SerialName("message_count") val messageCount: MaybeAbsent<Int>? = null,
    @SerialName("member_count") val memberCount: MaybeAbsent<Int>? = null,
    @SerialName("thread_metadata") val threadMetadata: MaybeAbsent<ThreadMetadataObject>? = null,
    @SerialName("thread_member") val member: MaybeAbsent<ThreadMemberObject>? = null,
    @SerialName("default_auto_archive_duration") val defaultAutoArchiveDuration: MaybeAbsent<Int>? = null,
    @SerialName("permissions") val permissions: MaybeAbsent<String>? = null,
    val flags: MaybeAbsent<Int>? = null,
    @SerialName("total_message_sent") val totalMessageSent: MaybeAbsent<Int>? = null,
    @SerialName("available_tags") val availableTags: MaybeAbsent<List<ForumTagObject>>? = null,
    @SerialName("applied_tags") val appliedTags: MaybeAbsent<List<Snowflake>>? = null,
    @SerialName("default_reaction_emoji") val defaultReactionEmoji: MaybeAbsent<ChannelDefaultReactionObject>? = null,
    @SerialName("default_thread_rate_limit_per_user") val defaultThreadRateLimitPerUser: MaybeAbsent<Int>? = null,
    @SerialName("default_sort_order") val defaultSortOrder: MaybeAbsent<Int>? = null,
    @SerialName("default_forum_layout") val defaultForumLayout: MaybeAbsent<Int>? = null,
    val message: MaybeAbsent<MessageObject>? = null, // from starting a thread in forum / media channel
)

@Serializable
data class ChannelPermissionOverwriteObject(
    val id: MaybeAbsent<Snowflake>? = null,
    val type: MaybeAbsent<Int>? = null,
    val allow: MaybeAbsent<String?>? = null,
    val deny: MaybeAbsent<String?>? = null
)

@Serializable
data class ClientStatusObject(
    val desktop: MaybeAbsent<String>? = null,
    val mobile: MaybeAbsent<String>? = null,
    val web: MaybeAbsent<String>? = null
)

@Serializable
data class EmbedAuthorObject(
    val name: MaybeAbsent<String>? = null,
    val url: MaybeAbsent<String>? = null,
    @SerialName("icon_url") val iconUrl: MaybeAbsent<String>? = null,
    @SerialName("proxy_icon_url") val proxyIconUrl: MaybeAbsent<String>? = null,
)

@Serializable
data class EmbedFieldObject(
    val name: MaybeAbsent<String>? = null,
    val value: MaybeAbsent<String>? = null,
    val inline: MaybeAbsent<Boolean>? = null
)

@Serializable
data class EmbedFooterObject(
    val text: MaybeAbsent<String>? = null,
    @SerialName("icon_url") val iconUrl: MaybeAbsent<String>? = null,
    @SerialName("proxy_icon_url") val proxyIconUrl: MaybeAbsent<String>? = null
)

@Serializable
data class EmbedImageObject(
    val url: MaybeAbsent<String>? = null,
    @SerialName("proxy_url") val proxyUrl: MaybeAbsent<String>? = null,
    val width: MaybeAbsent<Int>? = null,
    val height: MaybeAbsent<Int>? = null
)

@Serializable
data class EmbedObject(
    val title: MaybeAbsent<String>? = null,
    val type: MaybeAbsent<String>? = null,
    val description: MaybeAbsent<String>? = null,
    val url: MaybeAbsent<String>? = null,
    val timestamp: MaybeAbsent<ISO8601Timestamp>? = null,
    val color: MaybeAbsent<Int>? = null,
    val footer: MaybeAbsent<EmbedFooterObject>? = null,
    val image: MaybeAbsent<EmbedImageObject>? = null,
    val thumbnail: MaybeAbsent<EmbedThumbnailObject>? = null,
    val video: MaybeAbsent<EmbedVideoObject>? = null,
    val provider: MaybeAbsent<EmbedProviderObject>? = null,
    val author: MaybeAbsent<EmbedAuthorObject>? = null,
    val fields: MaybeAbsent<List<EmbedFieldObject>>? = null
)

@Serializable
data class EmbedProviderObject(
    val name: MaybeAbsent<String>? = null,
    val url: MaybeAbsent<String>? = null
)

@Serializable
data class EmbedThumbnailObject(
    val url: MaybeAbsent<String>? = null,
    @SerialName("proxy_url") val proxyUrl: MaybeAbsent<String>? = null,
    val width: MaybeAbsent<Int>? = null,
    val height: MaybeAbsent<Int>? = null
)

@Serializable
data class EmbedVideoObject(
    val url: MaybeAbsent<String>? = null,
    @SerialName("proxy_url") val proxyUrl: MaybeAbsent<String>? = null,
    val width: MaybeAbsent<String>? = null,
    val height: MaybeAbsent<String>? = null
)

@Serializable
data class EmojiObject(
    val id: MaybeAbsent<Snowflake?>? = null,
    val name: MaybeAbsent<String?>? = null,
    val roles: MaybeAbsent<List<Snowflake>>? = null,
    val user: MaybeAbsent<UserObject>? = null,
    @SerialName("require_colons") val requireColons: MaybeAbsent<Boolean>? = null,
    val managed: MaybeAbsent<Boolean>? = null,
    val animated: MaybeAbsent<Boolean>? = null,
    val available: MaybeAbsent<Boolean>? = null
)

@Serializable
data class EntitlementObject(
    val id: MaybeAbsent<Snowflake>? = null,
    @SerialName("sku_id") val skuId: MaybeAbsent<Snowflake>? = null,
    @SerialName("application_id") val applicationId: MaybeAbsent<Snowflake>? = null,
    @SerialName("user_id") val userId: MaybeAbsent<Snowflake>? = null,
    val type: MaybeAbsent<Int>? = null,
    val deleted: MaybeAbsent<Boolean>? = null,
    @SerialName("starts_at") val startsAt: MaybeAbsent<ISO8601Timestamp>? = null,
    @SerialName("ends_at") val endsAt: MaybeAbsent<ISO8601Timestamp>? = null,
    @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
    val consumed: MaybeAbsent<Boolean>? = null,
    @SerialName("subscription_id") val subscriptionId: MaybeAbsent<Snowflake>? = null,
)

@Serializable
data class FollowedChannelObject(
    @SerialName("channel_id") val channelId: MaybeAbsent<Snowflake>? = null,
    @SerialName("webhook_id") val webhookId: MaybeAbsent<Snowflake>? = null
)

@Serializable
data class ForumTagObject(
    val id: MaybeAbsent<Snowflake>? = null,
    val name: MaybeAbsent<String>? = null,
    val moderated: MaybeAbsent<Boolean>? = null,
    @SerialName("emoji_id") val emojiId: MaybeAbsent<Snowflake>? = null,
    @SerialName("emoji_name") val emojiName: MaybeAbsent<String>? = null
)

@Serializable
data class ForumThreadMessageParametersObject(
    val content: MaybeAbsent<String>? = null,
    val embeds: MaybeAbsent<List<EmbedObject>>? = null,
    val allowedMentions: MaybeAbsent<AllowedMentionsObject>? = null,
    val components: MaybeAbsent<List<MessageComponentObject>>? = null,
    val stickerIds: MaybeAbsent<List<Snowflake>>? = null,
    val attachments: MaybeAbsent<List<AttachmentObject>>? = null,
    val flags: MaybeAbsent<Int>? = null
)

@Serializable
data class GuildApplicationCommandPermissionsObject(
    val id: MaybeAbsent<Snowflake>? = null,
    @SerialName("application_id") val applicationId: MaybeAbsent<Snowflake>? = null,
    @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
    val permissions: MaybeAbsent<List<ApplicationCommandPermissionObject>>? = null
)

@Serializable
data class GuildAuditLogObject(
    @SerialName("application_commands") val applicationCommands: MaybeAbsent<List<ApplicationCommandObject>>? = null,
    @SerialName("audit_log_entries") val auditLogEntries: MaybeAbsent<List<AuditLogEntryObject>>? = null,
    @SerialName("autoModerationRules") val autoModerationRules: MaybeAbsent<List<GuildAutoModerationRuleObject>>? = null,
    @SerialName("guild_scheduled_events") val guildScheduledEvents: MaybeAbsent<List<GuildScheduledEventObject>>? = null,
    val integrations: MaybeAbsent<List<GuildIntegrationObject>>? = null,
    val threads: MaybeAbsent<List<ChannelObject>>? = null,
    val users: MaybeAbsent<List<UserObject>>? = null,
    val webhooks: MaybeAbsent<List<WebhookObject>>? = null
)

@Serializable
data class GuildAutoModerationRuleObject(
    val id: MaybeAbsent<Snowflake>? = null,
    @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
    val name: MaybeAbsent<String>? = null,
    @SerialName("creator_id") val creatorId: MaybeAbsent<Snowflake>? = null,
    @SerialName("event_type") val eventType: MaybeAbsent<Int>? = null,
    @SerialName("trigger_type") val triggerType: MaybeAbsent<Int>? = null,
    @SerialName("trigger_metadata") val triggerMetadata: MaybeAbsent<AutoModerationRuleTriggerMetadataObject>? = null,
    val actions: MaybeAbsent<List<AutoModerationActionObject>>? = null,
    val enabled: MaybeAbsent<Boolean>? = null,
    @SerialName("exempt_roles") val exemptRoles: MaybeAbsent<List<Snowflake>>? = null,
    @SerialName("exempt_channels") val exemptChannels: MaybeAbsent<List<Snowflake>>? = null
)

@Serializable
data class GuildBanObject(
    val reason: MaybeAbsent<String>? = null,
    val user: MaybeAbsent<UserObject>? = null
)

@Serializable
data class GuildIntegrationObject(
    val id: MaybeAbsent<Snowflake>? = null,
    val name: MaybeAbsent<String>? = null,
    val type: MaybeAbsent<String>? = null,
    val enabled: MaybeAbsent<Boolean>? = null,
    val syncing: MaybeAbsent<Boolean>? = null,
    @SerialName("role_id") val roleId: MaybeAbsent<Snowflake>? = null,
    @SerialName("enable_emoticons") val enableEmoticons: MaybeAbsent<Boolean>? = null,
    @SerialName("expire_behaviour") val expireBehaviour: MaybeAbsent<Int>? = null,
    @SerialName("expire_grace_period") val expireGracePeriod: MaybeAbsent<Int>? = null,
    val user: MaybeAbsent<UserObject>? = null,
    val account: MaybeAbsent<IntegrationAccountObject>? = null,
    @SerialName("synced_at") val syncedAt: MaybeAbsent<ISO8601Timestamp>? = null,
    @SerialName("subscriber_count") val subscriberCount: MaybeAbsent<Int>? = null,
    val revoked: MaybeAbsent<Boolean>? = null,
    val application: MaybeAbsent<IntegrationApplicationObject>? = null,
    val scopes: MaybeAbsent<List<String>>? = null,
    @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null
)

@Serializable
data class GuildMemberObject(
    val user: MaybeAbsent<UserObject>? = null,
    val nick: MaybeAbsent<String>? = null,
    val avatar: MaybeAbsent<String>? = null,
    val banner: MaybeAbsent<String>? = null,
    val roles: MaybeAbsent<List<Snowflake>>? = null,
    @SerialName("joined_at") val joinedAt: MaybeAbsent<ISO8601Timestamp>? = null,
    @SerialName("premium_since") val premiumSince: MaybeAbsent<ISO8601Timestamp>? = null,
    val deaf: MaybeAbsent<Boolean>? = null,
    val mute: MaybeAbsent<Boolean>? = null,
    val flags: MaybeAbsent<Int>? = null,
    val pending: MaybeAbsent<Boolean>? = null,
    val permissions: MaybeAbsent<String>? = null,
    @SerialName("communication_disabled_until") val communicationDisabledUntil: MaybeAbsent<ISO8601Timestamp>? = null,
    @SerialName("avatar_decoration_data") val avatarDecorationData: MaybeAbsent<UserAvatarDecorationDataObject>? = null,
    @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null
)

/**
 * Low-level representation of Discord API Guild object and UnavailableGuild object.
 *
 * @see <a href="https://discord.com/developers/docs/resources/guild">Discord API Reference: Value<Guild</a>
 */
@Serializable
data class GuildObject(
    val id: MaybeAbsent<Snowflake>? = null,
    val name: MaybeAbsent<String>? = null,
    val icon: MaybeAbsent<String>? = null,
    @SerialName("icon_hash") val iconHash: MaybeAbsent<String>? = null,
    val splash: MaybeAbsent<String>? = null,
    @SerialName("discovery_splash") val discoverySplash: MaybeAbsent<String>? = null,
    val owner: MaybeAbsent<String>? = null, //Only set when using the GET Current User Guilds endpoint, and are relative to the requested user
    @SerialName("owner_id") val ownerId: MaybeAbsent<Snowflake>? = null,
    val permissions: MaybeAbsent<String>? = null,
    @Deprecated("replaced by channel.rtc_region") val region: MaybeAbsent<String>? = null,
    @SerialName("afk_channel_id") val afkChannelId: MaybeAbsent<Snowflake>? = null,
    @SerialName("afk_timeout") val afkTimeout: MaybeAbsent<Int>? = null,
    @SerialName("widget_enabled") val widgetEnabled: MaybeAbsent<Boolean>? = null,
    @SerialName("widget_channel_id") val widgetChannelId: MaybeAbsent<Snowflake>? = null,
    @SerialName("verification_level") val verificationLevel: MaybeAbsent<Int>? = null,
    @SerialName("default_message_notifications") val defaultMessageNotifications: MaybeAbsent<Int>? = null,
    @SerialName("explicit_content_filter") val explicitContentFilter: MaybeAbsent<Int>? = null,
    val roles: MaybeAbsent<List<RoleObject>>? = null,
    val emojis: MaybeAbsent<List<EmojiObject>>? = null,
    val features: MaybeAbsent<List<String>>? = null,
    @SerialName("mfa_level") val mfaLevel: MaybeAbsent<Int>? = null,
    @SerialName("application_id") val applicationId: MaybeAbsent<Snowflake>? = null,
    @SerialName("system_channel_id") val systemChannelId: MaybeAbsent<Snowflake>? = null,
    @SerialName("system_channel_flags") val systemChannelFlags: MaybeAbsent<Int>? = null,
    @SerialName("rules_channel_id") val rulesChannelId: MaybeAbsent<Snowflake>? = null,
    @SerialName("max_presences") val maxPresences: MaybeAbsent<Int>? = null,
    @SerialName("max_members") val maxMembers: MaybeAbsent<Int>? = null,
    @SerialName("vanity_url_code") val vanityUrlCode: MaybeAbsent<String>? = null,
    val description: MaybeAbsent<String>? = null,
    val banner: MaybeAbsent<String>? = null,
    @SerialName("premium_tier") val premiumTier: MaybeAbsent<Int>? = null,
    @SerialName("premium_subscription_count") val premiumSubscriptionCount: MaybeAbsent<Int>? = null,
    @SerialName("preferred_locale") val preferredLocale: MaybeAbsent<String>? = null,
    @SerialName("public_updates_channel_id") val publicUpdatesChannelId: MaybeAbsent<Snowflake>? = null,
    @SerialName("max_video_channel_users") val maxVideoChannelUsers: MaybeAbsent<Int>? = null,
    @SerialName("max_stage_video_channel_users") val maxStageVideoChannelUsers: MaybeAbsent<Int>? = null,
    @SerialName("approximate_member_count") val approximateMemberCount: MaybeAbsent<Int>? = null,
    @SerialName("approximate_presence_count") val approximatePresenceCount: MaybeAbsent<Int>? = null,
    @SerialName("welcome_screen") val welcomeScreen: MaybeAbsent<GuildWelcomeScreenObject>? = null,
    @SerialName("nsfw_level") val nsfwLevel: MaybeAbsent<Int>? = null,
    val stickers: MaybeAbsent<List<StickerObject>>? = null,
    @SerialName("premium_progress_bar_enabled") val premiumProgressBarEnabled: MaybeAbsent<Boolean>? = null,
    @SerialName("safety_alerts_channel_id") val safetyAlertsChannelId: MaybeAbsent<Snowflake>? = null,
    val unavailable: MaybeAbsent<Boolean>? = null,
    // From Gateway Guild Create Event
    @SerialName("joined_at") val joinedAt: MaybeAbsent<ISO8601Timestamp>? = null,
    @SerialName("large") val large: MaybeAbsent<Boolean>? = null,
    @SerialName("member_count") val memberCount: MaybeAbsent<Int>? = null,
    @SerialName("voice_states") val voiceStates: MaybeAbsent<List<VoiceStateObject>>? = null,
    @SerialName("members") val members: MaybeAbsent<List<GuildMemberObject>>? = null,
    @SerialName("channels") val channels: MaybeAbsent<List<ChannelObject>>? = null,
    @SerialName("threads") val threads: MaybeAbsent<List<ChannelObject>>? = null,
    @SerialName("presences") val presences: MaybeAbsent<List<UpdatePresenceObject>>? = null,
    @SerialName("stage_instances") val stageInstances: MaybeAbsent<List<StageInstanceObject>>? = null,
    @SerialName("guild_scheduled_events") val guildScheduledEvents: MaybeAbsent<List<GuildScheduledEventObject>>? = null,
    @SerialName("soundboard_sounds") val soundboardSounds: MaybeAbsent<List<SoundboardSoundObject>>? = null,
)

@Serializable
data class GuildOnboardingObject(
    @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
    val prompts: MaybeAbsent<List<GuildOnboardingPromptObject>>? = null,
    @SerialName("default_channel_ids") val defaultChannelIds: MaybeAbsent<List<Snowflake>>? = null,
    val enabled: MaybeAbsent<Boolean>? = null,
    val mode: MaybeAbsent<Int>? = null
)

@Serializable
data class GuildOnboardingPromptObject(
    val id: MaybeAbsent<Snowflake>? = null,
    val options: MaybeAbsent<List<GuildOnboardingPromptOptionObject>>? = null,
    val title: MaybeAbsent<String>? = null,
    @SerialName("single_select") val singleSelect: MaybeAbsent<Boolean>? = null,
    val required: MaybeAbsent<Boolean>? = null,
    @SerialName("in_onboarding") val inOnboarding: MaybeAbsent<Boolean>? = null
)

@Serializable
data class GuildOnboardingPromptOptionObject(
    val id: MaybeAbsent<Snowflake>? = null,
    @SerialName("channel_ids") val channelIds: MaybeAbsent<List<Snowflake>>? = null,
    @SerialName("role_ids") val roleIds: MaybeAbsent<List<Snowflake>>? = null,
    val emoji: MaybeAbsent<EmojiObject>? = null,
    @SerialName("emoji_id") val emojiId: MaybeAbsent<Snowflake>? = null,
    @SerialName("emoji_name") val emojiName: MaybeAbsent<String>? = null,
    @SerialName("emoji_animated") val emojiAnimated: MaybeAbsent<Boolean>? = null,
    val title: MaybeAbsent<String>? = null,
    val description: MaybeAbsent<String>? = null
)

@Serializable
data class GuildPreviewObject(
    val id: MaybeAbsent<Snowflake>? = null,
    val name: MaybeAbsent<String>? = null,
    val icon: MaybeAbsent<String>? = null,
    val splash: MaybeAbsent<String>? = null,
    @SerialName("discovery_splash") val discoverySplash: MaybeAbsent<String>? = null,
    val emojis: MaybeAbsent<List<EmojiObject>>? = null,
    val features: MaybeAbsent<List<String>>? = null,
    @SerialName("approximate_member_count") val approximateMemberCount: MaybeAbsent<Int>? = null,
    @SerialName("approximate_presence_count") val approximatePresenceCount: MaybeAbsent<Int>? = null,
    val description: MaybeAbsent<String>? = null,
    val stickers: MaybeAbsent<List<StickerObject>>? = null
)

@Serializable
data class GuildScheduledEventEntityMetadataObject(
    val location: MaybeAbsent<String>? = null
)

@Serializable
data class GuildScheduledEventObject(
    val id: MaybeAbsent<Snowflake>? = null,
    @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
    @SerialName("channel_id") val channelId: MaybeAbsent<Snowflake>? = null,
    @SerialName("creator_id") val creatorId: MaybeAbsent<Snowflake>? = null,
    val name: MaybeAbsent<String>? = null,
    val description: MaybeAbsent<String>? = null,
    @SerialName("scheduled_start_time") val scheduledStartTime: MaybeAbsent<ISO8601Timestamp>? = null,
    @SerialName("scheduled_end_time") val scheduledEndTime: MaybeAbsent<ISO8601Timestamp>? = null,
    @SerialName("privacy_level") val privacyLevel: MaybeAbsent<Int>? = null,
    val status: MaybeAbsent<Int>? = null,
    @SerialName("entity_type") val entityType: MaybeAbsent<Int>? = null,
    @SerialName("entity_id") val entityId: MaybeAbsent<Snowflake>? = null,
    @SerialName("entity_metadata") val entityMetadata: MaybeAbsent<GuildScheduledEventEntityMetadataObject>? = null,
    val creator: MaybeAbsent<UserObject>? = null,
    @SerialName("user_count") val userCount: MaybeAbsent<Int>? = null,
    val image: MaybeAbsent<String>? = null,
    @SerialName("recurrence_rule") val recurrenceRule: MaybeAbsent<GuildScheduledEventRecurrenceRuleObject>? = null,
)

@Serializable
data class GuildScheduledEventRecurrenceRuleObject(
    val start: MaybeAbsent<ISO8601Timestamp>? = null,
    val end: MaybeAbsent<ISO8601Timestamp>? = null,
    val frequency: MaybeAbsent<Int>? = null,
    val interval: MaybeAbsent<Int>? = null,
    @SerialName("by_weekday") val byWeekday: MaybeAbsent<List<Int>>? = null,
    @SerialName("by_n_weekday") val byNWeekday: MaybeAbsent<List<RecurrenceRuleNWeekdayObject>>? = null,
    @SerialName("by_month") val byMonth: MaybeAbsent<List<Int>>? = null,
    @SerialName("by_month_day") val byMonthDay: MaybeAbsent<List<Int>>? = null,
    @SerialName("by_year_day") val byYearDay: MaybeAbsent<List<Int>>? = null,
    val count: MaybeAbsent<Int>? = null
)

@Serializable
data class GuildScheduledEventUserObject(
    val guildScheduledEventId: MaybeAbsent<Snowflake>? = null,
    val user: MaybeAbsent<UserObject>? = null,
    val member: MaybeAbsent<GuildMemberObject>? = null
)

@Serializable
data class GuildTemplateObject(
    val code: MaybeAbsent<String>? = null,
    val name: MaybeAbsent<String>? = null,
    val description: MaybeAbsent<String>? = null,
    @SerialName("usage_count") val usageCount: MaybeAbsent<Int>? = null,
    @SerialName("creator_id") val creatorId: MaybeAbsent<Snowflake>? = null,
    val creator: MaybeAbsent<UserObject>? = null,
    @SerialName("created_at") val createdAt: MaybeAbsent<ISO8601Timestamp>? = null,
    @SerialName("updated_at") val updatedAt: MaybeAbsent<ISO8601Timestamp>? = null,
    @SerialName("source_guild_id") val sourceGuildId: MaybeAbsent<Snowflake>? = null,
    @SerialName("serialized_source_guild") val serializedSourceGuild: MaybeAbsent<GuildObject>? = null,
    @SerialName("is_dirty") val isDirty: MaybeAbsent<Boolean>? = null
)

@Serializable
data class GuildWelcomeScreenChannelObject(
    @SerialName("channel_id") val channelId: MaybeAbsent<Snowflake>? = null,
    val description: MaybeAbsent<String>? = null,
    @SerialName("emoji_id") val emojiId: MaybeAbsent<Snowflake>? = null,
    @SerialName("emoji_name") val emojiName: MaybeAbsent<String>? = null
)

@Serializable
data class GuildWelcomeScreenObject(
    val description: MaybeAbsent<String>? = null,
    @SerialName("welcome_channels") val welcomeChannels: MaybeAbsent<List<GuildWelcomeScreenChannelObject>>? = null
)

@Serializable
data class GuildWidgetObject(
    val id: MaybeAbsent<Snowflake>? = null,
    val name: MaybeAbsent<String>? = null,
    @SerialName("instant_invite") val instantInvite: MaybeAbsent<String>? = null,
    val channels: MaybeAbsent<List<ChannelObject>>? = null,
    val members: MaybeAbsent<List<GuildMemberObject>>? = null,
    @SerialName("presence_count") val presenceCount: MaybeAbsent<Int>? = null
)

@Serializable
data class GuildWidgetSettingsObject(
    val enabled: MaybeAbsent<Boolean>? = null,
    @SerialName("channel_id") val channelId: MaybeAbsent<Snowflake>? = null
)

@Serializable
data class IntegrationAccountObject(
    val id: MaybeAbsent<String>? = null,
    val name: MaybeAbsent<String>? = null
)

@Serializable
data class IntegrationApplicationObject(
    val id: MaybeAbsent<Snowflake>? = null,
    val name: MaybeAbsent<String>? = null,
    val icon: MaybeAbsent<String>? = null,
    val description: MaybeAbsent<String>? = null,
    val bot: MaybeAbsent<UserObject>? = null
)

@Serializable
data class InteractionDataObject(
    val id: MaybeAbsent<Snowflake>? = null,
    val name: MaybeAbsent<String>? = null,
    val type: MaybeAbsent<Int>? = null,
    val resolved: MaybeAbsent<ResolvedDataObject>? = null,
    val options: MaybeAbsent<List<ApplicationCommandInteractionDataOptionObject>>? = null,
    @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
    @SerialName("target_id") val targetId: MaybeAbsent<Snowflake>? = null
)

@Serializable
data class InteractionObject(
    val id: MaybeAbsent<Snowflake>? = null,
    @SerialName("application_id") val applicationId: MaybeAbsent<Snowflake>? = null,
    val type: MaybeAbsent<Int>? = null,
    val data: MaybeAbsent<InteractionDataObject>? = null,
    val guild: MaybeAbsent<GuildObject>? = null,
    @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
    val channel: MaybeAbsent<ChannelObject>? = null,
    @SerialName("channel_id") val channelId: MaybeAbsent<Snowflake>? = null,
    val member: MaybeAbsent<GuildMemberObject>? = null,
    val user: MaybeAbsent<UserObject>? = null,
    val token: MaybeAbsent<String>? = null,
    val version: MaybeAbsent<Int>? = null,
    val message: MaybeAbsent<MessageObject>? = null,
    @SerialName("app_permissions") val appPermissions: MaybeAbsent<String>? = null,
    val locale: MaybeAbsent<String>? = null,
    @SerialName("guild_locale") val guildLocale: MaybeAbsent<String>? = null,
    val entitlements: MaybeAbsent<List<EntitlementObject>>? = null,
    @SerialName("authorizing_integration_owners") val authorizingIntegrationOwners: MaybeAbsent<Map<Int, String>>? = null
)

@Serializable
data class InviteObject(
    val type: MaybeAbsent<Int>? = null,
    val code: MaybeAbsent<String>? = null,
    val uses: MaybeAbsent<Int>? = null,
    val guild: MaybeAbsent<GuildObject>? = null,
    val channel: MaybeAbsent<ChannelObject>? = null,
    val inviter: MaybeAbsent<UserObject>? = null,
    @SerialName("target_type") val targetType: MaybeAbsent<Int>? = null,
    @SerialName("target_user") val targetUser: MaybeAbsent<UserObject>? = null,
    @SerialName("target_application") val targetApplication: MaybeAbsent<ApplicationObject>? = null,
    @SerialName("approximate_presence_count") val approximatePresenceCount: MaybeAbsent<Int>? = null,
    @SerialName("approximate_member_count") val approximateMemberCount: MaybeAbsent<Int>? = null,
    @SerialName("expires_at") val expiresAt: MaybeAbsent<ISO8601Timestamp>? = null,
    @SerialName("stage_instance") val stageInstance: MaybeAbsent<InviteStageInstanceObject>? = null,
    @SerialName("guild_scheduled_event") val guildScheduledEvent: MaybeAbsent<GuildScheduledEventObject>? = null,
    val flags: MaybeAbsent<Int>? = null
)

@Serializable
data class InviteStageInstanceObject(
    val members: MaybeAbsent<List<GuildMemberObject>>? = null,
    @SerialName("participation_count") val participationCount: MaybeAbsent<Int>? = null,
    @SerialName("speaker_count") val speakerCount: MaybeAbsent<Int>? = null,
    val topic: MaybeAbsent<String>? = null
)

interface MessageComponentObject {
    val type: MaybeAbsent<Int>?
    val id: MaybeAbsent<Int>?
    val customId: MaybeAbsent<String>?
}

@Serializable
data class ActionRowComponentObject(
    override val type: MaybeAbsent<Int>? = null,
    override val id: MaybeAbsent<Int>? = null,
    @SerialName("custom_id") override val customId: MaybeAbsent<String>? = null,
    val components: MaybeAbsent<List<MessageComponentObject>>? = null,
) : MessageComponentObject

@Serializable
data class ButtonComponentObject(
    override val type: MaybeAbsent<Int>? = null,
    override val id: MaybeAbsent<Int>? = null,
    @SerialName("custom_id") override val customId: MaybeAbsent<String>? = null,
    val style: MaybeAbsent<Int>? = null,
    val label: MaybeAbsent<String>? = null,
    val emoji: MaybeAbsent<EmojiObject>? = null,
    @SerialName("sku_id") val skuId: MaybeAbsent<Snowflake>? = null,
    val url: MaybeAbsent<String>? = null,
    val disabled: MaybeAbsent<Boolean>? = null,
) : MessageComponentObject

@Serializable
data class StringSelectComponentObject(
    override val type: MaybeAbsent<Int>? = null,
    override val id: MaybeAbsent<Int>? = null,
    @SerialName("custom_id") override val customId: MaybeAbsent<String>? = null,
    val options: MaybeAbsent<List<SelectOptionObject>>? = null,
    val placeholder: MaybeAbsent<String>? = null,
    @SerialName("min_values") val minValues: MaybeAbsent<Int>? = null,
    val disabled: MaybeAbsent<Boolean>? = null,
) : MessageComponentObject

@Serializable
data class SelectOptionObject(
    val label: MaybeAbsent<String>? = null,
    val value: MaybeAbsent<String>? = null,
    val description: MaybeAbsent<String>? = null,
    val emoji: MaybeAbsent<EmojiObject>? = null,
    val default: MaybeAbsent<Boolean>? = null,
)

@Serializable
data class TextInputComponentObject(
    override val type: MaybeAbsent<Int>? = null,
    override val id: MaybeAbsent<Int>? = null,
    @SerialName("custom_id") override val customId: MaybeAbsent<String>? = null,
    val style: MaybeAbsent<Int>? = null,
    val label: MaybeAbsent<String>? = null,
    @SerialName("min_length") val minLength: MaybeAbsent<Int>? = null,
    @SerialName("max_length") val maxLength: MaybeAbsent<Int>? = null,
    val required: MaybeAbsent<Boolean>? = null,
    val value: MaybeAbsent<String>? = null,
    val placeholder: MaybeAbsent<String>? = null,
) : MessageComponentObject

@Serializable
data class UserSelectComponentObject(
    override val type: MaybeAbsent<Int>? = null,
    override val id: MaybeAbsent<Int>? = null,
    @SerialName("custom_id") override val customId: MaybeAbsent<String>? = null,
    val placeholder: MaybeAbsent<String>? = null,
    @SerialName("default_values") val defaultValues: MaybeAbsent<List<SelectDefaultValueObject>>? = null,
    @SerialName("min_values") val minValues: MaybeAbsent<Int>? = null,
    @SerialName("max_values") val maxValues: MaybeAbsent<Int>? = null,
    val disabled: MaybeAbsent<Boolean>? = null,
) : MessageComponentObject

@Serializable
data class SelectDefaultValueObject(
    val id: MaybeAbsent<Snowflake>? = null,
    val type: MaybeAbsent<String>? = null
)

@Serializable
data class RoleSelectComponentObject(
    override val type: MaybeAbsent<Int>? = null,
    override val id: MaybeAbsent<Int>? = null,
    @SerialName("custom_id") override val customId: MaybeAbsent<String>? = null,
    val placeholder: MaybeAbsent<String>? = null,
    @SerialName("default_values") val defaultValues: MaybeAbsent<List<SelectDefaultValueObject>>? = null,
    @SerialName("min_values") val minValues: MaybeAbsent<Int>? = null,
    @SerialName("max_values") val maxValues: MaybeAbsent<Int>? = null,
    val disabled: MaybeAbsent<Boolean>? = null,
) : MessageComponentObject

@Serializable
data class MentionableSelectComponentObject(
    override val type: MaybeAbsent<Int>? = null,
    override val id: MaybeAbsent<Int>? = null,
    @SerialName("custom_id") override val customId: MaybeAbsent<String>? = null,
    val placeholder: MaybeAbsent<String>? = null,
    @SerialName("default_values") val defaultValues: MaybeAbsent<List<SelectDefaultValueObject>>? = null,
    @SerialName("min_values") val minValues: MaybeAbsent<Int>? = null,
    @SerialName("max_values") val maxValues: MaybeAbsent<Int>? = null,
    val disabled: MaybeAbsent<Boolean>? = null,
) : MessageComponentObject

@Serializable
data class ChannelSelectComponentObject(
    override val type: MaybeAbsent<Int>? = null,
    override val id: MaybeAbsent<Int>? = null,
    @SerialName("custom_id") override val customId: MaybeAbsent<String>? = null,
    @SerialName("channel_types") val channelTypes: MaybeAbsent<List<Int>>? = null,
    val placeholder: MaybeAbsent<String>? = null,
    @SerialName("default_values") val defaultValues: MaybeAbsent<List<SelectDefaultValueObject>>? = null,
    @SerialName("min_values") val minValues: MaybeAbsent<Int>? = null,
    @SerialName("max_values") val maxValues: MaybeAbsent<Int>? = null,
    val disabled: MaybeAbsent<Boolean>? = null
) : MessageComponentObject

@Serializable
data class SectionComponentObject(
    override val type: MaybeAbsent<Int>? = null,
    override val id: MaybeAbsent<Int>? = null,
    @SerialName("custom_id") override val customId: MaybeAbsent<String>? = null,
    val components: MaybeAbsent<List<TextDisplayComponentObject>>? = null,
    val accessory: MaybeAbsent<MessageComponentObject>? = null,
) : MessageComponentObject

@Serializable
data class TextDisplayComponentObject(
    override val type: MaybeAbsent<Int>? = null,
    override val id: MaybeAbsent<Int>? = null,
    @SerialName("custom_id") override val customId: MaybeAbsent<String>? = null,
    val content: MaybeAbsent<String>? = null
) : MessageComponentObject

@Serializable
data class ThumbnailComponentObject(
    override val type: MaybeAbsent<Int>? = null,
    override val id: MaybeAbsent<Int>? = null,
    @SerialName("custom_id") override val customId: MaybeAbsent<String>? = null,
    val media: MaybeAbsent<UnfurledMediaItemObject>? = null,
    val description: MaybeAbsent<String>? = null,
    val spoiler: MaybeAbsent<Boolean>? = null,
) : MessageComponentObject

@Serializable
data class UnfurledMediaItemObject(
    val url: MaybeAbsent<String>? = null,
    @SerialName("proxy_url") val proxyUrl: MaybeAbsent<String>? = null,
    val height: MaybeAbsent<Int>? = null,
    val width: MaybeAbsent<Int>? = null,
    @SerialName("content_type") val contentType: MaybeAbsent<String>? = null,
)

@Serializable
data class FileComponentObject(
    override val type: MaybeAbsent<Int>? = null,
    override val id: MaybeAbsent<Int>? = null,
    @SerialName("custom_id") override val customId: MaybeAbsent<String>? = null,
    val file: MaybeAbsent<UnfurledMediaItemObject>? = null,
    val spoiler: MaybeAbsent<Boolean>? = null
) : MessageComponentObject

@Serializable
data class SeparatorComponentObject(
    override val type: MaybeAbsent<Int>? = null,
    override val id: MaybeAbsent<Int>? = null,
    @SerialName("custom_id") override val customId: MaybeAbsent<String>? = null,
    val divider: MaybeAbsent<Boolean>? = null,
    val spacing: MaybeAbsent<Int>? = null,
) : MessageComponentObject

@Serializable
data class ContainerComponentObject(
    override val type: MaybeAbsent<Int>,
    override val id: MaybeAbsent<Int>? = null,
    @SerialName("custom_id") override val customId: MaybeAbsent<String>? = null,
    val components: MaybeAbsent<List<MessageComponentObject>>? = null,
    @SerialName("accent_color") val accentColor: MaybeAbsent<Int>? = null,
    val spoiler: MaybeAbsent<Boolean>? = null,
) : MessageComponentObject

@Serializable
data class MessageActivityObject(
    val type: MaybeAbsent<Int>? = null,
    @SerialName("party_id") val partyId: MaybeAbsent<String>? = null
)

@Serializable
data class MessageCallObject(
    val participants: MaybeAbsent<List<Snowflake>>? = null,
    val endedTimestamp: MaybeAbsent<ISO8601Timestamp>? = null
)

@Serializable
abstract class MessageInteractionMetadataObject {
    abstract val id: MaybeAbsent<Snowflake>?
    abstract val type: MaybeAbsent<Int>?
    abstract val user: MaybeAbsent<UserObject>?

    @SerialName("authorizing_integration_owners")
    abstract val authorizingIntegrationOwners: MaybeAbsent<Map<Int, Snowflake>>?
    
    @SerialName("original_response_message_id")
    abstract val originalResponseMessageId: MaybeAbsent<Snowflake>?
}

@Serializable
data class ApplicationCommandInteractionMetadataObject(
    override val id: MaybeAbsent<Snowflake>? = null,
    override val type: MaybeAbsent<Int>? = null,
    override val user: MaybeAbsent<UserObject>? = null,
    override val authorizingIntegrationOwners: MaybeAbsent<Map<Int, Snowflake>>? = null,
    override val originalResponseMessageId: MaybeAbsent<Snowflake>? = null,
    @SerialName("target_user") val targetUser: MaybeAbsent<UserObject>? = null,
    @SerialName("target_message_id") val targetMessageId: MaybeAbsent<Snowflake>? = null,
) : MessageInteractionMetadataObject()

@Serializable
data class MessageComponentInteractionMetadataObject(
    override val id: MaybeAbsent<Snowflake>? = null,
    override val type: MaybeAbsent<Int>? = null,
    override val user: MaybeAbsent<UserObject>? = null,
    override val authorizingIntegrationOwners: MaybeAbsent<Map<Int, Snowflake>>? = null,
    override val originalResponseMessageId: MaybeAbsent<Snowflake>? = null,
    @SerialName("interacted_message_id") val interactedMessageId: MaybeAbsent<Snowflake>? = null,
) : MessageInteractionMetadataObject()

@Serializable
data class ModalSubmitInteractionMetadataObject(
    override val id: MaybeAbsent<Snowflake>? = null,
    override val type: MaybeAbsent<Int>? = null,
    override val user: MaybeAbsent<UserObject>? = null,
    override val authorizingIntegrationOwners: MaybeAbsent<Map<Int, Snowflake>>? = null,
    override val originalResponseMessageId: MaybeAbsent<Snowflake>? = null,
    @SerialName("trigger_interaction_metadata") val triggerInteractionMetadata: MaybeAbsent<MessageInteractionMetadataObject>? = null,
) : MessageInteractionMetadataObject()

@Serializable
data class MessageObject(
    val id: MaybeAbsent<Snowflake>? = null,
    val channelId: MaybeAbsent<Snowflake>? = null,
    val author: MaybeAbsent<UserObject>? = null,
    val content: MaybeAbsent<String>? = null,
    val timestamp: MaybeAbsent<ISO8601Timestamp>? = null,
    @SerialName("edited_timestamp") val editedTimestamp: MaybeAbsent<ISO8601Timestamp>? = null,
    val tts: MaybeAbsent<Boolean>? = null,
    @SerialName("mention_everyone") val mentionEveryone: MaybeAbsent<Boolean>? = null,
    val mentions: MaybeAbsent<List<UserObject>>? = null,
    @SerialName("mention_roles") val mentionRoles: MaybeAbsent<List<RoleObject>>? = null,
    @SerialName("mention_channels") val mentionChannels: MaybeAbsent<List<ChannelMentionObject>>? = null,
    val attachments: MaybeAbsent<List<AttachmentObject>>? = null,
    val embeds: MaybeAbsent<List<EmbedObject>>? = null,
    val reactions: MaybeAbsent<List<ReactionObject>>? = null,
    val nonce: MaybeAbsent<String>? = null,
    val pinned: MaybeAbsent<Boolean>? = null,
    @SerialName("webhook_id") val webhookId: MaybeAbsent<Snowflake>? = null,
    val type: MaybeAbsent<Int>? = null,
    val activity: MaybeAbsent<MessageActivityObject>? = null,
    val application: MaybeAbsent<ApplicationObject>? = null,
    @SerialName("application_id") val applicationId: MaybeAbsent<Snowflake>? = null,
    val flags: MaybeAbsent<Int>? = null,
    @SerialName("message_reference") val messageReference: MaybeAbsent<MessageReferenceObject>? = null,
    @SerialName("message_snapshots") val messageSnapshots: MaybeAbsent<MessageSnapshotObject>? = null,
    @SerialName("referenced_message") val referencedMessage: MaybeAbsent<MessageReferenceObject>? = null,
    @SerialName("interaction_metadata") val interactionMetadata: MaybeAbsent<MessageInteractionMetadataObject>? = null,
    val interaction: MaybeAbsent<InteractionObject>? = null,
    val thread: MaybeAbsent<ChannelObject>? = null,
    val components: MaybeAbsent<List<MessageComponentObject>>? = null,
    @SerialName("sticker_items") val stickerItems: MaybeAbsent<List<StickerObject>>? = null,
    val stickers: MaybeAbsent<List<StickerObject>>? = null,
    val position: MaybeAbsent<Int>? = null,
    @SerialName("role_subscription_data") val roleSubscriptionData: MaybeAbsent<RoleSubscriptionDataObject>? = null,
    val resolved: MaybeAbsent<ResolvedDataObject>? = null,
    val poll: MaybeAbsent<PollObject>? = null,
    val call: MaybeAbsent<MessageCallObject>? = null,
    @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
    val member: MaybeAbsent<GuildMemberObject>? = null
)

@Serializable
data class MessageReferenceObject(
    val type: MaybeAbsent<Int>? = null,
    @SerialName("message_id") val messageId: MaybeAbsent<Snowflake>? = null,
    @SerialName("channel_id") val channelId: MaybeAbsent<Snowflake>? = null,
    @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
    @SerialName("fail_if_not_exists") val failIfNotExists: MaybeAbsent<Boolean>? = null
)

@Serializable
data class MessageSnapshotObject(
    val message: MaybeAbsent<MessageObject>? = null
)

@Serializable
data class PollAnswerCountObject(
    val id: MaybeAbsent<Int>? = null,
    val count: MaybeAbsent<Int>? = null,
    @SerialName("me_voted") val meVoted: MaybeAbsent<Boolean>? = null
)

@Serializable
data class PollAnswerObject(
    @SerialName("answer_id") val answerId: MaybeAbsent<Int>? = null,
    @SerialName("poll_media") val pollMedia: MaybeAbsent<PollMediaObject>? = null,
)

@Serializable
data class PollMediaObject(
    val text: MaybeAbsent<String>? = null,
    val emoji: MaybeAbsent<EmojiObject>? = null
)

@Serializable
data class PollObject(
    val question: MaybeAbsent<PollMediaObject>? = null,
    val answers: MaybeAbsent<List<PollAnswerObject>>? = null,
    val expiry: MaybeAbsent<ISO8601Timestamp>? = null,
    @SerialName("allow_multiselect") val allowMultiselect: MaybeAbsent<Boolean>? = null,
    @SerialName("layout_type") val layoutType: MaybeAbsent<Int>? = null,
    val results: MaybeAbsent<PollResultsObject>? = null
)

@Serializable
data class PollResultsObject(
    @SerialName("is_finalized") val isFinalized: MaybeAbsent<Boolean>? = null,
    @SerialName("answer_counts") val answerCounts: MaybeAbsent<List<PollAnswerCountObject>>? = null
)

@Serializable
data class ReactionCountDetailsObject(
    val burst: MaybeAbsent<Int>? = null,
    val normal: MaybeAbsent<Int>? = null
)

@Serializable
data class ReactionObject(
    val count: MaybeAbsent<Int>? = null,
    @SerialName("count_details") val countDetails: MaybeAbsent<ReactionCountDetailsObject>? = null,
    val me: MaybeAbsent<Boolean>? = null,
    @SerialName("me_burst") val meBurst: MaybeAbsent<Boolean>? = null,
    val emoji: MaybeAbsent<EmojiObject>? = null,
    @SerialName("burst_colors") val burstColors: MaybeAbsent<List<Int>>? = null
)

@Serializable
data class RecurrenceRuleNWeekdayObject(
    val n: MaybeAbsent<Int>? = null,
    val day: MaybeAbsent<Int>? = null
)

@Serializable
data class ResolvedDataObject(
    val users: MaybeAbsent<Map<Snowflake, UserObject>>? = null,
    val members: MaybeAbsent<Map<Snowflake, GuildMemberObject>>? = null,
    val roles: MaybeAbsent<Map<Snowflake, RoleObject>>? = null,
    val channels: MaybeAbsent<Map<Snowflake, ChannelObject>>? = null,
    val messages: MaybeAbsent<Map<Snowflake, MessageObject>>? = null,
    val attachments: MaybeAbsent<Map<Snowflake, AttachmentObject>>? = null,
)

@Serializable
data class RoleObject(
    val id: MaybeAbsent<Snowflake>? = null,
    val name: MaybeAbsent<String>? = null,
    val color: MaybeAbsent<Int>? = null,
    val hoist: MaybeAbsent<Boolean>? = null,
    val icon: MaybeAbsent<String>? = null,
    @SerialName("unicode_emoji") val unicodeEmoji: MaybeAbsent<String>? = null,
    val position: MaybeAbsent<Int>? = null,
    val permissions: MaybeAbsent<String>? = null,
    val managed: MaybeAbsent<Boolean>? = null,
    val mentionable: MaybeAbsent<Boolean>? = null,
    val tags: MaybeAbsent<RoleTagsObject>? = null,
    val flags: MaybeAbsent<Int>? = null
)

@Serializable
data class RoleSubscriptionDataObject(
    @SerialName("role_subscription_listing_id") val roleSubscriptionListingId: MaybeAbsent<Snowflake>? = null,
    @SerialName("tier_name") val tierName: MaybeAbsent<String>? = null,
    @SerialName("total_months_subscribed") val totalMonthsSubscribed: MaybeAbsent<Int>? = null,
    @SerialName("is_renewal") val isRenewal: MaybeAbsent<Boolean>? = null
)

@Serializable
data class RoleTagsObject(
    val botId: MaybeAbsent<Snowflake>? = null,
    @SerialName("integration_id") val integrationId: MaybeAbsent<Snowflake>? = null,
    @SerialName("premium_subscriber") val premiumSubscriber: MaybeAbsent<Boolean>? = null,
    @SerialName("subscription_listing_id") val subscriptionListingId: MaybeAbsent<Snowflake>? = null,
    @SerialName("available_for_purchase") val availableForPurchase: MaybeAbsent<Boolean>? = null,
    @SerialName("guild_connections") val guildConnections: MaybeAbsent<Boolean>? = null
)

@Serializable
data class SkuObject(
    val id: MaybeAbsent<Snowflake>? = null,
    val type: MaybeAbsent<Int>? = null,
    @SerialName("application_id") val applicationId: MaybeAbsent<Snowflake>? = null,
    val name: MaybeAbsent<String>? = null,
    val slug: MaybeAbsent<String>? = null,
    val flags: MaybeAbsent<Int>? = null
)

@Serializable
data class SoundboardSoundObject(
    val name: MaybeAbsent<String>? = null,
    @SerialName("sound_id") val soundId: MaybeAbsent<Snowflake>? = null,
    val volume: MaybeAbsent<Double>? = null,
    @SerialName("emoji_id") val emojiId: MaybeAbsent<Snowflake>? = null,
    @SerialName("emoji_name") val emojiName: MaybeAbsent<String>? = null,
    @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
    val available: MaybeAbsent<Boolean>? = null,
    val user: MaybeAbsent<UserObject>? = null
)

@Serializable
data class StageInstanceObject(
    val id: MaybeAbsent<Snowflake>? = null,
    @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
    @SerialName("channel_id") val channelId: MaybeAbsent<Snowflake>? = null,
    val topic: MaybeAbsent<String>? = null,
    @SerialName("privacy_level") val privacyLevel: MaybeAbsent<Int>? = null,
    @SerialName("discoverable_disabled") val discoverableDisabled: MaybeAbsent<Boolean>? = null,
    @SerialName("guild_scheduled_event_id") val guildScheduledEventId: MaybeAbsent<Snowflake?>? = null,
)

@Serializable
data class StickerObject(
    val id: MaybeAbsent<Snowflake>? = null,
    @SerialName("pack_id") val packId: MaybeAbsent<Snowflake>? = null,
    val name: MaybeAbsent<String>? = null,
    val description: MaybeAbsent<String>? = null,
    val tags: MaybeAbsent<String>? = null,
    val type: MaybeAbsent<Int>? = null,
    @SerialName("format_type") val formatType: MaybeAbsent<Int>? = null,
    val available: MaybeAbsent<Boolean>? = null,
    @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
    val user: MaybeAbsent<UserObject>? = null,
    @SerialName("sort_value") val sortValue: MaybeAbsent<Int>? = null,
)

@Serializable
data class StickerPackObject(
    val id: MaybeAbsent<Snowflake>? = null,
    val stickers: MaybeAbsent<List<StickerObject>>? = null,
    val name: MaybeAbsent<String>? = null,
    @SerialName("sku_id") val skuId: MaybeAbsent<Snowflake>? = null,
    @SerialName("cover_sticker_id") val coverStickerId: MaybeAbsent<Snowflake>? = null,
    val description: MaybeAbsent<String>? = null,
    @SerialName("banner_asset_id") val bannerAssetId: MaybeAbsent<Snowflake>? = null
)

@Serializable
data class SubscriptionObject(
    val id: MaybeAbsent<Snowflake>? = null,
    @SerialName("user_id") val userId: MaybeAbsent<Snowflake>? = null,
    @SerialName("sku_ids") val skuIds: MaybeAbsent<List<Snowflake>>? = null,
    @SerialName("entitlement_ids") val entitlementIds: MaybeAbsent<List<Snowflake>>? = null,
    @SerialName("renewal_sku_ids") val renewalSkuIds: MaybeAbsent<List<Snowflake>>? = null,
    @SerialName("current_period_start") val currentPeriodStart: MaybeAbsent<ISO8601Timestamp>? = null,
    @SerialName("current_period_end") val currentPeriodEnd: MaybeAbsent<ISO8601Timestamp>? = null,
    val status: MaybeAbsent<Int>? = null,
    @SerialName("canceled_at") val canceledAt: MaybeAbsent<ISO8601Timestamp>? = null,
    val country: MaybeAbsent<String>? = null
)

@Serializable
data class TeamMemberObject(
    @SerialName("membership_state") val membershipState: MaybeAbsent<Int>? = null,
    @SerialName("team_id") val teamId: MaybeAbsent<Snowflake>? = null,
    val user: MaybeAbsent<UserObject>? = null,
    val role: MaybeAbsent<String>? = null
)

@Serializable
data class TeamObject(
    val icon: MaybeAbsent<String>? = null,
    val id: MaybeAbsent<Snowflake>? = null,
    val members: MaybeAbsent<List<TeamMemberObject>>? = null,
    val name: MaybeAbsent<String>? = null,
    @SerialName("owner_user_id") val ownerUserId: MaybeAbsent<Snowflake>? = null,
)

@Serializable
data class ThreadMemberObject(
    val id: MaybeAbsent<Snowflake>? = null,
    @SerialName("user_id") val userId: MaybeAbsent<Snowflake>? = null,
    @SerialName("join_timestamp") val joinTimestamp: MaybeAbsent<ISO8601Timestamp>? = null,
    val flags: MaybeAbsent<Int>? = null,
    val member: MaybeAbsent<GuildMemberObject>? = null,
    @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null
)

@Serializable
data class ThreadMetadataObject(
    val archived: MaybeAbsent<Boolean>? = null,
    @SerialName("auto_archive_duration") val autoArchiveDuration: MaybeAbsent<Int>? = null,
    @SerialName("archive_timestamp") val archiveTimestamp: MaybeAbsent<ISO8601Timestamp>? = null,
    val locked: MaybeAbsent<Boolean>? = null,
    val invitable: MaybeAbsent<Boolean>? = null,
    @SerialName("create_timestamp") val createTimestamp: MaybeAbsent<ISO8601Timestamp>? = null,
)

@Serializable
data class UpdatePresenceObject(
    val since: MaybeAbsent<Int>? = null,
    val activities: MaybeAbsent<ActivityObject>? = null,
    val status: MaybeAbsent<String>? = null,
    val afk: MaybeAbsent<Boolean>? = null
)

@Serializable
data class UserApplicationRoleConnectionObject(
    @SerialName("platform_name") val platformName: MaybeAbsent<String>? = null,
    @SerialName("platform_username") val platformUsername: MaybeAbsent<String>? = null,
    val metadata: MaybeAbsent<Map<String, ApplicationRoleConnectionMetadataObject>>? = null
)

@Serializable
data class UserConnectionObject(
    val id: MaybeAbsent<String>? = null,
    val name: MaybeAbsent<String>? = null,
    val type: MaybeAbsent<String>? = null,
    val revoked: MaybeAbsent<Boolean>? = null,
    val integrations: MaybeAbsent<List<GuildIntegrationObject>>? = null,
    val verified: MaybeAbsent<Boolean>? = null,
    @SerialName("friend_sync") val friendSync: MaybeAbsent<Boolean>? = null,
    @SerialName("show_activity") val showActivity: MaybeAbsent<Boolean>? = null,
    @SerialName("two_way_link") val twoWayLink: MaybeAbsent<Boolean>? = null,
    val visibility: MaybeAbsent<Int>? = null
)

@Serializable
data class UserObject(
    val id: MaybeAbsent<Snowflake>? = null,
    val username: MaybeAbsent<String>? = null,
    val discriminator: MaybeAbsent<String>? = null,
    @SerialName("global_name") val globalName: MaybeAbsent<String>? = null,
    val avatar: MaybeAbsent<String>? = null,
    val bot: MaybeAbsent<Boolean>? = null,
    val system: MaybeAbsent<Boolean>? = null,
    @SerialName("mfa_enabled") val mfaEnabled: MaybeAbsent<Boolean>? = null,
    val banner: MaybeAbsent<String>? = null,
    @SerialName("accent_color") val accentColor: MaybeAbsent<Int>? = null,
    val locale: MaybeAbsent<String>? = null,
    val verified: MaybeAbsent<Boolean>? = null,
    val email: MaybeAbsent<String>? = null,
    val flags: MaybeAbsent<Int>? = null,
    @SerialName("premium_type") val premiumType: MaybeAbsent<Int>? = null,
    @SerialName("public_flags") val publicFlags: MaybeAbsent<Int>? = null,
    @SerialName("avatar_decoration_data") val avatarDecorationData: MaybeAbsent<UserAvatarDecorationDataObject>? = null,
    val collectibles: MaybeAbsent<UserCollectiblesObject?>? = null,
    @SerialName("primary_guild") val primaryGuild: MaybeAbsent<UserPrimaryGuildObject?>? = null
)

@Serializable
data class UserCollectiblesObject(
    val nameplate: MaybeAbsent<UserNameplateObject?>? = null
)

@Serializable
data class UserNameplateObject(
    @SerialName("sku_id") val skuId: MaybeAbsent<Snowflake?>? = null,
    val asset: MaybeAbsent<String?>? = null,
    val label: MaybeAbsent<String?>? = null,
    val palette: MaybeAbsent<String?>? = null
)

@Serializable
data class UserPrimaryGuildObject(
    @SerialName("identity_guild_id") val identityGuildId: MaybeAbsent<Snowflake?>? = null,
    @SerialName("identity_enabled") val identityEnabled: MaybeAbsent<Boolean?>? = null,
    val tag: MaybeAbsent<String?>? = null,
    val badge: MaybeAbsent<String?>? = null
)

@Serializable
data class VoiceRegionObject(
    @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
    @SerialName("channel_id") val channelId: MaybeAbsent<Snowflake>? = null,
    @SerialName("user_id") val userId: MaybeAbsent<Snowflake>? = null,
    val member: MaybeAbsent<GuildMemberObject>? = null,
    @SerialName("session_id") val sessionId: MaybeAbsent<String>? = null,
    val deaf: MaybeAbsent<Boolean>? = null,
    val mute: MaybeAbsent<Boolean>? = null,
    @SerialName("self_deaf") val selfDeaf: MaybeAbsent<Boolean>? = null,
    @SerialName("self_mute") val selfMute: MaybeAbsent<Boolean>? = null,
    @SerialName("self_stream") val selfStream: MaybeAbsent<Boolean>? = null,
    @SerialName("self_video") val selfVideo: MaybeAbsent<Boolean>? = null,
    val suppress: MaybeAbsent<Boolean>? = null,
    @SerialName("request_to_speak_timestamp") val requestToSpeakTimestamp: MaybeAbsent<ISO8601Timestamp>? = null
)

@Serializable
data class VoiceStateObject(
    @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
    @SerialName("channel_id") val channelId: MaybeAbsent<Snowflake>? = null,
    @SerialName("user_id") val userId: MaybeAbsent<Snowflake>? = null,
    val member: MaybeAbsent<GuildMemberObject>? = null,
    @SerialName("session_id") val sessionId: MaybeAbsent<String>? = null,
    val deaf: MaybeAbsent<Boolean>? = null,
    val mute: MaybeAbsent<Boolean>? = null,
    @SerialName("self_deaf") val selfDeaf: MaybeAbsent<Boolean>? = null,
    @SerialName("self_mute") val selfMute: MaybeAbsent<Boolean>? = null,
    @SerialName("self_stream") val selfStream: MaybeAbsent<Boolean>? = null,
    @SerialName("self_video") val selfVideo: MaybeAbsent<Boolean>? = null,
    val suppress: MaybeAbsent<Boolean>? = null,
    @SerialName("request_to_speak_timestamp") val requestToSpeakTimestamp: MaybeAbsent<ISO8601Timestamp>? = null
)

@Serializable
data class WebhookObject(
    val id: MaybeAbsent<Snowflake>? = null,
    val type: MaybeAbsent<Int>? = null,
    @SerialName("guild_id") val guildId: MaybeAbsent<Snowflake>? = null,
    @SerialName("channel_id") val channelId: MaybeAbsent<Snowflake>? = null,
    val user: MaybeAbsent<UserObject>? = null,
    val name: MaybeAbsent<String>? = null,
    val avatar: MaybeAbsent<String>? = null,
    val token: MaybeAbsent<String>? = null,
    @SerialName("application_id") val applicationId: MaybeAbsent<Snowflake>? = null,
    @SerialName("source_guild") val sourceGuild: MaybeAbsent<GuildObject>? = null,
    @SerialName("source_channel") val sourceChannel: MaybeAbsent<ChannelObject>? = null,
    val url: MaybeAbsent<String>? = null
)