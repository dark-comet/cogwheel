package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.ChannelObject
import xyz.darkcomet.cogwheel.core.network.objects.InviteObject
import xyz.darkcomet.cogwheel.core.network.objects.MessageObject
import xyz.darkcomet.cogwheel.core.network.objects.ThreadMemberObject
import xyz.darkcomet.cogwheel.core.network.objects.request.*
import xyz.darkcomet.cogwheel.core.network.objects.response.ListJoinedPrivateArchivedThreadsResponseObject
import xyz.darkcomet.cogwheel.core.network.objects.response.ListPrivateArchivedThreadsResponseObject
import xyz.darkcomet.cogwheel.core.network.objects.response.ListPublicArchivedThreadsResponseObject
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class ChannelResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun getChannel(channelId: Snowflake): CwHttpResponse<ChannelObject> {
        TODO("Not implemented yet")
    }

    fun modifyChannel(
        channelId: Snowflake,
        request: ModifyChannelRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<ChannelObject> {
        TODO("Not implemented yet")
    }

    fun deleteOrCloseChannel(
        channelId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<ChannelObject> {
        TODO("Not implemented yet")
    }
    
    fun editChannelPermissions(
        channelId: Snowflake,
        overwriteId: Snowflake,
        request: EditChannelPermissionsParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }
    
    fun getChannelInvites(channelId: Snowflake) : CwHttpResponse<List<InviteObject>> {
        TODO("Not implemented yet")
    }
    
    fun createChannelInvite(
        channelId: Snowflake,
        request: CreateChannelInviteRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<InviteObject> {
        TODO("Not implemented yet")
    }
    
    fun deleteChannelPermission(
        channelId: Snowflake, 
        overwriteId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }
    
    fun followAnnouncementChannel(
        channelId: Snowflake,
        request: FollowAnnouncementChannelRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<ChannelObject> {
        TODO("Not implemented yet")
    }
    
    fun triggerTypingIndicator(channelId: Snowflake): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }
    
    fun getPinnedMessages(channelId: Snowflake): CwHttpResponse<List<MessageObject>> {
        TODO("Not implemented yet")
    }
    
    fun pinMessage(
        channelId: Snowflake, 
        messageId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }
    
    fun unpinMessage(
        channelId: Snowflake, 
        messageId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }
    
    fun groupDmAddRecipient(
        channelId: Snowflake, 
        userId: Snowflake, 
        request: GroupDmAddRecipientRequestParameters
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }

    fun groupDmRemoveRecipient(
        channelId: Snowflake, 
        userId: Snowflake
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }
    
    fun startThreadFromMessage(
        channelId: Snowflake,
        messageId: Snowflake,
        request: StartThreadFromMessageRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<ChannelObject> {
        TODO("Not implemented yet")
    }
    
    fun startThreadWithoutMessage(
        channelId: Snowflake,
        request: StartThreadWithoutMessageRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<ChannelObject> {
        TODO("Not implemented yet")
    }
    
    fun startThreadInFormOrMediaChannel(
        channelId: Snowflake,
        request: StartThreadInForumOrMediaChannelRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<ChannelObject> {
        TODO("Not implemented yet")
    }
    
    fun joinThread(channelId: Snowflake): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }
    
    fun addThreadMember(
        channelId: Snowflake, 
        userId: Snowflake
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }
    
    fun leaveThread(channelId: Snowflake): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }
    
    fun removeThreadMember(
        channelId: Snowflake, 
        userId: Snowflake
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }
    
    fun getThreadMember(
        channelId: Snowflake, 
        userId: Snowflake, 
        withMember: Boolean? = null
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }

    fun listThreadMembers(
        channelId: Snowflake, 
        after: Snowflake? = null, 
        limit: Int? = null
    ): CwHttpResponse<List<ThreadMemberObject>> {
        TODO("Not implemented yet")
    }
    
    fun listPublicArchivedThreads(
        channelId: Snowflake,
        before: ISO8601Timestamp? = null,
        limit: Int? = null
    ): CwHttpResponse<ListPublicArchivedThreadsResponseObject> {
        TODO("Not implemented yet")
    }
    
    fun listPrivateArchivedThreads(
        channelId: Snowflake,
        before: ISO8601Timestamp? = null,
        limit: Int? = null
    ): CwHttpResponse<ListPrivateArchivedThreadsResponseObject> {
        TODO("Not implemented yet")
    }
    
    fun listJoinedPrivateArchivedThreads(
        channelId: Snowflake,
        before: ISO8601Timestamp? = null,
        limit: Int? = null
    ): CwHttpResponse<ListJoinedPrivateArchivedThreadsResponseObject> {
        TODO("Not implemented yet")
    }
}