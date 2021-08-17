package com.agilefreaks.freaks_catalog.features.freaks

import com.agilefreaks.freaks_catalog.features.freaks.model.FreaksViewModel
import com.agilefreaks.freaks_catalog.features.freaks.repository.FreaksRepository
import com.agilefreaks.freaks_catalog.features.freaks.repository.FreaksRepositoryImpl
import org.koin.dsl.module

val freaksFragmentModule = module {
    single { ApolloDataSource() as FreaksDataSource}
    single { FreaksRepositoryImpl(get()) as FreaksRepository }
    single { FreaksViewModel(get()) }
    single { FreaksViewModelFactory(get()) }
}
