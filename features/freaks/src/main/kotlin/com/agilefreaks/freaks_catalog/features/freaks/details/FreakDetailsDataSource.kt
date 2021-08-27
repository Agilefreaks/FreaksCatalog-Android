package com.agilefreaks.freaks_catalog.features.freaks.details

import com.agilefreaks.freaks_catalog.features.freaks.FreakDetailsQuery
import com.agilefreaks.freaks_catalog.features.freaks.apolloClient
import com.apollographql.apollo.coroutines.await
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface FreakDetailsDataSource {
    suspend fun getFreaks(): FreakDetailsQuery.Data?
}

class FreakDetailsApolloDataSource : FreakDetailsDataSource {
    override suspend fun getFreaks(): FreakDetailsQuery.Data? = withContext(Dispatchers.IO) {
        apolloClient.query(FreakDetailsQuery()).await().data
    }
}
