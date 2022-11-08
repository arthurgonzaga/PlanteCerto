package br.com.plantecerto.domain.usecase.generatedtext

import br.com.plantecerto.domain.data.entity.responses.ProdutividadeResponse
import br.com.plantecerto.domain.data.entity.responses.ZoneamentoResponse
import br.com.plantecerto.domain.data.entity.responses.ZoneamentoResponse.Companion.APP_FORMAT

/**
 * Created by Arthur Gonzaga on 10/31/2022
 */
class GenerateTextUseCase {

    operator fun invoke(
        zoneamentoList: List<ZoneamentoResponse>,
        produtividade: ProdutividadeResponse?
    ): String {
        val result = mutableListOf<String>()
        val stringBuilder = StringBuilder()

        if (zoneamentoList.isNotEmpty()) {
            val dateInicio = zoneamentoList[0].getInicioSafraDate(pattern = APP_FORMAT)
            val dateFim = zoneamentoList[0].getFimSafraDate(pattern = APP_FORMAT)

            stringBuilder.append("Recomendamos dar inicio a plantação em $dateInicio para solo ")

            val size = zoneamentoList.size
            val hasOne = size == 1
            val hasTwo = size == 2
            val hasMoreThanTwo = size > 2
            zoneamentoList.map { it.solo.lowercase() }.distinct().apply {
                forEachIndexed { index, solo ->
                    val isLastIndex = index == lastIndex
                    val isSecondLastIndex = index == lastIndex-1
                    when {
                        (hasTwo || hasMoreThanTwo) && !isLastIndex && !isSecondLastIndex -> {
                            stringBuilder.append("$solo, ")
                        }
                        (hasTwo || hasMoreThanTwo) && !isLastIndex && isSecondLastIndex -> {
                            stringBuilder.append("$solo e ")
                        }
                        hasOne || isLastIndex -> {
                            stringBuilder.append("$solo ")
                        }
                    }
                }
            }
            if(produtividade != null) {
                stringBuilder.append(
                    "para se obter uma produtividade média de ${"%.3f".format(produtividade.mediaMunicipio)} kg/ha, "
                )
            }
            stringBuilder.append("com fim da safra em $dateFim.")

        }
        return stringBuilder.toString()
    }

}

