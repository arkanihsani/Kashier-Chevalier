package org.chevalierlab.kashier.home.presentation

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kashier.composeapp.generated.resources.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.chevalierlab.kashier.core.navigation.HistoryDestination
import org.chevalierlab.kashier.home.presentation.components.*
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
    onNavigate: (Any) -> Unit
) {
    // Buat coroutineScope
    val scope = rememberCoroutineScope()
    // Snackbar untuk melihatkan hasil ID unik.
    val snackbarHostState = remember { SnackbarHostState() }

    // Cek state username
    LaunchedEffect(state.userName) {
        delay(500) // Beri waktu Preferences untuk load value.
        if (state.userName.isBlank()) {
            // Jika kosong maka generate.
            onEvent(HomeEvent.CreateUserName)
        } else {
            // Jika tidak, maka tampilkan di Snackbar
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = "Username telah dibuat: " + state.userName,
                    withDismissAction = true
                )
            }
        }
    }

    LaunchedEffect(state.items) {
        if (state.items.isEmpty()) {
            onEvent(HomeEvent.OnLoadData)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(Res.string.app_name))
                },
                actions = {
                    IconButton(
                        onClick = { onNavigate(HistoryDestination) }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.History,
                            contentDescription = stringResource(Res.string.history_topbar)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { onEvent(HomeEvent.OnShowBottomSheet(true)) },
                containerColor = MaterialTheme.colorScheme.tertiary,
                text = { Text(text = stringResource(Res.string.add_item_fab_label)) },
                icon = { Icon(Icons.Filled.Add, contentDescription = stringResource(Res.string.add_item_fab_label)) }
            )
        }
    ) { paddingValues ->
        if (state.showModalBottomSheet) {
            ItemModalBottomSheet(
                itemId = state.itemId,
                itemName = state.itemName,
                onItemNameChange = {  },
                itemPrice = state.itemPrice,
                onItemPriceChange = {  },
                onSubmit = {  },
                onDismissRequest = { onEvent(HomeEvent.OnShowBottomSheet(false)) }
            )
        }
        LazyColumn(
            contentPadding = PaddingValues(top = paddingValues.calculateTopPadding(), bottom = 76.dp),
        ) {
            item {
                TotalPriceHeader(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    totalPrice = state.totalPrice
                )
            }
            item {
                SaveButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onSave = { TODO("Save data.") },
                    enabled = state.selectedItems.isNotEmpty() && state.totalPrice > 0.00
                )
            }
            item {
                HomeSeparator(
                    modifier = Modifier.padding(start = 16.dp, end = 4.dp),
                    title = stringResource(Res.string.selected_item_label),
                    visible = state.selectedItemVisible,
                    onAction = { visible ->
                        onEvent(HomeEvent.OnSelectedItemVisibilityChange(visible))
                    }
                )
            }
            item {
                AnimatedContent(
                    targetState = state.selectedItems.isEmpty(),
                    transitionSpec = {
                        fadeIn() togetherWith fadeOut()
                    }
                ) { empty ->
                    if (!empty) {
                        AnimatedVisibility(
                            visible = state.selectedItemVisible,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            FlowRow(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                itemVerticalAlignment = Alignment.Top,
                                content = {
                                    state.selectedItems.map { item ->
                                        SelectedItemChip(
                                            onRemove = {
                                                onEvent(HomeEvent.OnRemoveItem(item))
                                            },
                                            item = item,
                                            modifier = Modifier.padding(horizontal = 4.dp)
                                        )
                                    }
                                }
                            )
                        }
                    } else {
                        Text(
                            modifier = Modifier.padding(16.dp)
                                .fillMaxWidth(),
                            text = stringResource(Res.string.empty_item),
                            style = MaterialTheme.typography.headlineSmall,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            item {
                HomeSeparator(
                    modifier = Modifier.padding(start = 16.dp, end = 4.dp),
                    title = stringResource(Res.string.all_item_label),
                    visible = state.allItemsVisible,
                    onAction = { visible ->
                        onEvent(HomeEvent.OnAllItemVisibilityChange(visible))
                    }
                )
            }
            item {
                Searchbar(
                    modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
                    value = state.searchQuery,
                    onValueChange = { onEvent(HomeEvent.OnSearchQueryChange(it)) },
                    onSearch = { onEvent(HomeEvent.OnSearchQuerySubmit) }
                )
            }
            items(state.items) { item ->
                AnimatedVisibility(
                    visible = state.allItemsVisible,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    ItemCard(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                        item = item,
                        onEdit = { },
                        onAdd = {
                            onEvent(HomeEvent.OnAddItem(item))
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        state = HomeState(),
        onEvent = { },
        onNavigate = { }
    )
}