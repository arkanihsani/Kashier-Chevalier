package org.chevalierlab.kashier.core.di

import org.chevalierlab.kashier.home.data.DummyDataSource
import org.chevalierlab.kashier.home.data.DummyDataSourceImpl
import org.chevalierlab.kashier.home.domain.repository.HomeRepository
import org.chevalierlab.kashier.home.domain.repository.HomeRepositoryImpl
import org.chevalierlab.kashier.home.presentation.HomeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModules: Module

val shareModules = module {
    /* Define modules here */
    /* From DataSourceImpl binded with interface -> RepositoryImpl binded with interface -> Factory ViewModel */
    singleOf(::DummyDataSourceImpl).bind<DummyDataSource>()
    singleOf(::HomeRepositoryImpl).bind<HomeRepository>()

    factory { HomeViewModel(get()) }
}