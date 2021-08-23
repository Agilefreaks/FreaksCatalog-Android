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

object PluginsVersions {
    const val LIFECYCLE_VIEW_MODEL = "2.3.1"
}

apollo {
    generateKotlinModels.set(true)
}

dependencies {
    implementation(project(BuildModules.Commons.UI))
}
