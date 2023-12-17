package com.example.digidex.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.digidex.data.repository.models.LocalDigimon

@Entity(tableName = "local")
data class LocalDataEntity (
    @PrimaryKey(autoGenerate = true) val id: Long,
    val localName:String,
    val localLevels: String,
    val localTypes: String,
    val localAttributes: String
){
    //Esto lo hago para que no me requiera el id en el constructor ya que es de por si solo autogenerado
    constructor(
        name: String,
        levels: String,
        types: String,
        attributes: String
    ) : this(0, name, levels, types, attributes)
}
fun List<LocalDataEntity?>.asLocalDigimonList():List<LocalDigimon?>{
    return this.map {
        it?.let { it1 -> LocalDigimon(it1.id, it.localName, it.localLevels, it.localTypes, it.localAttributes) }
    }
}