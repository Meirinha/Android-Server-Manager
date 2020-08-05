package com.mssm.servermanagement.viewmodel

import androidx.lifecycle.*
import com.mssm.servermanagement.db.Server
import com.mssm.servermanagement.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ServerDetailsViewModel : ViewModel() {

    private lateinit var server : Server
    val isOnline: MutableLiveData<Boolean> = MutableLiveData()
    val playerList: MutableLiveData<List<Player>> = MutableLiveData()
    private var api: HttpInterface? = null

    fun setServer(server: Server) {
        this.server = server
        api = NetworkFactory.createNetwork(server.URL)


        viewModelScope.launch(Dispatchers.IO) {
            login()
            status()
            playerList()
        }


    }

    private fun login() {
        val token = api?.login(LoginInfo(server.password, server.username))?.execute()?.body()
        token?.let {
            NetworkFactory.receiveToken(token)
        }
    }

    private fun status() {
        isOnline.postValue(api?.serverStatus()?.execute()?.body()?.online ?: false)

    }

    private fun playerList() {

        playerList.postValue(api?.connectedPlayerList()?.execute()?.body()?.players ?: emptyList())

    }

    fun kickPlayer(player: Player) {

        api?.kickPlayer(player)
        playerList()

    }

    fun startStop(){
        if(isOnline.value == true){
            stopServer()
        }
        else{
            startServer()
        }
    }

    private fun startServer(){

        viewModelScope.launch(Dispatchers.IO) {
            api?.startServer()?.execute()
        }
    }

    private fun stopServer(){

        viewModelScope.launch(Dispatchers.IO) {
            api?.stopServer()?.execute()
        }
    }

    fun updateServer(){

        viewModelScope.launch(Dispatchers.IO) {
            api?.updateServer()?.execute()
        }
    }

    fun deleteServer(){

        viewModelScope.launch(Dispatchers.IO) {
            api?.deleteServer()?.execute()
        }
    }


}