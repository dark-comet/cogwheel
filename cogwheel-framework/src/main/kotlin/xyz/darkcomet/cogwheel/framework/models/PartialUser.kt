package xyz.darkcomet.cogwheel.framework.models

import xyz.darkcomet.cogwheel.framework.primitives.UserId

open class PartialUser(
    val id: UserId,
    val username: String,
    val discriminator: String,
    val globalName: String?,
    val avatar: String?
)