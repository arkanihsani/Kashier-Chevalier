package org.chevalierlab.kashier.core.di

import org.chevalierlab.kashier.core.preferences.AppPreferences
import org.chevalierlab.kashier.home.data.HomeRepositoryImpl
import org.chevalierlab.kashier.home.data.datasource.DummyDataSource
import org.chevalierlab.kashier.home.data.datasource.DummyDataSourceImpl
import org.chevalierlab.kashier.home.data.datasource.UserLocalDataSource
import org.chevalierlab.kashier.home.data.datasource.UserLocalDataSourceImpl
import org.chevalierlab.kashier.home.domain.repository.HomeRepository
import org.chevalierlab.kashier.home.presentation.HomeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModules: Module

val sharedModules = module {
    /* Define modules here */
    /* From DataSourceImpl binded with interface -> RepositoryImpl binded with interface -> Factory ViewModel */
    singleOf(::DummyDataSourceImpl).bind<DummyDataSource>()
    singleOf(::UserLocalDataSourceImpl).bind<UserLocalDataSource>()
    singleOf(::HomeRepositoryImpl).bind<HomeRepository>()

    factory { AppPreferences(get()) }
    factory { HomeViewModel(get()) }
}