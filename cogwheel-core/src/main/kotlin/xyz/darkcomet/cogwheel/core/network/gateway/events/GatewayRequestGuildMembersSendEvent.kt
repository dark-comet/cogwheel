package xyz.darkcomet.cogwheel.core.network.gateway.events

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import xyz.darkcomet.cogwheel.core.network.gateway.GatewayPayload
import xyz.darkcomet.cogwheel.core.network.gateway.codes.GatewayOpCode
import xyz.darkcomet.cogwheel.core.network.objects.request.GatewayRequestGuildMembersRequestParameters

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