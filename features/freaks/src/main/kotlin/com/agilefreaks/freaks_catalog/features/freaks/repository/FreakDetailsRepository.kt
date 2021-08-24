package com.agilefreaks.freaks_catalog.features.freaks.repository

import com.agilefreaks.freaks_catalog.features.freaks.FreakD
import com.agilefreaks.freaks_catalog.features.freaks.FreakDetailsDataSource
import com.agilefreaks.freaks_catalog.features.freaks.FreakDetailsQuery

interface FreakDetailsRepository {
    suspend fun getFreakFromApi(freakId: String): FreakD?
}

class FreakDetailsRepositoryImpl(private val dataSource: FreakDetailsDataSource) :
    FreakDetailsRepository {
    override suspend fun getFreakFromApi(freakId: String): FreakD? =
        mapFreaks(dataSource.getFreaks())?.getFreakById(freakId)

    private fun mapFreaks(response: FreakDetailsQuery.Data?): List<FreakD>? =
        response?.freaks?.nodes?.map {
            it.toFreak()
        }

    private fun List<FreakD>.getFreakById(freakId: String): FreakD? =
        this.find { freak -> freak.freakId == freakId }

    private fun FreakDetailsQuery.Node?.toFreak() = FreakD(
        freakId = this?.id ?: "",
        firstName = this?.firstName ?: "",
        lastName = this?.lastName ?: "",
        description = this?.description ?: "",
        level = this?.level?.name ?: "",
        norm = this?.norm?.name ?: "",
        photo = this?.photo?.uri as String? ?: "",
        role = this?.role?.name ?: "",
        projects = buildProjectsNameList(this!!.projects).joinToString(", "),
        skills = buildSkillsNameList(this.skills).joinToString(", ")
    )

    private fun buildProjectsNameList(projects: List<FreakDetailsQuery.Project>): List<String> =
        projects.map { it.name }

    private fun buildSkillsNameList(skills: List<FreakDetailsQuery.Skill>): List<String> =
        skills.map { it.name }
}
