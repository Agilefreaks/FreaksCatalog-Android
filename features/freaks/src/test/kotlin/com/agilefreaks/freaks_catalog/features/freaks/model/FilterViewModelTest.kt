package com.agilefreaks.freaks_catalog.features.freaks.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.agilefreaks.freaks_catalog.features.freaks.CoroutineRule
import com.agilefreaks.freaks_catalog.features.freaks.filter.FilterViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

class FilterViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = CoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Test
    fun `init will load skills with all the proper skill names`() {
        val viewModel = FilterViewModel()
        val skillNames = viewModel.skills.value?.map { it.name }
        assertThat(skillNames).containsExactly("Android", "Kotlin", "Other Skill", "QA", "Ruby", "iOS")
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `init will set all skills to have isChecked false`() {
        val viewModel = FilterViewModel()
        val anyAreChecked = viewModel.skills.value?.any { it.isChecked.get() == true }

        assertThat(anyAreChecked).isFalse()
    }

    @Test
    fun `init will load projects`() {
        val viewModel = FilterViewModel()
        val projectNames = viewModel.projects.value?.map { it.name }
        assertThat(projectNames).containsExactly("EPIX", "Freaks Catalog", "New Project", "reAsig")
    }

    @Test
    fun `reset will set isChecked to false for all skills`() {
        val viewModel = FilterViewModel()
        viewModel.reset()
        val anyAreChecked = viewModel.skills.value?.any { it.isChecked.get() == true }
        assertThat(anyAreChecked).isFalse()
    }

    @Test
    fun `reset will set isChecked to false for all projects`() {
        val viewModel = FilterViewModel()
        viewModel.reset()
        val anyAreChecked = viewModel.projects.value?.any { it.isChecked.get() == true }
        assertThat(anyAreChecked).isFalse()
    }
}
