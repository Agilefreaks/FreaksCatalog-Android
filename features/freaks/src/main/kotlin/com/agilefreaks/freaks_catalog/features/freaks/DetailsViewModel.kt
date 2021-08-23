package com.agilefreaks.freaks_catalog.features.freaks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agilefreaks.freaks_catalog.features.freaks.repository.FreakDetailsRepository
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: FreakDetailsRepository) : ViewModel() {
    private var _curFreak = MutableLiveData<FreakD>()
    val curFreak: LiveData<FreakD>
        get() = _curFreak

    private var _freakDetailsLoaded = MutableLiveData(false)
    val freakDetailsLoaded: LiveData<Boolean>
        get() = _freakDetailsLoaded

    fun loadFreak(freakId: String) {
        viewModelScope.launch {
            _curFreak.value = repository.getFreakFromApi(freakId)
            _freakDetailsLoaded.value = true
        }
    }
}
