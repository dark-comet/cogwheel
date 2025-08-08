@file:Suppress("RedundantOverride", "PARAMETER_NAME_CHANGED_ON_OVERRIDE", "unused")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.InviteResource
import xyz.darkcomet.cogwheel.framework.models.entitles.invite.Invite
import xyz.darkcomet.cogwheel.framework.models.entitles.invite.toModel
import xyz.darkcomet.cogwheel.framework.models.request.GetInviteRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.Invocation1
import xyz.darkcomet.cogwheel.framework.primitives.RequestInvocation1S
import xyz.darkcomet.cogwheel.framework.primitives.Response

class InviteApi
internal constructor(resource: InviteResource) {
    
    @JvmField
    val get = object : RequestInvocation1S<String, GetInviteRequestSpec, Invite>() {
        
        override fun createRequest(p1: String): GetInviteRequestSpec {
            return GetInviteRequestSpec(p1)
        }

        override suspend fun invoke(request: GetInviteRequestSpec): Response<Invite> {
            val response = resource.getInvite(
                request.inviteCode,
                request.withCounts,
                request.withExpiration,
                request.guildScheduledEventId
            )
            val result = response.data?.toModel()

            return Response(result, response)
        }

    }
    
    @JvmField
    val delete = object : Invocation1<String, Invite>() {
        
        override suspend fun invoke(inviteCode: String): Response<Invite> {
            val response = resource.deleteInvite(inviteCode)
            val result = response.data?.toModel()

            return Response(result, response)
        }

    }
    
}