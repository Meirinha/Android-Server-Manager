package com.mssm.servermanagement.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mssm.servermanagement.db.Server
import com.mssm.servermanagement.general.ServerDBRepository
import com.mssm.servermanagement.general.ServerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ServerViewModel : ViewModel() {

    private val serverRepo : ServerRepository = ServerDBRepository()

    fun saveServer(server : Server){
        viewModelScope.launch(Dispatchers.IO) {
            serverRepo.saveServer(server)
        }

    }
}