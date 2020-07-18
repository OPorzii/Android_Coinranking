package com.piyawat.android_coinranking.ui

import android.util.Log
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
class CoinsListViewModel(private val repository: CoinsRepository) : ViewModel(){

    private var coinsListResult : Flow<PagingData<Coin>>? = null

    suspend fun fetchCoinsList() : Flow<PagingData<Coin>>?{
        val result : Flow<PagingData<Coin>> = repository.getCoins().cachedIn(viewModelScope)
        return result
    }

    fun testViewMo(){
        Log.d("VIEW_MODEL", "OK")
    }

}