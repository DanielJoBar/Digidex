package com.example.digidex.data.repository

import com.example.digidex.data.api.DigimonApiRepository
import com.example.digidex.data.api.digimonEntityList
import com.example.digidex.data.db.DigimonLocalRepository
import com.example.digidex.data.db.entities.DigimonEntity
import com.example.digidex.data.db.entities.asDigimonList
import com.example.digidex.data.repository.models.Digimon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DigimonRepository @Inject constructor(
    private val localRepository: DigimonLocalRepository,
    private val apiRepository: DigimonApiRepository
){
    val allDigimon: Flow<List<Digimon>>
        get(){
            return  localRepository.allDigimonList.map {
                list -> list.asDigimonList()
            }
        }

    //Actualiza la base de datos local
    suspend fun updateDatabase()= withContext(Dispatchers.IO){
        withContext(Dispatchers.IO) {
            val apiDigimon = apiRepository.getAll()
            localRepository.insert(apiDigimon.digimonEntityList())
        }
    }
     suspend fun getOneFromDatabase(digimonName:String):DigimonEntity{
         return withContext(Dispatchers.IO) {
             localRepository.getOne(digimonName)
         }
    }
}