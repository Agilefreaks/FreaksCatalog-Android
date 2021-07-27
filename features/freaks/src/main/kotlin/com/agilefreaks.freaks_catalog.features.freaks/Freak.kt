package com.agilefreaks.freaks_catalog.features.freaks

import java.io.Serializable

data class Freak(
    val firstName: String,
    val lastName: String,
    val role: String,
    val norm: String,
    val level: String,
    val description: String,
    val image: Int,
    val skills: List<String>,
    val projects: List<String>
) : Serializable {
    companion object {
        private const val serialVersionUID: Long = -1
    }
}
