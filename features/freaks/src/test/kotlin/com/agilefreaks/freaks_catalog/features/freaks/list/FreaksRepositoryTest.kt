package com.agilefreaks.freaks_catalog.features.freaks.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.agilefreaks.freaks_catalog.features.freaks.CoroutineRule
import com.agilefreaks.freaks_catalog.features.freaks.model.Freak
import com.agilefreaks.freaks_catalog.features.freaks.FreaksDataSource
import com.agilefreaks.freaks_catalog.features.freaks.FreaksListQuery
import com.agilefreaks.freaks_catalog.features.freaks.FreaksRepositoryImpl
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class FreaksRepositoryTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = CoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Test
    fun `getFreaksFromApi will return a list of Freak models`() {
        val freak = Freak(
            id = "42",
            firstName = "Ion",
            photo = "photo_uri",
            skillsIds = listOf("11"),
            projectIds = listOf("22")
        )
        val dataSource = MockDataSource()
        val repository = FreaksRepositoryImpl(dataSource)

        val list = runBlocking { repository.getFreaksFromApi() }

        assertThat(list).containsExactly(freak)
    }

    class MockDataSource : FreaksDataSource {
        override suspend fun getFreaks(): FreaksListQuery.Data {
            val photo = FreaksListQuery.Photo(uri = "photo_uri")
            val node =
                FreaksListQuery.Node(
                    id = "42", firstName = "Ion", photo = photo,
                    technologies = listOf(FreaksListQuery.Technology(id = "11")),
                    projects = listOf(FreaksListQuery.Project(id = "22"))
                )
            val freaks = FreaksListQuery.Freaks(nodes = listOf(node))
            return FreaksListQuery.Data(freaks)
        }
    }
}
