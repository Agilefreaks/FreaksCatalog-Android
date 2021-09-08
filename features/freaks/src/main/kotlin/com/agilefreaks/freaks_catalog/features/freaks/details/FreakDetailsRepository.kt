package com.agilefreaks.freaks_catalog.features.freaks.details

import com.agilefreaks.freaks_catalog.features.freaks.model.FreakDetails
import com.agilefreaks.freaks_catalog.features.freaks.FreakDetailsQuery

interface FreakDetailsRepository {
    suspend fun getFreakDetailsFromApi(freakId: String): FreakDetails?
}

class FreakDetailsRepositoryImpl(private val dataSource: FreakDetailsDataSource) :
    FreakDetailsRepository {
    override suspend fun getFreakDetailsFromApi(freakId: String): FreakDetails? =
        dataSource.getFreakDetails(freakId)?.freak?.toFreak()

    private fun FreakDetailsQuery.Freak.toFreak() = FreakDetails(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        description = this.description,
        level = this.level.name,
        norm = this.norm.name,
        photo = this.photo?.uri as String? ?: "",
        role = this.role.name,
        projects = buildProjectsNameList(this.projects).joinToString(", "),
        skills = buildSkillsNameList(this.technologies).joinToString(", ")
    )

    private fun buildProjectsNameList(projects: List<FreakDetailsQuery.Project>): List<String> =
        projects.map { it.name }

    private fun buildSkillsNameList(skills: List<FreakDetailsQuery.Technology>): List<String> =
        skills.map { it.name }
}