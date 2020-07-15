package com.piyawat.android_coinranking.utils

import androidx.lifecycle.ViewModelProvider
import com.piyawat.android_coinranking.repository.CoinsRepository
import com.piyawat.android_coinranking.service.ApiService
import com.piyawat.android_coinranking.ui.CoinListViewModelFactory

object Injection {

    private fun provideCoinRepository(): CoinsRepository {
        return CoinsRepository(ApiService.instance)
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return CoinListViewModelFactory(provideCoinRepository())
    }

}