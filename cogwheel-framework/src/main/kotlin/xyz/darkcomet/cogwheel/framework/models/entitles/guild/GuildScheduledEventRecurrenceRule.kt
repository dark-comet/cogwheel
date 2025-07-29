@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.GuildScheduledEventRecurrenceRuleObject
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.framework.models.require
import xyz.darkcomet.cogwheel.framework.models.requireNonNull
import xyz.darkcomet.cogwheel.framework.primitives.RecurrenceRuleFrequency
import xyz.darkcomet.cogwheel.framework.primitives.RecurrenceRuleMonth
import xyz.darkcomet.cogwheel.framework.primitives.RecurrenceRuleWeekday

class GuildScheduledEventRecurrenceRule(
    val start: ISO8601Timestamp,
    val end: ISO8601Timestamp?,
    val frequency: RecurrenceRuleFrequency,
    val interval: Int,
    val byWeekday: List<RecurrenceRuleWeekday>?,
    val byNWeekday: List<RecurrenceRuleNWeekday>?,
    val byMonth: List<RecurrenceRuleMonth>?,
    val byMonthDay: List<Int>?,
    val byYearDay: List<Int>?,
    val count: Int?
) {
    companion object {
        internal fun from(obj: GuildScheduledEventRecurrenceRuleObject): GuildScheduledEventRecurrenceRule {
            return obj.toModel()
        }
    }
    
    internal fun toObject(): GuildScheduledEventRecurrenceRuleObject {
        return GuildScheduledEventRecurrenceRuleObject(
            start = MaybeAbsent(start),
            end = MaybeAbsent(end),
            
        )
    }
}

internal fun GuildScheduledEventRecurrenceRuleObject.toModel(): GuildScheduledEventRecurrenceRule {
    return GuildScheduledEventRecurrenceRule(
        start = requireNonNull(this, GuildScheduledEventRecurrenceRuleObject::start),
        end = require(this, GuildScheduledEventRecurrenceRuleObject::end),
        frequency = requireNonNull(this, GuildScheduledEventRecurrenceRuleObject::frequency).let { RecurrenceRuleFrequency.fromOrAdd(it) },
        interval = requireNonNull(this, GuildScheduledEventRecurrenceRuleObject::interval),
        byWeekday = require(this, GuildScheduledEventRecurrenceRuleObject::byWeekday)?.map { RecurrenceRuleWeekday.fromOrAdd(it) },
        byNWeekday = require(this, GuildScheduledEventRecurrenceRuleObject::byNWeekday)?.map { it.toModel() },
        byMonth = require(this, GuildScheduledEventRecurrenceRuleObject::byMonth)?.map { RecurrenceRuleMonth.fromOrAdd(it) },
        byMonthDay = require(this, GuildScheduledEventRecurrenceRuleObject::byMonthDay),
        byYearDay = require(this, GuildScheduledEventRecurrenceRuleObject::byYearDay),
        count = require(this, GuildScheduledEventRecurrenceRuleObject::count)
    )
}