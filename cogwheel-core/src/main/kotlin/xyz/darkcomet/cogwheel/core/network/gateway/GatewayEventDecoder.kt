package xyz.darkcomet.cogwheel.core.network.gateway

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import xyz.darkcomet.cogwheel.core.events.*
import xyz.darkcomet.cogwheel.core.network.objects.GatewayHelloEventDataObject
import xyz.darkcomet.cogwheel.core.network.objects.GatewayReadyEventDataObject
import xyz.darkcomet.cogwheel.core.network.objects.GuildObject
import xyz.darkcomet.cogwheel.core.network.gateway.codes.GatewayOpCode
import xyz.darkcomet.cogwheel.core.network.gateway.codes.GatewayReceivePayloadName
import kotlin.reflect.typeOf

internal class GatewayEventDecoder private constructor() {
    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(GatewayEventDecoder::class.java)
        private val JSON_DESERIALIZER = Json { ignoreUnknownKeys = true }
        
        fun decode(payload: GatewayPayload): Event? {
            return when (payload.op) {
                GatewayOpCode.DISPATCH.code -> decodeDispatchEvent(payload)
                GatewayOpCode.IDENTIFY.code -> unsupportedDecode(payload)
                GatewayOpCode.HELLO.code -> decodeWithData<GatewayHelloEventDataObject, GatewayHelloEvent>(payload) { GatewayHelloEvent(it) }
                GatewayOpCode.RECONNECT.code -> GatewayReconnectEvent()
                GatewayOpCode.INVALID_SESSION.code -> GatewayInvalidSessionEvent(shouldTryResume = JSON_DESERIALIZER.decodeFromJsonElement(payload.d!!))
                GatewayOpCode.HEARTBEAT.code -> GatewayHeartbeatEvent()
                GatewayOpCode.HEARTBEAT_ACK.code -> GatewayHeartbeatAckEvent()
                else -> null
            }
        }

        private fun decodeDispatchEvent(payload: GatewayPayload): Event? {
            return when (payload.t) {
                GatewayReceivePayloadName.READY.name -> decodeWithData<GatewayReadyEventDataObject, GatewayReadyEvent>(payload) { GatewayReadyEvent(it) }
                GatewayReceivePayloadName.RESUMED.name -> GatewayResumedEvent()
                GatewayReceivePayloadName.GUILD_CREATE.name -> decodeWithData<GuildObject, GuildCreateEvent>(payload) { GuildCreateEvent(it) }
                GatewayReceivePayloadName.GUILD_UPDATE.name -> decodeWithData<GuildObject, GuildUpdateEvent>(payload) { GuildUpdateEvent(it) }
                GatewayReceivePayloadName.GUILD_DELETE.name -> decodeWithData<GuildObject, GuildDeleteEvent>(payload) { GuildDeleteEvent(it) }
                else -> null
            }
        }

        private fun unsupportedDecode(payload: GatewayPayload): Event? {
            throw IllegalArgumentException("Not expected to receive event with op: ${payload.op}")
        }

        private inline fun <reified TData, TResult> decodeWithData(
            payload: GatewayPayload,
            resultFactory: (TData) -> TResult
        ): TResult? {
            if (payload.d == null) {
                LOGGER.warn("Error parsing payload (op: {}) to type {}: 'd' == null", payload.op, typeOf<TData>())
                return null;
            }

            val eventData = JSON_DESERIALIZER.decodeFromJsonElement<TData>(payload.d)
            return resultFactory.invoke(eventData)
        }
    }
}