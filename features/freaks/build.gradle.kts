import extensions.addProductFlavours

plugins {
    id("commons.android-library")
    id("kotlin-android")
    id("com.apollographql.apollo").version("2.5.9")
}

android {
    addProductFlavours(this)
}

apollo {
    generateKotlinModels.set(true)
}

dependencies {
    implementation(project(BuildModules.Commons.UI))
}
