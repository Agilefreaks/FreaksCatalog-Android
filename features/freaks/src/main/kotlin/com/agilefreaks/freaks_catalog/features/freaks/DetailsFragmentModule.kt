package com.agilefreaks.freaks_catalog.features.freaks

import com.agilefreaks.freaks_catalog.features.freaks.repository.FreakDetailsRepository
import com.agilefreaks.freaks_catalog.features.freaks.repository.FreakDetailsRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsFragmentModule = module {
    single<FreakDetailsDataSource> { FreakDetailsApolloDataSource()}
    single<FreakDetailsRepository> { FreakDetailsRepositoryImpl(get()) }
    viewModel {
        DetailsViewModel(get())
    }
}
