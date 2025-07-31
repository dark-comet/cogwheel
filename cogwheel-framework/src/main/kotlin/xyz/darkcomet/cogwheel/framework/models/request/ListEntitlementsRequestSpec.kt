@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationId
import xyz.darkcomet.cogwheel.framework.primitives.EntitlementId
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.SkuId
import xyz.darkcomet.cogwheel.framework.primitives.UserId

class ListEntitlementsRequestSpec(internal val applicationId: ApplicationId) {
    
    internal var userId: Snowflake? = null
    internal var skuIds: String? = null
    internal var before: Snowflake? = null
    internal var after: Snowflake? = null
    internal var limit: Int? = null
    internal var guildId: Snowflake? = null
    internal var excludeEnded: Boolean? = null
    internal var excludeDeleted: Boolean? = null
    
    fun userId(id: UserId): ListEntitlementsRequestSpec
        = apply { this.userId = id.snowflake }
    
    fun skuIds(vararg id: SkuId): ListEntitlementsRequestSpec
        = apply { this.skuIds = id.joinToString(",") { it.snowflake.toString() } }
    
    fun before(id: EntitlementId): ListEntitlementsRequestSpec
        = apply { this.before = id.snowflake }
    
    fun after(id: EntitlementId): ListEntitlementsRequestSpec
        = apply { this.after = id.snowflake }
    
    fun limit(quantity: Int): ListEntitlementsRequestSpec
        = apply { this.limit = quantity }
    
    fun guildId(id: GuildId): ListEntitlementsRequestSpec
        = apply { this.guildId = id.snowflake }
    
    fun excludeEnded(excludeEnded: Boolean): ListEntitlementsRequestSpec
        = apply { this.excludeEnded = excludeEnded } 
    
    fun excludeDeleted(excludeDeleted: Boolean): ListEntitlementsRequestSpec
        = apply { this.excludeDeleted = excludeDeleted }
    
}