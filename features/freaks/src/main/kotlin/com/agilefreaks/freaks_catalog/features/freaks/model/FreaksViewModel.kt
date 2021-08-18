package com.agilefreaks.freaks_catalog.features.freaks.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agilefreaks.freaks_catalog.features.freaks.FreakList
import com.agilefreaks.freaks_catalog.features.freaks.repository.FreaksRepository
import kotlinx.coroutines.launch

class FreaksViewModel(private val repository: FreaksRepository) : ViewModel() {
    private val _freaks = MutableLiveData<List<FreakList>>().apply {
        viewModelScope.launch {
            value = loadFreaks()
        }
    }
    val freaks: LiveData<List<FreakList>>
        get() = _freaks

    private suspend fun loadFreaks(): List<FreakList> = repository.getFreaksFromApi()
}
