package com.agilefreaks.freaks_catalog.features.projects.details

import com.agilefreaks.freaks_catalog.features.projects.ProjectDetailsQuery

interface ProjectDetailsRepository {
    suspend fun getProjectFromApi(projectId: String): ProjectDetails?
}

class ProjectDetailsRepositoryImpl(private val dataSource: ProjectDetailsDataSource?) :
    ProjectDetailsRepository {
    override suspend fun getProjectFromApi(projectId: String): ProjectDetails? =
        mapProjects(dataSource?.getProjectDetails(projectId))

    private fun mapProjects(response: ProjectDetailsQuery.Data?): ProjectDetails? =
        response?.project?.toProject()


    private fun ProjectDetailsQuery.Project?.toProject() = ProjectDetails(
        id = this?.id ?: "",
        name = this?.name ?: "",
        description = this?.description ?: "",
        photo = this?.logoUrl?.uri as String ?: "",
        freaks = buildFreaksNameList(this.freaks).joinToString(", "),
        technology = buildTechnologiesList(this.technologies).joinToString (", ")
    )

    private fun buildFreaksNameList(freaks: List<ProjectDetailsQuery.Freak>): List<String> =
        freaks.map { it.firstName }

    private fun buildTechnologiesList(technologies: List<ProjectDetailsQuery.Technology>): List<String> =
        technologies.map { it.name }
}