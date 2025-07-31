package xyz.darkcomet.cogwheel.framework.models.entitles.message

import xyz.darkcomet.cogwheel.core.network.objects.MessageObject

class Message {
    companion object {
        internal fun from(obj: MessageObject): Message {
            return obj.toModel()
        }
    }
}

internal fun MessageObject.toModel(): Message {
    TODO()
}