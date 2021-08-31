package com.agilefreaks.freaks_catalog.features.freaks.filter

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val filterFragmentModule = module {
    single<FilterDataSource> { FilterApolloDataSource() }
    single<FilterRepository> { FilterRepositoryImpl(get()) }
    viewModel {
        FilterViewModel(get())
    }
}
