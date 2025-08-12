@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.GuildScheduledEventRecurrenceRuleObject
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import xyz.darkcomet.cogwheel.framework.utils.require
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull
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
            frequency = MaybeAbsent(frequency.key),
            interval = MaybeAbsent(interval),
            byWeekday = MaybeAbsent(byWeekday?.map { it.key }),
            byNWeekday = MaybeAbsent(byNWeekday?.map { it.toObject() }),
            byMonth = MaybeAbsent(byMonth?.map { it.key }),
            byMonthDay = MaybeAbsent(byMonthDay),
            byYearDay = MaybeAbsent(byYearDay),
            count = MaybeAbsent(count)
        )
    }
    
    class BuilderSpec {
        
        private var start: ISO8601Timestamp? = null
        private var end: ISO8601Timestamp? = null
        private var frequency: RecurrenceRuleFrequency? = null
        private var interval: Int? = null
        private var byWeekday: List<RecurrenceRuleWeekday>? = null
        private var byNWeekday: List<RecurrenceRuleNWeekday>? = null
        private var byMonth: List<RecurrenceRuleMonth>? = null
        private var byMonthDay: List<Int>? = null
        private var byYearDay: List<Int>? = null
        private var count: Int? = null
        
        fun start(start: ISO8601Timestamp): BuilderSpec
            = apply { this.start = start }
        
        fun end(end: ISO8601Timestamp?): BuilderSpec 
            = apply { this.end = end }
        
        fun frequency(frequency: RecurrenceRuleFrequency): BuilderSpec
            = apply { this.frequency = frequency }
        
        fun interval(interval: Int): BuilderSpec
            = apply { this.interval = interval }
        
        fun byWeekday(weekdays: List<RecurrenceRuleWeekday>): BuilderSpec
            = apply { this.byWeekday = weekdays }
        
        fun byWeekday(vararg weekdays: RecurrenceRuleWeekday): BuilderSpec
            = apply { this.byWeekday = weekdays.toList() }
        
        fun byNWeekday(nWeekdays: List<RecurrenceRuleNWeekday>): BuilderSpec
            = apply { this.byNWeekday = nWeekdays }
        
        fun byNWeekday(vararg nWeekdays: RecurrenceRuleNWeekday): BuilderSpec
            = apply { this.byNWeekday = nWeekdays.toList() }
        
        fun byMonth(months: List<RecurrenceRuleMonth>): BuilderSpec
            = apply { this.byMonth = months }
        
        fun byMonth(vararg months: RecurrenceRuleMonth): BuilderSpec
            = apply { this.byMonth = months.toList() }
        
        fun byMonthDay(monthDays: List<Int>): BuilderSpec
            = apply { this.byMonthDay = monthDays }
        
        fun byMonthDay(vararg monthDays: Int): BuilderSpec
            = apply { this.byMonthDay = monthDays.toList() }
        
        fun byYearDay(yearDays: List<Int>): BuilderSpec
            = apply { this.byYearDay = yearDays }
        
        fun byYearDay(vararg yearDays: Int): BuilderSpec
            = apply { this.byYearDay = yearDays.toList() }
        
        fun build(): GuildScheduledEventRecurrenceRule {
            return GuildScheduledEventRecurrenceRule(
                start = this.start ?: throw InvalidModelException("'start' is required"),
                end = this.end,
                frequency = this.frequency ?: throw InvalidModelException("'frequency' is required"),
                interval = this.interval ?: throw InvalidModelException("'interval' is required"),
                byWeekday = this.byWeekday,
                byNWeekday = this.byNWeekday,
                byMonth = this.byMonth,
                byMonthDay = this.byMonthDay,
                byYearDay = this.byYearDay,
                count = this.count
            )
        }
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