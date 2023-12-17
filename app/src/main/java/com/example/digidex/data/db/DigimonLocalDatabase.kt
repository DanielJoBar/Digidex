package com.example.digidex.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.digidex.data.db.dao.LocalDataDao
import com.example.digidex.data.db.entities.LocalDataEntity

@Database(entities = [LocalDataEntity::class], version = 1)
abstract class DigimonLocalDatabase: RoomDatabase() {
    abstract fun localDataDaoA(): LocalDataDao
    companion object {
        @Volatile
        private var INSTANCE: DigimonLocalDatabase? = null

        fun  getInstance(context: Context): DigimonLocalDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { digimonLocalDatabase ->  INSTANCE = digimonLocalDatabase}
            }
        }

        private fun buildDatabase(context: Context): DigimonLocalDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                DigimonLocalDatabase::class.java,
                "local_digimon_db"
            ).build()
        }
    }
}