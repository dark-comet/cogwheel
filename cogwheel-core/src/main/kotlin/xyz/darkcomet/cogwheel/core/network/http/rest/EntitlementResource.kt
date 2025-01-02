package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.entities.EntitlementEntity
import xyz.darkcomet.cogwheel.core.network.entities.request.CreateTestEntitlementRequestEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class EntitlementResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun list(
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
    
    fun get(applicationId: Snowflake, entitlementId: Snowflake): CwHttpResponse<EntitlementEntity> {
        TODO("To be implemented")
    }
    
    fun consume(applicationId: Snowflake, entitlementId: Snowflake): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
    fun createTest(
        applicationId: Snowflake, 
        request: CreateTestEntitlementRequestEntity
    ): CwHttpResponse<EntitlementEntity> {
        TODO("To be implemented")
    }
    
    fun deleteTest(applicationId: Snowflake, entitlementId: Snowflake): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
}