@file:Suppress("unused") // All definitions are part of the public API

package xyz.darkcomet.cogwheel.framework.primitives

import xyz.darkcomet.cogwheel.core.primitives.Snowflake


data class ApplicationId(val id: Snowflake)

data class UserId(val id: Snowflake)

data class GuildId(val id: Snowflake)

data class ChannelId(val id: Snowflake)

data class EmojiId(val id: Snowflake)

data class TeamId(val id: Snowflake)

data class SkuId(val id: Snowflake)

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
data class ApplicationFlag
private constructor(override val key: Int) : ExtensibleEnumValue<Int> {
    companion object : ExtensibleEnum<Int, ApplicationFlag>() {
        val APPLICATION_AUTO_MODERATION_RULE_CREATE_BADGE = addPreset(1 shl 6)
        val GATEWAY_PRESENCE = addPreset(1 shl 12)
        val GATEWAY_PRESENCE_LIMITED = addPreset(1 shl 13)
        val GATEWAY_GUILD_MEMBERS = addPreset(1 shl 14)
        val GATEWAY_GUILD_MEMBERS_LIMITED = addPreset(1 shl 15)
        val VERIFICATION_PENDING_GUILD_LIMIT = addPreset(1 shl 16)
        val EMBEDDED = addPreset(1 shl 17)
        val GATEWAY_MESSAGE_CONTENT = addPreset(1 shl 18)
        val GATEWAY_MESSAGE_CONTENT_LIMITED = addPreset(1 shl 19)
        val APPLICATION_COMMAND_BADGE = addPreset(1 shl 23)
        
        override fun createValue(newKey: Int): ApplicationFlag
            = ApplicationFlag(newKey)
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

    internal abstract fun createValue(newKey: TKey): TValue
}

sealed interface ExtensibleEnumValue<TKey> {
    val key: TKey
}