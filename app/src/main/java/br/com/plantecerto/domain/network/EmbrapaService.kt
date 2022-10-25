package br.com.plantecerto.domain.network

import br.com.plantecerto.BuildConfig
import br.com.plantecerto.domain.data.responses.AccessTokenResponse
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
interface EmbrapaService {

    @FormUrlEncoded
    @POST("/token")
    suspend fun getAccessToken(
        @Header("Authorization") auth: String = BuildConfig.AGRITEC_AUTHORIZATION,
        @Field("grant_type") grantType: String = "client_credentials"
    ): AccessTokenResponse

    companion object {
        private val client = OkHttpClient.Builder().addInterceptor(LoggingInterceptor).build()

        private var _instance: EmbrapaService? = null
        val instance: EmbrapaService
            get() {
                return if (_instance != null) {
                    _instance!!
                } else {
                    _instance = Retrofit.Builder()
                        .baseUrl("https://api.cnptia.embrapa.br/")
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(EmbrapaService::class.java)
                    _instance!!
                }
            }

        suspend fun init() {
            AgritecInteceptor.accessToken = instance.getAccessToken().get()
        }
    }
}