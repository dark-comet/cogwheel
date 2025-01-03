package xyz.darkcomet.cogwheel.dsl.primitives

enum class GuildExplicitContentFilterLevel(val value: Int) {
    DISABLED(0),
    MEMBERS_WITHOUT_ROLES(1),
    ALL_MEMBERS(2)
}