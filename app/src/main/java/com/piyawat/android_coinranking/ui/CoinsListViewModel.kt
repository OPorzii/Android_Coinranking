package com.piyawat.android_coinranking.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.PagingData
import com.piyawat.android_coinranking.model.Coin
import com.piyawat.android_coinranking.repository.CoinsRepository
import com.piyawat.android_coinranking.service.ApiService
import com.piyawat.android_coinranking.utils.Coroutines
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

class CoinsListViewModel : ViewModel(){

    private val repository = CoinsRepository(ApiService.instance)

    private var coinsListResult : Flow<PagingData<Coin>>? = null



    suspend fun fetchCoinsList(){
        val result : Flow<PagingData<Coin>> = repository.getCoins().

    }

}