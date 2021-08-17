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
            "https://bloximages.chicago2.vip.townnews.com/thesouthern.com/content/tncms/assets/v3/editorial/8/47/847c15dd-3d3b-5b08-a3ff-82ab517cf88b/608826f2678a4.image.jpg?crop=339%2C339%2C85%2C0&resize=1200%2C1200&order=crop%2Cresize",
        )
        return mutableListOf<Freak>().apply {
            repeat(10) { this.add(freak) }
        }
    }
}
