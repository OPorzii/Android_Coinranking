package com.piyawat.android_coinranking.di

import com.piyawat.android_coinranking.ui.CoinsListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.compat.ScopeCompat.viewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


@ExperimentalCoroutinesApi
val coinMode = module {

    viewModel { CoinsListViewModel(get()) }
}