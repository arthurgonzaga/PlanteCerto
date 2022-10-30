package br.com.plantecerto.domain.database

import androidx.room.Dao
import androidx.room.Query
import br.com.plantecerto.domain.data.model.CulturaEntity

/**
 * Created by Arthur Gonzaga on 10/29/2022
 */
@Dao
interface CulturasDAO {

    @Query("SELECT * FROM culturas WHERE idCultivar = :idCultivar")
    suspend fun getCulturaBy(idCultivar: Int): CulturaEntity

}