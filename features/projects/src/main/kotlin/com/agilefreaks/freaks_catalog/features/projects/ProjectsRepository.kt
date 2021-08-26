package com.agilefreaks.freaks_catalog.features.projects

interface ProjectsRepository {
    fun getProjectsFromApi(): List<Project>
}

class ProjectsRepositoryImpl() : ProjectsRepository{
    override fun getProjectsFromApi(): List<Project> {
        val projects = mutableListOf<Project>()
        val mockProject = Project("Ask Mihai","https://cdn2.thecatapi.com/images/8lr.jpg")
        repeat(5){
            projects.add(mockProject)
        }
        return projects
    }
}