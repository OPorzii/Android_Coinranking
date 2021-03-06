package com.piyawat.android_coinranking.repository

import android.util.Log
import androidx.paging.PagingSource
import com.piyawat.android_coinranking.model.Coin
import com.piyawat.android_coinranking.service.ApiService
import retrofit2.HttpException
import java.io.IOException


private const val PAGE_INDEX = 0

class CoinsPagingSource(private val api : ApiService, private val query: String?) : PagingSource<Int, Coin>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Coin> {
        val position = params.key ?: PAGE_INDEX
        Log.d("PARAM_KEY_VALUE", "KEY="+params.key+"\nLoadSize="+params.loadSize)
        return try{
            val response = api.getCoinsList(query, position, params.loadSize)
            val repos = response.data.coins
            LoadResult.Page(
                data = repos,
                prevKey = if(position == PAGE_INDEX) null else position - params.loadSize,
                nextKey = if (repos.isEmpty() or (repos.size < params.loadSize)) null else position + params.loadSize
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}


