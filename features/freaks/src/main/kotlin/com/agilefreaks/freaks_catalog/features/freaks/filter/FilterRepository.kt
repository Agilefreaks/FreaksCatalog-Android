package com.agilefreaks.freaks_catalog.features.freaks.filter

import com.agilefreaks.freaks_catalog.features.freaks.ProjectsListQuery
import com.agilefreaks.freaks_catalog.features.freaks.TechnologiesListQuery
import com.agilefreaks.freaks_catalog.features.freaks.model.Project
import com.agilefreaks.freaks_catalog.features.freaks.model.Technology

interface FilterRepository {
    suspend fun getTechnologiesFromApi(): List<Technology>
    suspend fun getProjectsFromApi(): List<Project>
}

class FilterRepositoryImpl(private val dataSource: FilterDataSource) : FilterRepository {
    override suspend fun getTechnologiesFromApi(): List<Technology> =
        mapTechnologies(dataSource.getTechnologies())

    override suspend fun getProjectsFromApi(): List<Project> =
        mapProjects(dataSource.getProjects())

    private fun mapTechnologies(response: TechnologiesListQuery.Data?): List<Technology> =
        response?.technologies?.map {
            it.toTechnology()
        } ?: emptyList()

    private fun TechnologiesListQuery.Technology.toTechnology() =
        Technology(
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
