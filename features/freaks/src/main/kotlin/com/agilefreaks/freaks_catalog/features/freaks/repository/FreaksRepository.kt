package com.agilefreaks.freaks_catalog.features.freaks.repository

import com.agilefreaks.freaks_catalog.features.freaks.Freak
import com.agilefreaks.freaks_catalog.features.freaks.FreaksListQuery
import com.agilefreaks.freaks_catalog.features.freaks.model.apolloClient
import com.apollographql.apollo.coroutines.await
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class FreaksRepository {
    suspend fun getFreaksFromApi(): List<Freak> = withContext(IO) {
        val freaks: List<Freak>?
        val response = apolloClient.query(FreaksListQuery()).await().data
        freaks = mapFreaks(response)

        freaks
    }

    private fun mapFreaks(response: FreaksListQuery.Data?): List<Freak> {
        val freaks = response?.freaks?.nodes?.map {
            Freak(
                id = it?.id ?: "",
                firstName = it?.name ?: "",
                lastName = it?.name ?: "",
                role = "",
                norm = "",
                level = "",
                description = "",
                photo = it?.photo?.uri as String,
                skills = emptyList(),
                projects = emptyList()
            )
        }
        return freaks ?: emptyList()
    }
}
