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
    val name:List<DigimonNameResponse>,
) {
    data class DigimonNameResponse(
        val name: String,
    )

    data class DigimonDetailResponse(
        val id: Int,
        val name: String,
        val images: DigimonImageResponse,
        val levels: DigimonLevelResponse,
        val types: DigimonTypeResponse,
        val attributes: DigimonAttributesResponse
    ){
        fun asApiModel():DigimonApiModel{
            return DigimonApiModel(
                id,
                name,
                images.href,
                levels.level,
                types.type,
                attributes.attribute
            )
        }
    }
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