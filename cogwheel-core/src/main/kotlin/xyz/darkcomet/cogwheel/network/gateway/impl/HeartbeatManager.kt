package xyz.darkcomet.cogwheel.network.gateway.impl

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import xyz.darkcomet.cogwheel.network.CancellationToken
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random

internal class HeartbeatManager(
    private val sessionId: String,
    private val heartbeatIntervalMs: Long,
    private val client: KtorGatewayClient,
    private val lastReceivedSequenceNumber: AtomicInteger,
    private val sessionCancellation: CancellationToken
) {
    private val logger: Logger = LoggerFactory.getLogger(HeartbeatManager::class.java)
    private var firstHeartbeat = true
    
    fun beginBackgroundHeartbeats() {
        val heartbeatThread = Thread {
            performHeartbeat()
        }
        
        heartbeatThread.start()
    }

    private fun performHeartbeat() {
        logger.trace("Begin heartbeat loop at {}ms intervals, sessionId: {}", heartbeatIntervalMs, sessionId)

        while (!sessionCancellation.isCanceled()) {
            val delayMs: Long

            if (firstHeartbeat) {
                val jitter = Random.nextFloat()
                delayMs = ((heartbeatIntervalMs.toDouble()) * jitter).toLong() - 8_000
                firstHeartbeat = false
            } else {
                delayMs = heartbeatIntervalMs - 5_000 /* Safety margin to account for network latency */
            }

            for (step in 1..delayMs) {
                Thread.sleep(1)

                if (sessionCancellation.isCanceled()) {
                    break
                }
            }

            logger.trace("Gateway heartbeat, delay: {}ms", delayMs)
            client.heartbeat(lastReceivedSequenceNumber.get())
        }
    }
}