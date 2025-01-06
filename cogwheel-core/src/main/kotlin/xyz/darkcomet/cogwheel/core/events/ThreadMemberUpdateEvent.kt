package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.ThreadMemberObject

class ThreadMemberUpdateEvent
internal constructor(override val data: ThreadMemberObject): Event<ThreadMemberObject>