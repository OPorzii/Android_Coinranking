package com.piyawat.android_coinranking.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class NetworkBuilder(){

    fun client(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level = Level.BASIC
        return OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(logger)
            .build()
    }

    inline fun <reified T> build(url : String, client: OkHttpClient, converterFactory: Converter.Factory) : T {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(url)
            .addConverterFactory(converterFactory)
            .build()
            .create(T::class.java)
    }
}
