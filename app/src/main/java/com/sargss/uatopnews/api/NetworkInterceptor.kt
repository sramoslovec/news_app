package com.sargss.uatopnews.api

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        /*try {
            return chain.proceed(request)
        } catch (t: Throwable) {
            throw t
        }*/
        return chain.proceed(request)
    }
}