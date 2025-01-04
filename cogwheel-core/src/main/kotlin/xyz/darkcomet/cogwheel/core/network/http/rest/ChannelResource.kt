package xyz.darkcomet.cogwheel.core.network.http.rest

import kotlinx.serialization.builtins.ListSerializer
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.*
import xyz.darkcomet.cogwheel.core.network.objects.request.*
import xyz.darkcomet.cogwheel.core.network.objects.response.ListJoinedPrivateArchivedThreadsResponseObject
import xyz.darkcomet.cogwheel.core.network.objects.response.ListPrivateArchivedThreadsResponseObject
import xyz.darkcomet.cogwheel.core.network.objects.response.ListPublicArchivedThreadsResponseObject
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class ChannelResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun getChannel(channelId: Snowflake): CwHttpResponse<ChannelObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/channels/${channelId}")
        val response = httpClient.submit(httpRequest)

        return response.withData(ChannelObject.serializer())
    }

    suspend fun modifyDmChannel(
        channelId: Snowflake,
        request: ModifyDmChannelRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<ChannelObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/channels/${channelId}") {
            jsonParams(request, ModifyDmChannelRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ChannelObject.serializer())
    }
    
    suspend fun modifyGuildChannel(
        channelId: Snowflake,
        request: ModifyGuildChannelRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<ChannelObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/channels/${channelId}") {
            jsonParams(request, ModifyGuildChannelRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ChannelObject.serializer())
    }

    suspend fun modifyThreadChannel(
        channelId: Snowflake,
        request: ModifyThreadChannelRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<ChannelObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/channels/${channelId}") {
            jsonParams(request, ModifyThreadChannelRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ChannelObject.serializer())
    }

    suspend fun deleteOrCloseChannel(
        channelId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<ChannelObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.DELETE, "/channels/${channelId}")
        val response = httpClient.submit(httpRequest)

        return response.withData(ChannelObject.serializer())
    }
    
    suspend fun editChannelPermissions(
        channelId: Snowflake,
        overwriteId: Snowflake,
        request: EditChannelPermissionsParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PUT, "/channels/${channelId}/permissions/${overwriteId}") {
            jsonParams(request, EditChannelPermissionsParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
    suspend fun getChannelInvites(channelId: Snowflake) : CwHttpResponse<List<InviteObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/channels/${channelId}/invites")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(InviteObject.serializer()))
    }
    
    suspend fun createChannelInvite(
        channelId: Snowflake,
        request: CreateChannelInviteRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<InviteObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/channels/${channelId}/invites") {
            jsonParams(request, CreateChannelInviteRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(InviteObject.serializer())
    }
    
    suspend fun deleteChannelPermission(
        channelId: Snowflake, 
        overwriteId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.DELETE, "/channels/${channelId}/permissions/${overwriteId}") {
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
    suspend fun followAnnouncementChannel(
        channelId: Snowflake,
        request: FollowAnnouncementChannelRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<FollowedChannelObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/channels/${channelId}/followers") {
            jsonParams(request, FollowAnnouncementChannelRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(FollowedChannelObject.serializer())
    }
    
    suspend fun triggerTypingIndicator(channelId: Snowflake): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/channels/${channelId}/typing")
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
    suspend fun getPinnedMessages(channelId: Snowflake): CwHttpResponse<List<MessageObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/channels/${channelId}/pins")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(MessageObject.serializer()))
    }
    
    suspend fun pinMessage(
        channelId: Snowflake, 
        messageId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PUT, "/channels/${channelId}/pins/${messageId}") {
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
    suspend fun unpinMessage(
        channelId: Snowflake, 
        messageId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.DELETE, "/channels/${channelId}/pins/${messageId}") {
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
    suspend fun groupDmAddRecipient(
        channelId: Snowflake, 
        userId: Snowflake, 
        request: GroupDmAddRecipientRequestParameters
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PUT, "/channels/${channelId}/recipients/${userId}") {
            jsonParams(request, GroupDmAddRecipientRequestParameters.serializer())
        }
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }

    suspend fun groupDmRemoveRecipient(
        channelId: Snowflake, 
        userId: Snowflake
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.DELETE, "/channels/${channelId}/recipients/${userId}")
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
    suspend fun startThreadFromMessage(
        channelId: Snowflake,
        messageId: Snowflake,
        request: StartThreadFromMessageRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<ChannelObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/channels/${channelId}/messages/${messageId}/threads") {
            jsonParams(request, StartThreadFromMessageRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ChannelObject.serializer())
    }
    
    suspend fun startThreadWithoutMessage(
        channelId: Snowflake,
        request: StartThreadWithoutMessageRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<ChannelObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/channels/${channelId}/threads") {
            jsonParams(request, StartThreadWithoutMessageRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ChannelObject.serializer())
    }
    
    suspend fun startThreadInFormOrMediaChannel(
        channelId: Snowflake,
        request: StartThreadInForumOrMediaChannelRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<ChannelObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/channels/${channelId}/threads") {
            jsonParams(request, StartThreadInForumOrMediaChannelRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ChannelObject.serializer())
    }
    
    suspend fun joinThread(channelId: Snowflake): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PUT, "/channels/${channelId}/thread-members/@me")
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
    suspend fun addThreadMember(
        channelId: Snowflake, 
        userId: Snowflake
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PUT, "/channels/${channelId}/thread-members/${userId}")
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
    suspend fun leaveThread(channelId: Snowflake): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.DELETE, "/channels/${channelId}/thread-members/@me")
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
    suspend fun removeThreadMember(
        channelId: Snowflake, 
        userId: Snowflake
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.DELETE, "/channels/${channelId}/thread-members/${userId}")
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
    suspend fun getThreadMember(
        channelId: Snowflake, 
        userId: Snowflake, 
        withMember: Boolean? = null
    ): CwHttpResponse<ThreadMemberObject?> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/channels/${channelId}/thread-members/${userId}") {
            optionalQueryStringParam("with_member", withMember)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ThreadMemberObject.serializer())
    }

    suspend fun listThreadMembers(
        channelId: Snowflake, 
        withMember: Boolean? = null,
        after: Snowflake? = null, 
        limit: Int? = null
    ): CwHttpResponse<List<ThreadMemberObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/channels/${channelId}/thread-members") {
            optionalQueryStringParam("with_member", withMember)
            optionalQueryStringParam("after", after)
            optionalQueryStringParam("limit", limit)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(ThreadMemberObject.serializer()))
    }
    
    suspend fun listPublicArchivedThreads(
        channelId: Snowflake,
        before: ISO8601Timestamp? = null,
        limit: Int? = null
    ): CwHttpResponse<ListPublicArchivedThreadsResponseObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/channels/${channelId}/threads/archived/public") {
            optionalQueryStringParam("before", before)
            optionalQueryStringParam("limit", limit)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ListPublicArchivedThreadsResponseObject.serializer())
    }
    
    suspend fun listPrivateArchivedThreads(
        channelId: Snowflake,
        before: ISO8601Timestamp? = null,
        limit: Int? = null
    ): CwHttpResponse<ListPrivateArchivedThreadsResponseObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/channels/${channelId}/threads/archived/private") {
            optionalQueryStringParam("before", before)
            optionalQueryStringParam("limit", limit)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ListPrivateArchivedThreadsResponseObject.serializer())
    }
    
    suspend fun listJoinedPrivateArchivedThreads(
        channelId: Snowflake,
        before: ISO8601Timestamp? = null,
        limit: Int? = null
    ): CwHttpResponse<ListJoinedPrivateArchivedThreadsResponseObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/channels/${channelId}/users/@me/threads/archived/private") {
            optionalQueryStringParam("before", before)
            optionalQueryStringParam("limit", limit)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ListJoinedPrivateArchivedThreadsResponseObject.serializer())
    }
}