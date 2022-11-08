package br.com.plantecerto.domain.data.network

import br.com.plantecerto.domain.data.entity.responses.AgritecGenericResponse
import br.com.plantecerto.domain.data.entity.responses.ProdutividadeResponse
import br.com.plantecerto.domain.data.entity.responses.ZoneamentoResponse
import br.com.plantecerto.domain.data.network.interceptor.AgritecInteceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


/**
 * Created by Arthur Gonzaga on 10/25/2022
 */
interface AgritecService {

    @GET("agritec/v1/health")
    suspend fun checkHealth(): Response<ResponseBody>

    @GET("agritec/v1/zoneamento")
    suspend fun zoneamento(
        @Query("codigoIBGE") codigoIBGE: Int,
        @Query("idCultura") idCultura: Int,
    ): AgritecGenericResponse<List<ZoneamentoResponse>>

    @GET("agritec/v1/produtividade")
    suspend fun produtividade(
        @Query("idCultura") idCultura: Int,
        @Query("idCultivar") idCultivar: Int,
        @Query("codigoIBGE") codigoIBGE: Int,
        @Query("dataPlantio") dataPlantio: String,
        @Query("expectativaProdutividade") expectativaProdutividade: Double,
        @Query("cad") cad: Int = 1,
    ): AgritecGenericResponse<ProdutividadeResponse>

}