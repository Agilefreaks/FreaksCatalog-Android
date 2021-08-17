package com.agilefreaks.freaks_catalog.features.freaks

import com.agilefreaks.freaks_catalog.features.freaks.model.apolloClient
import com.apollographql.apollo.coroutines.await
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface FreaksDataSource {
    suspend fun getFreaks(): FreaksListQuery.Data?
}

class ApolloDataSource : FreaksDataSource {
    override suspend fun getFreaks(): FreaksListQuery.Data? = withContext(Dispatchers.IO) {
        apolloClient.query(FreaksListQuery()).await().data
    }
}
