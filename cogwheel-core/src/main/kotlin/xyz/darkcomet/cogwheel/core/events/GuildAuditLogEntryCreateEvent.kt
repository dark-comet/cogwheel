package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.AuditLogEntryObject

class GuildAuditLogEntryCreateEvent
internal constructor(override val data: AuditLogEntryObject) : Event<AuditLogEntryObject>