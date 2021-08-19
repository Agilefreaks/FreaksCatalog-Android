package com.agilefreaks.freaks_catalog.features.freaks

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agilefreaks.freaks_catalog.features.freaks.repository.FreakDetailsRepository
import com.apollographql.apollo.exception.ApolloException
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: FreakDetailsRepository) : ViewModel() {
    private var _curFreak = MutableLiveData<Freak>()
    val curFreak: LiveData<Freak>
        get() = _curFreak

    private var _freakDetailsLoaded = MutableLiveData(false)
    val freakDetailsLoaded: LiveData<Boolean>
        get() = _freakDetailsLoaded

    fun loadFreak(freakId: String, profilePicture: ImageView) {
        viewModelScope.launch {
            try {
                _curFreak.value = repository.getFreakFromApi(freakId)
                _freakDetailsLoaded.value = true
            }catch (e: ApolloException){
                _curFreak.value?.description = e.message.toString()
            }
        }
    }
}
