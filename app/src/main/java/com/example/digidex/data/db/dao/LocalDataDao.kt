package com.example.digidex.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digidex.data.db.entities.LocalDataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createLocalDigimon(localDigimon: LocalDataEntity)

    @Query("SELECT * FROM local")
    fun getAll(): Flow<List<LocalDataEntity?>>

    @Query("SELECT * FROM local WHERE localName = :name")
    fun getOneLocal(name:String): LocalDataEntity
}