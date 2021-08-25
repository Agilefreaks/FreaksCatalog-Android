package com.agilefreaks.freaks_catalog

import com.agilefreaks.freaks_catalog.features.freaks.detailsFragmentModule
import com.agilefreaks.freaks_catalog.features.freaks.freaksFragmentModule
import com.google.android.play.core.splitcompat.SplitCompatApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FreaksCatalogApp : SplitCompatApplication() {
    override fun onCreate() {
        super.onCreate()

        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(freaksFragmentModule)
            modules(detailsFragmentModule)
        }
    }
}
