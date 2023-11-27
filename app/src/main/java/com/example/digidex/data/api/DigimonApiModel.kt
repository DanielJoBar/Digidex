package com.example.digidex.data.api

data class DigimonApiModel(
    val id: Number,
    val name: String,
    val images: String,
    val levels: String,
    val types: String,
    val attributes: String
)
data class DigimonListApiModel(
    val name:List<DigimonNameResponse>,
) {
    data class DigimonNameResponse(
        val name: String,
    )

    data class DigimonDetailResponse(
        val id: Number,
        val name: String,
        val images: DigimonImageResponse,
        val levels: DigimonLevelResponse,
        val types: DigimonTypeResponse,
        val attributes: DigimonAttributesResponse
    )
    data class DigimonImageResponse(
        val href: String,
        val transparent: Boolean,
    )

    data class DigimonLevelResponse(
        val level: String
    )
    data class DigimonTypeResponse(
        val type:String
    )
    data class DigimonAttributesResponse(
        val attribute:String
    )
}