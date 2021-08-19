package com.agilefreaks.freaks_catalog.features.freaks.repository

import com.agilefreaks.freaks_catalog.features.freaks.Freak
import com.agilefreaks.freaks_catalog.features.freaks.FreakDetailsDataSource
import com.agilefreaks.freaks_catalog.features.freaks.FreakDetailsQuery

interface FreakDetailsRepository {
    suspend fun getFreakFromApi(freakId: String): Freak?
}

class FreakDetailsRepositoryImpl(private val dataSource: FreakDetailsDataSource) :
    FreakDetailsRepository {
    override suspend fun getFreakFromApi(freakId: String): Freak? =
        mapFreaks(dataSource.getFreaks())?.getFreakById(freakId)

    private fun mapFreaks(response: FreakDetailsQuery.Data?): List<Freak>? =
        response?.freaks?.nodes?.map {
            it.toFreak()
        }

    private fun List<Freak>.getFreakById(freakId: String): Freak? =
        this.find { freak -> freak.freakId == freakId }

    private fun FreakDetailsQuery.Node?.toFreak() = Freak(
        freakId = this?.id ?: "",
        firstName = this?.firstName ?: "",
        lastName = this?.lastName ?: "",
        description = this?.description ?: "",
        level = this?.level?.name ?: "",
        norm = this?.norm?.name ?: "",
        photo = "https://cdn2.thecatapi.com/images/15o.jpg",
        role = this?.role?.name ?: "",
        projects = buildProjectsNameList(this!!.projects).joinToString(", "),
        skills = buildSkillsNameList(this.skills).joinToString(", ")
    )

    private fun buildProjectsNameList(projects: List<FreakDetailsQuery.Project>): List<String> =
        projects.map { it.name }

    private fun buildSkillsNameList(skills: List<FreakDetailsQuery.Skill>): List<String> =
        skills.map { it.name }
}
