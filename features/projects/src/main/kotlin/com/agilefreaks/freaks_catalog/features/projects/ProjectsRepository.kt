package com.agilefreaks.freaks_catalog.features.projects

import org.koin.core.component.getScopeId

interface ProjectsRepository {
    suspend fun getProjectsFromApi(): List<Project>
}

class ProjectsRepositoryImpl(private val dataSource: ProjectsDataSource) : ProjectsRepository {
    override suspend fun getProjectsFromApi(): List<Project> {
        return dataSource.getProjects().map {
            it.toProject()
        }
    }

    private fun ProjectListQuery.Project.toProject() = Project(
        id = this?.id ?: "",
        name = this?.name ?: "",
        image = (this?.logoUrl?.uri ?: "") as String
    )



}