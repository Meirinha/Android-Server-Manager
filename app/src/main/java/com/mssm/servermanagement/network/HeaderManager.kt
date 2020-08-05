package com.mssm.servermanagement.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderManager() : Interceptor {

    var token : Token? = null


    override fun intercept(chain: Interceptor.Chain): Response {

        return token?.token?.let {
            chain.proceed(
                chain.request().newBuilder().addHeader("Authorization", it).build()
            )
        } ?: chain.proceed(chain.request())

    }
}