package com.agilefreaks.freaks_catalog.features.freaks.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agilefreaks.freaks_catalog.features.freaks.model.Project
import com.agilefreaks.freaks_catalog.features.freaks.model.Technology
import kotlinx.coroutines.launch

class FilterViewModel : ViewModel() {
    private val _filtersList = MutableLiveData<Pair<List<Technology>, List<Project>>>().apply {
        viewModelScope.launch {
            value = Pair(loadSkills(), loadProjects())
        }
    }
    val filtersList: LiveData<Pair<List<Technology>, List<Project>>>
        get() = _filtersList

    fun reset() {
        filtersList.value?.first?.forEach { it.reset() }
        filtersList.value?.second?.forEach { it.reset() }
    }

    fun applyFilters(): Pair<MutableList<String>, MutableList<String>> {
        val selectedFilters: Pair<MutableList<String>, MutableList<String>> =
            Pair(mutableListOf(), mutableListOf())
        filtersList.value?.first?.forEach {
            if (it.isChecked.get() == true) {
                selectedFilters.first.add(
                    it.id
                )
            }
        }
        filtersList.value?.second?.forEach {
            if (it.isChecked.get() == true) {
                selectedFilters.second.add(
                    it.id
                )
            }
        }
        return selectedFilters
    }

    private fun loadSkills(): MutableList<Technology> {
        val technologiesList: MutableList<Technology> = mutableListOf()
        technologiesList.add(Technology("1", "Android"))
        technologiesList.add(Technology("2", "Kotlin"))
        technologiesList.add(Technology("3", "iOS"))
        technologiesList.add(Technology("4", "Ruby"))
        technologiesList.add(Technology("5", "QA"))
        technologiesList.add(Technology("6", "Other Skill"))
        technologiesList.sortBy { it.name }
        return technologiesList
    }

    private fun loadProjects(): MutableList<Project> {
        val projectsList: MutableList<Project> = mutableListOf()
        projectsList.add(Project("1", "Freaks Catalog"))
        projectsList.add(Project("2", "EPIX"))
        projectsList.add(Project("3", "reAsig"))
        projectsList.add(Project("4", "New Project"))
        projectsList.sortBy { it.name }
        return projectsList
    }
}
