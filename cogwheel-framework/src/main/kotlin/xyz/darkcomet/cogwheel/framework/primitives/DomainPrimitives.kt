@file:Suppress("unused") // All definitions are part of the public API

package xyz.darkcomet.cogwheel.framework.primitives

import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import java.math.BigInteger


data class ApplicationId(val snowflake: Snowflake)

data class ApplicationCommandId(val snowflake: Snowflake)

data class AuditLogEntryId(val snowflake: Snowflake)

data class AutoModerationRuleId(val snowflake: Snowflake)

data class UserId(val snowflake: Snowflake)

data class GuildId(val snowflake: Snowflake)

data class RoleId(val snowflake: Snowflake)

data class ChannelId(val snowflake: Snowflake)

data class EmojiId(val snowflake: Snowflake)

data class TeamId(val snowflake: Snowflake)

data class SkuId(val snowflake: Snowflake)

data class LocalizationMap(private val map: Map<String, String>) {
    
    fun toObject(): Map<String, String> {
        return this.map
    }
    
    companion object {
        fun from(rawMap: Map<String, String>): LocalizationMap {
            return LocalizationMap(rawMap)
        }
    }
}

@ConsistentCopyVisibility
data class DiscordColor 
private constructor(val value: Int) {
    companion object {
        // TODO
    }
}

@ConsistentCopyVisibility
data class DiscordLocale
private constructor(val value: String) {
    companion object {
        // TODO
    }
}

data class PermissionSet(val value: BigInteger) {

    infix fun and(permission: Permission): PermissionSet {
        return PermissionSet(value or permission.value)
    }

    infix fun and(otherSet: PermissionSet): PermissionSet {
        return PermissionSet(value or otherSet.value)
    }

    fun contains(permission: Permission): Boolean {
        return (value and permission.value) == permission.value
    }

    override fun equals(other: Any?): Boolean {
        if (other !is PermissionSet) {
            return false
        }

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return value.toString(10)
    }

    companion object {
        @JvmStatic 
        fun from(vararg permissions: Permission): PermissionSet {
            val value = permissions.fold(BigInteger.ZERO) { acc, permission ->
                acc or permission.value
            }
            return PermissionSet(value)
        }

        @JvmStatic 
        fun from(rawString: String): PermissionSet {
            val value = BigInteger(rawString, 10)
            return PermissionSet(value)
        }

        @JvmStatic 
        fun all(): PermissionSet {
            val value = Permission.entries.fold(BigInteger.ZERO) { acc, permission ->
                acc or permission.value
            }
            return PermissionSet(value)
        }
    }
}

enum class Permission(val value: BigInteger) {

    CREATE_INSTANT_INVITE(BigInteger("0000000000000001", 16)),
    KICK_MEMBERS(BigInteger("0000000000000002", 16)),
    BAN_MEMBERS(BigInteger("0000000000000004", 16)),
    ADMINISTRATOR(BigInteger("0000000000000008", 16)),
    MANAGE_CHANNELS(BigInteger("0000000000000010", 16)),
    MANAGE_GUILD(BigInteger("0000000000000020", 16)),
    ADD_REACTIONS(BigInteger("0000000000000040", 16)),
    VIEW_AUDIT_LOG(BigInteger("0000000000000080", 16)),
    PRIORITY_SPEAKER(BigInteger("0000000000000100", 16)),
    STREAM(BigInteger("0000000000000200", 16)),
    VIEW_CHANNEL(BigInteger("0000000000000400", 16)),
    SEND_MESSAGES(BigInteger("0000000000000800", 16)),
    SEND_TTS_MESSAGES(BigInteger("0000000000001000", 16)),
    MANAGE_MESSAGES(BigInteger("0000000000002000", 16)),
    EMBED_LINKS(BigInteger("0000000000004000", 16)),
    ATTACH_FILES(BigInteger("0000000000008000", 16)),
    READ_MESSAGE_HISTORY(BigInteger("0000000000010000", 16)),
    MENTION_EVERYONE(BigInteger("0000000000020000", 16)),
    USE_EXTERNAL_EMOJIS(BigInteger("0000000000040000", 16)),
    VIEW_GUILD_INSIGHTS(BigInteger("0000000000080000", 16)),
    CONNECT(BigInteger("0000000000100000", 16)),
    SPEAK(BigInteger("0000000000200000", 16)),
    MUTE_MEMBERS(BigInteger("0000000000400000", 16)),
    DEAFEN_MEMBERS(BigInteger("0000000000800000", 16)),
    MOVE_MEMBERS(BigInteger("0000000001000000", 16)),
    USE_VAD(BigInteger("0000000002000000", 16)),
    CHANGE_NICKNAME(BigInteger("0000000004000000", 16)),
    MANAGE_NICKNAMES(BigInteger("0000000008000000", 16)),
    MANAGE_ROLES(BigInteger("0000000010000000", 16)),
    MANAGE_WEBHOOKS(BigInteger("0000000020000000", 16)),
    MANAGE_GUILD_EXPRESSIONS(BigInteger("0000000040000000", 16)),
    USE_APPLICATION_COMMANDS(BigInteger("0000000080000000", 16)),
    REQUEST_TO_SPEAK(BigInteger("0000000100000000", 16)),
    MANAGE_EVENTS(BigInteger("0000000200000000", 16)),
    MANAGE_THREADS(BigInteger("0000000400000000", 16)),
    CREATE_PUBLIC_THREADS(BigInteger("0000000800000000", 16)),
    CREATE_PRIVATE_THREADS(BigInteger("0000001000000000", 16)),
    USE_EXTERNAL_STICKERS(BigInteger("0000002000000000", 16)),
    SEND_MESSAGE_IN_THREADS(BigInteger("0000004000000000", 16)),
    USE_EMBEDDED_ACTIVITIES(BigInteger("0000008000000000", 16)),
    MODERATE_MEMBERS(BigInteger("0000010000000000", 16)),
    VIEW_CREATOR_MONETIZATION_ANALYTICS(BigInteger("0000020000000000", 16)),
    USE_SOUNDBOARD(BigInteger("0000040000000000", 16)),
    CREATE_GUILD_EXPRESSIONS(BigInteger("0000080000000000", 16)),
    CREATE_EVENTS(BigInteger("0000100000000000", 16)),
    USE_EXTERNAL_SOUNDS(BigInteger("0000200000000000", 16)),
    SEND_VOICE_MESSAGES(BigInteger("0000400000000000", 16)),
    SEND_POLLS(BigInteger("0002000000000000", 16)),
    USE_EXTERNAL_APPS(BigInteger("0004000000000000", 16)),

    ;

    infix fun and(other: Permission): PermissionSet {
        return PermissionSet(value or other.value)
    }
    
}

@ConsistentCopyVisibility
data class OAuth2Scope
private constructor(override val key: String) : ExtensibleEnumValue<String> {
    companion object : ExtensibleEnum<String, OAuth2Scope>() {
        
        @JvmField val ACTIVITIES_READ = addPreset("activities.read")
        @JvmField val ACTIVITIES_WRITE = addPreset("activities.write")
        @JvmField val APPLICATIONS_BUILDS_READ = addPreset("applications.builds.read")
        @JvmField val APPLICATIONS_BUILDS_UPLOAD = addPreset("applications.builds.upload")
        @JvmField val APPLICATIONS_COMMANDS = addPreset("applications.commands")
        @JvmField val APPLICATIONS_COMMANDS_UPDATE = addPreset("applications.commands.update")
        @JvmField val APPLICATIONS_COMMANDS_PERMISSIONS_UPDATE = addPreset("applications.commands.permissions.update")
        @JvmField val APPLICATIONS_ENTITLEMENTS = addPreset("applications.entitlements")
        @JvmField val APPLICATIONS_STORE_UPDATE = addPreset("applications.store.update")
        @JvmField val BOT = addPreset("bot")
        @JvmField val CONNECTIONS = addPreset("connections")
        @JvmField val DM_CHANNELS_READ = addPreset("dm_channels.read")
        @JvmField val EMAIL = addPreset("email")
        @JvmField val GDM_JOIN = addPreset("gdm.join")
        @JvmField val GUILDS = addPreset("guilds")
        @JvmField val GUILDS_JOIN = addPreset("guilds.join")
        @JvmField val GUILDS_MEMBERS_READ = addPreset("guilds.members.read")
        @JvmField val IDENTIFY = addPreset("identify")
        @JvmField val MESSAGES_READ = addPreset("messages.read")
        @JvmField val RELATIONSHIPS_READ = addPreset("relationships.read")
        @JvmField val ROLE_CONNECTIONS_WRITE = addPreset("role_connections.write")
        @JvmField val RPC = addPreset("rpc")
        @JvmField val RPC_ACTIVITIES_WRITE = addPreset("rpc.activities.write")
        @JvmField val RPC_NOTIFICATIONS_READ = addPreset("rpc.notifications.read")
        @JvmField val RPC_VOICE_READ = addPreset("rpc.voice.read")
        @JvmField val RPC_VOICE_WRITE = addPreset("rpc.voice.write")
        @JvmField val VOICE = addPreset("voice")
        @JvmField val WEBHOOK_INCOMING = addPreset("webhook.incoming")
        
        override fun createValue(newKey: String): OAuth2Scope
            = OAuth2Scope(newKey)
    }
}

@ConsistentCopyVisibility
data class ApplicationIntegrationType
private constructor(override val key: Int) : ExtensibleEnumValue<Int> {
    companion object : ExtensibleEnum<Int, ApplicationIntegrationType>() {
        
        @JvmField val GUILD_INSTALL = addPreset(0)
        @JvmField val USER_INSTALL = addPreset(1)
        
        override fun createValue(newKey: Int): ApplicationIntegrationType
            = ApplicationIntegrationType(newKey)
    }
}

@ConsistentCopyVisibility
data class ApplicationRoleConnectionMetadataType
private constructor(override val key: Int) : ExtensibleEnumValue<Int> {
    companion object : ExtensibleEnum<Int, ApplicationRoleConnectionMetadataType>() {
        
        @JvmField val INTEGER_LESS_THAN_OR_EQUAL = addPreset(1)
        @JvmField val INTEGER_GREATER_THAN_OR_EQUAL = addPreset(2)
        @JvmField val INTEGER_EQUAL = addPreset(3)
        @JvmField val INTEGER_NOT_EQUAL = addPreset(4)
        @JvmField val DATETIME_LESS_THAN_OR_EQUAL = addPreset(5)
        @JvmField val DATETIME_GREATER_THAN_OR_EQUAL = addPreset(6)
        @JvmField val BOOLEAN_EQUAL = addPreset(7)
        @JvmField val BOOLEAN_NOT_EQUAL = addPreset(8)


        override fun createValue(newKey: Int): ApplicationRoleConnectionMetadataType
            = ApplicationRoleConnectionMetadataType(newKey)

    } 
}

@ConsistentCopyVisibility
data class ApplicationFlag
private constructor(override val key: Int) : ExtensibleEnumValue<Int> {
    companion object : ExtensibleEnum<Int, ApplicationFlag>() {
        
        @JvmField val APPLICATION_AUTO_MODERATION_RULE_CREATE_BADGE = addPreset(1 shl 6)
        @JvmField val GATEWAY_PRESENCE = addPreset(1 shl 12)
        @JvmField val GATEWAY_PRESENCE_LIMITED = addPreset(1 shl 13)
        @JvmField val GATEWAY_GUILD_MEMBERS = addPreset(1 shl 14)
        @JvmField val GATEWAY_GUILD_MEMBERS_LIMITED = addPreset(1 shl 15)
        @JvmField val VERIFICATION_PENDING_GUILD_LIMIT = addPreset(1 shl 16)
        @JvmField val EMBEDDED = addPreset(1 shl 17)
        @JvmField val GATEWAY_MESSAGE_CONTENT = addPreset(1 shl 18)
        @JvmField val GATEWAY_MESSAGE_CONTENT_LIMITED = addPreset(1 shl 19)
        @JvmField val APPLICATION_COMMAND_BADGE = addPreset(1 shl 23)
        
        override fun createValue(newKey: Int): ApplicationFlag
            = ApplicationFlag(newKey)
    }
}

@ConsistentCopyVisibility
data class ApplicationCommandType
private constructor(override val key: Int) : ExtensibleEnumValue<Int> {
    companion object : ExtensibleEnum<Int, ApplicationCommandType>() {

        @JvmField val CHAT_INPUT = addPreset(1)
        @JvmField val USER = addPreset(2)
        @JvmField val MESSAGE = addPreset(3)
        @JvmField val PRIMARY_ENTRY_POINT = addPreset(4)

        override fun createValue(newKey: Int): ApplicationCommandType
                = ApplicationCommandType(newKey)
    }
}

@ConsistentCopyVisibility
data class ApplicationCommandOptionType
private constructor(override val key: Int) : ExtensibleEnumValue<Int> {
    companion object : ExtensibleEnum<Int, ApplicationCommandOptionType>() {
        
        @JvmField val SUB_COMMAND = addPreset(1)
        @JvmField val SUB_COMMAND_GROUP = addPreset(2)
        @JvmField val STRING = addPreset(3)
        @JvmField val INTEGER = addPreset(4)
        @JvmField val BOOLEAN = addPreset(5)
        @JvmField val USER = addPreset(6)
        @JvmField val CHANNEL = addPreset(7)
        @JvmField val ROLE = addPreset(8)
        @JvmField val MENTIONABLE = addPreset(9)
        @JvmField val NUMBER = addPreset(10)
        @JvmField val ATTACHMENT = addPreset(11)
        
        override fun createValue(newKey: Int): ApplicationCommandOptionType
            = ApplicationCommandOptionType(newKey)
    }
}

@ConsistentCopyVisibility
data class InteractionContextType
private constructor(override val key: Int) : ExtensibleEnumValue<Int> {
    companion object : ExtensibleEnum<Int, InteractionContextType>() {
        
        @JvmField val GUILD = addPreset(0)
        @JvmField val BOT_DM = addPreset(1)
        @JvmField val PRIVATE_CHANNEL = addPreset(2)
        
        override fun createValue(newKey: Int): InteractionContextType
                = InteractionContextType(newKey)
    }
}

@ConsistentCopyVisibility
data class ApplicationCommandHandlerType
private constructor(override val key: Int) : ExtensibleEnumValue<Int> {
    companion object : ExtensibleEnum<Int, ApplicationCommandHandlerType>() {

        @JvmField val APP_HANDLER = addPreset(1)
        @JvmField val DISCORD_LAUNCH_ACTIVITY = addPreset(2)

        override fun createValue(newKey: Int): ApplicationCommandHandlerType
                = ApplicationCommandHandlerType(newKey)
    }
}


@ConsistentCopyVisibility
data class AuditLogEventType
private constructor(override val key: Int) : ExtensibleEnumValue<Int> {
    companion object : ExtensibleEnum<Int, AuditLogEventType>() {
        
        @JvmField val GUILD_UPDATE = addPreset(1)
        @JvmField val CHANNEL_CREATE = addPreset(10)
        @JvmField val CHANNEL_UPDATE = addPreset(11)
        @JvmField val CHANNEL_DELETE = addPreset(12)
        @JvmField val CHANNEL_OVERWRITE_CREATE = addPreset(13)
        @JvmField val CHANNEL_OVERWRITE_UPDATE = addPreset(14)
        @JvmField val CHANNEL_OVERWRITE_DELETE = addPreset(15)
        @JvmField val MEMBER_KICK = addPreset(20)
        @JvmField val MEMBER_PRUNE = addPreset(21)
        @JvmField val MEMBER_BAN_ADD = addPreset(22)
        @JvmField val MEMBER_BAN_REMOVE = addPreset(23)
        @JvmField val MEMBER_UPDATE = addPreset(24)
        @JvmField val MEMBER_ROLE_UPDATE = addPreset(25)
        @JvmField val MEMBER_MOVE = addPreset(26)
        @JvmField val MEMBER_DISCONNECT = addPreset(27)
        @JvmField val BOT_ADD = addPreset(28)
        @JvmField val ROLE_CREATE = addPreset(30)
        @JvmField val ROLE_UPDATE = addPreset(31)
        @JvmField val ROLE_DELETE = addPreset(32)
        @JvmField val INVITE_CREATE = addPreset(40)
        @JvmField val INVITE_UPDATE = addPreset(41)
        @JvmField val INVITE_DELETE = addPreset(42)
        @JvmField val WEBHOOK_CREATE = addPreset(50)
        @JvmField val WEBHOOK_UPDATE = addPreset(51)
        @JvmField val WEBHOOK_DELETE = addPreset(52)
        @JvmField val EMOJI_CREATE = addPreset(60)
        @JvmField val EMOJI_UPDATE = addPreset(61)
        @JvmField val EMOJI_DELETE = addPreset(62)
        @JvmField val MESSAGE_DELETE = addPreset(72)
        @JvmField val MESSAGE_BULK_DELETE = addPreset(73)
        @JvmField val MESSAGE_PIN = addPreset(74)
        @JvmField val MESSAGE_UNPIN = addPreset(75)
        @JvmField val INTEGRATION_CREATE = addPreset(80)
        @JvmField val INTEGRATION_UPDATE = addPreset(81)
        @JvmField val INTEGRATION_DELETE = addPreset(82)
        @JvmField val STAGE_INSTANCE_CREATE = addPreset(83)
        @JvmField val STAGE_INSTANCE_UPDATE = addPreset(84)
        @JvmField val STAGE_INSTANCE_DELETE = addPreset(85)
        @JvmField val STICKER_CREATE = addPreset(90)
        @JvmField val STICKER_UPDATE = addPreset(91)
        @JvmField val STICKER_DELETE = addPreset(92)
        @JvmField val GUILD_SCHEDULED_EVENT_CREATE = addPreset(100)
        @JvmField val GUILD_SCHEDULED_EVENT_UPDATE = addPreset(101)
        @JvmField val GUILD_SCHEDULED_EVENT_DELETE = addPreset(102)
        @JvmField val THREAD_CREATE = addPreset(110)
        @JvmField val THREAD_UPDATE = addPreset(111)
        @JvmField val THREAD_DELETE = addPreset(112)
        @JvmField val APPLICATION_COMMAND_PERMISSIONS_UPDATE = addPreset(121)
        @JvmField val SOUNDBOARD_SOUND_CREATE = addPreset(130)
        @JvmField val SOUNDBOARD_SOUND_UPDATE = addPreset(131)
        @JvmField val SOUNDBOARD_SOUND_DELETE = addPreset(132)
        @JvmField val AUTO_MODERATION_RULE_CREATE = addPreset(140)
        @JvmField val AUTO_MODERATION_RULE_UPDATE = addPreset(141)
        @JvmField val AUTO_MODERATION_RULE_DELETE = addPreset(142)
        @JvmField val AUTO_MODERATION_BLOCK_MESSAGE = addPreset(143)
        @JvmField val AUTO_MODERATION_FLAG_TO_CHANNEL = addPreset(144)
        @JvmField val AUTO_MODERATION_USER_COMMUNICATION_DISABLED = addPreset(145)
        @JvmField val CREATOR_MONETIZATION_REQUEST_CREATED = addPreset(150)
        @JvmField val CREATOR_MONETIZATION_TERMS_ACCEPTED = addPreset(151)
        @JvmField val ONBOARDING_PROMPT_CREATE = addPreset(163)
        @JvmField val ONBOARDING_PROMPT_UPDATE = addPreset(164)
        @JvmField val ONBOARDING_PROMPT_DELETE = addPreset(165)
        @JvmField val ONBOARDING_CREATE = addPreset(166)
        @JvmField val ONBOARDING_UPDATE = addPreset(167)
        @JvmField val HOME_SETTINGS_CREATE = addPreset(190)
        @JvmField val HOME_SETTINGS_UPDATE = addPreset(191)

        override fun createValue(newKey: Int): AuditLogEventType 
            = AuditLogEventType(newKey)

    }
}

@ConsistentCopyVisibility
data class AutoModerationEventType
private constructor(override val key: Int) : ExtensibleEnumValue<Int> {
    companion object : ExtensibleEnum<Int, AutoModerationEventType>() {
        
        @JvmField val MESSAGE_SEND = addPreset(1)
        @JvmField val MEMBER_UPDATE = addPreset(2)
        
        override fun createValue(newKey: Int): AutoModerationEventType 
            = AutoModerationEventType(newKey)

    }
}

@ConsistentCopyVisibility
data class AutoModerationTriggerType
private constructor(override val key: Int) : ExtensibleEnumValue<Int> {
    companion object : ExtensibleEnum<Int, AutoModerationTriggerType>() {

        @JvmField val KEYWORD = addPreset(1)
        @JvmField val SPAM = addPreset(3)
        @JvmField val KEYWORD_PRESET = addPreset(4)
        @JvmField val MENTION_SPAM = addPreset(5)
        @JvmField val MEMBER_PROFILE = addPreset(6)

        override fun createValue(newKey: Int): AutoModerationTriggerType
            = AutoModerationTriggerType(newKey)

    }
}

@ConsistentCopyVisibility
data class AutoModerationKeywordPresetType
private constructor(override val key: Int) : ExtensibleEnumValue<Int> {
    companion object : ExtensibleEnum<Int, AutoModerationKeywordPresetType>() {

        @JvmField val PROFANITY = addPreset(1)
        @JvmField val SEXUAL_CONTENT = addPreset(2)
        @JvmField val SLURS = addPreset(3)

        override fun createValue(newKey: Int): AutoModerationKeywordPresetType
            = AutoModerationKeywordPresetType(newKey)

    }
}

@ConsistentCopyVisibility
data class AutoModerationActionType
private constructor(override val key: Int) : ExtensibleEnumValue<Int> {
    companion object : ExtensibleEnum<Int, AutoModerationActionType>() {

        @JvmField val BLOCK_MESSAGE = addPreset(1)
        @JvmField val SEND_ALERT_MESSAGE = addPreset(2)
        @JvmField val TIMEOUT = addPreset(3)
        @JvmField val BLOCK_MEMBER_INTERACTION = addPreset(4)

        override fun createValue(newKey: Int): AutoModerationActionType
            = AutoModerationActionType(newKey)

    }
}

@ConsistentCopyVisibility
data class UserFlag
private constructor(override val key: Int) : ExtensibleEnumValue<Int> {
    companion object : ExtensibleEnum<Int, UserFlag>() {
        
        @JvmField val STAFF = addPreset(1 shl 0)
        @JvmField val PARTNER = addPreset(1 shl 1)
        @JvmField val HYPESQUAD = addPreset(1 shl 2)
        @JvmField val BUG_HUNTER_LEVEL_1 = addPreset(1 shl 3)
        @JvmField val HYPESQUAD_ONLINE_HOUSE_1 = addPreset(1 shl 6)
        @JvmField val HYPESQUAD_ONLINE_HOUSE_2 = addPreset(1 shl 7)
        @JvmField val HYPESQUAD_ONLINE_HOUSE_3 = addPreset(1 shl 8)
        @JvmField val PREMIUM_EARLY_SUPPORTER = addPreset(1 shl 9)
        @JvmField val TEAM_PSEUDO_USER = addPreset(1 shl 10)
        @JvmField val BUG_HUNTER_LEVEL_2 = addPreset(1 shl 14)
        @JvmField val VERIFIED_BOT = addPreset(1 shl 16)
        @JvmField val VERIFIED_DEVELOPER = addPreset(1 shl 17)
        @JvmField val CERTIFIED_MODERATOR = addPreset(1 shl 18)
        @JvmField val BOT_HTTP_INTERACTIONS = addPreset(1 shl 19)
        @JvmField val ACTIVE_DEVELOPER = addPreset(1 shl 22)
        
        override fun createValue(newKey: Int): UserFlag
            = UserFlag(newKey)
    }
}

@ConsistentCopyVisibility
data class UserPremiumType
private constructor(override val key: Int) : ExtensibleEnumValue<Int> {
    companion object : ExtensibleEnum<Int, UserPremiumType>() {
        
        @JvmField val NONE = addPreset(0)
        @JvmField val NITRO_CLASSIC = addPreset(1)
        @JvmField val NITRO = addPreset(2)
        @JvmField val NITRO_BASIC = addPreset(3)

        override fun createValue(newKey: Int): UserPremiumType
            = UserPremiumType(newKey)
    }
}


@ConsistentCopyVisibility
data class UserNameplatePalette
private constructor(override val key: String) : ExtensibleEnumValue<String> {
    companion object : ExtensibleEnum<String, UserNameplatePalette>() {
        
        @JvmField val CRIMSON = addPreset("crimson")
        @JvmField val BERRY = addPreset("berry")
        @JvmField val SKY = addPreset("sky")
        @JvmField val TEAL = addPreset("teal")
        @JvmField val FOREST = addPreset("forest")
        @JvmField val BUBBLE_GUM = addPreset("bubble_gum")
        @JvmField val VIOLET = addPreset("violet")
        @JvmField val COBALT = addPreset("cobalt")
        @JvmField val CLOVER = addPreset("clover")
        @JvmField val LEMON = addPreset("lemon")
        @JvmField val WHITE = addPreset("white")

        override fun createValue(newKey: String): UserNameplatePalette
            = UserNameplatePalette(newKey)
    }
}


@ConsistentCopyVisibility
data class TeamMembershipState
private constructor (override val key: Int) : ExtensibleEnumValue<Int> {
    companion object : ExtensibleEnum<Int, TeamMembershipState>() {
        
        @JvmField val INVITED = addPreset(1)
        @JvmField val ACCEPTED = addPreset(2)
        
        override fun createValue(newKey: Int): TeamMembershipState
            = TeamMembershipState(newKey)
            
    }
}

@ConsistentCopyVisibility
data class TeamMemberRole 
private constructor(override val key: String?) : ExtensibleEnumValue<String?> {
    companion object : ExtensibleEnum<String?, TeamMemberRole>() {
        
        @JvmField val OWNER = addPreset(null)
        @JvmField val ADMIN = addPreset("admin")
        @JvmField val DEVELOPER = addPreset("developer")
        @JvmField val READ_ONLY = addPreset("read_only")
        
        override fun createValue(newKey: String?): TeamMemberRole 
            = TeamMemberRole(newKey)
    }
}

@ConsistentCopyVisibility
data class ApplicationEventWebhookStatus
private constructor(override val key: Int) : ExtensibleEnumValue<Int> {
    companion object : ExtensibleEnum<Int, ApplicationEventWebhookStatus>() {
        
        @JvmField val DISABLED = addPreset(1)
        @JvmField val ENABLED = addPreset(2)
        @JvmField val DISABLED_BY_DISCORD = addPreset(3)
        
        override fun createValue(newKey: Int): ApplicationEventWebhookStatus
            = ApplicationEventWebhookStatus(newKey)
    }
}

@ConsistentCopyVisibility
data class WebhookEventType
private constructor(override val key: String) : ExtensibleEnumValue<String> {
    companion object : ExtensibleEnum<String, WebhookEventType>() {
        
        @JvmField val APPLICATION_AUTHORIZED = addPreset("APPLICATION_AUTHORIZED")
        @JvmField val APPLICATION_DEAUTHORIZED = addPreset("APPLICATION_DEAUTHORIZED")
        @JvmField val ENTITLEMENT_CREATE = addPreset("ENTITLEMENT_CREATE")
        @JvmField val QUEST_USER_ENROLLMENT = addPreset("QUEST_USER_ENROLLMENT")
        
        override fun createValue(newKey: String): WebhookEventType 
            = WebhookEventType(newKey)
    }
}

@ConsistentCopyVisibility
data class GuildVerificationLevel
private constructor(override val key: Int) : ExtensibleEnumValue<Int> {
    companion object : ExtensibleEnum<Int, GuildVerificationLevel>() {
        
        @JvmField val NONE = addPreset(0)
        @JvmField val LOW = addPreset(1)
        @JvmField val MEDIUM = addPreset(2)
        @JvmField val HIGH = addPreset(3)
        @JvmField val VERY_HIGH = addPreset(4)
        
        override fun createValue(newKey: Int): GuildVerificationLevel
            = GuildVerificationLevel(newKey)
    }
}

@ConsistentCopyVisibility
data class GuildExplicitContentFilterLevel
private constructor(override val key: Int) : ExtensibleEnumValue<Int> {
    companion object : ExtensibleEnum<Int, GuildExplicitContentFilterLevel>() {
        
        @JvmField val DISABLED = addPreset(0)
        @JvmField val MEMBERS_WITHOUT_ROLES = addPreset(1)
        @JvmField val ALL_MEMBERS = addPreset(2)
        
        override fun createValue(newKey: Int): GuildExplicitContentFilterLevel
            = GuildExplicitContentFilterLevel(newKey)
    }
}

@ConsistentCopyVisibility
data class GuildDefaultMessageNotificationLevel
private constructor (override val key: Int) : ExtensibleEnumValue<Int> {
    companion object : ExtensibleEnum<Int, GuildDefaultMessageNotificationLevel>() {
        
        @JvmField val ALL_MESSAGES = addPreset(0)
        @JvmField val ONLY_MENTIONS = addPreset(1)
        
        override fun createValue(newKey: Int): GuildDefaultMessageNotificationLevel
            = GuildDefaultMessageNotificationLevel(newKey)
    }
}

@ConsistentCopyVisibility
data class GuildFeature
private constructor(override val key: String) : ExtensibleEnumValue<String> {
    companion object : ExtensibleEnum<String, GuildFeature>() {
        
        @JvmField val ANIMATED_BANNER = addPreset("ANIMATED_BANNER")
        @JvmField val ANIMATED_ICON = addPreset("ANIMATED_ICON")
        @JvmField val APPLICATION_COMMAND_PERMISSIONS_V2 = addPreset("APPLICATION_COMMAND_PERMISSIONS_V2")
        @JvmField val AUTO_MODERATION = addPreset("AUTO_MODERATION")
        @JvmField val BANNER = addPreset("BANNER")
        @JvmField val COMMUNITY = addPreset("COMMUNITY")
        @JvmField val CREATOR_MONETIZABLE_PROVISIONAL = addPreset("CREATOR_MONETIZABLE_PROVISIONAL")
        @JvmField val CREATOR_STORE_PAGE = addPreset("CREATOR_STORE_PAGE")
        @JvmField val DEVELOPER_SUPPORT_SERVER = addPreset("DEVELOPER_SUPPORT_SERVER")
        @JvmField val DISCOVERABLE = addPreset("DISCOVERABLE")
        @JvmField val FEATURABLE = addPreset("FEATURABLE")
        @JvmField val INVITES_DISABLED = addPreset("INVITES_DISABLED")
        @JvmField val INVITE_SPLASH = addPreset("INVITE_SPLASH")
        @JvmField val MEMBER_VERIFICATION_GATE_ENABLED = addPreset("MEMBER_VERIFICATION_GATE_ENABLED")
        @JvmField val MORE_SOUNDBOARD = addPreset("MORE_SOUNDBOARD")
        @JvmField val MORE_STICKERS = addPreset("MORE_STICKERS")
        @JvmField val NEWS = addPreset("NEWS")
        @JvmField val PARTNERED = addPreset("PARTNERED")
        @JvmField val PREVIEW_ENABLED = addPreset("PREVIEW_ENABLED")
        @JvmField val RAID_ALERTS_DISABLED = addPreset("RAID_ALERTS_DISABLED")
        @JvmField val ROLE_ICONS = addPreset("ROLE_ICONS")
        @JvmField val ROLE_SUBSCRIPTIONS_AVAILABLE_FOR_PURCHASE = addPreset("ROLE_SUBSCRIPTIONS_AVAILABLE_FOR_PURCHASE")
        @JvmField val ROLE_SUBSCRIPTIONS_ENABLED = addPreset("ROLE_SUBSCRIPTIONS_ENABLED")
        @JvmField val SOUNDBOARD = addPreset("SOUNDBOARD")
        @JvmField val TICKETED_EVENTS_ENABLED = addPreset("TICKETED_EVENTS_ENABLED")
        @JvmField val VANITY_URL = addPreset("VANITY_URL")
        @JvmField val VERIFIED = addPreset("VERIFIED")
        @JvmField val VIP_REGIONS = addPreset("VIP_REGIONS")
        @JvmField val WELCOME_SCREEN_ENABLED = addPreset("WELCOME_SCREEN_ENABLED")
        @JvmField val GUESTS_ENABLED = addPreset("GUESTS_ENABLED")
        @JvmField val ENHANCED_ROLE_COLORS = addPreset("ENHANCED_ROLE_COLORS")

        override fun createValue(newKey: String): GuildFeature
            = GuildFeature(newKey)
    }
}

@ConsistentCopyVisibility
data class GuildNsfwLevel
private constructor(override val key: Int) : ExtensibleEnumValue<Int> {
    companion object : ExtensibleEnum<Int, GuildNsfwLevel>() {
        
        @JvmField val DEFAULT = addPreset(0)
        @JvmField val EXPLICIT = addPreset(1)
        @JvmField val SAFE = addPreset(2)
        @JvmField val AGE_RESTRICTED = addPreset(3)

        override fun createValue(newKey: Int): GuildNsfwLevel
            = GuildNsfwLevel(newKey)
    }
}

@ConsistentCopyVisibility
data class GuildPremiumTier
private constructor(override val key: Int) : ExtensibleEnumValue<Int> {
    companion object : ExtensibleEnum<Int, GuildPremiumTier>() {
        
        @JvmField val NONE = addPreset(0)
        @JvmField val TIER_1 = addPreset(1)
        @JvmField val TIER_2 = addPreset(2)
        @JvmField val TIER_3 = addPreset(3)

        override fun createValue(newKey: Int): GuildPremiumTier
            = GuildPremiumTier(newKey)
    }
}

@ConsistentCopyVisibility
data class ChannelType
private constructor(override val key: Int) : ExtensibleEnumValue<Int> {
    companion object : ExtensibleEnum<Int, ChannelType>() {
        
        @JvmField val GUILD_TEXT = addPreset(0)
        @JvmField val DM = addPreset(1)
        @JvmField val GUILD_VOICE = addPreset(2)
        @JvmField val GROUP_DM = addPreset(3)
        @JvmField val GUILD_CATEGORY = addPreset(4)
        @JvmField val GUILD_ANNOUNCEMENT = addPreset(5)
        @JvmField val ANNOUNCEMENT_THREAD = addPreset(6)
        @JvmField val PUBLIC_THREAD = addPreset(7)
        @JvmField val PRIVATE_THREAD = addPreset(8)
        @JvmField val GUILD_STAGE_VOICE = addPreset(9)
        @JvmField val GUILD_DIRECTORY = addPreset(10)
        @JvmField val GUILD_FORUM = addPreset(11)
        @JvmField val GUILD_MEDIA = addPreset(12)

        override fun createValue(newKey: Int): ChannelType
            = ChannelType(newKey)
    }
}

/*
    Base class for all enum-like API value definitions in this file. This is used instead of the
    standard enum to allow for unknown values to be handled without requiring a library version update.
 */
internal class ExtensibleEnumContainer<TValue> {
    private val presetValues: MutableSet<TValue> = LinkedHashSet()
    private val lock = Any()

    fun register(value: TValue): TValue {
        synchronized(lock) {
            if (!presetValues.add(value)) {
                throw IllegalArgumentException("Preset value is already defined: $value")
            }
            return value
        }
    }

    fun contains(value: TValue): Boolean {
        synchronized(lock) {
            return presetValues.contains(value);
        }
    }

    fun values(): Iterable<TValue> {
        synchronized(lock) {
            return LinkedHashSet(presetValues)
        }
    }

    fun <TKey> matchOrAdd(
        keyToMatch: TKey,
        newValueFactory: (TKey) -> TValue,
        keyExtractor: (TValue) -> TKey
    ): TValue {
        synchronized (lock) {
            return matchOrNull(keyToMatch, keyExtractor)
                ?: register(newValueFactory(keyToMatch))
        }
    }

    fun <TKey> matchOrNull(keyToMatch: TKey, keyExtractor: (TValue) -> TKey): TValue? {
        synchronized(lock) {
            return presetValues.find { keyToMatch == keyExtractor(it) }
        }
    }
}

sealed class ExtensibleEnum<TKey, TValue : ExtensibleEnumValue<TKey>> {
    private val values = ExtensibleEnumContainer<TValue>()

    fun fromOrAdd(key: TKey): TValue {
        return values.matchOrAdd(
            key,
            newValueFactory = { key -> createValue(key) },
            keyExtractor = { it.key }
        )
    }

    fun addPreset(newKey: TKey): TValue {
        return values.register(createValue(newKey))
    }
    
    fun values(): Iterable<TValue> {
        return values.values()
    }

    internal abstract fun createValue(newKey: TKey): TValue
}

sealed interface ExtensibleEnumValue<TKey> {
    val key: TKey
}