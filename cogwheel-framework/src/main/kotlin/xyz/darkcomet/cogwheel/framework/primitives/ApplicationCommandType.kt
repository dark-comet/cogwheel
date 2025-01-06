package xyz.darkcomet.cogwheel.framework.primitives

enum class ApplicationCommandType(val value: Int) {
    CHAT_INPUT(1),
    USER(2),
    MESSAGE(3),
    PRIMARY_ENTRY_POINT(4)
}