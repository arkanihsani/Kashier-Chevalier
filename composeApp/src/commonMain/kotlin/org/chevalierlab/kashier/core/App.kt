package org.chevalierlab.kashier.core

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.chevalierlab.kashier.core.navigation.HistoryDestination
import org.chevalierlab.kashier.core.navigation.HomeDestination
import org.chevalierlab.kashier.history.presentation.HistoryScreen
import org.chevalierlab.kashier.home.presentation.HomeScreen
import org.chevalierlab.kashier.home.presentation.HomeViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {

        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = HomeDestination
        ) {
            composable<HomeDestination> {
                val viewModel = koinViewModel<HomeViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                HomeScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onNavigate = { navController.navigate(it) }
                )
            }
            composable<HistoryDestination> {
                HistoryScreen(
                    onNavigateBack = { navController.navigateUp() }
                )
            }
        }
    }
}