package org.chevalierlab.kashier.core.di

import org.chevalierlab.kashier.core.preferences.createDataStore
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModules: Module
    get() = module {
        single { createDataStore() }
    }