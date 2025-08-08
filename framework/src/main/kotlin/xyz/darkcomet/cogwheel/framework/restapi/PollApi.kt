@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "unused")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.PollResource
import xyz.darkcomet.cogwheel.framework.models.entitles.message.Message
import xyz.darkcomet.cogwheel.framework.models.entitles.message.toModel
import xyz.darkcomet.cogwheel.framework.models.entitles.user.User
import xyz.darkcomet.cogwheel.framework.models.entitles.user.toModel
import xyz.darkcomet.cogwheel.framework.models.request.GetPollAnswerVotersRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.*

class PollApi
internal constructor(resource: PollResource) {
    
    @JvmField
    val getAnswerVoters = object : RequestInvocation3S<ChannelId, MessageId, Int, GetPollAnswerVotersRequestSpec, List<User>>() {
        
        override fun createRequest(
            p1: ChannelId,
            p2: MessageId,
            p3: Int
        ): GetPollAnswerVotersRequestSpec {
            
            return GetPollAnswerVotersRequestSpec(p1, p2, p3)
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

    }
    
    @JvmField
    val end = object : Invocation2<ChannelId, MessageId, Message>() {
        
        override suspend fun invoke(
            p1: ChannelId,
            p2: MessageId
        ): Response<Message> {
            val response = resource.endPoll(p1.snowflake, p2.snowflake)
            val result = response.data?.toModel()

            return Response(result, response)
        }

    }
    
}