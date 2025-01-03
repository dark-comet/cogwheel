package xyz.darkcomet.cogwheel.core

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import xyz.darkcomet.cogwheel.core.events.GatewayReadyEvent
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean

abstract class IntegrationTestFixture {
    companion object {
        fun withGateway(client: DiscordClient, testCode: suspend () -> Unit) {
            val gatewayAwaiter = CountDownLatch(1)
            val gatewayShutdownOk = AtomicBoolean(false)

            val gatewayReadyListener: (GatewayReadyEvent) -> Unit = { event ->
                runBlocking {
                    for (guild in event.data.guilds) {
                        launch {
                            val response = client.restApi().guild.deleteGuild(guild.id)
                            println("Delete owned guild: " + guild.id + ", status: " + response.raw.statusCode)
                        }
                    }
                }

                gatewayAwaiter.countDown()
            }
            client.events().subscribe(GatewayReadyEvent::class.java, gatewayReadyListener)

            val gatewayThread = Thread {
                runBlocking {
                    client.startGatewayConnection()
                    gatewayShutdownOk.set(true)
                }
            }
            gatewayThread.start()

            assertTrue(gatewayAwaiter.await(30, TimeUnit.SECONDS), "Timed out waiting for gateway startup")
            client.events().unsubscribe(GatewayReadyEvent::class.java, gatewayReadyListener)

            try {
                runBlocking {
                    testCode.invoke()
                }
            } finally {
                client.stop()
                gatewayThread.join(60_000L)
                assertTrue(gatewayShutdownOk.get(), "Failed to shutdown gateway connection on test end")
            }
        }
    }
}