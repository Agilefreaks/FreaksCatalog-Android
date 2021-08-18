package com.agilefreaks.freaks_catalog.features.freaks

import com.agilefreaks.freaks_catalog.features.freaks.model.FreaksViewModel
import com.agilefreaks.freaks_catalog.features.freaks.repository.FreaksRepository
import com.agilefreaks.freaks_catalog.features.freaks.repository.FreaksRepositoryImpl
import org.koin.dsl.module

val freaksFragmentModule = module {
    single <FreaksDataSource> { ApolloDataSource() }
    single<FreaksRepository> { FreaksRepositoryImpl(get()) }
    single { FreaksViewModel(get()) }
    single { FreaksViewModelFactory(get()) }
}
