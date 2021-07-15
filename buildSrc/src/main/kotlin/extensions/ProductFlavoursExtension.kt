package extensions

import com.android.build.gradle.TestedExtension

fun addProductFlavours(extension: TestedExtension) {
    val apiUrlConfigFieldName = "API_BASE_URL"
    val apiUrlConfigFieldType = "String"
    val flavourDimension = "env"
    val prod = "prod"
    val staging = "staging"
    val prodUrl = "\"\""
    val stagingUrl = "\"\""

    extension.flavorDimensions(flavourDimension)

    extension.productFlavors {
        create(prod) {
            dimension = flavourDimension
            buildConfigField(apiUrlConfigFieldType, apiUrlConfigFieldName, prodUrl)
        }

        create(staging) {
            dimension = flavourDimension
            buildConfigField(apiUrlConfigFieldType, apiUrlConfigFieldName, stagingUrl)
        }
    }
}
