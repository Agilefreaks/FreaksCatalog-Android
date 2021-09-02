package com.agilefreaks.freaks_catalog.features.freaks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agilefreaks.freaks_catalog.features.freaks.FreaksFragment.Companion.SKILLS
import com.agilefreaks.freaks_catalog.features.freaks.filter.FilterRepository
import com.agilefreaks.freaks_catalog.features.freaks.model.Freak
import com.agilefreaks.freaks_catalog.features.freaks.model.Project
import com.agilefreaks.freaks_catalog.features.freaks.model.Skill
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
    private val allFreaks: LiveData<List<Freak>>
        get() = _allFreaks

    private val _filteredFreaks = MutableLiveData<List<Freak>>().apply {
        viewModelScope.launch {
            value = loadFreaks()
        }
    }
    val filteredFreaks: LiveData<List<Freak>>
        get() = _filteredFreaks

    private suspend fun loadFreaks(): List<Freak> = freaksRepository.getFreaksFromApi()

    private val _skills = MutableLiveData<List<Skill>>().apply {
        viewModelScope.launch {
            value = loadSkills()
        }
    }
    val skills: LiveData<List<Skill>>
        get() = _skills

    private val _projects = MutableLiveData<List<Project>>().apply {
        viewModelScope.launch {
            value = loadProjects()
        }
    }
    val projects: LiveData<List<Project>>
        get() = _projects

    private suspend fun loadSkills(): List<Skill> = filterRepository.getSkillsFromApi()

    private suspend fun loadProjects(): List<Project> = filterRepository.getProjectsFromApi()

//    fun onSkillsFilterClicked() {
//        showFilterDialog.value = skilsList
//    }

    private fun getSelectedSkills(): List<String> {
        val selectedSkills: MutableList<String> = mutableListOf()
        skills.value?.forEach {
            if (it.isChecked.get() == true) {
                selectedSkills.add(
                    it.id
                )
            }
        }
        return selectedSkills
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
        val skillsList: List<String> = if (getSelectedSkills().isNullOrEmpty()) {
            getStringListOfAllSkills()
        } else {
            getSelectedSkills()
        }
        val projectsList: List<String> = if (getSelectedProjects().isNullOrEmpty()) {
            getStringListOfAllProjects()
        } else {
            getSelectedProjects()
        }
        _filteredFreaks.value = filterFreaks(skillsList, projectsList)

    }

    fun onResetButtonClicked(name: String) {
        if (name == SKILLS) {
            skills.value?.forEach {
                it.reset()
            }
        } else {
            projects.value?.forEach {
                it.reset()
            }
        }
    }

    private fun getStringListOfAllSkills(): List<String> {
        val selectedSkills: MutableList<String> = mutableListOf()
        skills.value?.forEach {
            selectedSkills.add(
                it.id
            )
        }
        return selectedSkills
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
        selectedSkills: List<String>,
        selectedProjects: List<String>
    ): List<Freak> {
        val filteredFreaksList1: MutableList<Freak> = mutableListOf()
        val filteredFreaksList2: MutableList<Freak> = mutableListOf()
        allFreaks.value?.forEach {
            if (it.technologyIds.intersect(selectedSkills).isNotEmpty()) {
                filteredFreaksList1.add(it)
            }
        }
        allFreaks.value?.forEach {
            if (it.projectIds.intersect(selectedProjects).isNotEmpty()) {
                filteredFreaksList2.add(it)
            }
        }

        return filteredFreaksList1.intersect(filteredFreaksList2).toList()
    }
}
