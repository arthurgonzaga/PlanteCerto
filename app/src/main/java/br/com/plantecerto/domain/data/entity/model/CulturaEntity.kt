package br.com.plantecerto.domain.data.entity.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Arthur Gonzaga on 10/29/2022
 */
@Entity(tableName = "culturas")
data class CulturaEntity(
    @PrimaryKey
    val idCultivar: Int,
    val idCultura: Int,
    val cultura: String,
    val expectativaProdutividade: Double,
)
