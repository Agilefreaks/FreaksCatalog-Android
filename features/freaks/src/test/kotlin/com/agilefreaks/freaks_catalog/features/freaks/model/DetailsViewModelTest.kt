package com.agilefreaks.freaks_catalog.features.freaks.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.agilefreaks.freaks_catalog.features.freaks.CoroutineRule
import com.agilefreaks.freaks_catalog.features.freaks.Freak
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
        val freak = Freak(
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
        private var freakDetails: Freak = Freak(
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
        override suspend fun getFreakFromApi(x: String): Freak? = freakDetails

        fun add(freak: Freak) {
            freakDetails = freak
        }
    }

    @Test
    fun `will get the freak with the right id`(){
        val freak1 = Freak(
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
        val freak2 = Freak(
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

        val freak3 = Freak(
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

        class DetailsMockRepository2(val freaks:List<Freak>): FreakDetailsRepository {
            override suspend fun getFreakFromApi(x: String): Freak? {
                return freaks[x.toInt()]
            }

        }

        val detailsMockRepository = DetailsMockRepository2(freaks)

        val rFreak = runBlocking { detailsMockRepository.getFreakFromApi("1") }
        assert(rFreak == rightFreak)
    }
}