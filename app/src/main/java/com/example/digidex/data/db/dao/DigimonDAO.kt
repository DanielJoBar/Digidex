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
    suspend fun createListDigimon(listPokemonEntity: List<DigimonEntity>)

    @Query("SELECT * FROM digimon")
     fun getAll(): Flow<List<DigimonEntity>>
    @Query("SELECT * FROM digimon WHERE name = :digimonName ")
    fun getOneDigimon(digimonName:String):DigimonEntity
}