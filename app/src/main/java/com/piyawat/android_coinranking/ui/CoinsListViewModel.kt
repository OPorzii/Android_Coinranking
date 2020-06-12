package com.piyawat.android_coinranking.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.piyawat.android_coinranking.model.Coin
import com.piyawat.android_coinranking.repository.CoinsRepository
import com.piyawat.android_coinranking.service.ApiService
import com.piyawat.android_coinranking.utils.Coroutines
import kotlinx.coroutines.Job

class CoinsListViewModel : ViewModel(){

    private lateinit var job: Job
    private val repository = CoinsRepository(ApiService.instance)


    private val _coinsList = MutableLiveData<List<Coin>>()
    val coinsList: LiveData<List<Coin>>
        get() = _coinsList




    fun fetchCoinsList(offset : Int, limit : Int){
        job = Coroutines.ioThenMain({
            repository.getCoins(offset, limit)
        }, {
            _coinsList.value = it
        })

    }


    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }

}