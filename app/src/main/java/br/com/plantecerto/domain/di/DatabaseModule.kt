package br.com.plantecerto.domain.di

import android.content.Context
import androidx.room.Room
import br.com.plantecerto.domain.database.CulturasDAO
import br.com.plantecerto.domain.database.MunicipioDao
import br.com.plantecerto.domain.database.PlanteCertoDB
import br.com.plantecerto.domain.network.AgritecService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Arthur Gonzaga on 10/25/2022
 */
@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun providesPlanteCertoDB(
        @ApplicationContext context: Context
    ): PlanteCertoDB = Room
        .databaseBuilder(context, PlanteCertoDB::class.java, "PlanteCerto.db")
        .createFromAsset("database/plantecerto.db")
        .build()

    @Singleton
    @Provides
    fun providesMunicipioDao(
        database: PlanteCertoDB
    ): MunicipioDao = database.municipioDao()

    @Singleton
    @Provides
    fun providesCulturasDao(
        database: PlanteCertoDB
    ): CulturasDAO = database.culturasDao()

}