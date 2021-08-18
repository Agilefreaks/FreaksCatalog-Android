package com.agilefreaks.freaks_catalog

import com.agilefreaks.freaks_catalog.features.freaks.freaksFragmentModule
import com.google.android.play.core.splitcompat.SplitCompatApplication
import org.koin.core.context.startKoin

class FreaksCatalogApp : SplitCompatApplication() {
    override fun onCreate() {
        super.onCreate()

        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        startKoin {
            modules(freaksFragmentModule)
        }
    }
}
