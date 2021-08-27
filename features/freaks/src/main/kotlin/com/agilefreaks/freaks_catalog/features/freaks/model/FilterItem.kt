
package com.agilefreaks.freaks_catalog.features.freaks.model

import androidx.databinding.ObservableField

interface FilterItem {
    val name: String
    val isChecked: ObservableField<Boolean>
    fun reset()
}

data class Technology(
    val id: String,
    override val name: String,
    override val isChecked: ObservableField<Boolean> = ObservableField(),
) : FilterItem {
    override fun reset() {
        isChecked.set(false)
    }
}

data class Project(
    val id: String,
    override val name: String,
    override val isChecked: ObservableField<Boolean> = ObservableField(),
) : FilterItem {
    override fun reset() {
        isChecked.set(false)
    }
}
