package com.agilefreaks.freaks_catalog.features.projects.details

import android.nfc.tech.TagTechnology

data class ProjectDetails (
    val id: String,
    val description: String,
    val photo: String,
    val freaks: String,
    val technology: String
)
