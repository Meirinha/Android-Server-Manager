package com.mssm.servermanagement.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Server::class], version = 1)

abstract class ServerDB : RoomDatabase() {

    abstract fun getDAO() : ServerDAO

}