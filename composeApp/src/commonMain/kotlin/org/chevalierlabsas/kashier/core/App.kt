package org.chevalierlabsas.kashier.core

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import org.chevalierlabsas.kashier.home.presentation.HomeScreen
import org.chevalierlabsas.kashier.home.presentation.HomeViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel = viewModel<HomeViewModel>()
        val state by viewModel.state.collectAsState()
        HomeScreen(
            state = state,
            onEvent = viewModel::onEvent,
        )
    }
}