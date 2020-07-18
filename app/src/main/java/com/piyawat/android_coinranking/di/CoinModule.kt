package com.piyawat.android_coinranking.di

import com.piyawat.android_coinranking.repository.CoinsRepository
import com.piyawat.android_coinranking.ui.CoinsListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


@ExperimentalCoroutinesApi
val coinModule = module {

    factory { CoinsRepository(get()) }
    viewModel { CoinsListViewModel(get()) }
}