package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class IntegrationDeleteEvent
internal constructor(override val data: DataObject) : Event<IntegrationDeleteEvent.DataObject> {

    @Serializable
    data class DataObject(
        val id: Snowflake,
        @SerialName("guild_id") val guildId: Snowflake,
        @SerialName("application_id") val applicationId: Snowflake? = null
    )

}