package xyz.darkcomet.cogwheel.core.primitives.auth

class BotToken(private val token: String) : Token {
    
    override val value: String
        get() = token

    override fun getAuthorizationHeaderValue(): String {
        return "Bot $token"
    }

}