package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.SoundboardSoundObject

class GuildSoundboardSoundCreateEvent
internal constructor(override val data: SoundboardSoundObject) : Event<SoundboardSoundObject>