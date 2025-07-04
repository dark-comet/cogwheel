package xyz.darkcomet.cogwheel.framework.primitives

import xyz.darkcomet.cogwheel.core.primitives.Snowflake

fun Snowflake.asApplicationId(): ApplicationId {
    return ApplicationId(this);
}

fun Snowflake.asUserId(): UserId {
    return UserId(this);
}

fun Snowflake.asGuildId(): GuildId {
    return GuildId(this);
}

fun Snowflake.asTeamId(): TeamId {
    return TeamId(this);
}

fun Snowflake.asSkuId(): SkuId {
    return SkuId(this);
}
