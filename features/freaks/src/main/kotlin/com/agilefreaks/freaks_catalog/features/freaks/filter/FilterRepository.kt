package com.agilefreaks.freaks_catalog.features.freaks.filter

import com.agilefreaks.freaks_catalog.features.freaks.ProjectsListQuery
import com.agilefreaks.freaks_catalog.features.freaks.SkillsListQuery
import com.agilefreaks.freaks_catalog.features.freaks.model.Project
import com.agilefreaks.freaks_catalog.features.freaks.model.Skill

interface FilterRepository {
    suspend fun getSkillsFromApi(): List<Skill>
    suspend fun getProjectsFromApi(): List<Project>
}

class FilterRepositoryImpl(private val dataSource: FilterDataSource) : FilterRepository {
    override suspend fun getSkillsFromApi(): List<Skill> =
        mapTechnologies(dataSource.getTechnologies())

    override suspend fun getProjectsFromApi(): List<Project> =
        mapProjects(dataSource.getProjects())

    private fun mapTechnologies(response: SkillsListQuery.Data?): List<Skill> =
        response?.technologies?.map {
            it.toSkill()
        } ?: emptyList()

    private fun SkillsListQuery.Technology.toSkill() =
        Skill(
            id = this.id,
            name = this.name
        )

    private fun mapProjects(response: ProjectsListQuery.Data?): List<Project> =
        response?.projects?.map {
            it.toProject()
        } ?: emptyList()

    private fun ProjectsListQuery.Project.toProject() =
        Project(
            id = this.id ?: "",
            name = this.name ?: ""
        )
}
