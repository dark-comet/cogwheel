package xyz.darkcomet.cogwheel.network.gateway

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import xyz.darkcomet.cogwheel.TestDiscordClient
import xyz.darkcomet.cogwheel.events.GatewayHelloEvent
import xyz.darkcomet.cogwheel.events.GatewayReadyEvent
import xyz.darkcomet.cogwheel.models.Intents
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class KtorGatewayClientIntegrationTest {
    
    @Test
    fun testConnectionWorks() {
        val client = TestDiscordClient.fromEnvBotToken { 
            useGateway(Intents.none())
        }
        
        val receiveHelloLatch = CountDownLatch(1)
        val receiveReadyLatch = CountDownLatch(1)
        
        val events = client.events()
        
        events.subscribe(GatewayHelloEvent::class.java) { receiveHelloLatch.countDown() }
        events.subscribe(GatewayReadyEvent::class.java) { receiveReadyLatch.countDown() }

        val assertions = Thread {
            val receivedReady = receiveReadyLatch.await(1000, TimeUnit.SECONDS)
            assertTrue(receivedReady, "Failed to receive READY event after 10 seconds!")

            val receivedHello = receiveHelloLatch.await(1000, TimeUnit.SECONDS)
            assertTrue(receivedHello, "Failed to receive HELLO event after 10 seconds!")

            client.stop()
        }

        assertions.start()

        runBlocking {
            client.startGatewayConnection()
        }
    }
    
//    @Test
//    fun testSessionInvalid_noHeartbeat_resumeWorks() {
//        val client = TestDiscordClient.fromEnvBotToken {
//            useGateway(Intents.none())
//        }
//    }
    
}