package com.agilefreaks.freaks_catalog.features.freaks.repository

import com.agilefreaks.freaks_catalog.features.freaks.FreaksListQuery
import com.agilefreaks.freaks_catalog.features.freaks.FreakList
import com.agilefreaks.freaks_catalog.features.freaks.FreaksDataSource

interface FreaksRepository {
    suspend fun getFreaksFromApi(): List<FreakList>
}

class FreaksRepositoryImpl(private val dataSource: FreaksDataSource) : FreaksRepository {
    override suspend fun getFreaksFromApi(): List<FreakList> =
        mapFreaks(dataSource.getFreaks())

    private fun mapFreaks(response: FreaksListQuery.Data?): List<FreakList> =
        response?.freaks?.nodes?.map {
            it.toFreak()
        } ?: emptyList()

    private fun FreaksListQuery.Node?.toFreak() =
        FreakList(
            id = this?.id ?: "",
            firstName = this?.firstName ?: "",
            photo = "https://cdn2.thecatapi.com/images/15o.jpg"
        )
}
