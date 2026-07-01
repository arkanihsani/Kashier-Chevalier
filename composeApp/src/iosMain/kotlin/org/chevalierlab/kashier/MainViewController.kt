package org.chevalierlab.kashier

import androidx.compose.ui.window.ComposeUIViewController
import org.chevalierlab.kashier.core.App

fun MainViewController() = ComposeUIViewController(
    configure = {

    }
) { App() }