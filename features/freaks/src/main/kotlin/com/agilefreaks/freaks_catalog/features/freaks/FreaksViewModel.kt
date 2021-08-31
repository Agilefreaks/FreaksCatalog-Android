package com.agilefreaks.freaks_catalog.features.freaks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agilefreaks.freaks_catalog.features.freaks.filter.FilterRepository
import com.agilefreaks.freaks_catalog.features.freaks.model.Freak
import com.agilefreaks.freaks_catalog.features.freaks.model.Project
import com.agilefreaks.freaks_catalog.features.freaks.model.Technology
import kotlinx.coroutines.launch

class FreaksViewModel(private val freaksRepository: FreaksRepository, private val filterRepository: FilterRepository) : ViewModel() {
    private val _freaks = MutableLiveData<List<Freak>>().apply {
        viewModelScope.launch {
            value = loadFreaks()
        }
    }
    val freaks: LiveData<List<Freak>>
        get() = _freaks

    private suspend fun loadFreaks(): List<Freak> = freaksRepository.getFreaksFromApi()

    private val _technologies = MutableLiveData<List<Technology>>().apply {
        viewModelScope.launch {
            value = loadTechnologies()
        }
    }
    val technologies: LiveData<List<Technology>>
        get() = _technologies

    private val _projects = MutableLiveData<List<Project>>().apply {
        viewModelScope.launch {
            value = loadProjects()
        }
    }
    val projects: LiveData<List<Project>>
        get() = _projects

    private suspend fun loadTechnologies(): List<Technology> = filterRepository.getTechnologiesFromApi()
    private suspend fun loadProjects(): List<Project> = filterRepository.getProjectsFromApi()

//    fun onSkillsFilterClicked() {
//        showFilterDialog.value = skilsList
//    }
}
