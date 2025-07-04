@file:Suppress("unused") // All definitions are part of the public API

package xyz.darkcomet.cogwheel.framework.primitives

import xyz.darkcomet.cogwheel.core.network.gateway.GatewayPayload
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import java.math.BigInteger


data class ApplicationId(val id: Snowflake)

data class UserId(val id: Snowflake)

data class GuildId(val id: Snowflake)

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
private constructor(val value: String) {
    companion object {
        @JvmStatic val ACTIVITIES_READ = OAuth2Scope("activities.read")
        @JvmStatic val ACTIVITIES_WRITE = OAuth2Scope("activities.write")
        @JvmStatic val APPLICATIONS_BUILDS_READ = OAuth2Scope("applications.builds.read")
        @JvmStatic val APPLICATIONS_BUILDS_UPLOAD = OAuth2Scope("applications.builds.upload")
        @JvmStatic val APPLICATIONS_COMMANDS = OAuth2Scope("applications.commands")
        @JvmStatic val APPLICATIONS_COMMANDS_UPDATE = OAuth2Scope("applications.commands.update")
        @JvmStatic val APPLICATIONS_COMMANDS_PERMISSIONS_UPDATE = OAuth2Scope("applications.commands.permissions.update")
        @JvmStatic val APPLICATIONS_ENTITLEMENTS = OAuth2Scope("applications.entitlements")
        @JvmStatic val APPLICATIONS_STORE_UPDATE = OAuth2Scope("applications.store.update")
        @JvmStatic val BOT = OAuth2Scope("bot")
        @JvmStatic val CONNECTIONS = OAuth2Scope("connections")
        @JvmStatic val DM_CHANNELS_READ = OAuth2Scope("dm_channels.read")
        @JvmStatic val EMAIL = OAuth2Scope("email")
        @JvmStatic val GDM_JOIN = OAuth2Scope("gdm.join")
        @JvmStatic val GUILDS = OAuth2Scope("guilds")
        @JvmStatic val GUILDS_JOIN = OAuth2Scope("guilds.join")
        @JvmStatic val GUILDS_MEMBERS_READ = OAuth2Scope("guilds.members.read")
        @JvmStatic val IDENTIFY = OAuth2Scope("identify")
        @JvmStatic val MESSAGES_READ = OAuth2Scope("messages.read")
        @JvmStatic val RELATIONSHIPS_READ = OAuth2Scope("relationships.read")
        @JvmStatic val ROLE_CONNECTIONS_WRITE = OAuth2Scope("role_connections.write")
        @JvmStatic val RPC = OAuth2Scope("rpc")
        @JvmStatic val RPC_ACTIVITIES_WRITE = OAuth2Scope("rpc.activities.write")
        @JvmStatic val RPC_NOTIFICATIONS_READ = OAuth2Scope("rpc.notifications.read")
        @JvmStatic val RPC_VOICE_READ = OAuth2Scope("rpc.voice.read")
        @JvmStatic val RPC_VOICE_WRITE = OAuth2Scope("rpc.voice.write")
        @JvmStatic val VOICE = OAuth2Scope("voice")
        @JvmStatic val WEBHOOK_INCOMING = OAuth2Scope("webhook.incoming")
        
        @JvmStatic
        fun from(value: String): OAuth2Scope {
            TODO();
        }
    }
}

@ConsistentCopyVisibility
data class ApplicationIntegrationType
private constructor(val type: String, val id: Int) {
    companion object {
        @JvmStatic val GUILD_INSTALL = ApplicationIntegrationType("GUILD_INSTALL", 0)
        @JvmStatic val USER_INSTALL = ApplicationIntegrationType("USER_INSTALL", 1)
        
        @JvmStatic
        fun fromId(id: Int): ApplicationIntegrationType? {
            return when(id) {
                GUILD_INSTALL.id -> GUILD_INSTALL
                USER_INSTALL.id -> USER_INSTALL
                else -> null
            }
        }
        
        @JvmStatic
        fun fromType(type: String): ApplicationIntegrationType? {
            return when(type) {
                GUILD_INSTALL.type -> GUILD_INSTALL
                USER_INSTALL.type -> USER_INSTALL
                else -> null
            }
        }
    }
}

@ConsistentCopyVisibility
data class ApplicationPublicFlags
private constructor(val value: Int) {
    companion object {
        const val APPLICATION_AUTO_MODERATION_RULE_CREATE_BADGE = 1 shl 6;
        const val GATEWAY_PRESENCE = 1 shl 12;
        const val GATEWAY_PRESENCE_LIMITED = 1 shl 13;
        const val GATEWAY_GUILD_MEMBERS = 1 shl 14;
        const val GATEWAY_GUILD_MEMBERS_LIMITED = 1 shl 15;
        const val VERIFICATION_PENDING_GUILD_LIMIT = 1 shl 16;
        const val EMBEDDED = 1 shl 17;
        const val GATEWAY_MESSAGE_CONTENT = 1 shl 18;
        const val GATEWAY_MESSAGE_CONTENT_LIMITED = 1 shl 19;
        const val APPLICATION_COMMAND_BADGE = 1 shl 23;
    }

    fun has(flag: Int): Boolean {
        return value.and(flag) != 0
    }
}

@ConsistentCopyVisibility
data class UserFlags
private constructor(val value: BigInteger) {
    companion object {
        // TODO
    }
}

@ConsistentCopyVisibility
data class UserPremiumType
private constructor(val value: Int) {
    companion object {
        @JvmStatic val NONE = UserPremiumType(0)
        @JvmStatic val NITRO_CLASSIC = UserPremiumType(1)
        @JvmStatic val NITRO = UserPremiumType(2)
        @JvmStatic val NITRO_BASIC = UserPremiumType(3)
        
        @JvmStatic
        fun from(value: Int): UserPremiumType {
            return when(value) {
                NONE.value -> NONE
                NITRO_BASIC.value -> NITRO_CLASSIC
                NITRO.value -> NITRO
                NITRO_CLASSIC.value -> NITRO_BASIC
                else -> UserPremiumType(value)
            }
        }
    }
}

@ConsistentCopyVisibility
data class TeamMembershipState
private constructor (val value: Int) {
    companion object {
        @JvmStatic val INVITED = TeamMembershipState(1)
        @JvmStatic val ACCEPTED = TeamMembershipState(2)
        
        @JvmStatic
        fun from(value: Int): TeamMembershipState {
            return when(value) {
                INVITED.value -> INVITED
                ACCEPTED.value -> ACCEPTED
                else -> TeamMembershipState(value)
            }
        }
    }
}

@ConsistentCopyVisibility
data class TeamMemberRole 
private constructor(val value: String?){
    companion object {
        @JvmStatic val OWNER = TeamMemberRole(null)
        @JvmStatic val ADMIN = TeamMemberRole("admin")
        @JvmStatic val DEVELOPER = TeamMemberRole("developer")
        @JvmStatic val READ_ONLY = TeamMemberRole("read_only")
        
        @JvmStatic
        fun from(value: String?): TeamMemberRole {
            return when (value) {
                OWNER.value, "" -> OWNER
                ADMIN.value -> ADMIN
                DEVELOPER.value -> DEVELOPER
                READ_ONLY.value -> READ_ONLY
                else -> TeamMemberRole(value)
            }
        }
    }
}

@ConsistentCopyVisibility
data class ApplicationEventWebhookStatus
private constructor(val value: Int) {
    companion object {
        @JvmStatic val DISABLED = ApplicationEventWebhookStatus(1)
        @JvmStatic val ENABLED = ApplicationEventWebhookStatus(2)
        @JvmStatic val DISABLED_BY_DISCORD = ApplicationEventWebhookStatus(3)
        
        @JvmStatic
        fun from(value: Int): ApplicationEventWebhookStatus {
            return when(value) {
                DISABLED.value -> DISABLED
                ENABLED.value -> ENABLED
                DISABLED_BY_DISCORD.value -> DISABLED_BY_DISCORD
                else -> ApplicationEventWebhookStatus(value)
            }
        }
    }
}

@ConsistentCopyVisibility
data class WebhookEventType
private constructor(val value: Int) {
    companion object {
        
    }
}

@ConsistentCopyVisibility
data class GuildVerificationLevel
private constructor(val value: Int) {
    companion object {
        @JvmStatic val NONE = GuildVerificationLevel(0)
        @JvmStatic val LOW = GuildVerificationLevel(1)
        @JvmStatic val MEDIUM = GuildVerificationLevel(2)
        @JvmStatic val HIGH = GuildVerificationLevel(3)
        @JvmStatic val VERY_HIGH = GuildVerificationLevel(4)
        
        @JvmStatic
        fun from(value: Int): GuildVerificationLevel {
            return when(value) {
                NONE.value -> NONE
                LOW.value -> LOW
                MEDIUM.value -> MEDIUM
                HIGH.value -> HIGH
                VERY_HIGH.value -> VERY_HIGH
                else -> GuildVerificationLevel(value)
            }
        }
    }
}

@ConsistentCopyVisibility
data class GuildExplicitContentFilterLevel
private constructor(val value: Int) {
    companion object {
        @JvmStatic val DISABLED = GuildExplicitContentFilterLevel(0)
        @JvmStatic val MEMBERS_WITHOUT_ROLES = GuildExplicitContentFilterLevel(1)
        @JvmStatic val ALL_MEMBERS = GuildExplicitContentFilterLevel(2)
        
        @JvmStatic
        fun from(value: Int): GuildExplicitContentFilterLevel {
            return when(value) {
                DISABLED.value -> DISABLED
                MEMBERS_WITHOUT_ROLES.value -> MEMBERS_WITHOUT_ROLES
                ALL_MEMBERS.value -> ALL_MEMBERS
                else -> GuildExplicitContentFilterLevel(value)
            }
        }
    }
}

@ConsistentCopyVisibility
data class GuildDefaultMessageNotificationLevel
private constructor (val value: Int) {
    companion object {
        @JvmStatic val ALL_MESSAGES = GuildDefaultMessageNotificationLevel(0)
        @JvmStatic val ONLY_MENTIONS = GuildDefaultMessageNotificationLevel(1)
        
        @JvmStatic
        fun from(value: Int): GuildDefaultMessageNotificationLevel {
            return when(value) {
                ALL_MESSAGES.value -> ONLY_MENTIONS
                ONLY_MENTIONS.value -> ONLY_MENTIONS
                else -> GuildDefaultMessageNotificationLevel(value)
            }
        }
    }
}

@ConsistentCopyVisibility
data class ChannelType
private constructor(val value: Int) {
    companion object {
        @JvmStatic val GUILD_TEXT = ChannelType(0)
        @JvmStatic val DM = ChannelType(1)
        @JvmStatic val GUILD_VOICE = ChannelType(2)
        @JvmStatic val GROUP_DM = ChannelType(3)
        @JvmStatic val GUILD_CATEGORY = ChannelType(4)
        @JvmStatic val GUILD_ANNOUNCEMENT = ChannelType(5)
        @JvmStatic val ANNOUNCEMENT_THREAD = ChannelType(6)
        @JvmStatic val PUBLIC_THREAD = ChannelType(7)
        @JvmStatic val PRIVATE_THREAD = ChannelType(8)
        @JvmStatic val GUILD_STAGE_VOICE = ChannelType(9)
        @JvmStatic val GUILD_DIRECTORY = ChannelType(10)
        @JvmStatic val GUILD_FORUM = ChannelType(11)
        @JvmStatic val GUILD_MEDIA = ChannelType(12)
        
        @JvmStatic
        fun from(value: Int): ChannelType {
            return when(value) {
                GUILD_TEXT.value -> GUILD_TEXT
                DM.value -> DM
                GUILD_VOICE.value -> GUILD_VOICE
                GROUP_DM.value -> GROUP_DM
                GUILD_CATEGORY.value -> GUILD_CATEGORY
                GUILD_ANNOUNCEMENT.value -> GUILD_ANNOUNCEMENT
                ANNOUNCEMENT_THREAD.value -> ANNOUNCEMENT_THREAD
                PUBLIC_THREAD.value -> PUBLIC_THREAD
                PRIVATE_THREAD.value -> PRIVATE_THREAD
                GUILD_STAGE_VOICE.value -> GUILD_STAGE_VOICE
                GUILD_DIRECTORY.value -> GUILD_DIRECTORY
                GUILD_FORUM.value -> GUILD_FORUM
                GUILD_MEDIA.value -> GUILD_MEDIA
                else -> ChannelType(value)
            }
        }
    }
}

