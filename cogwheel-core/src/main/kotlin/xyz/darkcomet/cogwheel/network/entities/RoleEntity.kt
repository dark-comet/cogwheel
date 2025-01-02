package xyz.darkcomet.cogwheel.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.models.Snowflake

@Serializable
data class RoleEntity(
    val id: Snowflake,
    val name: String,
    val color: Int,
    val hoist: Boolean,
    val icon: String? = null,
    @SerialName("unicode_emoji") val unicodeEmoji: String? = null,
    val position: Int,
    val permissions: String,
    val managed: Boolean,
    val mentionable: Boolean,
    val tags: RoleTagsEntity? = null,
    val flags: Int
)