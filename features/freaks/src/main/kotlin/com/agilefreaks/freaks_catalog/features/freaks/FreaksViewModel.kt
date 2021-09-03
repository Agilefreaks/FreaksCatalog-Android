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
        val skillsList: List<String> = getSelectedSkills()
        val projectsList: List<String> = getSelectedProjects()

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

    private fun filterFreaks(
        selectedSkills: List<String>,
        selectedProjects: List<String>
    ): List<Freak> {
        val filteredFreaksBySkills: MutableList<Freak>
        val filteredFreaksByProjects: MutableList<Freak>
        if (selectedSkills.isNullOrEmpty()) {
            filteredFreaksBySkills = allFreaks.value as MutableList<Freak>
        } else {
            filteredFreaksBySkills = mutableListOf()
            allFreaks.value?.forEach {
                if (it.skillsIds.intersect(selectedSkills).isNotEmpty()) {
                    filteredFreaksBySkills.add(it)
                }
            }
        }
        if (selectedProjects.isNullOrEmpty()) {
            filteredFreaksByProjects = allFreaks.value as MutableList<Freak>
        } else {
            filteredFreaksByProjects = mutableListOf()
            allFreaks.value?.forEach {
                if (it.projectIds.intersect(selectedProjects).isNotEmpty()) {
                    filteredFreaksByProjects.add(it)
                }
            }
        }
        return filteredFreaksBySkills.intersect(filteredFreaksByProjects).toList()
    }
}
