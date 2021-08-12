package com.agilefreaks.freaks_catalog.features.freaks.repository

import com.agilefreaks.freaks_catalog.features.freaks.Freak
import com.agilefreaks.freaks_catalog.features.freaks.FreaksListQuery
import com.agilefreaks.freaks_catalog.features.freaks.model.apolloClient
import com.apollographql.apollo.coroutines.await
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

interface FreaksRepository {
    suspend fun getFreaksFromApi(): List<Freak>
}

class FreaksRepositoryImpl : FreaksRepository {
    override suspend fun getFreaksFromApi(): List<Freak> = withContext(IO) {
        val response = apolloClient.query(FreaksListQuery()).await().data
        mapFreaks(response)
    }

    private fun mapFreaks(response: FreaksListQuery.Data?): List<Freak> =
        response?.freaks?.nodes?.map {
            it.toFreak()
        } ?: emptyList()

    private fun FreaksListQuery.Node?.toFreak() =
        Freak(
            id = this?.id ?: "",
            firstName = this?.name ?: "",
            lastName = this?.name ?: "",
            role = "",
            norm = "",
            level = "",
            description = "",
            photo = this?.photo?.uri as String,
            skills = emptyList(),
            projects = emptyList()
        )
}
