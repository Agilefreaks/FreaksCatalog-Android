package com.agilefreaks.freaks_catalog.features.freaks.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.agilefreaks.freaks_catalog.features.freaks.CoroutineRule
import com.agilefreaks.freaks_catalog.features.freaks.FreakList
import com.agilefreaks.freaks_catalog.features.freaks.repository.FreaksRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

class FreaksViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = CoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Test
    fun `freaks will populate on init`() {
        // setup
        val freak = FreakList(
            id = "22",
            firstName = "Robert",
            photo = "https://i.ibb.co/kK7MQQD/rice.jpg"
        )
        val freaksRepositoryMock = FreaksRepositoryMock()
        freaksRepositoryMock.add(freak)

        val viewModel = FreaksViewModel(freaksRepositoryMock)

        assertThat(viewModel.freaks.value).containsExactly(freak)
    }

    class FreaksRepositoryMock(private val freakLists: MutableList<FreakList> = mutableListOf()) :
        FreaksRepository {
        override suspend fun getFreaksFromApi(): List<FreakList> = freakLists

        fun add(freakList: FreakList) = freakLists.add(freakList)
    }
}
