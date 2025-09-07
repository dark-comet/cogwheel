@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.GuildIncidentsDataObject
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.framework.utils.require

class GuildIncidentsData(
    val invitesDisabledUntil: ISO8601Timestamp?,
    val dmsDisabledUntil: ISO8601Timestamp?,
    val dmSpamDetectedAt: ISO8601Timestamp?,
    val raidDetectedAt: ISO8601Timestamp?
) {
    companion object {
        internal fun from(obj: GuildIncidentsDataObject): GuildIncidentsData {
            return obj.toModel()
        }
    }
}

internal fun GuildIncidentsDataObject.toModel(): GuildIncidentsData {
    return GuildIncidentsData(
        invitesDisabledUntil = require(this, GuildIncidentsDataObject::invitesDisabledUntil),
        dmsDisabledUntil = require(this, GuildIncidentsDataObject::dmsDisabledUntil),
        dmSpamDetectedAt = require(this, GuildIncidentsDataObject::dmSpamDetectedAt),
        raidDetectedAt = require(this, GuildIncidentsDataObject::raidDetectedAt)
    )
}