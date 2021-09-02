package com.agilefreaks.freaks_catalog.features.freaks.model

data class Freak(
    val id: String,
    val firstName: String,
    val photo: String,
    val technologyIds: List<String>,
    val projectIds: List<String>
)
