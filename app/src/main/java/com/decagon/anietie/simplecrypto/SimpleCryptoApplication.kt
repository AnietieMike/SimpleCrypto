package com.decagon.anietie.simplecrypto

import android.app.Application
import com.decagon.anietie.simplecrypto.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SimpleCryptoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        configureDI()
    }

    private fun configureDI() = startKoin {
        androidContext(this@SimpleCryptoApplication)
        modules(appComponent)
    }
}