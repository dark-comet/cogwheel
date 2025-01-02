package xyz.darkcomet.cogwheel.core

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import xyz.darkcomet.cogwheel.core.models.Snowflake
import xyz.darkcomet.cogwheel.core.network.entities.request.CreateGuildRequestEntity
import xyz.darkcomet.cogwheel.core.network.entities.request.ModifyGuildRequestEntity
import java.util.*

class GuildApiIntegrationTest {
    
    private val client = TestDiscordClient.fromEnvBotToken()
    private val guildApi = client.restApi().guild()

    @Test
    fun testCreate_Get_GetPreview_Modify_Delete() {
        runBlocking {
            // CREATE
            val guildName = "Test Guild " + UUID.randomUUID().toString()
            val createRequest = CreateGuildRequestEntity(guildName)
            val createResponse = guildApi.create(createRequest)
            
            try {
                assertAll(
                    { assertEquals(true, createResponse.raw.success) },
                    { assertEquals(201, createResponse.raw.statusCode) },
                    { assertNotNull(createResponse.entity) }
                )
                
                val guildId = createResponse.entity!!.id
                assertNotNull(guildId)
                
                testGetPreview(guildId, guildName)
                
                testModifyAndGet(guildId)
            } finally {
                // DELETE
                val guildId = createResponse.entity?.id
                if (guildId != null) {
                    val deleteResponse = guildApi.delete(guildId)
                    assertAll(
                        { assertTrue(deleteResponse.raw.success) },
                        { assertEquals(204, deleteResponse.raw.statusCode) }
                    )
                }
            }
        }    
    }

    private suspend fun testGetPreview(guildId: Snowflake, guildName: String) {
        val getPreviewResponse = guildApi.getPreview(guildId)
        
        assertAll(
            { assertEquals(true, getPreviewResponse.raw.success) },
            { assertEquals(200, getPreviewResponse.raw.statusCode) },
            { assertNotNull(getPreviewResponse.entity) }
        )
        assertAll(
            { assertEquals(guildId, getPreviewResponse.entity!!.id) },
            { assertEquals(guildName, getPreviewResponse.entity!!.name) },
            { assertNull(getPreviewResponse.entity!!.icon) },
            { assertNull(getPreviewResponse.entity!!.splash) },
            { assertNull(getPreviewResponse.entity!!.discoverySplash) },
            { assertTrue(getPreviewResponse.entity!!.emojis.isEmpty()) },
            { assertNull(getPreviewResponse.entity!!.description) },
            { assertTrue(getPreviewResponse.entity!!.stickers.isEmpty()) },
        )
    }

    private suspend fun testModifyAndGet(guildId: Snowflake) {
        val newGuildName = "Test Guild " + UUID.randomUUID().toString()
        val newGuildDescription = "Updated description " + UUID.randomUUID().toString()
        val newVerificationLevel = 3 // HIGH
        val newDefaultMessageNotificationLevel = 1 // ONLY_MENTIONS
        val newExplicitContentFilterLevel = 1 // MEMBERS_WITHOUT_ROLES
        val newAfkTimeout = 1800
        val newPremiumProgressBarEnabled = true

        val modifyRequest = ModifyGuildRequestEntity(
            name = newGuildName,
            verificationLevel = newVerificationLevel,
            defaultMessageNotifications = newDefaultMessageNotificationLevel,
            explicitContentFilter = newExplicitContentFilterLevel,
            afkTimeout = newAfkTimeout,
            premiumProgressBarEnabled = newPremiumProgressBarEnabled,
            description = newGuildDescription
        )
        val modifyResponse = guildApi.modify(guildId, modifyRequest)

        assertAll(
            { assertEquals(true, modifyResponse.raw.success) },
            { assertEquals(200, modifyResponse.raw.statusCode) },
            { assertNotNull(modifyResponse.entity) }
        )
        
        assertAll(
            { assertEquals(guildId, modifyResponse.entity!!.id) },
            { assertEquals(newVerificationLevel, modifyResponse.entity!!.verificationLevel) },
            { assertEquals(newDefaultMessageNotificationLevel, modifyResponse.entity!!.defaultMessageNotifications) },
            { assertEquals(newExplicitContentFilterLevel, modifyResponse.entity!!.explicitContentFilter) },
            { assertEquals(newAfkTimeout, modifyResponse.entity!!.afkTimeout) },
            { assertEquals(newPremiumProgressBarEnabled, modifyResponse.entity!!.premiumProgressBarEnabled) },
        )
        
        // Test GET
        val getResponse = guildApi.get(guildId, withCounts = true)

        assertAll(
            { assertEquals(true, getResponse.raw.success) },
            { assertEquals(200, getResponse.raw.statusCode) },
            { assertNotNull(getResponse.entity) }
        )

        assertAll(
            { assertEquals(guildId, getResponse.entity!!.id) },
            { assertEquals(newVerificationLevel, getResponse.entity!!.verificationLevel) },
            { assertEquals(newDefaultMessageNotificationLevel, getResponse.entity!!.defaultMessageNotifications) },
            { assertEquals(newExplicitContentFilterLevel, getResponse.entity!!.explicitContentFilter) },
            { assertEquals(newAfkTimeout, getResponse.entity!!.afkTimeout) },
            { assertEquals(newPremiumProgressBarEnabled, getResponse.entity!!.premiumProgressBarEnabled) },
        )
    }
}