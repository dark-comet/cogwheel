package xyz.darkcomet.cogwheel.core

import kotlinx.coroutines.runBlocking

abstract class IntegrationTestFixture {
    companion object {
        fun runTest(client: DiscordClient, assertions: () -> Unit) {
            val clientThread = Thread {
                runBlocking {
                    client.startGatewayConnection()
                }
            }

            clientThread.start()

            try {
                assertions.invoke()
            } finally {
                client.stop()
                clientThread.join()
            }
        }
    }
}