import extensions.addProductFlavours

plugins {
    id("commons.android-library")
    id("kotlin-android")
}

android {
    addProductFlavours(this)
}
dependencies {
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
}
