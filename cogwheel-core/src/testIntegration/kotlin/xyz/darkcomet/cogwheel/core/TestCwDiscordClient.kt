package xyz.darkcomet.cogwheel.core

class TestCwDiscordClient {
    companion object {
        private const val TEST_BOT_TOKEN_ENV_KEY = "CW_CORE_CI_BOT_TOKEN"
        private const val TEST_OAUTH2_TOKEN_ENV_KEY = "CW_CORE_CI_OAUTH2_TOKEN"
        
        fun fromEnvBotToken(init: (CwDiscordClientBuilder.() -> Unit)? = null): CwDiscordClient {
            val token = System.getenv(TEST_BOT_TOKEN_ENV_KEY)
            
            if (token == null || token.isBlank()) {
                throw IllegalArgumentException("No bot token set for ENV key: $TEST_BOT_TOKEN_ENV_KEY")
            }
            
            return CwDiscordClient.fromBotToken(token) {
                applyTestSettings(this)
                init?.invoke(this)
            }
        }

        fun fromEnvOauth2Token(init: (CwDiscordClientBuilder.() -> Unit)?): CwDiscordClient {
            val token = System.getenv(TEST_OAUTH2_TOKEN_ENV_KEY)

            if (token == null || token.isBlank()) {
                throw IllegalArgumentException("No OAuth2 token set for ENV key: $TEST_OAUTH2_TOKEN_ENV_KEY")
            }

            return CwDiscordClient.fromOAuth2Token(token) {
                applyTestSettings(this)
                init?.invoke(this)
            }
        }

        private fun applyTestSettings(builder: CwDiscordClientBuilder) {
            builder.gatewayFetchUrlMaxAttempts = 5
            builder.gatewaySessionReconnectMaxAttempts = 5
        }
    }
}