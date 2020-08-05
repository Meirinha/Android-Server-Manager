package com.mssm.servermanagement.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkFactory {

    private val headerManager : HeaderManager = HeaderManager()

    fun createNetwork(url : String) : HttpInterface?{

        try {
            val retrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).client(
                buildClient()).build()
            return retrofit.create(HttpInterface::class.java)
        }

        catch (exception : IllegalArgumentException){
            return null
        }
    }

    private fun buildClient() : OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(headerManager).build()
    }

    fun receiveToken(token : Token){
        headerManager.token = token
    }

}