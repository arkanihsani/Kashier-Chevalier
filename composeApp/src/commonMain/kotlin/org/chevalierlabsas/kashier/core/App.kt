package org.chevalierlabsas.kashier.core

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.chevalierlabsas.kashier.home.presentation.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        HomeScreen()
    }
}