package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.SoundboardSoundObject

class GuildSoundboardSoundUpdateEvent
internal constructor(override val data: SoundboardSoundObject) : Event<SoundboardSoundObject>