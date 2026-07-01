package org.chevalierlab.kashier

import androidx.compose.ui.window.ComposeUIViewController
import org.chevalierlab.kashier.core.App
import org.chevalierlab.kashier.core.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }