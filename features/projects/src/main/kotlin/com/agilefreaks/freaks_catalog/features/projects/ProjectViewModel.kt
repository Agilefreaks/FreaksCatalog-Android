package com.agilefreaks.freaks_catalog.features.projects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProjectViewModel(private val repository: ProjectsRepository) : ViewModel() {
    private val _projects = MutableLiveData<List<Project>>().apply {
        value = getProjects()
    }
    val projects: LiveData<List<Project>>
    get() = _projects


    private fun getProjects(): List<Project> = repository.getProjectsFromApi()
}