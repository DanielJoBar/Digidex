package com.example.digidex.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton

interface DigimonApi{
    @GET("digimon")
    suspend fun getAll():DigimonListApiModel
    @GET("digimon/{name}")
    suspend fun getDetail(@Path("name")name: String):DigimonListApiModel.DigimonDetailResponse
}
@Singleton
class DigimonService @Inject constructor(){
    private val retrofit = Retrofit.Builder().baseUrl("https://www.digi-api.com/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val  api:DigimonApi = retrofit.create(DigimonApi::class.java)
}