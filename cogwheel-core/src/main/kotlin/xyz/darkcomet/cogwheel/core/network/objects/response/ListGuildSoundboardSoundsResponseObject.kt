package xyz.darkcomet.cogwheel.core.network.objects.response

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.SoundboardSoundObject

@Serializable
data class ListGuildSoundboardSoundsResponseObject(val items: List<SoundboardSoundObject>)