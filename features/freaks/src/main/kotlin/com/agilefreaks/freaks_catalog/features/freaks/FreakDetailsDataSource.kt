package com.agilefreaks.freaks_catalog.features.freaks

import com.agilefreaks.freaks_catalog.features.freaks.model.apolloClient
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface FreakDetailsDataSource {
    suspend fun getFreaks(): FreakDetailsQuery.Data?
}

class ApolloDataSource : FreakDetailsDataSource {
    override suspend fun getFreaks(): FreakDetailsQuery.Data? = withContext(Dispatchers.IO) {
        apolloClient.query(FreakDetailsQuery()).await().data
    }
}