package com.example.digidex.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import androidx.room.OnConflictStrategy
import com.example.digidex.data.db.entities.DigimonEntity

@Dao
interface DigimonDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(listPokemonEntity: List<DigimonEntity>)
    @Query("SELECT * FROM digimon")
     fun getAll(): Flow<List<DigimonEntity>>

}