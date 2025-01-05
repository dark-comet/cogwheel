package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
abstract class ApplicationCommandOptionChoiceObject {
    abstract val name: String
    
    @SerialName("name_localizations")
    abstract val nameLocalizations: Map<String, String>?
}

@Serializable
class ApplicationCommandOptionStringChoiceObject(
    override val name: String,
    override val nameLocalizations: Map<String, String>? = null,
    val value: String
) : ApplicationCommandOptionChoiceObject()

@Serializable
class ApplicationCommandOptionIntegerChoiceObject(
    override val name: String, 
    override val nameLocalizations: Map<String, String>?,
    val value: Int
) : ApplicationCommandOptionChoiceObject()

@Serializable
class ApplicationCommandOptionDoubleChoiceObject(
    override val name: String, 
    override val nameLocalizations: Map<String, String>?,
    val value: Double
) : ApplicationCommandOptionChoiceObject()