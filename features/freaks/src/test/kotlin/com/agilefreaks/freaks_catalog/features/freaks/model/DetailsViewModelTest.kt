package com.agilefreaks.freaks_catalog.features.freaks.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.agilefreaks.freaks_catalog.features.freaks.CoroutineRule
import com.agilefreaks.freaks_catalog.features.freaks.FreakD
import com.agilefreaks.freaks_catalog.features.freaks.repository.FreakDetailsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class DetailsViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = CoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Test
    fun `freak will populate on init`() {
        val freak = FreakD(
            "1",
            "Mihai",
            "Boss",
            "Developer",
            "Full-time",
            "expert",
            "the boss",
            "photo",
            "c++",
            "pasaj"
        )

        val freakDetailsRepository = DetailsViewModelTest.DetailsMockRepository()
        freakDetailsRepository.add(freak)
    }

    class DetailsMockRepository(
        private var freakDetails: FreakD = FreakD(
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
        )
    ) : FreakDetailsRepository {
        override suspend fun getFreakFromApi(x: String): FreakD? = freakDetails

        fun add(freakD: FreakD) {
            freakDetails = freakD
        }
    }
}
