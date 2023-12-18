package com.example.digidex.data.repository

import com.example.digidex.data.api.digimonEntityList
import com.example.digidex.data.db.DigimonLocalRepository
import com.example.digidex.data.db.entities.DigimonEntity
import com.example.digidex.data.db.entities.LocalDataEntity
import com.example.digidex.data.db.entities.asLocalDigimonList
import com.example.digidex.data.repository.models.LocalDigimon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDigimonRepository @Inject constructor(private val localRepository: DigimonLocalRepository){

    val allLocalDigimon: Flow<List<LocalDigimon?>>
        get(){
            return  localRepository.allLocalDigimonList.map {
                    list -> list.asLocalDigimonList()
            }
        }

    //Actualiza la base de datos local
    suspend fun updateDatabase(newDigimon:LocalDigimon)= withContext(Dispatchers.IO){
        localRepository.insertLocal(newDigimon.asLocalDataEntity())
    }
    suspend fun getOneFromDatabase(digimonName:String): LocalDataEntity {
        return withContext(Dispatchers.IO) {
            localRepository.getOneLocal(digimonName)
        }
    }
}