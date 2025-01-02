package xyz.darkcomet.cogwheel.dsl.models

enum class GuildDefaultMessageNotificationLevel(val value: Int) {
    ALL_MESSAGES(0),
    ONLY_MENTIONS(1)
}