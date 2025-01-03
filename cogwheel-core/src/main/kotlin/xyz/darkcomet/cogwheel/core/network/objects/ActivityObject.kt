package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class ActivityObject(
    val name: String,
    val type: Int,
    val url: String? = null,
    @SerialName("created_at") val createdAt: Int,
    val timestamps: ActivityTimestampsObject? = null,
    @SerialName("application_id") val applicationId: Snowflake? = null,
    val details: String? = null,
    val state: String? = null,
    val emoji: ActivityEmojiObject? = null,
    val party: ActivityPartyObject? = null,
    val assets: ActivityAssetsObject? = null,
    val secrets: ActivitySecretsObject? = null,
    val instance: Boolean? = null,
    val flags: Int? = null,
    val buttons: List<ActivityButtonObject>? = null
)