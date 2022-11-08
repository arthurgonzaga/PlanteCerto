package br.com.plantecerto.domain.di

import br.com.plantecerto.domain.data.network.AgritecService
import br.com.plantecerto.domain.data.network.EmbrapaService
import br.com.plantecerto.domain.data.network.IpInfoService
import br.com.plantecerto.domain.data.network.LoggingInterceptor
import br.com.plantecerto.domain.data.network.interceptor.AgritecInteceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Arthur Gonzaga on 10/25/2022
 */
@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private val agritecClient = OkHttpClient.Builder().addInterceptor(AgritecInteceptor()).addInterceptor(
        LoggingInterceptor
    ).build()
    private val basicClient = OkHttpClient.Builder().addInterceptor(LoggingInterceptor).build()

    @Singleton
    @Provides
    fun providesAgritecService(): AgritecService = Retrofit.Builder()
        .baseUrl("https://api.cnptia.embrapa.br/")
        .client(agritecClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(AgritecService::class.java)

    @Singleton
    @Provides
    fun providesEmbrapaService(): EmbrapaService = Retrofit.Builder()
        .baseUrl("https://api.cnptia.embrapa.br/")
        .client(basicClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(EmbrapaService::class.java)

    @Singleton
    @Provides
    fun providesIpInfoService(): IpInfoService = Retrofit.Builder()
        .baseUrl("https://ipinfo.io")
        .client(basicClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(IpInfoService::class.java)

}