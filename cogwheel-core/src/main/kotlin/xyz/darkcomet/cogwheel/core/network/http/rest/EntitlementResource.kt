package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.entities.EntitlementEntity
import xyz.darkcomet.cogwheel.core.network.entities.request.CreateTestEntitlementRequestEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class EntitlementResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun listEntitlements(
        applicationId: Snowflake,
        userId: Snowflake? = null,
        skuIds: List<Snowflake>? = null,
        before: Snowflake? = null,
        after: Snowflake? = null,
        limit: Int? = null,
        guildId: Snowflake? = null,
        excludeEnded: Boolean? = null,
        excludeDeleted: Boolean? = null
    ): CwHttpResponse<List<EntitlementEntity>> {
        TODO("To be implemented")
    }
    
    fun getEntitlement(
        applicationId: Snowflake, 
        entitlementId: Snowflake
    ): CwHttpResponse<EntitlementEntity> {
        TODO("To be implemented")
    }
    
    fun consumeEntitlement(
        applicationId: Snowflake, 
        entitlementId: Snowflake
    ): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
    fun createTestEntitlement(
        applicationId: Snowflake, 
        request: CreateTestEntitlementRequestEntity
    ): CwHttpResponse<EntitlementEntity> {
        TODO("To be implemented")
    }
    
    fun deleteTestEntitlement(
        applicationId: Snowflake, 
        entitlementId: Snowflake
    ): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
}