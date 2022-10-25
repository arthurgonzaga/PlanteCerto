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




    companion object {
        private val client = OkHttpClient.Builder()
            .addInterceptor(AgritecInteceptor())
            .addInterceptor(LoggingInterceptor)
            .build()

        private var _instance: AgritecService? = null
        val instance: AgritecService
            get() {
                return if (_instance != null) {
                    _instance!!
                } else {
                    _instance = Retrofit.Builder()
                        .baseUrl("https://api.cnptia.embrapa.br/")
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(AgritecService::class.java)
                    _instance!!
                }
            }
    }
}