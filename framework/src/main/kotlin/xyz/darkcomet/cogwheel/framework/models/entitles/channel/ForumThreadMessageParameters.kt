@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.channel

import xyz.darkcomet.cogwheel.core.network.objects.ForumThreadMessageParametersObject
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.framework.models.entitles.message.AllowedMentions
import xyz.darkcomet.cogwheel.framework.models.entitles.message.Embed
import xyz.darkcomet.cogwheel.framework.models.entitles.message.MessageComponent
import xyz.darkcomet.cogwheel.framework.models.entitles.message.PartialAttachment
import xyz.darkcomet.cogwheel.framework.primitives.BitField
import xyz.darkcomet.cogwheel.framework.primitives.MessageFlag
import xyz.darkcomet.cogwheel.framework.primitives.StickerId

class ForumThreadMessageParameters(
    val content: String?,
    val embeds: List<Embed>?,
    val allowedMentions: AllowedMentions?,
    val components: List<MessageComponent>?,
    val stickerIds: List<StickerId>?,
    val attachments: List<PartialAttachment>?,
    val flags: BitField<MessageFlag>?
) {
   
    internal fun toObject(): ForumThreadMessageParametersObject {
        return ForumThreadMessageParametersObject(
            content = if (content != null) MaybeAbsent(content) else null,
            embeds = if(embeds != null) MaybeAbsent(embeds.map { it.toObject() }) else null,
            allowedMentions = if(allowedMentions != null) MaybeAbsent(allowedMentions.toObject()) else null,
            components = if (components != null) MaybeAbsent(components.map { it.toObject() }) else null,
            stickerIds = if (stickerIds != null) MaybeAbsent(stickerIds.map { it.snowflake }) else null,
            attachments = if (attachments != null) MaybeAbsent(attachments.map { it.toObject() }) else null,
            flags = if (flags != null) MaybeAbsent(flags.toInt()) else null,
        )
    }
}