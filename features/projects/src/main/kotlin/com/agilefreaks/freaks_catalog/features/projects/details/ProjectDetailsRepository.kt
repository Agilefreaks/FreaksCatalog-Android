package com.agilefreaks.freaks_catalog.features.projects.details

import com.agilefreaks.freaks_catalog.features.projects.ProjectDetailsQuery

interface ProjectDetailsRepository {
    suspend fun getProjectFromApi(projectId: String): ProjectDetails?
}

class ProjectDetailsRepositoryImpl(private val dataSource: ProjectDetailsDataSource?) :
    ProjectDetailsRepository {
    override suspend fun getProjectFromApi(projectId: String): ProjectDetails? =
        mapProjects(dataSource?.getProjectDetails())?.getProjectById(projectId)

    private fun mapProjects(response: ProjectDetailsQuery.Data?): List<ProjectDetails>? =
        response?.projects?.map {
            it.toProject()
        }

    private fun List<ProjectDetails>.getProjectById(projectId: String): ProjectDetails? =
        this.find { project -> project.id == projectId }

    private fun ProjectDetailsQuery.Project?.toProject() = ProjectDetails(
        id = this?.id ?: "",
        name = this as String,
        description = this?.description ?: "",
        photo = this?.logoUrl as String ?: "",
        freaks = buildFreaksNameList(this!!.freaks).joinToString(", "),
        technology = buildTechnologiesList(this!!.technologies).joinToString (", ")
    )

    private fun buildFreaksNameList(freaks: List<ProjectDetailsQuery.Freak>): List<String> =
        freaks.map { it.firstName }

    private fun buildTechnologiesList(technologies: List<ProjectDetailsQuery.Technology>): List<String> =
        technologies.map { it.name }
}