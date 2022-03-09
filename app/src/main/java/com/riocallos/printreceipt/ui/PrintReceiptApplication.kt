package com.riocallos.printreceipt.ui

import android.app.Application
import com.riocallos.printreceipt.di.appModule
import com.riocallos.printreceipt.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Application class for initializing config, initiations and dependencies
 */
class PrintReceiptApplication : Application() {

    /**
     * initializes the koin dependencies when application is created
     */
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PrintReceiptApplication)
            modules(listOf(appModule, viewModelModule))
        }

    }

}