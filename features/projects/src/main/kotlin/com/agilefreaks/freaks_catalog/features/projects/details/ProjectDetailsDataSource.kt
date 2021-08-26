package com.agilefreaks.freaks_catalog.features.projects.details

import com.agilefreaks.freaks_catalog.features.projects.apolloClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ProjectDetailsDataSource {
    suspend fun getProjects(): ProjectDetailsQuery.Data?
}

class ProjectDetailsApolloDataSource: ProjectDetailsDataSource {
    override suspend fun getProjects(): ProjectDetailsQuery.Data? = withContext(Dispatchers.IO) {
        apolloClient.query(PRojectDetailsQuery()).await().data
    }
}
