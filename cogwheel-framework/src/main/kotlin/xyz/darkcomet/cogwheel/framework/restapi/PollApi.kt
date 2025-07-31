@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "unused")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.PollResource
import xyz.darkcomet.cogwheel.framework.models.entitles.message.Message
import xyz.darkcomet.cogwheel.framework.models.entitles.message.toModel
import xyz.darkcomet.cogwheel.framework.models.entitles.user.User
import xyz.darkcomet.cogwheel.framework.models.entitles.user.toModel
import xyz.darkcomet.cogwheel.framework.models.request.GetPollAnswerVotersRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.*
import java.util.concurrent.Future
import java.util.function.Consumer

class PollApi
internal constructor(resource: PollResource) {
    
    @JvmField
    val getAnswerVoters = GetPollAnswerVotersEndpoint(resource)
    
    @JvmField
    val end = EndPollEndpoint(resource)
    
}

class GetPollAnswerVotersEndpoint
internal constructor(private val resource: PollResource)
    : RequestInvocation3S<ChannelId, MessageId, Int, GetPollAnswerVotersRequestSpec, List<User>>() {
        
    override fun createRequest(
        channelId: ChannelId,
        messageId: MessageId,
        answerId: Int
    ): GetPollAnswerVotersRequestSpec {
        return GetPollAnswerVotersRequestSpec(channelId, messageId, answerId)
    }

    override suspend fun invoke(request: GetPollAnswerVotersRequestSpec): Response<List<User>> {
        val response = resource.getAnswerVoters(
            channelId = request.channelId.snowflake,
            messageId = request.messageId.snowflake,
            answerId = request.answerId,
            after = request.after,
            limit = request.limit,
        )
        val result = response.data?.users?.map { it.toModel() }
        
        return Response(result, response)
    }

    override fun async(
        channelId: ChannelId,
        messageId: MessageId,
        answerId: Int,
        config: Consumer<GetPollAnswerVotersRequestSpec>?
    ): Future<Response<List<User>>> {
        return super.async(channelId, messageId, answerId, config)
    }

    override fun block(
        channelId: ChannelId,
        messageId: MessageId,
        answerId: Int,
        config: Consumer<GetPollAnswerVotersRequestSpec>?
    ): Response<List<User>> {
        return super.block(channelId, messageId, answerId, config)
    }
}

class EndPollEndpoint
internal constructor(private val resource: PollResource)
    : Invocation2<ChannelId, MessageId, Message>() {
        
    override suspend fun invoke(
        channelId: ChannelId,
        messageId: MessageId
    ): Response<Message> {
        val response = resource.endPoll(channelId.snowflake, messageId.snowflake)
        val result = response.data?.toModel()
        
        return Response(result, response)
    }

    override fun async(
        channelId: ChannelId,
        messageId: MessageId
    ): Future<Response<Message>> {
        return super.async(channelId, messageId)
    }

    override fun block(
        channelId: ChannelId,
        messageId: MessageId
    ): Response<Message> {
        return super.block(channelId, messageId)
    }
}