@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.GuildWidgetSettingsObject
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.asChannelId
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent

class GuildWidgetSettings(
    val enabled: Boolean,
    val channelId: ChannelId?
) {
    companion object {
        internal fun from(obj: GuildWidgetSettingsObject): GuildWidgetSettings {
            return obj.toModel()
        }
    }
}

internal fun GuildWidgetSettingsObject.toModel(): GuildWidgetSettings {
    return GuildWidgetSettings(
        enabled = requireNonNull(this, GuildWidgetSettingsObject::enabled),
        channelId = requireNonNullIfPresent(this, GuildWidgetSettingsObject::channelId)?.asChannelId()
    )
}