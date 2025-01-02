package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.entities.InviteEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse

class InviteResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun get(inviteCode: String): CwHttpResponse<InviteEntity> {
        TODO("To be implemented")
    }
    
    fun delete(inviteCode: String): CwHttpResponse<InviteEntity> {
        TODO("To be implemented")
    }
    
}