package com.agilefreaks.freaks_catalog.features.projects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProjectViewModel(private val repository: ProjectsRepository) : ViewModel() {
    private val _projects = MutableLiveData<List<Project>>().apply {
        viewModelScope.launch {
            value = loadProjects()
            _projectLoaded.value=true
        }
    }
    val projects: LiveData<List<Project>>
        get() = _projects

    private val _projectLoaded = MutableLiveData(false)
    val projectsLoaded:LiveData<Boolean>
    get() = _projectLoaded


    private suspend fun loadProjects():List<Project> =
        repository.getProjectsFromApi()
}