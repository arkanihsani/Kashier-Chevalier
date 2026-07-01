package org.chevalierlab.kashier.history.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kashier.composeapp.generated.resources.Res
import kashier.composeapp.generated.resources.history_topbar
import kashier.composeapp.generated.resources.navigate_back
import org.chevalierlab.kashier.history.presentation.component.HistoryCard
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    state: HistoryState,
    onEvent: (HistoryEvent) -> Unit,
    onNavigateBack: () -> Unit
) {

    LaunchedEffect(state.userId) {
        if (state.userId.isNotBlank()) {
            onEvent(HistoryEvent.OnLoadData)
        }
    }

    Box {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(stringResource(Res.string.history_topbar))
                    },
                    navigationIcon = {
                        IconButton(onClick = onNavigateBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(Res.string.navigate_back)
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            LazyColumn(
                contentPadding = paddingValues
            ) {
                state.histories.forEach { (label, histories) ->
                    stickyHeader {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            text = label,
                        )
                    }
                    items(histories) { historyItem ->
                        HistoryCard(
                            history = historyItem,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }
        AnimatedVisibility(
            visible = state.isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }

}

@OptIn(ExperimentalTime::class)
@Preview
@Composable
fun HomeScreenPreview() {
    HistoryScreen(HistoryState(), onNavigateBack = {}, onEvent = {})
}