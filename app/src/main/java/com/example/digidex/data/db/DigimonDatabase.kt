package com.example.digidex.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.digidex.data.db.dao.DigimonDAO
import com.example.digidex.data.db.entities.DigimonEntity

@Database(entities = [DigimonEntity::class], version = 1)
abstract class DigimonDatabase():RoomDatabase(){
    abstract fun digimonDao(): DigimonDAO

    companion object {
        @Volatile
        private var INSTANCE: DigimonDatabase? = null

        fun  getInstance(context: Context): DigimonDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { digimonDatabase ->  INSTANCE = digimonDatabase}
            }
        }

        private fun buildDatabase(context: Context): DigimonDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                DigimonDatabase::class.java,
                "digimon_db"
            ).build()
        }
    }
}