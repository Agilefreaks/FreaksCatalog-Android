package com.agilefreaks.freaks_catalog.features.freaks.model

import com.agilefreaks.freaks_catalog.features.freaks.MainCoroutineScopeRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class FreaksViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineScopeRule()
    
    @ExperimentalCoroutinesApi
    @Test
    fun `freaks will populate on init`() = mainCoroutineRule.runBlockingTest {
        val viewModel = FreaksViewModel()

        assertThat(viewModel.freaks.value).isNotEmpty()
    }
}
