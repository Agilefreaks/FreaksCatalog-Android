package com.agilefreaks.freaks_catalog.features.freaks

import java.io.Serializable

data class Freak(
    val freakId:String,
    val firstName: String,
    val lastName: String,
    val role: String,
    val norm: String,
    val level: String,
    val description: String,
    val photo: String,
    val skills: String,
    val projects: String
)