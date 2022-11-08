package br.com.plantecerto.domain.data.network

import br.com.plantecerto.BuildConfig
import br.com.plantecerto.domain.data.entity.responses.LocationResponse
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

}