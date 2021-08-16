package com.agilefreaks.freaks_catalog.features.freaks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.agilefreaks.freaks_catalog.features.freaks.model.FreaksViewModel
import com.agilefreaks.freaks_catalog.features.freaks.repository.FreaksRepository

class FreaksViewModelFactory(private val repository: FreaksRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        FreaksViewModel(repository) as T
}
