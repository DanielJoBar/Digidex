package com.example.digidex.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.digidex.data.repository.Digimon

@Entity(tableName = "local")
data class LocalDataEntity (
    @PrimaryKey(autoGenerate = true) val id: Long,
    val localName:String,
    val localLevels: String,
    val localTypes: String,
    val localAttributes: String
)
fun List<LocalDataEntity>.asLocalDigimonList():List<Digimon>{
    return this.map {
        Digimon(it.id, it.localName,"", it.localLevels, it.localTypes, it.localAttributes)
    }
}