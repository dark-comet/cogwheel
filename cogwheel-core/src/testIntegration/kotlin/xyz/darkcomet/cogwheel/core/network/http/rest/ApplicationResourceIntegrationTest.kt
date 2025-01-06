package xyz.darkcomet.cogwheel.core.network.http.rest

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import xyz.darkcomet.cogwheel.core.TestCwDiscordClient
import xyz.darkcomet.cogwheel.core.network.objects.request.ModifyCurrentApplicationRequestParameters
import java.util.UUID

class ApplicationResourceIntegrationTest {
    
    private val client = TestCwDiscordClient.fromEnvBotToken()
    private val api = client.restApi()
    
    @Test
    fun testGetCurrentApplication() {
        runBlocking {
            val response = api.application.getCurrentApplication()
            assertEquals(true, response.raw.success)
        }
    }
    
    @Test
    fun testEditCurrentApplication() {
        runBlocking { 
            val request = ModifyCurrentApplicationRequestParameters(description = "test description: ${UUID.randomUUID()}")
            val response = api.application.editCurrentApplication(request)
            
            assertEquals(true, response.raw.success)
            assertNotNull(response.data)
            assertEquals(request.description, response.data!!.description)
        }
    }
}