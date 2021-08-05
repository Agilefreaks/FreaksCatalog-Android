package com.agilefreaks.freaks_catalog.features.freaks

import androidx.lifecycle.ViewModel

class FreaksViewModel: ViewModel() {
    companion object {
        private const val FREAKS_COUNT = 10
    }

    fun loadFreaks(): List<Freak> {
        val freak = Freak(
            "Ciprian",
            "Hotea",
            "Android Intern",
            "Full Time",
            "Beginner",
            "Description",
            0,
            listOf("Kotlin"),
            listOf("Freaks Catalog")
        )
        return mutableListOf<Freak>().apply {
            repeat(FREAKS_COUNT) { this.add(freak) }
        }
    }
}
