package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class ApplicationCommandObject(
    val id: Snowflake,
    val type: Int? = 1,
    @SerialName("application_id") val applicationId: Snowflake,
    @SerialName("guild_id") val guildId: Snowflake? = null,
    val name: String,
    @SerialName("name_localizations") val nameLocalizations: Map<String, String>? = null,
    val description: String,
    @SerialName("description_localizations") val descriptionLocalizations: Map<String, String>? = null,
    val options: List<ApplicationCommandOptionObject>? = null,
    @SerialName("default_member_permissions") val defaultMemberPermissions: String? = null,
    @SerialName("dm_permission") val dmPermission: Boolean? = null,
    @SerialName("default_permission") val defaultPermission: Boolean? = null,
    val nsfw: Boolean? = null,
    @SerialName("integration_types") val integrationTypes: List<Int>? = null,
    val contexts: List<Int>? = null,
    val version: Snowflake,
    val handler: Int? = null
)