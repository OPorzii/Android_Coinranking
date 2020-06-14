package com.piyawat.android_coinranking.repository

import com.piyawat.android_coinranking.model.Coin
import com.piyawat.android_coinranking.repository.base.BaseRepository
import com.piyawat.android_coinranking.service.ApiService

class CoinsRepository(private val api : ApiService) : BaseRepository() {

    suspend fun getCoins(offset : Int, limit : Int) : List<Coin>? {
        val response = safeApiRequest { api.getCoinsList(offset, limit).await() }
        return response.data.coins
    }
}