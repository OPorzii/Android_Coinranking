package com.piyawat.android_coinranking.service

import com.piyawat.android_coinranking.model.CoinsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL = "https://api.coinranking.com/v1/public/"

interface ApiService {

    @GET("coins")
    suspend fun getCoinsList(@Query("offset") offset : Int, @Query("limit") limit : Int): CoinsResponse

    companion object {
        val instance: ApiService by lazy {
            val logger = HttpLoggingInterceptor()
            logger.level = Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(ApiService::class.java)
        }
    }
}