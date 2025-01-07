package xyz.darkcomet.cogwheel.core.network.gateway.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import xyz.darkcomet.cogwheel.core.impl.authentication.Token
import xyz.darkcomet.cogwheel.core.network.gateway.GatewayPayload
import xyz.darkcomet.cogwheel.core.network.gateway.codes.GatewayOpCode
import xyz.darkcomet.cogwheel.core.network.objects.*
import xyz.darkcomet.cogwheel.core.primitives.Intents

internal interface GatewaySendEvent {
    fun asPayload() : GatewayPayload
}

internal class GatewayHeartbeatSendEvent(
    private val lastReceivedSequenceNumber: Int
) : GatewaySendEvent {
    
    override fun asPayload(): GatewayPayload {
        return GatewayPayload(
            op = GatewayOpCode.HEARTBEAT.code,
            d = Json.encodeToJsonElement(lastReceivedSequenceNumber)
        )
    }
    
}

internal class GatewayIdentifySendEvent(
    private val token: Token,
    private val intents: Intents,
    private val libName: String
) : GatewaySendEvent {

    override fun asPayload(): GatewayPayload {
        val data = DataObject(
            token = token.value,
            properties = DataObject.ConnectionPropertiesObject(
                os = System.getProperty("os.name"),
                browser = libName,
                device = libName
            ),
            intents = intents.value
        )

        return GatewayPayload(
            op = GatewayOpCode.IDENTIFY.code,
            d = Json.encodeToJsonElement(DataObject.serializer(), data)
        )
    }

    @Serializable
    data class DataObject(
        val token: String,
        val properties: ConnectionPropertiesObject,
        val compress: Boolean? = null,
        @SerialName("large_threshold") val largeThreshold: Int? = null,
        val shard: List<Int>? = null,
        val presence: UpdatePresenceObject? = null,
        val intents: Int
    ) {

        @Serializable
        data class ConnectionPropertiesObject(
            val os: String,
            val browser: String,
            val device: String
        )

    }

}

internal class GatewayRequestGuildMembersSendEvent(
    private val request: GatewayRequestGuildMembersRequestParameters
) : GatewaySendEvent {

    override fun asPayload(): GatewayPayload {
        return GatewayPayload(
            op = GatewayOpCode.REQUEST_GUILD_MEMBERS.code,
            d = Json.encodeToJsonElement(request)
        )
    }

}

internal class GatewayRequestSoundboardSoundsSendEvent(
    private val request: GatewayRequestSoundboardSoundsRequestParameters
) : GatewaySendEvent {

    override fun asPayload(): GatewayPayload {
        return GatewayPayload(
            op = GatewayOpCode.REQUEST_SOUNDBOARD_SOUNDS.code,
            d = Json.encodeToJsonElement(request)
        )
    }

}

internal class GatewayResumeSendEvent(
    private val token: Token,
    private val sessionId: String,
    private val lastReceivedSequenceNumber: Int,
) : GatewaySendEvent {

    override fun asPayload(): GatewayPayload {
        val data = DataObject(token.value, sessionId, lastReceivedSequenceNumber)

        return GatewayPayload(
            op = GatewayOpCode.RESUME.code,
            d = Json.encodeToJsonElement(DataObject.serializer(), data)
        )
    }

    @Serializable
    data class DataObject(
        val token: String,
        @SerialName("session_id") val sessionId: String,
        val seq: Int
    )

}

internal class GatewayUpdatePresenceSendEvent(
    private val request: GatewayPresenceUpdateRequestParameters
) : GatewaySendEvent {

    override fun asPayload(): GatewayPayload {
        return GatewayPayload(
            op = GatewayOpCode.PRESENCE_UPDATE.code,
            d = Json.encodeToJsonElement(request)
        )
    }

}

internal class GatewayUpdateVoiceStateSendEvent(
    private val request: GatewayVoiceStateUpdateRequestParameters
) : GatewaySendEvent {

    override fun asPayload(): GatewayPayload {
        return GatewayPayload(
            op = GatewayOpCode.VOICE_STATE_UPDATE.code,
            d = Json.encodeToJsonElement(request)
        )
    }

}