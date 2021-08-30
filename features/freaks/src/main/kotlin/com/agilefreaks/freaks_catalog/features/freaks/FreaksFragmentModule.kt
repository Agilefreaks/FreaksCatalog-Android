package com.agilefreaks.freaks_catalog.features.freaks

import com.agilefreaks.freaks_catalog.features.freaks.model.FilterViewModel
import com.agilefreaks.freaks_catalog.features.freaks.model.FreaksViewModel
import com.agilefreaks.freaks_catalog.features.freaks.repository.FreaksRepository
import com.agilefreaks.freaks_catalog.features.freaks.repository.FreaksRepositoryImpl
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val freaksFragmentModule = module {
    single<FreaksDataSource> { FreaksApolloDataSource() }
    single<FreaksRepository> { FreaksRepositoryImpl(get()) }
    viewModel {
        FreaksViewModel(get())
    }
    viewModel {
        FilterViewModel()
    }
}
