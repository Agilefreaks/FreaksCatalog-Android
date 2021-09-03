package com.agilefreaks.freaks_catalog.features.projects.details

import com.agilefreaks.freaks_catalog.features.freaks.model.apolloClient
import com.agilefreaks.freaks_catalog.features.projects.ProjectDetailsQuery
import com.apollographql.apollo.coroutines.await
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ProjectDetailsDataSource {
    suspend fun getProjectDetails(): ProjectDetailsQuery.Data?
}

class ProjectDetailsApolloDataSource : ProjectDetailsDataSource {
    override suspend fun getProjectDetails(): ProjectDetailsQuery.Data? =
        withContext(Dispatchers.IO) {
            apolloClient.query(ProjectDetailsQuery()).await().data
        }
}
