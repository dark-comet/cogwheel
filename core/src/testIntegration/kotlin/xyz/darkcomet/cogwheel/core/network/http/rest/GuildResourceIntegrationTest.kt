//package xyz.darkcomet.cogwheel.core.network.http.rest
//
//import org.junit.jupiter.api.Assertions.*
//import org.junit.jupiter.api.Test
//import xyz.darkcomet.cogwheel.core.IntegrationTestFixture
//import xyz.darkcomet.cogwheel.core.TestCwDiscordClient
//import xyz.darkcomet.cogwheel.core.events.Gateway
//import xyz.darkcomet.cogwheel.core.network.objects.CreateGuildRequestParameters
//import xyz.darkcomet.cogwheel.core.network.objects.ModifyGuildRequestParameters
//import xyz.darkcomet.cogwheel.core.primitives.ImageData
//import xyz.darkcomet.cogwheel.core.primitives.Intents
//import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
//import xyz.darkcomet.cogwheel.core.primitives.Snowflake
//import java.util.*
//import java.util.concurrent.CountDownLatch
//import java.util.concurrent.TimeUnit
//
//class GuildResourceIntegrationTest : IntegrationTestFixture() {
//    
//    private val client = TestCwDiscordClient.fromEnvBotToken() { useGateway(Intents.of(Intents.GUILDS)) }
//    private val guildApi = client.restApi().guild
//
//    @Test
//    fun testCreate_Get_GetPreview_Modify_Delete() {
//        val receivedGuildCreateEvent = CountDownLatch(1)
//        client.events().subscribe(Gateway.GuildCreate) { receivedGuildCreateEvent.countDown() }
//        
//        val receivedGuildUpdateEvent = CountDownLatch(1)
//        client.events().subscribe(Gateway.GuildUpdate) { receivedGuildUpdateEvent.countDown() }
//        
//        val receivedGuildDeleteEvent = CountDownLatch(1)
//        client.events().subscribe(Gateway.GuildDelete) { receivedGuildDeleteEvent.countDown() }
//        
//        withGateway(client) {
//            // CREATE
//            val guildName = "Test Guild " + UUID.randomUUID().toString()
//            val createRequest = CreateGuildRequestParameters(guildName)
//            val createResponse = guildApi.createGuild(createRequest)
//            
//            try {
//                assertAll(
//                    { assertEquals(true, createResponse.raw.success) },
//                    { assertEquals(201, createResponse.raw.statusCode) },
//                    { assertNotNull(createResponse.data) }
//                )
//                assertTrue(receivedGuildCreateEvent.await(10, TimeUnit.SECONDS), "Did not receive GUILD_CREATE gateway event")
//                
//                val guildId = createResponse.data!!.id!!.value!!
//                
//                testGetPreview(guildId, guildName)
//                
//                testModifyAndGet(guildId)
//                assertTrue(receivedGuildUpdateEvent.await(10, TimeUnit.SECONDS), "Did not receive GUILD_UPDATE gateway event")
//            } finally {
//                // DELETE
//                val guildId = createResponse.data?.id!!.value
//                
//                if (guildId != null) {
//                    val deleteResponse = guildApi.deleteGuild(guildId)
//                    
//                    assertAll(
//                        { assertTrue(deleteResponse.raw.success) },
//                        { assertEquals(204, deleteResponse.raw.statusCode) }
//                    )
//
//                    assertTrue(receivedGuildDeleteEvent.await(10, TimeUnit.SECONDS), "Did not receive GUILD_DELETE gateway event")
//                }
//            }
//        }    
//    }
//
//    private suspend fun testGetPreview(guildId: Snowflake, guildName: String) {
//        val getPreviewResponse = guildApi.getGuildPreview(guildId)
//        
//        assertAll(
//            { assertEquals(true, getPreviewResponse.raw.success) },
//            { assertEquals(200, getPreviewResponse.raw.statusCode) },
//            { assertNotNull(getPreviewResponse.data) }
//        )
//        assertAll(
//            { assertEquals(guildId, getPreviewResponse.data!!.id!!.value) },
//            { assertEquals(guildName, getPreviewResponse.data!!.name!!.value) },
//            { assertNull(getPreviewResponse.data!!.icon) },
//            { assertNull(getPreviewResponse.data!!.splash) },
//            { assertNull(getPreviewResponse.data!!.discoverySplash) },
//            { assertTrue(getPreviewResponse.data!!.emojis!!.value!!.isEmpty()) },
//            { assertNull(getPreviewResponse.data!!.description) },
//            { assertTrue(getPreviewResponse.data!!.stickers!!.value!!.isEmpty()) },
//        )
//    }
//
//    private suspend fun testModifyAndGet(guildId: Snowflake) {
//        val newGuildName = "Test Guild " + UUID.randomUUID().toString()
//        val newGuildDescription = "Updated description " + UUID.randomUUID().toString()
//        val newVerificationLevel = 3 // HIGH
//        val newDefaultMessageNotificationLevel = 1 // ONLY_MENTIONS
//        val newExplicitContentFilterLevel = 1 // MEMBERS_WITHOUT_ROLES
//        val newAfkTimeout = 1800
//        val newPremiumProgressBarEnabled = true
//        val newIconImageData = this::class.java.getResourceAsStream("/fuzzy_bread.png")!!.readBytes()
//        val iconImageData = ImageData.fromBytes(ImageData.FileExtension.PNG, newIconImageData)
//
//        val modifyRequest = ModifyGuildRequestParameters(
//            name = MaybeAbsent(newGuildName),
//            verificationLevel = MaybeAbsent(newVerificationLevel),
//            defaultMessageNotifications = MaybeAbsent(newDefaultMessageNotificationLevel),
//            explicitContentFilter = MaybeAbsent(newExplicitContentFilterLevel),
//            afkTimeout = MaybeAbsent(newAfkTimeout),
//            premiumProgressBarEnabled = MaybeAbsent(newPremiumProgressBarEnabled),
//            description = MaybeAbsent(newGuildDescription),
//            icon = MaybeAbsent(iconImageData.hash)
//        )
//        val modifyResponse = guildApi.modifyGuild(guildId, modifyRequest)
//
//        assertAll(
//            { assertEquals(true, modifyResponse.raw.success) },
//            { assertEquals(200, modifyResponse.raw.statusCode) },
//            { assertNotNull(modifyResponse.data) }
//        )
//        
//        assertAll(
//            { assertEquals(guildId, modifyResponse.data!!.id!!.value) },
//            { assertEquals(newVerificationLevel, modifyResponse.data!!.verificationLevel!!.value) },
//            { assertEquals(newDefaultMessageNotificationLevel, modifyResponse.data!!.defaultMessageNotifications!!.value) },
//            { assertEquals(newExplicitContentFilterLevel, modifyResponse.data!!.explicitContentFilter!!.value) },
//            { assertEquals(newAfkTimeout, modifyResponse.data!!.afkTimeout!!.value) },
//            { assertEquals(newPremiumProgressBarEnabled, modifyResponse.data!!.premiumProgressBarEnabled!!.value) },
//            { assertNotNull(modifyResponse.data!!.icon) },
//            { assertNotNull(modifyResponse.data!!.icon!!.value) }
//        )
//        
//        // Test GET
//        val getResponse = guildApi.getGuild(guildId, withCounts = true)
//
//        assertAll(
//            { assertEquals(true, getResponse.raw.success) },
//            { assertEquals(200, getResponse.raw.statusCode) },
//            { assertNotNull(getResponse.data) }
//        )
//
//        assertAll(
//            { assertEquals(guildId, getResponse.data!!.id!!.value) },
//            { assertEquals(newVerificationLevel, getResponse.data!!.verificationLevel!!.value) },
//            { assertEquals(newDefaultMessageNotificationLevel, getResponse.data!!.defaultMessageNotifications!!.value) },
//            { assertEquals(newExplicitContentFilterLevel, getResponse.data!!.explicitContentFilter!!.value) },
//            { assertEquals(newAfkTimeout, getResponse.data!!.afkTimeout!!.value) },
//            { assertEquals(newPremiumProgressBarEnabled, getResponse.data!!.premiumProgressBarEnabled!!.value) },
//        )
//    }
//}