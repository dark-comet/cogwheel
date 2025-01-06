package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.UserObject

class UserUpdateEvent
internal constructor(override val data: UserObject) : Event<UserObject>