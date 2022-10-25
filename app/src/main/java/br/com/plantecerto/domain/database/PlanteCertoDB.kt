package br.com.plantecerto.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.plantecerto.domain.data.model.MunicipioEntity

/**
 * Created by Arthur Gonzaga on 10/25/2022
 */
@Database(entities = [MunicipioEntity::class], version = 1)
abstract class PlanteCertoDB : RoomDatabase() {
    abstract fun municipioDao(): MunicipioDao
}