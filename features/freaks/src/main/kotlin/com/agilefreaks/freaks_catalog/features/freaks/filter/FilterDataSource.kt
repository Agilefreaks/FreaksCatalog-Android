package com.agilefreaks.freaks_catalog.features.freaks.filter

import com.agilefreaks.freaks_catalog.features.freaks.ProjectsListQuery
import com.agilefreaks.freaks_catalog.features.freaks.SkillsListQuery
import com.agilefreaks.freaks_catalog.features.freaks.apolloClient
import com.apollographql.apollo.coroutines.await
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface FilterDataSource {
    suspend fun getTechnologies(): SkillsListQuery.Data?
    suspend fun getProjects(): ProjectsListQuery.Data?
}

class FilterApolloDataSource : FilterDataSource {
    override suspend fun getTechnologies(): SkillsListQuery.Data? = withContext(Dispatchers.IO) {
        apolloClient.query(SkillsListQuery()).await().data
    }
    override suspend fun getProjects(): ProjectsListQuery.Data? = withContext(Dispatchers.IO) {
        apolloClient.query(ProjectsListQuery()).await().data
    }
}
