package xyz.darkcomet.cogwheel.framework.models.entitles.webhook

import xyz.darkcomet.cogwheel.core.network.objects.WebhookObject

class Webhook {
    companion object {
        internal fun from(obj: WebhookObject): Webhook {
            return obj.toModel()
        }
    }
}

internal fun WebhookObject.toModel(): Webhook {
    TODO()
}