package com.agilefreaks.freaks_catalog.features.freaks

import com.agilefreaks.freaks_catalog.features.freaks.model.apolloClient
import com.agilefreaks.freaks_catalog.features.freaks.model.apolloClient2
import com.apollographql.apollo.coroutines.await
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface FreaksDataSource {
    suspend fun getFreaks(): FreaksListQuery.Data?
}

class FreakListApolloDataSource : FreaksDataSource {
    override suspend fun getFreaks(): FreaksListQuery.Data? = withContext(Dispatchers.IO) {
        apolloClient2.query(FreaksListQuery()).await().data
    }
}
