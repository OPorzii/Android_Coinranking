package com.piyawat.android_coinranking.repository

import androidx.paging.PagedList
import androidx.paging.PagingData
import com.piyawat.android_coinranking.model.Coin
import com.piyawat.android_coinranking.repository.base.BaseRepository
import com.piyawat.android_coinranking.service.ApiService
import kotlinx.coroutines.flow.Flow

class CoinsRepository(private val api : ApiService) : BaseRepository() {

    suspend fun getCoins(offset : Int, limit : Int) : Flow<PagingData<Coin>> {
        val response = safeApiRequest { api.getCoinsList(offset, limit).await() }
        return response.data.coins
    }
}