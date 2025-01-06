package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.VoiceStateObject

class VoiceStateUpdateEvent
internal constructor(override val data: VoiceStateObject) : Event<VoiceStateObject>
