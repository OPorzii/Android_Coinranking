package com.piyawat.android_coinranking.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.piyawat.android_coinranking.repository.CoinsRepository

class CoinListViewModelFactory(private val repository: CoinsRepository) : ViewModelProvider.Factory  {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CoinsListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CoinsListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}