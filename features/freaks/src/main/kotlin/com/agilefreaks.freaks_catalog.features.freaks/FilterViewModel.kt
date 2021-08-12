package com.agilefreaks.freaks_catalog.features.freaks

import androidx.lifecycle.ViewModel

class FilterViewModel : ViewModel() {

    fun loadFilters(activeFilter: String) =
        if (activeFilter == SKILL_FILTER) {
            listOf("Android", "Kotlin", "Other Skill", "iOS", "Ruby", "QA")
        } else {
            listOf("Freaks Catalog", "Proj2", "Tutorial", "Altkeva")
        }

    companion object {
        private const val SKILL_FILTER = "Skills"
    }
}
