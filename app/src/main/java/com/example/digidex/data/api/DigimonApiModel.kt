package com.example.digidex.data.api

import com.example.digidex.data.db.entities.DigimonEntity

data class DigimonApiModel(
    val id: Int,
    val name: String,
    val images: String,
    val levels: String,
    val types: String,
    val attributes: String
)
fun List<DigimonApiModel>.digimonEntityList():List<DigimonEntity>{
    return this.map {
        DigimonEntity(
            it.id,
            it.name,
            it.images,
            it.levels,
            it.types,
            it.attributes
        )
    }
}
data class DigimonListApiModel(
    val content:List<DigimonDetailResponse>,
) {
    data class DigimonDetailResponse(
        val id: Int,
        val name: String,
        val images: List<DigimonImageResponse>,
        val levels: List<DigimonLevelResponse>,
        val types: List<DigimonTypeResponse>,
        val attributes: List<DigimonAttributesResponse>
    ){
        fun asApiModel():DigimonApiModel{
            return DigimonApiModel(
                id,
                name,
                images[0].href,
                levels[0].level,
                types[0].type,
                attributes[0].attribute
            )
        }
    }
    data class DigimonImageResponse(
        val href: String,
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