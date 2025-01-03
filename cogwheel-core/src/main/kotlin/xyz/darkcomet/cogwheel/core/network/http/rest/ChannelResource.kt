package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.core.network.objects.ChannelEntity
import xyz.darkcomet.cogwheel.core.network.objects.InviteEntity
import xyz.darkcomet.cogwheel.core.network.objects.MessageEntity
import xyz.darkcomet.cogwheel.core.network.objects.ThreadMemberEntity
import xyz.darkcomet.cogwheel.core.network.objects.request.*
import xyz.darkcomet.cogwheel.core.network.objects.response.ListJoinedPrivateArchivedThreadsResponseEntity
import xyz.darkcomet.cogwheel.core.network.objects.response.ListPrivateArchivedThreadsResponseEntity
import xyz.darkcomet.cogwheel.core.network.objects.response.ListPublicArchivedThreadsResponseEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp

class ChannelResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun getChannel(channelId: Snowflake): CwHttpResponse<ChannelEntity> {
        TODO("Not implemented yet")
    }

    fun modifyChannel(
        channelId: Snowflake, 
        request: ModifyChannelRequestEntity, 
        auditLogReason: String? = null
    ): CwHttpResponse<ChannelEntity> {
        TODO("Not implemented yet")
    }

    fun deleteOrCloseChannel(
        channelId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<ChannelEntity> {
        TODO("Not implemented yet")
    }
    
    fun editChannelPermissions(
        channelId: Snowflake,
        overwriteId: Snowflake,
        request: EditChannelPermissionsRequestEntity,
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }
    
    fun getChannelInvites(channelId: Snowflake) : CwHttpResponse<List<InviteEntity>> {
        TODO("Not implemented yet")
    }
    
    fun createChannelInvite(
        channelId: Snowflake,
        request: CreateChannelInviteRequestEntity,
        auditLogReason: String? = null
    ): CwHttpResponse<InviteEntity> {
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
        request: FollowAnnouncementChannelRequestEntity,
        auditLogReason: String? = null
    ): CwHttpResponse<ChannelEntity> {
        TODO("Not implemented yet")
    }
    
    fun triggerTypingIndicator(channelId: Snowflake): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }
    
    fun getPinnedMessages(channelId: Snowflake): CwHttpResponse<List<MessageEntity>> {
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
        request: GroupDmAddRecipientRequestEntity
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
        request: StartThreadFromMessageRequestEntity,
        auditLogReason: String? = null
    ): CwHttpResponse<ChannelEntity> {
        TODO("Not implemented yet")
    }
    
    fun startThreadWithoutMessage(
        channelId: Snowflake, 
        request: StartThreadWithoutMessageRequestEntity,
        auditLogReason: String? = null
    ): CwHttpResponse<ChannelEntity> {
        TODO("Not implemented yet")
    }
    
    fun startThreadInFormOrMediaChannel(
        channelId: Snowflake,
        request: StartThreadInForumOrMediaChannelRequestEntity,
        auditLogReason: String? = null
    ): CwHttpResponse<ChannelEntity> {
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
    ): CwHttpResponse<List<ThreadMemberEntity>> {
        TODO("Not implemented yet")
    }
    
    fun listPublicArchivedThreads(
        channelId: Snowflake,
        before: ISO8601Timestamp? = null,
        limit: Int? = null
    ): CwHttpResponse<ListPublicArchivedThreadsResponseEntity> {
        TODO("Not implemented yet")
    }
    
    fun listPrivateArchivedThreads(
        channelId: Snowflake,
        before: ISO8601Timestamp? = null,
        limit: Int? = null
    ): CwHttpResponse<ListPrivateArchivedThreadsResponseEntity> {
        TODO("Not implemented yet")
    }
    
    fun listJoinedPrivateArchivedThreads(
        channelId: Snowflake,
        before: ISO8601Timestamp? = null,
        limit: Int? = null
    ): CwHttpResponse<ListJoinedPrivateArchivedThreadsResponseEntity> {
        TODO("Not implemented yet")
    }
}