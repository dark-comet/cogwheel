package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class GuildIntegrationObject(
    val id: Snowflake,
    val name: String,
    val type: String,
    val enabled: Boolean,
    val syncing: Boolean? = null,
    @SerialName("role_id") val roleId: Snowflake,
    @SerialName("enable_emoticons") val enableEmoticons: Boolean? = null,
    @SerialName("expire_behaviour") val expireBehaviour: Int? = null,
    @SerialName("expire_grace_period") val expireGracePeriod: Int? = null,
    val user: UserObject? = null,
    val account: IntegrationAccountObject? = null,
    @SerialName("synced_at") val syncedAt: ISO8601Timestamp? = null,
    @SerialName("subscriber_count") val subscriberCount: Int? = null,
    val revoked: Boolean? = null,
    val application: IntegrationApplicationObject? = null,
    val scopes: List<String>
)