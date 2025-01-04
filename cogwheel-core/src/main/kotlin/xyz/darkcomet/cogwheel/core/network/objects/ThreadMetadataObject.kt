package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp

@Serializable
data class ThreadMetadataObject(
    val archived: Boolean,
    @SerialName("auto_archive_duration") val autoArchiveDuration: Int,
    @SerialName("archive_timestamp") val archiveTimestamp: ISO8601Timestamp,
    val locked: Boolean,
    val invitable: Boolean? = null,
    @SerialName("create_timestamp") val createTimestamp: ISO8601Timestamp? = null,
)