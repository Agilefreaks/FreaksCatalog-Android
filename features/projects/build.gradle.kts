import extensions.addProductFlavours

plugins {
    id("commons.android-library")
    id("kotlin-android")
}

android {
    addProductFlavours(this)
}

dependencies {
    implementation(project(BuildModules.Commons.UI))
}