package xyz.darkcomet.cogwheel.core.impl.authentication

interface Token {

    val value: String
    
    fun getAuthorizationHeaderValue(): String
    
}