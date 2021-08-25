package com.agilefreaks.freaks_catalog.features.freaks.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FilterViewModel : ViewModel() {
    private val _skills = MutableLiveData<List<FilterItem>>().apply {
        viewModelScope.launch {
            value = loadSkills().map { skill -> FilterItem(skill) }
        }
    }
    val skills: LiveData<List<FilterItem>>
        get() = _skills

    private val _projects = MutableLiveData<List<FilterItem>>().apply {
        viewModelScope.launch {
            value = loadProjects().map { project -> FilterItem(project) }
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

    private fun loadSkills(): List<String> =
        listOf("Android", "Kotlin", "Other Skill", "iOS", "Ruby", "QA")

    private fun loadProjects(): List<String> =
        listOf("Freaks Catalog", "Proj2", "Tutorial", "Altceva")
}
