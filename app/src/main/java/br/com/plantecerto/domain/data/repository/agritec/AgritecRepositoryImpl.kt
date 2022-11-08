package br.com.plantecerto.domain.data.repository.agritec

import br.com.plantecerto.domain.data.entity.model.CulturaEntity
import br.com.plantecerto.domain.data.entity.responses.ProdutividadeResponse
import br.com.plantecerto.domain.data.entity.responses.ZoneamentoResponse
import br.com.plantecerto.domain.data.network.AgritecService
import javax.inject.Inject

/**
 * Created by Arthur Gonzaga on 10/30/2022
 */
class AgritecRepositoryImpl @Inject constructor(
    val service: AgritecService
): AgritecRepository {

    override suspend fun getZoneamento(codigoIBGE: Int, idCultura: Int): List<ZoneamentoResponse> {
        return try {
            service.zoneamento(
                codigoIBGE = codigoIBGE,
                idCultura = idCultura
            ).data
        } catch (e: Exception){
            emptyList()
        }
    }

    override suspend fun getProdutividade(
        codigoIBGE: Int,
        dataPlantio: String,
        culturaEntity: CulturaEntity
    ): ProdutividadeResponse? {
        return try {
            service.produtividade(
                idCultura = culturaEntity.idCultura,
                idCultivar = culturaEntity.idCultivar,
                codigoIBGE = codigoIBGE,
                dataPlantio = dataPlantio,
                expectativaProdutividade = culturaEntity.expectativaProdutividade
            ).data
        } catch (e: Exception){
            null
        }
    }

}