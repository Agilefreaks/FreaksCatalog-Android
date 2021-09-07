package com.agilefreaks.freaks_catalog.features.freaks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.agilefreaks.freaks_catalog.features.freaks.details.DetailsViewModel
import com.agilefreaks.freaks_catalog.features.freaks.details.FreakDetailsRepository
import com.agilefreaks.freaks_catalog.features.freaks.model.FreakDetails
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailsViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val freakDetailsRepository = mock<FreakDetailsRepository>()

    @Test
    fun `loadFreak will populate freak with value from repository`() = runBlockingTest {
        val freak = FreakDetails(
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
        val viewModel = DetailsViewModel(freakDetailsRepository)
        whenever(freakDetailsRepository.getFreakDetailsFromApi("1")).thenReturn(freak)

        viewModel.loadFreak("1")

        assertThat(viewModel.freak.value).isEqualTo(freak)
    }
}