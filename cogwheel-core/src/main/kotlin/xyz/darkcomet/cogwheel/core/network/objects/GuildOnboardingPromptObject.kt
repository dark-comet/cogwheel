package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class GuildOnboardingPromptObject(
    val id: Snowflake,
    val options: List<GuildOnboardingPromptOptionObject>,
    val title: String,
    @SerialName("single_select") val singleSelect: Boolean,
    val required: Boolean,
    @SerialName("in_onboarding") val inOnboarding: Boolean
)