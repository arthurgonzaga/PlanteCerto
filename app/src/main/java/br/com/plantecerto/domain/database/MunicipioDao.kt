package br.com.plantecerto.domain.database

import androidx.room.Dao
import androidx.room.Query

/**
 * Created by Arthur Gonzaga on 10/25/2022
 */
@Dao
interface MunicipioDao {

    @Query("SELECT codigoIBGE FROM municipios WHERE uf=:uf AND nome=:nome")
    suspend fun getCodigoIbgeFrom(uf: String, nome: String): Int

}