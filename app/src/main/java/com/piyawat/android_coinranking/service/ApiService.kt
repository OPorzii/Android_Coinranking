package com.piyawat.android_coinranking.service

import com.piyawat.android_coinranking.model.CoinsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("coins")
    suspend fun getCoinsList(@Query("offset") offset : Int, @Query("limit") limit : Int): CoinsResponse


}