package xyz.darkcomet.cogwheel.framework.modules

import xyz.darkcomet.cogwheel.core.network.http.rest.ApplicationResource

class ApplicationModule 
internal constructor(private val resource: ApplicationResource){
    
//    suspend fun getCurrent(): Response<Application?> {
//        
//    }
//    
//    suspend fun editCurrent(config: EditCurrentApplicationRequestSpec.() -> Unit): Response<Application?> {
//        val requestSpec = EditCurrentApplicationRequestSpec();
//        requestSpec.apply(config);
//        
//        val response = resource.editCurrentApplication(requestSpec.build())
//        
//    }
//    
}