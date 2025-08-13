package xyz.darkcomet.cogwheel.framework.models.entitles.message

import xyz.darkcomet.cogwheel.core.network.objects.AllowedMentionsObject
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.framework.primitives.RoleId
import xyz.darkcomet.cogwheel.framework.primitives.UserId

class AllowedMentions(
    val parse: List<String>?,
    val roles: List<RoleId>?,
    val users: List<UserId>?,
    val repliedUser: Boolean?
) {
    
    internal fun toObject(): AllowedMentionsObject {
        return AllowedMentionsObject(
            parse = if (this.parse != null) MaybeAbsent(this.parse) else null,
            roles = if (this.roles != null) MaybeAbsent(this.roles.map { it.snowflake }) else null,
            users = if (this.users != null) MaybeAbsent(this.users.map { it.snowflake }) else null,
            repliedUser = if (repliedUser != null) MaybeAbsent(this.repliedUser) else null
        )
    }
}