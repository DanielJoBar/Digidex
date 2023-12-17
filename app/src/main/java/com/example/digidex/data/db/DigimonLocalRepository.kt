package com.example.digidex.data.db

import androidx.annotation.WorkerThread
import com.example.digidex.data.db.dao.DigimonDAO
import com.example.digidex.data.db.dao.LocalDataDao
import com.example.digidex.data.db.entities.DigimonEntity
import com.example.digidex.data.db.entities.LocalDataEntity
import com.example.digidex.data.repository.models.Digimon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DigimonLocalRepository @Inject constructor(private val digimonDao: DigimonDAO,private val localDataDao: LocalDataDao) {
    val allLocalDigimonList: Flow<List<LocalDataEntity?>> = localDataDao.getAll()
    val allDigimonList: Flow<List<DigimonEntity>> = digimonDao.getAll()
//Aqui es donde se realizan los casos de usos
    @WorkerThread
    suspend fun insert(listDigimonEntity: List<DigimonEntity>) {
        digimonDao.createListDigimon(listDigimonEntity)
    }
     fun getOne(digimonName:String):DigimonEntity{
        return digimonDao.getOneDigimon(digimonName)
    }

    @WorkerThread
    suspend fun insertLocal(localDataEntity: LocalDataEntity){
        localDataDao.createLocalDigimon(localDataEntity)
    }

    suspend fun getOneLocal(localName:String):LocalDataEntity{
        return localDataDao.getOneLocal(localName)
    }
}