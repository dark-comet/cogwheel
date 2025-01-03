package xyz.darkcomet.cogwheel.core.network.objects.response

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.SoundboardSoundEntity

@Serializable
data class ListGuildSoundboardSoundsResponseEntity(val items: List<SoundboardSoundEntity>)