package br.com.plantecerto.domain.data.entity.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Arthur Gonzaga on 10/25/2022
 */
@Entity(tableName = "municipios")
data class MunicipioEntity(
    @PrimaryKey
    val codigoIBGE: Int,
    val nome: String,
    val uf: String,
    val dataAtualizacao: String
)
