package com.agilefreaks.freaks_catalog.features.freaks.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agilefreaks.freaks_catalog.features.freaks.Freak
import com.agilefreaks.freaks_catalog.features.freaks.repository.FreaksRepository
import kotlinx.coroutines.launch

class FreaksViewModel(private val repository: FreaksRepository) : ViewModel() {
    private val _freaks = MutableLiveData<List<Freak>>().apply {
        viewModelScope.launch {
            value = loadFreaks()
        }
    }
    val freaks: LiveData<List<Freak>>
        get() = _freaks

    private suspend fun loadFreaks(): List<Freak> {
        //repository.getFreaksFromApi()
        val freak = Freak(
            "22",
            "Robert",
            "https://i.ibb.co/kK7MQQD/rice.jpg",
        )
        return mutableListOf<Freak>().apply {
            repeat(FREAKS_COUNT) { this.add(freak) }
        }
    }

    companion object {
        private const val FREAKS_COUNT = 10
    }
}
