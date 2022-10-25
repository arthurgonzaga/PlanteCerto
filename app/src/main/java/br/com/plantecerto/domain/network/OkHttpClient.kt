package br.com.plantecerto.domain.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

val LoggingInterceptor get() = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
