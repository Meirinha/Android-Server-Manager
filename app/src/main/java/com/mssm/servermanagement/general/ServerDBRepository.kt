package com.mssm.servermanagement.general

import androidx.lifecycle.LiveData
import com.mssm.servermanagement.db.DBFactory
import com.mssm.servermanagement.db.Server

class ServerDBRepository : ServerRepository {

    private val serverDAO = DBFactory.getDB().getDAO()

    suspend override fun saveServer(server: Server) {
        serverDAO.insert(server)
    }

    override fun getServers(): LiveData<List<Server>> {
        return serverDAO.getAll()
    }
}