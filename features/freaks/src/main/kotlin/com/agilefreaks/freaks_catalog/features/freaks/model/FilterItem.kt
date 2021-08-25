package com.agilefreaks.freaks_catalog.features.freaks.model

import androidx.databinding.ObservableField

class FilterItem(val skillName: String) {
    val isChecked: ObservableField<Boolean> = ObservableField()

    fun reset() {
        isChecked.set(false)
    }
}
