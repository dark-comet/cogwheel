package xyz.darkcomet.cogwheel.core.primitives.auth

class OAuth2Token(private val token: String) : Token {
    
    override val value: String
        get() = token

    override fun getAuthorizationHeaderValue(): String {
        return "Bearer $token"
    }
    
}