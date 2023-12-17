package com.example.digidex.data.repository.models

import com.example.digidex.data.db.entities.LocalDataEntity

data class LocalDigimon(
    val id: Number,
    val name: String,
    val levels: String,
    val types: String,
    val attributes: String
) {
    fun asLocalDataEntity(): LocalDataEntity {
        return LocalDataEntity(
            name,
            levels,
            attributes,
            types
        )
    }
}
