package xyz.darkcomet.cogwheel.framework.primitives

import xyz.darkcomet.cogwheel.core.primitives.Snowflake

fun Snowflake.asApplicationId(): ApplicationId {
    return ApplicationId(this)
}

fun Snowflake.asApplicationCommandId(): ApplicationCommandId {
    return ApplicationCommandId(this)
}

fun Snowflake.asAuditLogEntryId(): AuditLogEntryId {
    return AuditLogEntryId(this)
}

fun Snowflake.asAutoModerationRuleId(): AutoModerationRuleId {
    return AutoModerationRuleId(this)
}

fun Snowflake.asUserId(): UserId {
    return UserId(this)
}

fun Snowflake.asRoleId(): RoleId {
    return RoleId(this)
}

fun Snowflake.asGuildId(): GuildId {
    return GuildId(this)
}

fun Snowflake.asChannelId(): ChannelId {
    return ChannelId(this)
}

fun Snowflake.asChannelForumTagId(): ChannelForumTagId {
    return ChannelForumTagId(this)
}

fun Snowflake.asEmojiId(): EmojiId {
    return EmojiId(this)
}

fun Snowflake.asTeamId(): TeamId {
    return TeamId(this)
}

fun Snowflake.asSkuId(): SkuId {
    return SkuId(this)
}

fun Snowflake.asEntitlementId(): EntitlementId {
    return EntitlementId(this)
}

fun Snowflake.asSubscriptionId(): SubscriptionId {
    return SubscriptionId(this)
}

fun Snowflake.asGuildScheduledEventId(): GuildScheduledEventId {
    return GuildScheduledEventId(this)
}

fun Snowflake.asMessageId(): MessageId {
    return MessageId(this)
}

fun Snowflake.asSoundboardSoundId(): SoundboardSoundId {
    return SoundboardSoundId(this)
}

fun Snowflake.asStageInstanceId(): StageInstanceId {
    return StageInstanceId(this)
}

fun Snowflake.asStickerId(): StickerId {
    return StickerId(this)
}

fun Snowflake.asStickerPackId(): StickerPackId {
    return StickerPackId(this)
}