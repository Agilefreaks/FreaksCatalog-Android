package com.agilefreaks.freaks_catalog.features.projects

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProjectsViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val projectsRepository = mock<ProjectsRepository>()
    private val viewModel: ProjectsViewModel by lazy {
        ProjectsViewModel(projectsRepository)
    }

    @Test
    fun `init will set isLoading with true`() = runBlockingTest {
        viewModel

        assertThat(viewModel.isLoading.value).isEqualTo(true)
    }

    @Test
    fun `loadProjects will set isLoading with false`() = runBlockingTest {
        viewModel.loadProjects()

        assertThat(viewModel.isLoading.value).isEqualTo(false)
    }

    @Test
    fun `loadProjects will set projects with correct value from repository`() = runBlockingTest {
        val projects = listOf(Project("1","proiect","uri"))
        whenever(projectsRepository.getProjectsFromApi()).thenReturn(projects)

        viewModel.loadProjects()

        assertThat(viewModel.projects.value).isEqualTo(projects)
    }
}
