package xyz.darkcomet.cogwheel.framework.primitives

import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse

class Response<T>(val result: T, val raw: CwHttpResponse<*>) {
    // TODO
}