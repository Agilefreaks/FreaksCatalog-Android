package com.agilefreaks.freaks_catalog.features.freaks.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agilefreaks.freaks_catalog.features.freaks.model.FreakDetails
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: FreakDetailsRepository) : ViewModel() {
    private var _freak = MutableLiveData<FreakDetails>()
    val freak: LiveData<FreakDetails>
        get() = _freak

    private var _freakDetailsLoaded = MutableLiveData(false)
    val freakDetailsLoaded: LiveData<Boolean>
        get() = _freakDetailsLoaded

    fun loadFreak(freakId: String) {
        viewModelScope.launch {
            _freak.value = repository.getFreakFromApi(freakId)
            _freakDetailsLoaded.value = true
        }
    }
}
