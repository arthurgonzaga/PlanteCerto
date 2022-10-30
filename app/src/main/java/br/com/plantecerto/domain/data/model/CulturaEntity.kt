package br.com.plantecerto.domain.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Arthur Gonzaga on 10/29/2022
 */
@Entity(tableName = "culturas")
data class CulturaEntity(
    @PrimaryKey
    val idCultura: Int,
    val idCultivar: Int,
    val cultura: String,
    val expectativaProdutividade: Double,
)
