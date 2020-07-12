package com.piyawat.android_coinranking.repository

import androidx.paging.PagingSource
import com.piyawat.android_coinranking.model.Coin
import com.piyawat.android_coinranking.service.ApiService


private const val PAGE_OFFSET = 0

class CoinsPagingSource(private val api : ApiService) : PagingSource<Int, Coin>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Coin> {
        val position = params.key ?: PAGE_OFFSET

        return try{
            val response = api.getCoinsList(position, params.loadSize)
            val repos = response.data.coins
            LoadResult.Page(
                data = repos,
                prevKey = if(position == PAGE_OFFSET),

            )

        }
    }


}