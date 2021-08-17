package com.agilefreaks.freaks_catalog.features.freaks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception

class DetailsViewModel(private val repository: FreakDetailsRepository) : ViewModel() {
    private var _curFreak = MutableLiveData<Freak>()
    val freak: LiveData<Freak>
        get() = _curFreak

    private var _freakDetailsLoaded = MutableLiveData(false)
    val freakDetailsLoaded: LiveData<Boolean>
        get() = _freakDetailsLoaded

    fun loadFreak(freakId: String) {
        viewModelScope.launch {
            try {
                _curFreak.value = repository.getFreakFromApi(freakId)
                _freakDetailsLoaded.value = true
            }catch (e: Exception){
                _freakDetailsLoaded.value = false
            }
        }
    }
}
