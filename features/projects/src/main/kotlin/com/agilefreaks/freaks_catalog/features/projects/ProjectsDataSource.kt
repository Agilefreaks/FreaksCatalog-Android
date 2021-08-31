package com.agilefreaks.freaks_catalog.features.projects

import com.agilefreaks.freaks_catalog.features.freaks.model.apolloClient
import com.apollographql.apollo.coroutines.await
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ProjectsDataSource {
    suspend fun getProjects(): List<ProjectListQuery.Project>
}

class ProjectsApolloDataSource : ProjectsDataSource {
    override suspend fun getProjects(): List<ProjectListQuery.Project> =
        withContext(Dispatchers.IO) {
            apolloClient.query(ProjectListQuery()).await().data?.projects ?: emptyList()
        }
}
