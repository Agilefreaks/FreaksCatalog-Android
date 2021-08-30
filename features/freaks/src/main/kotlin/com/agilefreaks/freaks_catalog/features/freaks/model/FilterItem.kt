package com.agilefreaks.freaks_catalog.features.freaks.model

import androidx.databinding.ObservableField

data class FilterItem(
    val id: String,
    val name: String,
    val isChecked: ObservableField<Boolean> = ObservableField()
) {
    fun reset() {
        isChecked.set(false)
    }
}
