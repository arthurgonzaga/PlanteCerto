package br.com.plantecerto.domain.data.entity.responses

import java.text.Normalizer

/**
 * Created by Arthur Gonzaga on 10/25/2022
 */
data class LocationResponse(
    private val city: String,
    private val country: String,
    private val region: String,
    private val loc: String,
) {
    val uf get() = siglas[region]!!
    val municipio get() = unaccent(city.uppercase())

    fun unaccent(str: String?): String {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replace("[^\\p{ASCII}]".toRegex(), "")
    }
}

private val siglas = hashMapOf(
    "Acre" to "AC",
    "Alagoas" to "AL",
    "Amapá" to "AP",
    "Amazonas" to "AM",
    "Bahia" to "BA",
    "Ceará" to "CE",
    "Espírito Santo" to "ES",
    "Goiás" to "GO",
    "Maranhão" to "MA",
    "Mato Grosso" to "MT",
    "Mato Grosso do Sul" to "MS",
    "Minas Gerais" to "MG",
    "Pará" to "PA",
    "Paraíba" to "PB",
    "Paraná" to "PR",
    "Pernambuco" to "PE",
    "Piauí" to "PI",
    "Rio de Janeiro" to "RJ",
    "Rio Grande do Norte" to "RN",
    "Rio Grande do Sul" to "RS",
    "Rondônia" to "RO",
    "Roraima" to "RR",
    "Santa Catarina" to "SC",
    "São Paulo" to "SP",
    "Sergipe" to "SE",
    "Tocantins" to "TO",
    "Distrito Federal" to "DF"
)