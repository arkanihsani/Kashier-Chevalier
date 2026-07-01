package org.chevalierlab.kashier

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.chevalierlab.kashier.core.App
import org.chevalierlab.kashier.core.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "Kashier",
    ) {
        App()
    }
}