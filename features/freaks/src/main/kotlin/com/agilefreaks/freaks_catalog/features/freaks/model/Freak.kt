package com.agilefreaks.freaks_catalog.features.freaks.model

data class Freak(
    val id: String,
    val firstName: String,
    val photo: String,
    val technologyId: List<String>,
    val projectId: List<String>
)
