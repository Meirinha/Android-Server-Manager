package com.mssm.servermanagement.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao

interface ServerDAO {
    @Delete
    suspend fun delete(server : Server)
    @Insert
    suspend fun insert(server : Server)
    @Update
    suspend fun update(server : Server)
    @Query("SELECT * FROM ServerList")
    fun getAll() : LiveData<List<Server>>
}