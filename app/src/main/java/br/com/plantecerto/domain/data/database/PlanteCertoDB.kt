package br.com.plantecerto.domain.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.plantecerto.domain.data.entity.model.CulturaEntity
import br.com.plantecerto.domain.data.entity.model.MunicipioEntity

/**
 * Created by Arthur Gonzaga on 10/25/2022
 */
@Database(entities = [MunicipioEntity::class, CulturaEntity::class], version = 1)
abstract class PlanteCertoDB : RoomDatabase() {
    abstract fun municipioDao(): MunicipioDao
    abstract fun culturasDao(): CulturasDAO
}