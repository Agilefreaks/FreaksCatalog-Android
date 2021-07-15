package com.agilefreaks.freaks_catalog

import com.google.android.play.core.splitcompat.SplitCompatApplication

class FreaksCatalogApp : SplitCompatApplication() {
    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        // TODO: init koin here
    }
}