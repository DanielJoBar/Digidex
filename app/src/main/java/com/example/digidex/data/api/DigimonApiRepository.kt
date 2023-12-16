package com.example.digidex.data.api

import javax.inject.Inject

class DigimonApiRepository @Inject constructor(private val service:DigimonService){
    //Aqui se alojan los métodos con respecto a la api
    suspend fun getAll(): List<DigimonApiModel> {
        val list = service.api.getAll(30)
        return list.content.map {
            service.api.getDetail(it.name).asApiModel()
        }
    }
}