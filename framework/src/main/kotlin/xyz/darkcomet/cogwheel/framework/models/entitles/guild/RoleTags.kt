package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.RoleTagsObject
import xyz.darkcomet.cogwheel.framework.primitives.IntegrationId
import xyz.darkcomet.cogwheel.framework.primitives.SubscriptionId
import xyz.darkcomet.cogwheel.framework.primitives.UserId
import xyz.darkcomet.cogwheel.framework.primitives.asIntegrationId
import xyz.darkcomet.cogwheel.framework.primitives.asSubscriptionId
import xyz.darkcomet.cogwheel.framework.primitives.asUserId
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent

@Suppress("unused")
class RoleTags(
    val botId: UserId?,
    val integrationId: IntegrationId?,
    val premiumSubscriber: Boolean,
    val subscriptionListingId: SubscriptionId?, // TODO: Is this right?
    val availableForPurchase: Boolean,
    val guildConnections: Boolean
) {
    companion object {
        internal fun from(obj: RoleTagsObject): RoleTags {
            return obj.toModel()
        }
    }
}

internal fun RoleTagsObject.toModel(): RoleTags {
    // Ref: https://discord.com/developers/docs/topics/permissions#role-object-role-tags-structure
    val isPremiumSubscriber = if (this.premiumSubscriber == null) false else this.premiumSubscriber?.value == null
    val isAvailableForPurchase = if (this.availableForPurchase == null) false else this.availableForPurchase?.value == null
    val isGuildConnections = if (this.guildConnections == null) false else this.guildConnections?.value == null
    
    return RoleTags(
        botId = requireNonNullIfPresent(this, RoleTagsObject::botId)?.asUserId(),
        integrationId = requireNonNullIfPresent(this, RoleTagsObject::integrationId)?.asIntegrationId(),
        premiumSubscriber = isPremiumSubscriber,
        subscriptionListingId = requireNonNullIfPresent(this, RoleTagsObject::subscriptionListingId)?.asSubscriptionId(),
        availableForPurchase = isAvailableForPurchase,
        guildConnections = isGuildConnections
    )
}