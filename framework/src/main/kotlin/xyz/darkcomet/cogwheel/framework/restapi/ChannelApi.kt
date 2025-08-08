@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "RedundantOverride", "unused")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.ChannelResource
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.Channel
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.FollowedChannel
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.ThreadMember
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.toModel
import xyz.darkcomet.cogwheel.framework.models.entitles.invite.Invite
import xyz.darkcomet.cogwheel.framework.models.entitles.invite.toModel
import xyz.darkcomet.cogwheel.framework.models.request.*
import xyz.darkcomet.cogwheel.framework.models.response.ListJoinedPrivateArchivedThreadsResponse
import xyz.darkcomet.cogwheel.framework.models.response.ListPrivateArchivedThreadsResponse
import xyz.darkcomet.cogwheel.framework.models.response.ListPublicArchivedThreadsResponse
import xyz.darkcomet.cogwheel.framework.models.response.toModel
import xyz.darkcomet.cogwheel.framework.primitives.*

class ChannelApi
internal constructor(resource: ChannelResource) {
    
    @JvmField
    val get = object : Invocation1<ChannelId, Channel>() {
        
        override suspend fun invoke(p1: ChannelId): Response<Channel> {
            val response = resource.getChannel(p1.snowflake)
            val result = response.data?.toModel()

            return Response(result, response)
        }
    }

    @JvmField
    val modifyDm = object : RequestInvocation1S<ChannelId, ModifyDmChannelRequestSpec, Channel>() {
        
        override fun createRequest(p1: ChannelId): ModifyDmChannelRequestSpec {
            return ModifyDmChannelRequestSpec(p1)
        }

        override suspend fun invoke(request: ModifyDmChannelRequestSpec): Response<Channel> {
            val response = resource.modifyDmChannel(
                request.channelId.snowflake,
                request.buildParameters(),
                request.auditLogReason
            )
            val result = response.data?.toModel()
            
            return Response(result, response)
        }

    }
    
    @JvmField
    val modifyGuild = object : RequestInvocation1S<ChannelId, ModifyGuildChannelRequestSpec, Channel>() {
        
        override fun createRequest(p1: ChannelId): ModifyGuildChannelRequestSpec {
            return ModifyGuildChannelRequestSpec(p1)
        }

        override suspend fun invoke(request: ModifyGuildChannelRequestSpec): Response<Channel> {
            val response = resource.modifyGuildChannel(
                request.channelId.snowflake, 
                request.buildParameters(),
                request.auditLogReason
            )
            val result = response.data?.toModel()
            
            return Response(result, response)
        }
    }
    
    @JvmField
    val modifyThread = object : RequestInvocation1S<ChannelId, ModifyThreadChannelRequestSpec, Channel>() {
        
        override fun createRequest(p1: ChannelId): ModifyThreadChannelRequestSpec {
            return ModifyThreadChannelRequestSpec(p1)
        }

        override suspend fun invoke(request: ModifyThreadChannelRequestSpec): Response<Channel> {
            val response = resource.modifyThreadChannel(
                request.channelId.snowflake,
                request.buildParameters(),
                request.auditLogReason
            )
            val result = response.data?.toModel()
            
            return Response(result, response)
        }
    }
    
    @JvmField
    val deleteOrClose = object : RequestInvocation1S<ChannelId, DeleteOrCloseChannelRequestSpec, Channel>() {
        
        override fun createRequest(p1: ChannelId): DeleteOrCloseChannelRequestSpec {
            return DeleteOrCloseChannelRequestSpec(p1)
        }

        override suspend fun invoke(request: DeleteOrCloseChannelRequestSpec): Response<Channel> {
            val response = resource.deleteOrCloseChannel(
                request.channelId.snowflake,
                request.auditLogReason
            )
            val result = response.data?.toModel()
            
            return Response(result, response)
        }
    }
    
    @JvmField
    val editPermissions = object : RequestInvocation2S<ChannelId, Snowflake, EditChannelPermissionsSpec, Boolean>() {
        
        override fun createRequest(
            p1: ChannelId,
            p2: Snowflake
        ): EditChannelPermissionsSpec {
            
            return EditChannelPermissionsSpec(p1, p2)
        }

        override suspend fun invoke(request: EditChannelPermissionsSpec): Response<Boolean> {
            val response = resource.editChannelPermissions(
                request.channelId.snowflake,
                request.overwriteId,
                request.buildParameters(),
                request.auditLogReason
            )
            val result = response.raw.success
            
            return Response(result, response)
        }

    }
    
    @JvmField
    val getInvites = object : Invocation1<ChannelId, List<Invite>>() {
        
        override suspend fun invoke(p1: ChannelId): Response<List<Invite>> {
            val response = resource.getChannelInvites(p1.snowflake)
            val result = response.data?.map { it.toModel() }
            
            return Response(result, response)
        }

    }
    
    @JvmField
    val createInvite = object : RequestInvocation1S<ChannelId, CreateChannelInviteRequestSpec, Invite>() {
        
        override fun createRequest(p1: ChannelId): CreateChannelInviteRequestSpec {
            return CreateChannelInviteRequestSpec(p1)
        }

        override suspend fun invoke(request: CreateChannelInviteRequestSpec): Response<Invite> {
            val response = resource.createChannelInvite(
                request.channelId.snowflake,
                request.buildParameters(),
                request.auditLogReason
            )
            val result = response.data?.toModel()
            
            return Response(result, response)
        }

    }
    
    @JvmField
    val deletePermission = object : RequestInvocation2S<ChannelId, Snowflake, DeleteChannelPermissionRequestSpec, Boolean>() {
        
        override fun createRequest(
            p1: ChannelId,
            p2: Snowflake
        ): DeleteChannelPermissionRequestSpec {
            return DeleteChannelPermissionRequestSpec(p1, p2)
        }

        override suspend fun invoke(request: DeleteChannelPermissionRequestSpec): Response<Boolean> {
            val response = resource.deleteChannelPermission(
                request.channelId.snowflake,
                request.overwriteId,
                request.auditLogReason
            )
            val result = response.raw.success
            
            return Response(result, response)
        }

    }
    
    @JvmField
    val followAnnouncements = object : RequestInvocation1S<ChannelId, FollowAnnouncementChannelRequestSpec, FollowedChannel>() {
        
        override fun createRequest(p1: ChannelId): FollowAnnouncementChannelRequestSpec {
            return FollowAnnouncementChannelRequestSpec(p1)
        }

        override suspend fun invoke(request: FollowAnnouncementChannelRequestSpec): Response<FollowedChannel> {
            val response = resource.followAnnouncementChannel(
                request.channelId.snowflake,
                request.buildParameters(),
                request.auditLogReason
            )
            val result = response.data?.toModel()
            
            return Response(result, response)
        }

    }
    
    @JvmField
    val triggerTypingIndicator = object : Invocation1<ChannelId, Boolean>() {
        
        override suspend fun invoke(p1: ChannelId): Response<Boolean> {
            val response = resource.triggerTypingIndicator(p1.snowflake)
            val result = response.raw.success
            
            return Response(result, response)
        }

    }
    
    @JvmField
    val groupDmAddRecipient = object : RequestInvocation2S<ChannelId, UserId, GroupDmAddRecipientRequestSpec, Boolean>() {
        
        override fun createRequest(
            p1: ChannelId,
            p2: UserId
        ): GroupDmAddRecipientRequestSpec {
            
            return GroupDmAddRecipientRequestSpec(p1, p2)
        }

        override suspend fun invoke(request: GroupDmAddRecipientRequestSpec): Response<Boolean> {
            val response = resource.groupDmAddRecipient(
                request.channelId.snowflake,
                request.userId.snowflake,
                request.buildParameters()
            )
            val result = response.raw.success
            
            return Response(result, response)
        }

    }
    
    @JvmField
    val groupDmRemoveRecipient = object : Invocation2<ChannelId, UserId, Boolean>() {
        
        override suspend fun invoke(
            p1: ChannelId,
            p2: UserId
        ): Response<Boolean> {
            val response = resource.groupDmRemoveRecipient(p1.snowflake, p2.snowflake)
            val result = response.raw.success
            
            return Response(result, response)
        }

    }
    
    @JvmField
    val startThreadFromMessage = object : RequestInvocation2S<ChannelId, MessageId, StartThreadFromMessageRequestSpec, Channel>() {
        
        override fun createRequest(
            p1: ChannelId,
            p2: MessageId
        ): StartThreadFromMessageRequestSpec {
            
            return StartThreadFromMessageRequestSpec(p1, p2)
        }

        override suspend fun invoke(request: StartThreadFromMessageRequestSpec): Response<Channel> {
            val response = resource.startThreadFromMessage(
                request.channelId.snowflake,
                request.messageId.snowflake,
                request.buildParameters(),
                request.auditLogReason
            )
            val result = response.data?.toModel()
            
            return Response(result, response)
        }

    }
    
    @JvmField
    val startThreadWithoutMessage = object : RequestInvocation1S<ChannelId, StartThreadWithoutMessageRequestSpec, Channel>() {
        
        override fun createRequest(p1: ChannelId): StartThreadWithoutMessageRequestSpec {
            return StartThreadWithoutMessageRequestSpec(p1)
        }

        override suspend fun invoke(request: StartThreadWithoutMessageRequestSpec): Response<Channel> {
            val response = resource.startThreadWithoutMessage(
                request.channelId.snowflake,
                request.buildParameters(),
                request.auditLogReason
            )
            val result = response.data?.toModel()
            
            return Response(result, response)
        }

    }
    
    @JvmField
    val startThreadInForumOrMedia = object : RequestInvocation1S<ChannelId, StartThreadInForumOrMediaChannelRequestSpec, Channel>() {
        
        override fun createRequest(p1: ChannelId): StartThreadInForumOrMediaChannelRequestSpec {
            return StartThreadInForumOrMediaChannelRequestSpec(p1)
        }

        override suspend fun invoke(request: StartThreadInForumOrMediaChannelRequestSpec): Response<Channel> {
            val response = resource.startThreadInFormOrMediaChannel(
                request.channelId.snowflake,
                request.buildParameters(),
                request.auditLogReason
            )
            val result = response.data?.toModel()
            
            return Response(result, response)
        }

    }
    
    @JvmField
    val joinThread = object : Invocation1<ChannelId, Boolean>() {
        
        override suspend fun invoke(p1: ChannelId): Response<Boolean> {
            val response = resource.joinThread(p1.snowflake)
            val result = response.raw.success
            
            return Response(result, response)
        }

    }
    
    @JvmField
    val addThreadMember = object : Invocation2<ChannelId, UserId, Boolean>() {
        
        override suspend fun invoke(
            p1: ChannelId,
            p2: UserId
        ): Response<Boolean> {
            val response = resource.addThreadMember(p1.snowflake, p2.snowflake)
            val result = response.raw.success
            
            return Response(result, response)
        }

    }
    
    @JvmField
    val leaveThread = object : Invocation1<ChannelId, Boolean>() {
        
        override suspend fun invoke(p1: ChannelId): Response<Boolean> {
            val response = resource.leaveThread(p1.snowflake)
            val result = response.raw.success
            
            return Response(result, response)
        }

    }
    
    @JvmField
    val removeThreadMember = object : Invocation2<ChannelId, UserId, Boolean>() {
        
        override suspend fun invoke(
            p1: ChannelId,
            p2: UserId
        ): Response<Boolean> {
            val response = resource.removeThreadMember(p1.snowflake, p2.snowflake)
            val result = response.raw.success
            
            return Response(result, response)
        }

    }
    
    @JvmField
    val getThreadMember = object : RequestInvocation2S<ChannelId, UserId, GetThreadMemberRequestSpec, ThreadMember>() {
        
        override fun createRequest(
            p1: ChannelId,
            p2: UserId
        ): GetThreadMemberRequestSpec {
            return GetThreadMemberRequestSpec(p1, p2)
        }

        override suspend fun invoke(request: GetThreadMemberRequestSpec): Response<ThreadMember> {
            val response = resource.getThreadMember(
                request.channelId.snowflake,
                request.userId.snowflake,
                request.withMember
            )
            val result = response.data?.toModel()
            
            return Response(result, response)
        }

    }
    
    @JvmField
    val listThreadMembers = object : RequestInvocation1S<ChannelId, ListThreadMembersRequestSpec, List<ThreadMember>>() {
        
        override fun createRequest(p1: ChannelId): ListThreadMembersRequestSpec {
            return ListThreadMembersRequestSpec(p1)
        }

        override suspend fun invoke(request: ListThreadMembersRequestSpec): Response<List<ThreadMember>> {
            val response = resource.listThreadMembers(
                request.channelId.snowflake,
                request.withMember,
                request.after?.snowflake,
                request.limit
            )
            val result = response.data?.map { it.toModel() }
            
            return Response(result, response)
        }

    }
    
    @JvmField
    val listPublicArchivedThreads = object : RequestInvocation1S<ChannelId, ListPublicArchivedThreadsRequestSpec, ListPublicArchivedThreadsResponse>() {
        
        override fun createRequest(p1: ChannelId): ListPublicArchivedThreadsRequestSpec {
            return ListPublicArchivedThreadsRequestSpec(p1)
        }

        override suspend fun invoke(request: ListPublicArchivedThreadsRequestSpec): Response<ListPublicArchivedThreadsResponse> {
            val response = resource.listPublicArchivedThreads(
                request.channelId.snowflake,
                request.before,
                request.limit
            )
            val result = response.data?.toModel()
            
            return Response(result, response)
        }

    }
    
    @JvmField
    val listPrivateArchivedThreads = object : RequestInvocation1S<ChannelId, ListPrivateArchivedThreadsRequestSpec, ListPrivateArchivedThreadsResponse>() {
        
        override fun createRequest(p1: ChannelId): ListPrivateArchivedThreadsRequestSpec {
            return ListPrivateArchivedThreadsRequestSpec(p1)
        }

        override suspend fun invoke(request: ListPrivateArchivedThreadsRequestSpec): Response<ListPrivateArchivedThreadsResponse> {
            val response = resource.listPrivateArchivedThreads(
                request.channelId.snowflake,
                request.before,
                request.limit
            )
            val result = response.data?.toModel()

            return Response(result, response)
        }
        
    }

    @JvmField
    val listJoinedPrivateArchivedThreads = object : RequestInvocation1S<ChannelId, ListJoinedPrivateArchivedThreadsRequestSpec, ListJoinedPrivateArchivedThreadsResponse>() {

        override fun createRequest(p1: ChannelId): ListJoinedPrivateArchivedThreadsRequestSpec {
            return ListJoinedPrivateArchivedThreadsRequestSpec(p1)
        }

        override suspend fun invoke(request: ListJoinedPrivateArchivedThreadsRequestSpec): Response<ListJoinedPrivateArchivedThreadsResponse> {
            val response = resource.listJoinedPrivateArchivedThreads(
                request.channelId.snowflake,
                request.before,
                request.limit
            )
            val result = response.data?.toModel()
            
            return Response(result, response)
        }

    }
    
}