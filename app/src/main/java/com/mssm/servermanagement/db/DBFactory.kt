package com.mssm.servermanagement.db

import androidx.room.Room
import com.mssm.servermanagement.MyApplication

object DBFactory {

    private var serverDB : ServerDB? = null

    fun getDB() : ServerDB{

        if(serverDB == null) {
            serverDB =
                Room.databaseBuilder(MyApplication.context, ServerDB::class.java, "SeverListDB")
                    .build()
        }
        return serverDB!!
    }
}