package br.com.plantecerto.domain.network

import br.com.plantecerto.BuildConfig
import br.com.plantecerto.domain.data.responses.LocationResponse
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Arthur Gonzaga on 10/25/2022
 */
interface IpInfoService {


    @GET("/")
    suspend fun getLocation(
        @Query("token") token: String = BuildConfig.IP_INFO_TOKEN
    ): LocationResponse


    companion object {
        private val client = OkHttpClient.Builder()
            .addInterceptor(LoggingInterceptor)
            .build()

        private var _instance: IpInfoService? = null
        val instance: IpInfoService
            get() {
                return if (_instance != null) {
                    _instance!!
                } else {
                    _instance = Retrofit.Builder()
                        .baseUrl("https://ipinfo.io")
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(IpInfoService::class.java)
                    _instance!!
                }
            }
    }
}