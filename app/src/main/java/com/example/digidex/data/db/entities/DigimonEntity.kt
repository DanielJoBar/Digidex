package com.example.digidex.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.digidex.data.repository.Digimon

@Entity(tableName = "digimon")
data class DigimonEntity(
    //Columnas de la tabla digimon
    val id: Int,
    @PrimaryKey val name: String,
    val images: String,
    val levels: String,
    val types: String,
    val attributes: String
)
    fun List<DigimonEntity>.asDigimonList():List<Digimon>{
        return this.map {
            Digimon(it.id, it.name, it.images, it.levels, it.types, it.attributes)
    }
}
