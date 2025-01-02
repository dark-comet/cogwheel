package xyz.darkcomet.cogwheel.core

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import xyz.darkcomet.cogwheel.core.network.entities.request.ModifyCurrentApplicationRequestEntity
import java.util.UUID

class ApplicationApiIntegrationTest {
    
    private val client = TestDiscordClient.fromEnvBotToken()
    private val api = client.restApi()
    
    @Test
    fun testGetCurrent() {
        runBlocking {
            val response = api.application().getCurrent()
            assertEquals(true, response.raw.success)
        }
    }
    
    @Test
    fun testEditCurrent() {
        runBlocking { 
            val request = ModifyCurrentApplicationRequestEntity(
                description = "test description: ${UUID.randomUUID()}"
            )
            
            val response = api.application().editCurrent(request)
            
            assertEquals(true, response.raw.success)
            assertNotNull(response.entity)
            assertEquals(request.description, response.entity!!.description)
        }
    }
}