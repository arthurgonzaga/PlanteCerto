package br.com.plantecerto.domain.data.repository.agritec

import br.com.plantecerto.domain.data.entity.model.CulturaEntity
import br.com.plantecerto.domain.data.entity.responses.ProdutividadeResponse
import br.com.plantecerto.domain.data.entity.responses.ZoneamentoResponse

/**
 * Created by Arthur Gonzaga on 10/30/2022
 */
interface AgritecRepository {

    suspend fun getZoneamento(
        codigoIBGE: Int,
        idCultura: Int,
    ): List<ZoneamentoResponse>

    suspend fun getProdutividade(
        codigoIBGE: Int,
        dataPlantio: String,
        culturaEntity: CulturaEntity
    ): ProdutividadeResponse?

}