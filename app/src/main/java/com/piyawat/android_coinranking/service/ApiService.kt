package com.piyawat.android_coinranking.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.piyawat.android_coinranking.model.CoinsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
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
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(ApiService::class.java)
        }
    }
}