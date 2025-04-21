package com.lincollincol.expensestracker.core.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url
            .newBuilder()
            .addQueryParameter("apiKey", BuildConfig.API_KEY)
            .build()
        return chain.proceed(chain.request().newBuilder().url(url).build())
    }
}