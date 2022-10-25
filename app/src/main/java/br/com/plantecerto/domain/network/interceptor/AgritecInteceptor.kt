package br.com.plantecerto.domain.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Arthur Gonzaga on 10/25/2022
 */
class AgritecInteceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain
                .request()
                .newBuilder()
                .apply { header("Authorization", accessToken) }
                .build()
        )
    }

    companion object {
        var accessToken: String = ""
    }

}