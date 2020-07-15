package com.piyawat.android_coinranking.repository

import androidx.paging.PagedList
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.piyawat.android_coinranking.model.Coin
import com.piyawat.android_coinranking.service.ApiService
import kotlinx.coroutines.flow.Flow

class CoinsRepository(private val api : ApiService) {

    suspend fun getCoins() : Flow<PagingData<Coin>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = PAGE_SIZE , enablePlaceholders = true),
            pagingSourceFactory = { CoinsPagingSource(api) }
        ).flow
    }

    companion object {
        private const val PAGE_SIZE = 10
    }


}