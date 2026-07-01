package org.chevalierlab.kashier.core.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import org.chevalierlab.kashier.core.network.HttpClientFactory
import org.chevalierlab.kashier.core.preferences.createDataStore
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModules: Module
    get() = module {
        single<HttpClient> {
            HttpClientFactory.create(
                engine = Darwin.create(),
                /* Gunakan `get()` agar otomatis di inject oleh Koin */
                preferences = get()
            )
        }
        single { createDataStore() }
    }