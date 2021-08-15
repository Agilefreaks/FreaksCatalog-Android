package com.agilefreaks.freaks_catalog.features.freaks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.coroutines.await
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {
    private var _freaks: List<Freak> = listOf()
    val freaks: List<Freak>
        get() = _freaks

    private var _curFreak = MutableLiveData(Freak("", "", "", "", "", "", "", "", "", ""))
    val freak: LiveData<Freak>
        get() = _curFreak

    fun getData(freakId: String) {
        _curFreak.value = freaks.get(freakId.toInt())
    }

    suspend fun getFreaksFromApi() {
        val response = apolloClient.query(FreakDetailsQuery()).await().data
        _freaks = mapFreaks(response) ?: listOf()
    }

    fun mapFreaks(response: FreakDetailsQuery.Data?): List<Freak>? =
        response?.freaks?.nodes?.map {
            it.toFreak()
        }

    private fun FreakDetailsQuery.Node?.toFreak() = Freak(
        freakId = this?.id ?: "",
        firstName = this?.name ?: "",
        lastName = this?.name ?: "",
        description = this?.description ?: "",
        level = this?.level?.name ?: "",
        norm = this?.norm?.name ?: "",
        photo = this?.photo?.uri as String ?: "",
        role = this.role.name ?: "",
        projects = buildProjectsNameList(this.projects).joinToString(", "),
        skills = buildSkillsNameList(this.skills).joinToString(", ")
    )

    fun buildProjectsNameList(projects: List<FreakDetailsQuery.Project>): List<String> =
        projects.map { it.name }

    fun buildSkillsNameList(skills: List<FreakDetailsQuery.Skill>): List<String> =
        skills.map { it.name }
}
