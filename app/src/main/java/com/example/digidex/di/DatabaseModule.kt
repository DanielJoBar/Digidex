package com.example.digidex.di

import android.content.Context
import com.example.digidex.data.db.dao.DigimonDAO
import com.example.digidex.data.db.DigimonDatabase
import com.example.digidex.data.db.DigimonLocalDatabase
import com.example.digidex.data.db.dao.LocalDataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
//A los modulos hay que indicarles el ciclo de vida
@InstallIn(SingletonComponent::class)
object  DatabaseModule {
    //Instancia la base de datos y lo provee
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context):DigimonDatabase{
        return DigimonDatabase.getInstance(context)
    }
    @Singleton
    @Provides
    fun provideLocalDatabase(@ApplicationContext context: Context):DigimonLocalDatabase{
        return DigimonLocalDatabase.getInstance(context)
    }
    @Singleton
    @Provides
    fun providePokemonDao(dataBase: DigimonDatabase): DigimonDAO {
        return dataBase.digimonDao()
    }
    @Singleton
    @Provides
    fun provideLocalPokemonDao(dataBase: DigimonLocalDatabase): LocalDataDao {
        return dataBase.localDataDaoA()
    }
}