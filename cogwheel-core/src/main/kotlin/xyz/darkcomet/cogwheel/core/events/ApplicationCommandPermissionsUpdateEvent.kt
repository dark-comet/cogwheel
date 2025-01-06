package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.ApplicationCommandPermissionObject

class ApplicationCommandPermissionsUpdateEvent
internal constructor(
    override val data: ApplicationCommandPermissionObject
) : Event<ApplicationCommandPermissionObject>