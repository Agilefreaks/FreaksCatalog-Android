package com.agilefreaks.freaks_catalog.features.freaks

import com.agilefreaks.freaks_catalog.features.freaks.filter.FilterRepository
import com.agilefreaks.freaks_catalog.features.freaks.filter.FilterRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val freaksFragmentModule = module {
    single<FreaksDataSource> { FreaksApolloDataSource() }
    single<FreaksRepository> { FreaksRepositoryImpl(get()) }
    single<FilterRepository> { FilterRepositoryImpl(get()) }
    viewModel {
        FreaksViewModel(get())
        FilterViewModel()
    }
}
