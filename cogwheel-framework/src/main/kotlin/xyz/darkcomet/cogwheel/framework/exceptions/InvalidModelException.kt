package xyz.darkcomet.cogwheel.framework.exceptions

class InvalidModelException : Exception {
    
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
    
}