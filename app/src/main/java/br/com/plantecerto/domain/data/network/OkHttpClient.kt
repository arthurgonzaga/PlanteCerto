package br.com.plantecerto.domain.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

val LoggingInterceptor get() = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
