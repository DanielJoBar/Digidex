package com.example.digidex.data.db

import androidx.annotation.WorkerThread
import com.example.digidex.data.db.dao.DigimonDAO
import com.example.digidex.data.db.entities.DigimonEntity
import com.example.digidex.data.repository.Digimon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DigimonLocalRepository @Inject constructor(private val digimonDao: DigimonDAO) {

    val allDigimonList: Flow<List<DigimonEntity>> = digimonDao.getAll()
//Aqui es donde se realizan los casos de usos
    @WorkerThread
    suspend fun insert(listDigimonEntity: List<DigimonEntity>) {
        digimonDao.createListDigimon(listDigimonEntity)
    }
     fun getOne(digimonName:String):DigimonEntity{
        return digimonDao.getOneDigimon(digimonName)
    }

}