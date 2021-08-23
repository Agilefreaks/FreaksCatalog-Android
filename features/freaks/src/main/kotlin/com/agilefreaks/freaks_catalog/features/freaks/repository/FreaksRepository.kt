package com.agilefreaks.freaks_catalog.features.freaks.repository

import com.agilefreaks.freaks_catalog.features.freaks.FreaksListQuery
import com.agilefreaks.freaks_catalog.features.freaks.Freak
import com.agilefreaks.freaks_catalog.features.freaks.FreaksDataSource

interface FreaksRepository {
    suspend fun getFreaksFromApi(): List<Freak>
}

class FreaksRepositoryImpl(private val dataSource: FreaksDataSource) : FreaksRepository {
    override suspend fun getFreaksFromApi(): List<Freak> =
        mapFreaks(dataSource.getFreaks())

    private fun mapFreaks(response: FreaksListQuery.Data?): List<Freak> =
        response?.freaks?.nodes?.map {
            it.toFreak()
        } ?: emptyList()

    private fun FreaksListQuery.Node?.toFreak() =
        Freak(
            freakId = this?.id ?: "",
            firstName = this?.firstName ?: "",
            photo = "https://cdn2.thecatapi.com/images/15o.jpg"
        )
}
