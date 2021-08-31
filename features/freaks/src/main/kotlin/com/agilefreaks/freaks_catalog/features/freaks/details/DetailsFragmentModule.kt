package com.agilefreaks.freaks_catalog.features.freaks.details

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsFragmentModule = module {
    single<FreakDetailsDataSource> { FreakDetailsApolloDataSource() }
    single<FreakDetailsRepository> { FreakDetailsRepositoryImpl(get()) }
    viewModel {
        DetailsViewModel(get())
    }
}
