import extensions.addProductFlavours

plugins {
    id("commons.android-library")
    id("kotlin-android")
}

android {
    addProductFlavours(this)
}
object PluginsVersions {
    const val LIFECYCLE_VIEW_MODEL = "4.2.1"
}


dependencies {
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${PluginsVersions.LIFECYCLE_VIEW_MODEL}")
}
