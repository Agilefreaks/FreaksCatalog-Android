package com.agilefreaks.freaks_catalog.features.projects.details

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsFragmentModule = module {
    single<ProjectDetailsDataSource> {ProjectDetailsApolloDataSource() }
    single<ProjectDetailsRepository> {ProjectDetailsRepositoryImpl(get())}
    viewModel {
        DetailsViewModel(get())
    }
}
