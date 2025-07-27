@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "RedundantOverride", "unused")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.EmojiResource
import xyz.darkcomet.cogwheel.framework.models.entitles.emoji.Emoji
import xyz.darkcomet.cogwheel.framework.models.entitles.emoji.toModel
import xyz.darkcomet.cogwheel.framework.models.request.CreateApplicationEmojiRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.CreateGuildEmojiRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.ModifyApplicationEmojiRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.ModifyGuildEmojiRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationId
import xyz.darkcomet.cogwheel.framework.primitives.EmojiId
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.Invocation1
import xyz.darkcomet.cogwheel.framework.primitives.Invocation2
import xyz.darkcomet.cogwheel.framework.primitives.RequestInvocation1S
import xyz.darkcomet.cogwheel.framework.primitives.RequestInvocation2S
import xyz.darkcomet.cogwheel.framework.primitives.Response
import java.util.concurrent.Future
import java.util.function.Consumer

class EmojiApi
internal constructor(resource: EmojiResource) {
    
    @JvmField
    val listForGuild = ListGuildEmojisEndpoint(resource)
    
    @JvmField
    val getForGuild = GetGuildEmojiEndpoint(resource)
    
    @JvmField
    val createForGuild = CreateGuildEmojiEndpoint(resource)
    
    @JvmField
    val modifyForGuild = ModifyGuildEmojiEndpoint(resource)
    
    @JvmField
    val deleteForGuild = DeleteGuildEmojiEndpoint(resource)
    
    @JvmField
    val listForApplication = ListApplicationEmojisEndpoint(resource)
    
    @JvmField
    val getForApplication = GetApplicationEmojiEndpoint(resource)
    
    @JvmField
    val createForApplication = CreateApplicationEmojiEndpoint(resource)
    
    @JvmField
    val modifyForApplication = ModifyApplicationEmojiEndpoint(resource)
    
    @JvmField
    val deleteForApplication = DeleteApplicationEmojiEndpoint(resource)
    
}

class ListGuildEmojisEndpoint
internal constructor (private val resource: EmojiResource) 
    : Invocation1<GuildId, List<Emoji>>() {
        
    override suspend fun invoke(guildId: GuildId): Response<List<Emoji>> {
        val response = resource.listGuildEmojis(guildId.snowflake)
        val result = response.data?.map { it.toModel() }
        
        return Response(result, response)
    }

    override fun async(guildId: GuildId): Future<Response<List<Emoji>>> {
        return super.async(guildId)
    }

    override fun block(guildId: GuildId): Response<List<Emoji>> {
        return super.block(guildId)
    }
    
}

class GetGuildEmojiEndpoint
internal constructor (private val resource: EmojiResource)
    : Invocation2<GuildId, EmojiId, Emoji>() {
        
    override suspend fun invoke(guildId: GuildId, emojiId: EmojiId): Response<Emoji> {
        val response = resource.getGuildEmoji(guildId.snowflake, emojiId.snowflake)
        val result = response.data?.toModel()
        
        return Response(result, response)
    }

    override fun async(guildId: GuildId, emojiId: EmojiId): Future<Response<Emoji>> {
        return super.async(guildId, emojiId)
    }

    override fun block(guildId: GuildId, emojiId: EmojiId): Response<Emoji> {
        return super.block(guildId, emojiId)
    }
    
}

class CreateGuildEmojiEndpoint
internal constructor (private val resource: EmojiResource)
    : RequestInvocation1S<GuildId, CreateGuildEmojiRequestSpec, Emoji>() {
        
    override fun createRequest(guildId: GuildId): CreateGuildEmojiRequestSpec {
        return CreateGuildEmojiRequestSpec(guildId)
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

    override suspend fun invoke(
        guildId: GuildId,
        request: CreateGuildEmojiRequestSpec?,
        config: (CreateGuildEmojiRequestSpec.() -> Unit)?
    ): Response<Emoji> {
        return super.invoke(guildId, request, config)
    }

    override fun async(
        guildId: GuildId,
        config: Consumer<CreateGuildEmojiRequestSpec>?
    ): Future<Response<Emoji>> {
        return super.async(guildId, config)
    }

    override fun block(
        guildId: GuildId,
        config: Consumer<CreateGuildEmojiRequestSpec>?
    ): Response<Emoji> {
        return super.block(guildId, config)
    }
}

class ModifyGuildEmojiEndpoint
internal constructor (private val resource: EmojiResource)
    : RequestInvocation2S<GuildId, EmojiId, ModifyGuildEmojiRequestSpec, Emoji>() {
        
    override fun createRequest(guildId: GuildId, emojiId: EmojiId): ModifyGuildEmojiRequestSpec {
        return ModifyGuildEmojiRequestSpec(guildId, emojiId)
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

    override fun async(
        guildId: GuildId,
        emojiId: EmojiId,
        config: Consumer<ModifyGuildEmojiRequestSpec>?
    ): Future<Response<Emoji>> {
        return super.async(guildId, emojiId, config)
    }

    override fun block(
        guildId: GuildId,
        emojiId: EmojiId,
        config: Consumer<ModifyGuildEmojiRequestSpec>?
    ): Response<Emoji> {
        return super.block(guildId, emojiId, config)
    }
}

class DeleteGuildEmojiEndpoint
internal constructor (private val resource: EmojiResource)
    : Invocation2<GuildId, EmojiId, Boolean>() {
        
    override suspend fun invoke(guildId: GuildId, emojiId: EmojiId): Response<Boolean> {
        val response = resource.deleteGuildEmoji(guildId.snowflake, emojiId.snowflake)
        val result = response.raw.success
        
        return Response(result, response)
    }

    override fun async(guildId: GuildId, emojiId: EmojiId): Future<Response<Boolean>> {
        return super.async(guildId, emojiId)
    }

    override fun block(guildId: GuildId, emojiId: EmojiId): Response<Boolean> {
        return super.block(guildId, emojiId)
    }
}

class ListApplicationEmojisEndpoint
internal constructor (private val resource: EmojiResource)
    : Invocation1<ApplicationId, List<Emoji>>() {
        
    override suspend fun invoke(appId: ApplicationId): Response<List<Emoji>> {
        val response = resource.listApplicationEmojis(appId.snowflake)
        
        val result: List<Emoji>? = response.data?.let { responseObj -> 
            responseObj.items.map { emojiObj -> emojiObj.toModel() }
        }
        
        return Response(result, response)
    }

    override fun async(appId: ApplicationId): Future<Response<List<Emoji>>> {
        return super.async(appId)
    }

    override fun block(appId: ApplicationId): Response<List<Emoji>> {
        return super.block(appId)
    }
}

class GetApplicationEmojiEndpoint
internal constructor(private val resource: EmojiResource)
    : Invocation2<ApplicationId, EmojiId, Emoji>() {
        
    override suspend fun invoke(appId: ApplicationId, emojiId: EmojiId): Response<Emoji> {
        val response = resource.getApplicationEmoji(appId.snowflake, emojiId.snowflake)
        val result = response.data?.toModel()
        
        return Response(result, response)
    }

    override fun async(appId: ApplicationId, emojiId: EmojiId): Future<Response<Emoji>> {
        return super.async(appId, emojiId)
    }

    override fun block(appId: ApplicationId, emojiId: EmojiId): Response<Emoji> {
        return super.block(appId, emojiId)
    }
}

class CreateApplicationEmojiEndpoint
internal constructor(private val resource: EmojiResource)
    : RequestInvocation1S<ApplicationId, CreateApplicationEmojiRequestSpec, Emoji>() {
    
        override fun createRequest(appId: ApplicationId): CreateApplicationEmojiRequestSpec {
        return CreateApplicationEmojiRequestSpec(appId)
    }

    override suspend fun invoke(request: CreateApplicationEmojiRequestSpec): Response<Emoji> {
        val response = resource.createApplicationEmoji(
            request.appId.snowflake,
            request.buildParameters()
        )
        val result = response.data?.toModel()
        
        return Response(result, response)
    }

    override suspend fun invoke(
        appId: ApplicationId,
        request: CreateApplicationEmojiRequestSpec?,
        config: (CreateApplicationEmojiRequestSpec.() -> Unit)?
    ): Response<Emoji> {
        return super.invoke(appId, request, config)
    }

    override fun async(
        appId: ApplicationId,
        config: Consumer<CreateApplicationEmojiRequestSpec>?
    ): Future<Response<Emoji>> {
        return super.async(appId, config)
    }

    override fun block(
        appId: ApplicationId,
        config: Consumer<CreateApplicationEmojiRequestSpec>?
    ): Response<Emoji> {
        return super.block(appId, config)
    }
}

class ModifyApplicationEmojiEndpoint
internal constructor (private val resource: EmojiResource)
    : RequestInvocation2S<ApplicationId, EmojiId, ModifyApplicationEmojiRequestSpec, Emoji>() {
        
    override fun createRequest(appId: ApplicationId, emojiId: EmojiId): ModifyApplicationEmojiRequestSpec {
        return ModifyApplicationEmojiRequestSpec(appId, emojiId)
    }

    override suspend fun invoke(request: ModifyApplicationEmojiRequestSpec): Response<Emoji> {
        val response = resource.modifyApplicationEmoji(
            request.appId.snowflake,
            request.emojiId.snowflake,
            request.buildParameters()
        )
        val result = response.data?.toModel()
        
        return Response(result, response)
    }
}

class DeleteApplicationEmojiEndpoint
internal constructor (private val resource: EmojiResource)
    : Invocation2<ApplicationId, EmojiId, Boolean>() {
    
    override suspend fun invoke(appId: ApplicationId, emojiId: EmojiId): Response<Boolean> {
        val response = resource.deleteApplicationEmoji(appId.snowflake, emojiId.snowflake)
        val result = response.raw.success
        
        return Response(result, response)
    }

    override fun async(appId: ApplicationId, emojiId: EmojiId): Future<Response<Boolean>> {
        return super.async(appId, emojiId)
    }

    override fun block(appId: ApplicationId, emojiId: EmojiId): Response<Boolean> {
        return super.block(appId, emojiId)
    }
}