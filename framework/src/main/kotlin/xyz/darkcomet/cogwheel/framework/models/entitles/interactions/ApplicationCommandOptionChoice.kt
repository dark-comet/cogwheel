package xyz.darkcomet.cogwheel.framework.models.entitles.interactions

import xyz.darkcomet.cogwheel.core.network.objects.ApplicationCommandOptionDoubleChoiceObject
import xyz.darkcomet.cogwheel.core.network.objects.ApplicationCommandOptionIntegerChoiceObject
import xyz.darkcomet.cogwheel.core.network.objects.ApplicationCommandOptionStringChoiceObject
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent
import xyz.darkcomet.cogwheel.framework.primitives.LocalizationMap

sealed interface ApplicationCommandOptionChoice 

data class ApplicationCommandOptionStringChoice(
    val name: String,
    val nameLocalizations: LocalizationMap?,
    val value: String
) : ApplicationCommandOptionChoice {
    companion object {
        internal fun from(obj: ApplicationCommandOptionStringChoiceObject): ApplicationCommandOptionStringChoice {
            return obj.toModel()
        }
    }
}

internal fun ApplicationCommandOptionStringChoiceObject.toModel(): ApplicationCommandOptionStringChoice {
    return ApplicationCommandOptionStringChoice(
        name = requireNonNull(this, ApplicationCommandOptionStringChoiceObject::name),
        nameLocalizations = requireNonNullIfPresent(this, ApplicationCommandOptionStringChoiceObject::nameLocalizations)?.let { LocalizationMap.from(it) },
        value = requireNonNull(this, ApplicationCommandOptionStringChoiceObject::value)
    )
}

data class ApplicationCommandOptionIntegerChoice(
    val name: String,
    val nameLocalizations: LocalizationMap?,
    val value: Int
) : ApplicationCommandOptionChoice {
    companion object {
        internal fun from(obj: ApplicationCommandOptionIntegerChoiceObject): ApplicationCommandOptionIntegerChoice {
            return obj.toModel()
        }
    }
}

internal fun ApplicationCommandOptionIntegerChoiceObject.toModel(): ApplicationCommandOptionIntegerChoice {
    return ApplicationCommandOptionIntegerChoice(
        name = requireNonNull(this, ApplicationCommandOptionIntegerChoiceObject::name),
        nameLocalizations = requireNonNullIfPresent(this, ApplicationCommandOptionIntegerChoiceObject::nameLocalizations)?.let { LocalizationMap.from(it) },
        value = requireNonNull(this, ApplicationCommandOptionIntegerChoiceObject::value)
    )
}

data class ApplicationCommandOptionDoubleChoice(
    val name: String,
    val nameLocalizations: LocalizationMap?,
    val value: Double
) : ApplicationCommandOptionChoice {
    companion object {
        internal fun from(obj: ApplicationCommandOptionDoubleChoiceObject): ApplicationCommandOptionDoubleChoice {
            return obj.toModel()
        }
    }
}

internal fun ApplicationCommandOptionDoubleChoiceObject.toModel(): ApplicationCommandOptionDoubleChoice {
    return ApplicationCommandOptionDoubleChoice(
        name = requireNonNull(this, ApplicationCommandOptionDoubleChoiceObject::name),
        nameLocalizations = requireNonNullIfPresent(this, ApplicationCommandOptionDoubleChoiceObject::nameLocalizations)?.let { LocalizationMap.from(it) },
        value = requireNonNull(this, ApplicationCommandOptionDoubleChoiceObject::value)
    )
}

