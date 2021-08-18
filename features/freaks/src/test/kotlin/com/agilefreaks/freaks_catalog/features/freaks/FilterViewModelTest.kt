package com.agilefreaks.freaks_catalog.features.freaks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.agilefreaks.freaks_catalog.features.freaks.model.FilterViewModel
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
        val skillNames = viewModel.skills.value?.map { it.skillName }
        assertThat(skillNames).containsExactly("Android", "Kotlin", "Other Skill", "iOS", "Ruby", "QA")
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

        assertThat(viewModel.projects.value).containsExactly("Freaks Catalog", "Proj2", "Tutorial", "Altkeva")
    }

    @Test
    fun `reset will set isChecked to false for all skill`() {
        TODO()
    }
}