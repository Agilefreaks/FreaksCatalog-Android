package com.agilefreaks.freaks_catalog.features.freaks

import com.apollographql.apollo.coroutines.await

interface FreakDetailsRepository{
    suspend fun getFreakFromApi(x: String): Freak?
}

class FreaksRepositoryImpl(private val dataSource: FreakDetailsDataSource)  : FreakDetailsRepository {
    override suspend fun getFreakFromApi(x: String): Freak? =
        mapFreaks(dataSource.getFreaks())?.get(x.toInt())

    fun mapFreaks(response: FreakDetailsQuery.Data?): List<Freak>? =
        response?.freaks?.nodes?.map {
            it.toFreak()
        }

    private fun FreakDetailsQuery.Node?.toFreak() = Freak(
        freakId = this?.id ?: "",
        firstName = this?.name ?: "",
        lastName = this?.name ?: "",
        description = this?.description ?: "",
        level = this?.level?.name ?: "",
        norm = this?.norm?.name ?: "",
        photo = this?.photo?.uri as String ?: "",
        role = this.role.name ?: "",
        projects = buildProjectsNameList(this.projects).joinToString(", "),
        skills = buildSkillsNameList(this.skills).joinToString(", ")
    )

    fun buildProjectsNameList(projects: List<FreakDetailsQuery.Project>): List<String> =
        projects.map { it.name }

    fun buildSkillsNameList(skills: List<FreakDetailsQuery.Skill>): List<String> =
        skills.map { it.name }
}
