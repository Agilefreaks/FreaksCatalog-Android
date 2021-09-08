package com.agilefreaks.freaks_catalog.features.freaks.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.databinding.ObservableField
import com.agilefreaks.freaks_catalog.features.freaks.FreaksRepository
import com.agilefreaks.freaks_catalog.features.freaks.FreaksViewModel
import com.agilefreaks.freaks_catalog.features.freaks.filter.FilterRepository
import com.nhaarman.mockitokotlin2.mock
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FreaksViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val freaksRepository = mock<FreaksRepository>()
    private val filterRepository = mock<FilterRepository>()
    private val viewModel: FreaksViewModel by lazy {
        FreaksViewModel(freaksRepository, filterRepository)
    }

    @Test
    fun `freaks will populate on init`() = runBlockingTest {
        val freak = Freak(
            id = "22",
            firstName = "Robert",
            photo = "photo_uri",
            skillsIds = listOf("11"),
            projectIds = listOf("22")
        )
        whenever(freaksRepository.getFreaksFromApi()).thenReturn(listOf(freak))

        assertThat(viewModel.filteredFreaks.value).isEqualTo(listOf(freak))
    }

    @Test
    fun `projects will populate on init`() = runBlockingTest {
        val project = Project(
            id = "1",
            name = "Freaks Catalog"
        )
        whenever(filterRepository.getProjectsFromApi()).thenReturn(listOf(project))

        assertThat(viewModel.projects.value).isEqualTo(listOf(project))
    }

    @Test
    fun `skills will populate on init`() = runBlockingTest {
        val skill = Skill(
            id = "1",
            name = "Freaks Catalog"
        )
        whenever(filterRepository.getSkillsFromApi()).thenReturn(listOf(skill))

        //assertThat(viewModel.skills).isEqualTo(listOf(skill))
    }

    @Test
    fun `onSkillsButtonClicked will populate showFilterDialog with skills list and skills type`() =
        runBlockingTest {
            val resultPair: Pair<String, List<Skill>> = Pair("SKILLS", listOf(Skill("1", "Kotlin")))

            viewModel.onSkillsButtonClicked()

            assertThat(viewModel.showFilterDialog.value).isEqualTo(resultPair)
        }

    @Test
    fun `onProjectsButtonClicked will populate showFilterDialog with projects list and projects type`() =
        runBlockingTest {
            val resultPair: Pair<String, List<Project>> = Pair("PROJECTS", listOf(Project("11", "Freaks Catalog")))

            viewModel.onProjectsButtonClicked()

            assertThat(viewModel.showFilterDialog.value).isEqualTo(resultPair)
        }
}
