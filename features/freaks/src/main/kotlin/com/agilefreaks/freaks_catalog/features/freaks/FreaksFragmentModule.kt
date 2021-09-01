package com.agilefreaks.freaks_catalog.features.freaks

import com.agilefreaks.freaks_catalog.features.freaks.filter.FilterViewModel
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
