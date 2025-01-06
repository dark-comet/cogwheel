package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.SubscriptionObject

class SubscriptionUpdateEvent
internal constructor(override val data: SubscriptionObject) : Event<SubscriptionObject>