package com.agilefreaks.freaks_catalog.features.projects.details

interface ProjectDetailsRepository {
    suspend fun getProjectFromApi(projectId: String): ProjectDetails?
}

class ProjectDetailsRepositoryImpl(private val dataSource: ProjectDetailsDataSource): ProjectDetailsRepository {
    override suspend fun getProjectFromApi(projectId: String): ProjectDetails? {
        return
    }
}