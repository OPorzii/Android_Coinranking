package com.piyawat.android_coinranking.di

import com.piyawat.android_coinranking.service.ApiService
import com.piyawat.android_coinranking.service.NetworkBuilder
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.coinranking.com/v1/public/"

val networkModule = module {

    single { NetworkBuilder().client() }

    single<Converter.Factory> { GsonConverterFactory.create() }

    single <ApiService> { NetworkBuilder().build(BASE_URL, get(), get()) }
}