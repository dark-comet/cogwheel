package xyz.darkcomet.cogwheel.core.primitives.auth

interface Token {

    val value: String
    
    fun getAuthorizationHeaderValue(): String
    
}