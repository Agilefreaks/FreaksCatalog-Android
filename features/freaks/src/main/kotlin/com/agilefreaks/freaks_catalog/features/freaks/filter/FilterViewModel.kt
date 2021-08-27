package com.agilefreaks.freaks_catalog.features.freaks.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agilefreaks.freaks_catalog.features.freaks.model.FilterItem
import com.agilefreaks.freaks_catalog.features.freaks.model.Project
import com.agilefreaks.freaks_catalog.features.freaks.model.Technology
import kotlinx.coroutines.launch

class FilterViewModel : ViewModel() {
    private val _skills = MutableLiveData<List<FilterItem>>().apply {
        viewModelScope.launch {
            value = loadSkills()
        }
    }
    val skills: LiveData<List<FilterItem>>
        get() = _skills

    private val _projects = MutableLiveData<List<FilterItem>>().apply {
        viewModelScope.launch {
            value = loadProjects()
        }
    }
    val projects: LiveData<List<FilterItem>>
        get() = _projects

    fun reset() {
        skills.value?.forEach { it.reset() }
        projects.value?.forEach { it.reset() }
    }

    fun applyFilters(): List<String> =
        listOf("", "")

    private fun loadSkills(): MutableList<FilterItem> {
        val technologiesList: MutableList<FilterItem> = mutableListOf()
        technologiesList.add(Technology("1", "Android"))
        technologiesList.add(Technology("2", "Kotlin"))
        technologiesList.add(Technology("3", "iOS"))
        technologiesList.add(Technology("4", "Ruby"))
        technologiesList.add(Technology("5", "QA"))
        technologiesList.add(Technology("6", "Other Skill"))
        technologiesList.sortBy { it.name }
        return technologiesList
    }

    private fun loadProjects(): MutableList<FilterItem> {
        val projectsList: MutableList<FilterItem> = mutableListOf()
        projectsList.add(Project("1", "Freaks Catalog"))
        projectsList.add(Project("2", "EPIX"))
        projectsList.add(Project("3", "reAsig"))
        projectsList.add(Project("4", "New Project"))
        projectsList.sortBy { it.name }
        return projectsList
    }
}
