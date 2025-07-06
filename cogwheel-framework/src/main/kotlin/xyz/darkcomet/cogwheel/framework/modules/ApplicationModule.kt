package xyz.darkcomet.cogwheel.framework.modules

import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.http.rest.ApplicationResource
import xyz.darkcomet.cogwheel.core.network.objects.ApplicationObject
import xyz.darkcomet.cogwheel.framework.models.entitles.application.Application
import xyz.darkcomet.cogwheel.framework.models.entitles.application.toModel
import xyz.darkcomet.cogwheel.framework.models.request.EditCurrentApplicationRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.Response

class ApplicationModule 
internal constructor(private val resource: ApplicationResource) {
    
    suspend fun getCurrent(): Response<Application> {
        val rawResponse: CwHttpResponse<ApplicationObject?> = resource.getCurrentApplication();
        val model: Application? = rawResponse.data?.toModel();
        
        return Response(model, rawResponse);
    }
    
    suspend fun editCurrent(config: EditCurrentApplicationRequestSpec.() -> Unit): Response<Application> {
        val requestSpec = EditCurrentApplicationRequestSpec();
        requestSpec.apply(config);

        val request = requestSpec.build();

        val rawResponse: CwHttpResponse<ApplicationObject?> = resource.editCurrentApplication(request)
        val model: Application? = rawResponse.data?.toModel();
        
        return Response(model, rawResponse);
    }

}