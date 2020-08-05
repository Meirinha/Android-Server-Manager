package com.mssm.servermanagement.network

import android.media.session.MediaSession
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface HttpInterface {

    @POST("/api/startServer")
    fun startServer() : Call<Unit>

    @POST("/api/stopServer")
    fun stopServer() : Call<Unit>

    @POST("/api/updateServer")
    fun updateServer() : Call<Unit>

    @GET("/api/serverStatus")
    fun serverStatus() : Call<ServerStatus>

    @GET("/api/connectedPlayerList")
    fun connectedPlayerList() : Call<ConnectedPlayerList>

    @POST("/api/kickPlayer")
    fun kickPlayer(@Body player : Player) : Call<Unit>

    @DELETE("/api/deleteServer")
    fun deleteServer() : Call<Unit>

    @POST("/api/login")
    fun login(@Body info : LoginInfo) : Call<Token>

}