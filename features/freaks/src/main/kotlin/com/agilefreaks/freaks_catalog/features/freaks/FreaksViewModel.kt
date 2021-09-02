package com.agilefreaks.freaks_catalog.features.freaks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agilefreaks.freaks_catalog.features.freaks.FreaksFragment.Companion.SKILLS
import com.agilefreaks.freaks_catalog.features.freaks.filter.FilterRepository
import com.agilefreaks.freaks_catalog.features.freaks.model.Freak
import com.agilefreaks.freaks_catalog.features.freaks.model.Project
import com.agilefreaks.freaks_catalog.features.freaks.model.Technology
import kotlinx.coroutines.launch

class FreaksViewModel(
    private val freaksRepository: FreaksRepository,
    private val filterRepository: FilterRepository
) : ViewModel() {
    private val _allFreaks = MutableLiveData<List<Freak>>().apply {
        viewModelScope.launch {
            value = loadFreaks()
        }
    }
    val allFreaks: LiveData<List<Freak>>
        get() = _allFreaks

    private val _filteredFreaks = MutableLiveData<List<Freak>>().apply {
        viewModelScope.launch {
            value = loadFreaks()
        }
    }
    val filteredFreaks: LiveData<List<Freak>>
        get() = _filteredFreaks

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

    private suspend fun loadTechnologies(): List<Technology> =
        filterRepository.getTechnologiesFromApi()

    private suspend fun loadProjects(): List<Project> = filterRepository.getProjectsFromApi()

//    fun onSkillsFilterClicked() {
//        showFilterDialog.value = skilsList
//    }

    private fun getSelectedTechnologies(): List<String> {
        val selectedTechnologies: MutableList<String> = mutableListOf()
        technologies.value?.forEach {
            if (it.isChecked.get() == true) {
                selectedTechnologies.add(
                    it.id
                )
            }
        }
        return selectedTechnologies
    }

    private fun getSelectedProjects(): List<String> {
        val selectedProjects: MutableList<String> = mutableListOf()
        projects.value?.forEach {
            if (it.isChecked.get() == true) {
                selectedProjects.add(
                    it.id
                )
            }
        }
        return selectedProjects
    }

    fun onApplyFilterClicked() {
        val technologiesList: List<String> = if (getSelectedTechnologies().isNullOrEmpty()) {
            getStringListOfAllTechnologies()
        } else {
            getSelectedTechnologies()
        }
        val projectsList: List<String> = if (getSelectedProjects().isNullOrEmpty()) {
            getStringListOfAllProjects()
        } else {
            getSelectedProjects()
        }
        if (technologiesList.containsAll(getStringListOfAllTechnologies()) && projectsList.containsAll(
                getStringListOfAllProjects()
            )
        ) {
            _filteredFreaks.value = allFreaks.value
        } else {
            _filteredFreaks.value = filterFreaks(technologiesList, projectsList)
        }
    }

    fun onResetButtonClicked(name: String) {
        if (name == SKILLS) {
            technologies.value?.forEach {
                it.reset()
            }
        } else {
            projects.value?.forEach {
                it.reset()
            }
        }
    }

    private fun getStringListOfAllTechnologies(): List<String> {
        val selectedTechnologies: MutableList<String> = mutableListOf()
        technologies.value?.forEach {
            selectedTechnologies.add(
                it.id
            )
        }
        return selectedTechnologies
    }

    private fun getStringListOfAllProjects(): List<String> {
        val selectedProjects: MutableList<String> = mutableListOf()
        projects.value?.forEach {
            selectedProjects.add(
                it.id
            )
        }
        return selectedProjects
    }

    private fun filterFreaks(
        selectedTechnologies: List<String>,
        selectedProjects: List<String>
    ): List<Freak> {
        val filteredFreaksList1: MutableList<Freak> = mutableListOf()
        val filteredFreaksList2: MutableList<Freak> = mutableListOf()
        allFreaks.value?.forEach {
            if (it.technologyId.intersect(selectedTechnologies).isNotEmpty()) {
                filteredFreaksList1.add(it)
            }
        }
        allFreaks.value?.forEach {
            if (it.projectId.intersect(selectedProjects).isNotEmpty()) {
                filteredFreaksList2.add(it)
            }
        }

        return filteredFreaksList1.intersect(filteredFreaksList2).toList()
    }
}
