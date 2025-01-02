package xyz.darkcomet.cogwheel.core.network.gateway.impl

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import xyz.darkcomet.cogwheel.core.IntegrationTestFixture
import xyz.darkcomet.cogwheel.core.TestDiscordClient
import xyz.darkcomet.cogwheel.core.events.GatewayHelloEvent
import xyz.darkcomet.cogwheel.core.events.GatewayInvalidSessionEvent
import xyz.darkcomet.cogwheel.core.events.GatewayReadyEvent
import xyz.darkcomet.cogwheel.core.primitives.Intents
import java.net.UnknownHostException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference

class KtorGatewayClientIntegrationTest : IntegrationTestFixture() {
    
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

        withGateway(client) {
            val receivedReady = receiveReadyLatch.await(1000, TimeUnit.SECONDS)
            assertTrue(receivedReady, "Failed to receive READY event after 10 seconds!")

            val receivedHello = receiveHelloLatch.await(1000, TimeUnit.SECONDS)
            assertTrue(receivedHello, "Failed to receive HELLO event after 10 seconds!")
        }
    }
    
    /*
        Test gateway opened with no heartbeat will:
        - Receive an invalid session eventually
        - Will automatically reconnect by re-identify
     */
    @Test
    fun testSessionInvalid_noHeartbeat_resumeWorks() {
        val client = TestDiscordClient.fromEnvBotToken {
            useGateway(Intents.none())
            testDisableGatewayHeartbeats = true
        }

        val receiveInvalidSessionEvent = CountDownLatch(1)
        val invalidSessionEvent = AtomicReference<GatewayInvalidSessionEvent>(null)

        val receiveHelloLatch = CountDownLatch(2)
        val receiveReadyLatch = CountDownLatch(2)
        
        val events = client.events()

        events.subscribe(GatewayHelloEvent::class.java) { receiveHelloLatch.countDown() }
        events.subscribe(GatewayReadyEvent::class.java) { receiveReadyLatch.countDown() }
        
        events.subscribe(GatewayInvalidSessionEvent::class.java) {
            invalidSessionEvent.set(it)
            receiveInvalidSessionEvent.countDown()
        }

        withGateway(client) {
            val receiveInvalidSession = receiveInvalidSessionEvent.await(5, TimeUnit.MINUTES)
            assertTrue(receiveInvalidSession, "Failed to receive INVALID_SESSION event after 5 minutes!")

            assertNotNull(invalidSessionEvent.get())
            assertFalse(invalidSessionEvent.get().shouldTryResume)

            val receivedReady = receiveReadyLatch.await(10, TimeUnit.SECONDS)
            assertTrue(receivedReady, "Failed to receive 2 READY events after 10 seconds!")

            val receivedHello = receiveHelloLatch.await(10, TimeUnit.SECONDS)
            assertTrue(receivedHello, "Failed to receive 2 HELLO events after 10 seconds!")
        }
    }
    
    @Test
    fun testFetchGatewayUrl_encountersUnknownHostException_retriesUntilSuccess() {
        val fetchGatewayUrlInvocations = AtomicInteger(0)
        val sessionAttemptInvocations = AtomicInteger(0)

        val receiveHelloLatch = CountDownLatch(1)
        val receiveReadyLatch = CountDownLatch(1)
        
        val client = TestDiscordClient.fromEnvBotToken {
            useGateway(Intents.none())
            testDisableGatewayHeartbeats = true

            aspects.gateway.fetchGatewayUrlComplete.addAdvice {
                if (fetchGatewayUrlInvocations.get() < 3) {
                    fetchGatewayUrlInvocations.incrementAndGet()
                    throw UnknownHostException("Simulated failure during URL fetch")
                }
            }

            aspects.gateway.connectionAttemptStarted.addAdvice {
                sessionAttemptInvocations.incrementAndGet()
            }
        }
        
        val events = client.events()
        events.subscribe(GatewayHelloEvent::class.java) { receiveHelloLatch.countDown() }
        events.subscribe(GatewayReadyEvent::class.java) { receiveReadyLatch.countDown() }

        withGateway(client) {
            assertTrue(receiveHelloLatch.await(1, TimeUnit.MINUTES), "Failed to receive HELLO event in time")
            assertTrue(receiveReadyLatch.await(1, TimeUnit.MINUTES), "Failed to receive READY event in time")
            assertEquals(1, sessionAttemptInvocations.get(), "Unexpected session attempt count")
        }
    }
}