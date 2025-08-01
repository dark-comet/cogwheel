@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.RecurrenceRuleNWeekdayObject
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.framework.models.requireNonNull
import xyz.darkcomet.cogwheel.framework.primitives.RecurrenceRuleWeekday

class RecurrenceRuleNWeekday(
    val n: Int,
    val day: RecurrenceRuleWeekday
) {
    companion object {
        internal fun from(obj: RecurrenceRuleNWeekdayObject): RecurrenceRuleNWeekday {
            return obj.toModel()
        }
    }
    
    internal fun toObject(): RecurrenceRuleNWeekdayObject {
        return RecurrenceRuleNWeekdayObject(
            n = MaybeAbsent(n),
            day = MaybeAbsent(day.key)
        )
    }
}

internal fun RecurrenceRuleNWeekdayObject.toModel(): RecurrenceRuleNWeekday {
    return RecurrenceRuleNWeekday(
        n = requireNonNull(this, RecurrenceRuleNWeekdayObject::n),
        day = requireNonNull(this, RecurrenceRuleNWeekdayObject::day).let { RecurrenceRuleWeekday.fromOrAdd(it) }
    )
}