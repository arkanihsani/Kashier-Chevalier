package org.chevalierlabsas.kashier

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.chevalierlabsas.kashier.core.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Kashier",
    ) {
        App()
    }
}