import extensions.addProductFlavours

plugins {
    id("commons.android-library")
    id("kotlin-android")
    id("com.apollographql.apollo").version("2.5.9")
}

android {
    addProductFlavours(this)
}

dependencies {
    implementation(project(BuildModules.Commons.UI))
    implementation(project(BuildModules.Commons.VIEWS))
}

apollo {
    generateKotlinModels.set(true)
}

dependencies {
    implementation(project(BuildModules.Commons.UI))
    testImplementation(project(mapOf("path" to ":features:freaks")))
}
