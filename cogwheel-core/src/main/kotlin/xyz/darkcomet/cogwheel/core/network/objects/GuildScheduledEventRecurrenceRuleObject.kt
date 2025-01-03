package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp

@Serializable
data class GuildScheduledEventRecurrenceRuleObject(
    val start: ISO8601Timestamp,
    val end: ISO8601Timestamp,
    val frequency: Int,
    val interval: Int,
    @SerialName("by_weekday") val byWeekday: List<Int>? = null,
    @SerialName("by_n_weekday") val byNWeekday: List<RecurrenceRuleNWeekdayObject>? = null,
    @SerialName("by_month") val byMonth: List<Int>? = null,
    @SerialName("by_month_day") val byMonthDay: List<Int>? = null,
    @SerialName("by_year_day") val byYearDay: List<Int>? = null,
    val count: Int? = null
)