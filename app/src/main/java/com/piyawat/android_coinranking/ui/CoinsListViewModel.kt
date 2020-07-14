package com.piyawat.android_coinranking.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.piyawat.android_coinranking.model.Coin
import com.piyawat.android_coinranking.repository.CoinsRepository
import com.piyawat.android_coinranking.service.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class CoinsListViewModel : ViewModel(){

    private val repository = CoinsRepository(ApiService.instance)

    private var coinsListResult : Flow<PagingData<Coin>>? = null

    suspend fun fetchCoinsList() : Flow<PagingData<Coin>>?{
        val result : Flow<PagingData<Coin>> = repository.getCoins().cachedIn(viewModelScope)

        return result
    }

}