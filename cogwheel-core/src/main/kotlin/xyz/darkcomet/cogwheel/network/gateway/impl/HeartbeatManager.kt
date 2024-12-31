package xyz.darkcomet.cogwheel.network.gateway.impl

import io.ktor.client.plugins.websocket.*
import kotlinx.coroutines.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.min
import kotlin.random.Random

internal class HeartbeatManager(
    private val sessionId: String,
    private val heartbeatIntervalMs: Long,
    private val client: KtorGatewayClient,
    private val lastReceivedSequenceNumber: AtomicInteger
) {
    private val logger: Logger = LoggerFactory.getLogger(HeartbeatManager::class.java)
    
    fun beginBackgroundHeartbeats(session: DefaultClientWebSocketSession) {
        val heartbeatThread = Thread() {
            performHeartbeat(session, HeartbeatSession())
        }
        
        heartbeatThread.start()
    }

    private fun performHeartbeat(coroutineScope: CoroutineScope, session: HeartbeatSession) {
        logger.trace("Begin heartbeat loop at {}ms intervals, sessionId: {}", heartbeatIntervalMs, sessionId)
        
        heartbeatLoop@ while (coroutineScope.isActive) {
            val delayMs: Long

            if (session.firstHeartbeat) {
                val jitter = Random.nextFloat()
                delayMs = min(4000L, ((heartbeatIntervalMs.toDouble()) * jitter).toLong() - 8_000L)
                session.firstHeartbeat = false
            } else {
                delayMs = heartbeatIntervalMs - 5_000 /* Safety margin to account for network latency */
            }

            for (step in 1..delayMs) {
                Thread.sleep(1L)

                if (!coroutineScope.isActive) {
                    break@heartbeatLoop
                }
            }

            logger.trace("Gateway heartbeat, delay: {}ms", delayMs)
            client.heartbeat(lastReceivedSequenceNumber.get())
        }
    }
    
    private class HeartbeatSession(var firstHeartbeat: Boolean = true)
}