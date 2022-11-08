package br.com.plantecerto.domain.data.entity.responses

import com.google.gson.annotations.SerializedName

/**
 * Created by Arthur Gonzaga on 10/30/2022
 */
data class ProdutividadeResponse(
    @SerializedName("produtividadeMediaMunicipio")
    private val produtividadeMediaMunicipio: List<Double>
) {
    val mediaMunicipio get() = produtividadeMediaMunicipio.average()
}