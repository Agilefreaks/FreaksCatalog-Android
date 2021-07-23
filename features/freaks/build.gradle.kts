import extensions.addProductFlavours
import dependencies.Dependencies

plugins {
    id("commons.android-dynamic-feature")
    id("kotlin-android")
}

android {
    addProductFlavours(this)
}
dependencies {
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation ("com.squareup.picasso:picasso:2.71828")
}
