import extensions.addProductFlavours

plugins {
    id("commons.android-library")
    id("kotlin-android")
}

android {
    addProductFlavours(this)
}
dependencies {
    implementation("com.google.code.gson:gson:2.8.6")
}
