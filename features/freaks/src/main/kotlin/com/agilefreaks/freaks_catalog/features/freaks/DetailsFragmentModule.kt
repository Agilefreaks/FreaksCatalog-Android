package com.agilefreaks.freaks_catalog.features.freaks

import com.agilefreaks.freaks_catalog.features.freaks.repository.FreakDetailsRepository
import com.agilefreaks.freaks_catalog.features.freaks.repository.FreakDetailsRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val DetailsFragmentModule = module {
    single<FreakDetailsApolloDataSource> { FreakDetailsApolloDataSource()}
    single<FreakDetailsRepository> { FreakDetailsRepositoryImpl(get()) }
    single { FreaksDetailsViewModelFactory(get()) }
    viewModel {
        DetailsViewModel(get())
    }
}
