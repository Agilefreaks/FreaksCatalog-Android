package com.agilefreaks.freaks_catalog.features.freaks

import java.io.Serializable

/**
* Freak is the data class that represents the properties of a Freak object.
 */
data class Freak(
    val firstName: String,
    val lastName: String,
    val role: String,
    val norm: String,
    val level: String,
    val description: String,
    var image: Int,
    val skills: List<String>,
    val projects: List<String>
) : Serializable {
    // Function for better readability
    fun printFreak(): String {
        return "$firstName $lastName \n$role : $norm, $level \n$description \nskills: $skills \nprojects: $projects"
    }
}