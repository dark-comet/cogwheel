package xyz.darkcomet.cogwheel.core.network.entities.response

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.entities.SoundboardSoundEntity

@Serializable
data class ListGuildSoundboardSoundsResponseEntity(val items: List<SoundboardSoundEntity>)