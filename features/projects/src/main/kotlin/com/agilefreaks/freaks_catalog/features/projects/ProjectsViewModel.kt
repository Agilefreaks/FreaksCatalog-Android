package com.agilefreaks.freaks_catalog.features.projects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProjectsViewModel(private val projectsRepository: ProjectsRepository) : ViewModel() {
    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _projects = MutableLiveData<List<Project>>()
    val projects: MutableLiveData<List<Project>>
        get() = _projects

    fun loadProjects() {
        viewModelScope.launch {
            _projects.value = projectsRepository.getProjectsFromApi()

            _isLoading.value = false
        }
    }
}
