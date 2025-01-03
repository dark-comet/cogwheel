package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable

@Serializable
data class RecurrenceRuleNWeekdayObject(
    val n: Int,
    val day: Int
)