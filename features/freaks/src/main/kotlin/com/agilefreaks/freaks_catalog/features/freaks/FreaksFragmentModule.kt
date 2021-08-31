package com.agilefreaks.freaks_catalog.features.freaks

import com.agilefreaks.freaks_catalog.features.freaks.filter.FilterRepository
import com.agilefreaks.freaks_catalog.features.freaks.filter.FilterRepositoryImpl
import com.agilefreaks.freaks_catalog.features.freaks.filter.FilterViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val freaksFragmentModule = module {
    single<FreaksDataSource> { FreaksApolloDataSource() }
    single<FreaksRepository> { FreaksRepositoryImpl(get()) }
    single<FilterRepository> { FilterRepositoryImpl(get()) }
    viewModel {
        FreaksViewModel(get(), get())
        FilterViewModel(get())
    }
}
