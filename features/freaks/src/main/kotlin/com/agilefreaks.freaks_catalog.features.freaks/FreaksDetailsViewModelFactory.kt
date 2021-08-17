package com.agilefreaks.freaks_catalog.features.freaks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FreaksDetailsViewModelFactory(private val repository: FreakDetailsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        DetailsViewModel(repository) as T
}