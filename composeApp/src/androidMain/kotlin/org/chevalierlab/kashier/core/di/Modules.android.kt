package org.chevalierlab.kashier.core.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.chevalierlab.kashier.core.network.HttpClientFactory
import org.chevalierlab.kashier.core.preferences.createDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModules: Module
    get() = module {
        single<HttpClient> { HttpClientFactory.create(OkHttp.create()) }
        single { createDataStore(androidContext()) }
    }