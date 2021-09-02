package com.agilefreaks.freaks_catalog.features.freaks.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.agilefreaks.freaks_catalog.features.freaks.FreakDetailsQuery
import com.agilefreaks.freaks_catalog.features.freaks.details.FreakDetailsDataSource
import com.agilefreaks.freaks_catalog.features.freaks.details.FreakDetailsRepositoryImpl
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FreakDetailsRepositoryImplTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val freakDetailsDataSource = mock<FreakDetailsDataSource>()

    @Test
    fun `getFreakFromApi will return only the freak with correct id`() = runBlockingTest {
        val freakDetailsRepository = FreakDetailsRepositoryImpl(freakDetailsDataSource)
        val freakList = buildFreakList()

        whenever(freakDetailsDataSource.getFreaks()).thenReturn(freakList)

        val test = freakDetailsRepository.getFreakFromApi("1")

        assertThat(test?.firstName).isEqualTo("George")
    }

    private fun buildFreakList(): FreakDetailsQuery.Data =
        FreakDetailsQuery.Data(
            freaks = FreakDetailsQuery.Freaks(
                nodes = listOf(
                    FreakDetailsQuery.Node(
                        id = "1",
                        firstName = "George",
                        lastName = "Buhnici",
                        description = "description",
                        photo = null,
                        norm = FreakDetailsQuery.Norm("Norm", "Full-time"),
                        role = FreakDetailsQuery.Role("Role", "Developer"),
                        level = FreakDetailsQuery.Level("Level", "Profesor"),
                        projects = listOf(),
                        technologies = listOf()
                    )
                )
            )
        )
}
