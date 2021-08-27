package com.agilefreaks.freaks_catalog.features.freaks.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agilefreaks.freaks_catalog.features.freaks.model.FilterItem
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
        technologiesList.add(FilterItem("1", "Android"))
        technologiesList.add(FilterItem("2", "Kotlin"))
        technologiesList.add(FilterItem("3", "iOS"))
        technologiesList.add(FilterItem("4", "Ruby"))
        technologiesList.add(FilterItem("5", "QA"))
        technologiesList.add(FilterItem("6", "Other Skill"))
        technologiesList.sortBy { it.name }
        return technologiesList
    }

    private fun loadProjects(): MutableList<FilterItem> {
        val projectsList: MutableList<FilterItem> = mutableListOf()
        projectsList.add(FilterItem("1", "Freaks Catalog"))
        projectsList.add(FilterItem("2", "EPIX"))
        projectsList.add(FilterItem("3", "reAsig"))
        projectsList.add(FilterItem("4", "New Project"))
        projectsList.sortBy { it.name }
        return projectsList
    }
}
