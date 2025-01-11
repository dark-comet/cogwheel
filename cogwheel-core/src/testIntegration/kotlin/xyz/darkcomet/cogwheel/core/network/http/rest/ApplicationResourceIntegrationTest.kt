package xyz.darkcomet.cogwheel.core.network.http.rest

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import xyz.darkcomet.cogwheel.core.TestCwDiscordClient
import xyz.darkcomet.cogwheel.core.network.objects.EditCurrentApplicationRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.ImageData
import xyz.darkcomet.cogwheel.core.primitives.NullableValue
import xyz.darkcomet.cogwheel.core.primitives.Value
import java.util.*

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
            val newIconImageData = this::class.java.getResourceAsStream("/fuzzy_bread.png")!!.readBytes()
            val iconImageData = ImageData.fromBytes(ImageData.FileExtension.PNG, newIconImageData)
            
            var request = EditCurrentApplicationRequestParameters(
                description = Value("test description: ${UUID.randomUUID()}"),
                icon = NullableValue(iconImageData)
            )
            var response = api.application.editCurrentApplication(request)
            
            assertEquals(true, response.raw.success)
            assertNotNull(response.data)
            assertEquals(request.description!!, response.data!!.description)

            request = EditCurrentApplicationRequestParameters(
                icon = NullableValue(null)
            )
            response = api.application.editCurrentApplication(request)
            
            assertEquals(true, response.raw.success, response.raw.statusMessage)
            assertNull(response.data!!.icon)
        }
    }
}