package com.piyawat.android_coinranking

import android.app.Application
import com.piyawat.android_coinranking.di.coinModule
import com.piyawat.android_coinranking.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MainApplication : Application()  {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()
            // use the Android context given there
            androidContext(this@MainApplication)
            // module list
            modules(listOf(networkModule, coinModule))
        }
    }
}