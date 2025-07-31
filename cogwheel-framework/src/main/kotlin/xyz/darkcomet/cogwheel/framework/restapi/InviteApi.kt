@file:Suppress("RedundantOverride", "PARAMETER_NAME_CHANGED_ON_OVERRIDE", "unused")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.InviteResource
import xyz.darkcomet.cogwheel.framework.models.entitles.invite.Invite
import xyz.darkcomet.cogwheel.framework.models.entitles.invite.toModel
import xyz.darkcomet.cogwheel.framework.models.request.GetInviteRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.Invocation1
import xyz.darkcomet.cogwheel.framework.primitives.RequestInvocation1S
import xyz.darkcomet.cogwheel.framework.primitives.Response
import java.util.concurrent.Future
import java.util.function.Consumer

class InviteApi
internal constructor(resource: InviteResource) {
    
    @JvmField
    val get = GetInviteEndpoint(resource)
    
    @JvmField
    val delete = DeleteInviteEndpoint(resource)
    
}

class GetInviteEndpoint
internal constructor(private val resource: InviteResource)
    : RequestInvocation1S<String, GetInviteRequestSpec, Invite>() {
        
    override fun createRequest(inviteCode: String): GetInviteRequestSpec {
        return GetInviteRequestSpec(inviteCode)
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

    override suspend fun invoke(
        inviteCode: String,
        request: GetInviteRequestSpec?,
        config: (GetInviteRequestSpec.() -> Unit)?
    ): Response<Invite> {
        return super.invoke(inviteCode, request, config)
    }

    override fun async(
        inviteCode: String,
        config: Consumer<GetInviteRequestSpec>?
    ): Future<Response<Invite>> {
        return super.async(inviteCode, config)
    }

    override fun block(
        inviteCode: String,
        config: Consumer<GetInviteRequestSpec>?
    ): Response<Invite> {
        return super.block(inviteCode, config)
    }
}

class DeleteInviteEndpoint 
internal constructor(private val resource: InviteResource)
    : Invocation1<String, Invite>() {
        
    override suspend fun invoke(inviteCode: String): Response<Invite> {
        val response = resource.deleteInvite(inviteCode)
        val result = response.data?.toModel()
        
        return Response(result, response)
    }

    override fun async(inviteCode: String): Future<Response<Invite>> {
        return super.async(inviteCode)
    }

    override fun block(inviteCode: String): Response<Invite> {
        return super.block(inviteCode)
    }
}