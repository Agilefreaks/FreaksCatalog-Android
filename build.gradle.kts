import extensions.applyDefault

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins.apply(BuildPlugins.UPDATE_DEPENDENCIES)

allprojects {
    repositories.applyDefault()

    plugins.apply(BuildPlugins.DETEKT)
    plugins.apply(BuildPlugins.KTLINT)
    plugins.apply(BuildPlugins.SPOTLESS)
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:7.0.1")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.20")
        classpath ("com.google.gms:google-services:4.3.10")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts.kts files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
