package com.mssm.servermanagement.viewmodel

import androidx.lifecycle.ViewModel
import com.mssm.servermanagement.general.ServerDBRepository
import com.mssm.servermanagement.general.ServerRepository

class ServerListViewModel : ViewModel(){

    private val serverRepo : ServerRepository = ServerDBRepository()

    val serverList = serverRepo.getServers()
}