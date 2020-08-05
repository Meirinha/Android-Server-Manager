package com.mssm.servermanagement.general

import androidx.lifecycle.LiveData
import com.mssm.servermanagement.db.Server

interface ServerRepository {

    suspend fun saveServer(server : Server)
    fun getServers() : LiveData<List<Server>>
}