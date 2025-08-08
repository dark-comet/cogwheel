@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "RedundantOverride", "unused")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.EmojiResource
import xyz.darkcomet.cogwheel.framework.models.entitles.emoji.Emoji
import xyz.darkcomet.cogwheel.framework.models.entitles.emoji.toModel
import xyz.darkcomet.cogwheel.framework.models.request.CreateApplicationEmojiRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.CreateGuildEmojiRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.ModifyApplicationEmojiRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.ModifyGuildEmojiRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.*

class EmojiApi
internal constructor(resource: EmojiResource) {
    
    @JvmField
    val listForGuild = object : Invocation1<GuildId, List<Emoji>>() {
        
        override suspend fun invoke(p1: GuildId): Response<List<Emoji>> {
            val response = resource.listGuildEmojis(p1.snowflake)
            val result = response.data?.map { it.toModel() }

            return Response(result, response)
        }

    }
    
    @JvmField
    val getForGuild = object : Invocation2<GuildId, EmojiId, Emoji>() {
        
        override suspend fun invoke(
            p1: GuildId,
            p2: EmojiId
        ): Response<Emoji> {
            val response = resource.getGuildEmoji(p1.snowflake, p2.snowflake)
            val result = response.data?.toModel()

            return Response(result, response)
        }

    }
    
    @JvmField
    val createForGuild = object : RequestInvocation1S<GuildId, CreateGuildEmojiRequestSpec, Emoji>() {
        
        override fun createRequest(p1: GuildId): CreateGuildEmojiRequestSpec {
            return CreateGuildEmojiRequestSpec(p1)
        }

        override suspend fun invoke(request: CreateGuildEmojiRequestSpec): Response<Emoji> {
            val response = resource.createGuildEmoji(
                request.guildId.snowflake,
                request.buildParameters(),
                request.auditLogReason
            )
            val result = response.data?.toModel()

            return Response(result, response)            
        }

    }
    
    @JvmField
    val modifyForGuild = object : RequestInvocation2S<GuildId, EmojiId, ModifyGuildEmojiRequestSpec, Emoji>() {
        
        override fun createRequest(
            p1: GuildId,
            p2: EmojiId
        ): ModifyGuildEmojiRequestSpec {
            
            return ModifyGuildEmojiRequestSpec(p1, p2)
        }

        override suspend fun invoke(request: ModifyGuildEmojiRequestSpec): Response<Emoji> {
            val response = resource.modifyGuildEmoji(
                request.guildId.snowflake,
                request.emojiId.snowflake,
                request.buildParameters(),
                request.auditLogReason
            )
            val result = response.data?.toModel()

            return Response(result, response)
        }

    }
    
    @JvmField
    val deleteForGuild = object : Invocation2<GuildId, EmojiId, Boolean>() {
        
        override suspend fun invoke(
            p1: GuildId,
            p2: EmojiId
        ): Response<Boolean> {
            val response = resource.deleteGuildEmoji(p1.snowflake, p1.snowflake)
            val result = response.raw.success

            return Response(result, response)
        }

    }
    
    @JvmField
    val listForApplication = object : Invocation1<ApplicationId, List<Emoji>>() {
        
        override suspend fun invoke(p1: ApplicationId): Response<List<Emoji>> {
            val response = resource.listApplicationEmojis(p1.snowflake)

            val result: List<Emoji>? = response.data?.let { responseObj ->
                responseObj.items.map { emojiObj -> emojiObj.toModel() }
            }

            return Response(result, response)
        }

    }
    
    @JvmField
    val getForApplication = object : Invocation2<ApplicationId, EmojiId, Emoji>() {
        
        override suspend fun invoke(
            p1: ApplicationId,
            p2: EmojiId
        ): Response<Emoji> {
            val response = resource.getApplicationEmoji(p1.snowflake, p2.snowflake)
            val result = response.data?.toModel()

            return Response(result, response)
        }

    }
    
    @JvmField
    val createForApplication = object : RequestInvocation1S<ApplicationId, CreateApplicationEmojiRequestSpec, Emoji>() {
        
        override fun createRequest(p1: ApplicationId): CreateApplicationEmojiRequestSpec {
            return CreateApplicationEmojiRequestSpec(p1)
        }

        override suspend fun invoke(request: CreateApplicationEmojiRequestSpec): Response<Emoji> {
            val response = resource.createApplicationEmoji(
                request.applicationId.snowflake,
                request.buildParameters()
            )
            val result = response.data?.toModel()

            return Response(result, response)
        }

    }
    
    @JvmField
    val modifyForApplication = object : RequestInvocation2S<ApplicationId, EmojiId, ModifyApplicationEmojiRequestSpec, Emoji>() {
        
        override fun createRequest(
            p1: ApplicationId,
            p2: EmojiId
        ): ModifyApplicationEmojiRequestSpec {
            return ModifyApplicationEmojiRequestSpec(p1, p2)
        }

        override suspend fun invoke(request: ModifyApplicationEmojiRequestSpec): Response<Emoji> {
            val response = resource.modifyApplicationEmoji(
                request.applicationId.snowflake,
                request.emojiId.snowflake,
                request.buildParameters()
            )
            val result = response.data?.toModel()

            return Response(result, response)
        }

    }
    
    @JvmField
    val deleteForApplication = object : Invocation2<ApplicationId, EmojiId, Boolean>() {
        
        override suspend fun invoke(
            p1: ApplicationId,
            p2: EmojiId
        ): Response<Boolean> {
            val response = resource.deleteApplicationEmoji(p1.snowflake, p2.snowflake)
            val result = response.raw.success

            return Response(result, response)
        }

    }
    
}