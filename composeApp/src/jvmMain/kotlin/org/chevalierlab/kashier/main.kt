package org.chevalierlab.kashier

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.chevalierlab.kashier.core.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Kashier",
    ) {
        App()
    }
}