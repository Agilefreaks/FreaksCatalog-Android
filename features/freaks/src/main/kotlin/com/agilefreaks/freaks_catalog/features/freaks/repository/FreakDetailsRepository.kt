package com.agilefreaks.freaks_catalog.features.freaks.repository

import com.agilefreaks.freaks_catalog.features.freaks.FreakDetails
import com.agilefreaks.freaks_catalog.features.freaks.FreakDetailsDataSource
import com.agilefreaks.freaks_catalog.features.freaks.FreakDetailsQuery

interface FreakDetailsRepository {
    suspend fun getFreakFromApi(freakId: String): FreakDetails?
}

class FreakDetailsRepositoryImpl(private val dataSource: FreakDetailsDataSource) :
    FreakDetailsRepository {
    override suspend fun getFreakFromApi(freakId: String): FreakDetails? =
        mapFreaks(dataSource.getFreaks())?.getFreakById(freakId)

    private fun mapFreaks(response: FreakDetailsQuery.Data?): List<FreakDetails>? =
        response?.freaks?.nodes?.map {
            it.toFreak()
        }

    private fun List<FreakDetails>.getFreakById(freakId: String): FreakDetails? =
        this.find { freak -> freak.id == freakId }

    private fun FreakDetailsQuery.Node?.toFreak() = FreakDetails(
        id = this?.id ?: "",
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
