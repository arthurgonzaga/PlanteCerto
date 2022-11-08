package br.com.plantecerto.domain.data.database

import androidx.room.Dao
import androidx.room.Query
import br.com.plantecerto.domain.data.entity.model.CulturaEntity

/**
 * Created by Arthur Gonzaga on 10/29/2022
 */
@Dao
interface CulturasDAO {

    @Query("SELECT * FROM culturas WHERE UPPER(cultura) = UPPER(:name)")
    suspend fun getCulturaBy(name: String): CulturaEntity

}