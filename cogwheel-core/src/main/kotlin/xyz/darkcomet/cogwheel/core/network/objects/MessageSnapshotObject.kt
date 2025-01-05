package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable

@Serializable
data class MessageSnapshotObject(
    val message: MessageObject?
)