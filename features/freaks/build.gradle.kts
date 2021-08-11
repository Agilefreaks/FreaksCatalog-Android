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
    const val APOLLO_VERSION = "2.5.9"
}

dependencies {
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${PluginsVersions.LIFECYCLE_VIEW_MODEL}")
    implementation("com.apollographql.apollo:apollo-runtime:${PluginsVersions.APOLLO_VERSION}")
    implementation("com.apollographql.apollo:apollo-coroutines-support:${PluginsVersions.APOLLO_VERSION}")
   // implementation("com.squareup.picasso:picasso:2.71828")
}

apollo {
    generateKotlinModels.set(true)
}
