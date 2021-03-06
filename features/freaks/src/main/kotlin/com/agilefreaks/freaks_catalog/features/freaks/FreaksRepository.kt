package com.agilefreaks.freaks_catalog.features.freaks

import com.agilefreaks.freaks_catalog.features.freaks.model.Freak

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
            id = this?.id ?: "",
            firstName = this?.firstName ?: "",
            photo = this?.photo?.uri?.toString() ?: ""
        )
}
