package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.Serializable

@Serializable
data class ModifyGuildCurrentMemberRequestParameters(
    val nick: String? = null
)