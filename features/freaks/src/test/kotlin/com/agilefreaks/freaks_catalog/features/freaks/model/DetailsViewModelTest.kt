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

    @Test
    fun `will get the freak with the right id`(){
        val freak1 = FreakD(
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
        val freak2 = FreakD(
            "2",
            "Mihai2",
            "Boss",
            "Developer",
            "Full-time",
            "expert",
            "the boss",
            "photo",
            "c++",
            "pasaj"
        )

        val freak3 = FreakD(
            "3",
            "Mihai3",
            "Boss",
            "Developer",
            "Full-time",
            "expert",
            "the boss",
            "photo",
            "c++",
            "pasaj"
        )

        val freaks = mutableListOf(freak1,freak2,freak3)
        val rightFreak = freak2

        class DetailsMockRepository2(val freaks:List<FreakD>): FreakDetailsRepository {
            override suspend fun getFreakFromApi(x: String): FreakD? {
                return freaks[x.toInt()]
            }

        }

        val detailsMockRepository = DetailsMockRepository2(freaks)

        val rFreak = runBlocking { detailsMockRepository.getFreakFromApi("1") }
        assert(rFreak == rightFreak)
    }
}