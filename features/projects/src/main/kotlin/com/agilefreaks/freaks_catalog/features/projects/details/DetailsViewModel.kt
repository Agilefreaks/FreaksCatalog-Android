package com.agilefreaks.freaks_catalog.features.projects.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DetailsViewModel (private val repository: ProjectDetailsRepository) : ViewModel() {
    private var _project = MutableLiveData<ProjectDetails>()
    val project: LiveData<ProjectDetails>
        get() = _project

    private var _projectDetailsLoaded = MutableLiveData(false)
    val projectDetailsLoaded: LiveData<Boolean>
        get() = _projectDetailsLoaded

    fun loadProject(projectId: String) {
        viewModelScope.launch {
            _project.value = repository.getProjectFromApi(projectId)
            _projectDetailsLoaded.value = true
        }
    }
}
