package com.agilefreaks.freaks_catalog.features.projects

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.agilefreaks.freaks_catalog.features.projects.details.ProjectDetailsViewModel
import com.agilefreaks.freaks_catalog.features.projects.details.ProjectDetails
import com.agilefreaks.freaks_catalog.features.projects.details.ProjectDetailsRepository
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProjectDetailsViewModelTest {
    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val projectDetailsRepository = mock<ProjectDetailsRepository>()

    @ExperimentalCoroutinesApi
    @Test
    fun `loadProject will populate project with values from repository`() = runBlockingTest {
        val project = ProjectDetails(
            "1",
            "EPIX",
            "An American premium cable and satellite TV network.",
            "photo",
            "John, John, Virgil, Robert",
            "Ruby"
        )
        val viewModel = ProjectDetailsViewModel(projectDetailsRepository)
        whenever(projectDetailsRepository.getProjectFromApi("1")).thenReturn(project)

        viewModel.loadProject("1")

        assertThat(viewModel.project.value).isEqualTo(project)
    }
}
