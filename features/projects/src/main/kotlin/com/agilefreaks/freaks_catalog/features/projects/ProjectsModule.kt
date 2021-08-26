package com.agilefreaks.freaks_catalog.features.projects

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val projectsFragmentModule = module {
    single <ProjectsDataSource> {ProjectsApolloDataSource()}
    single<ProjectsRepository> {ProjectsRepositoryImpl(get())}
    viewModel{
        ProjectViewModel(get())
    }
}