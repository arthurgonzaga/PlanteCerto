package br.com.plantecerto.domain.network

import br.com.plantecerto.domain.network.interceptor.AgritecInteceptor
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
        @Query("idCultura") cultura: Int,
    ): Response<ResponseBody>

}