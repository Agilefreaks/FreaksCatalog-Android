package com.agilefreaks.freaks_catalog.features.freaks

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
)