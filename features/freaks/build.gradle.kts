import extensions.addProductFlavours

plugins {
    id("commons.android-library")
    id("kotlin-android")
    id("com.apollographql.apollo").version("2.5.9")
}

android {
    addProductFlavours(this)
}
object PluginsVersions {
    const val LIFECYCLE_VIEW_MODEL = "2.3.1"
}

apollo {
    generateKotlinModels.set(true)
}
