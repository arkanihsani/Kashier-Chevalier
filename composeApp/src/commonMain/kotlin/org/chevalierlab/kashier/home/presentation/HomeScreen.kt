package org.chevalierlab.kashier.home.presentation

import androidx.compose.animation.*
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
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
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
    onNavigate: (Any) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.items, state.userName) {
        if (state.items.isEmpty() && state.userName.isNotEmpty()) {
            onEvent(HomeEvent.OnLoadData)
        }
    }

    LaunchedEffect(state.userName) {
        delay(500.milliseconds)
        if (state.userName.isBlank()) {
            onEvent(HomeEvent.CreateUserName)
        } else {
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = getString(Res.string.user_created) + state.userName,
                    withDismissAction = true
                )
            }
        }
    }

    LaunchedEffect(state.errorMessage) {
        if (!state.errorMessage.isNullOrEmpty()) {
            snackbarHostState.showSnackbar(
                message = state.errorMessage,
                withDismissAction = true
            )
        }
    }

    if (state.itemSheetOpen) {
        ItemBottomSheet(
            itemName = state.itemName,
            itemPrice = state.itemPrice,
            onDismissRequest = { onEvent(HomeEvent.DismissItemSheet) },
            onItemNameChange = { onEvent(HomeEvent.OnItemNameChanged(it)) },
            onItemPriceChange = { onEvent(HomeEvent.OnItemPriceChanged(it)) },
            onSave = { onEvent(HomeEvent.OnPostItem) },
            onDelete = { onEvent(HomeEvent.OnDeleteItem(state.itemId)) },
            isEditing = state.isEditing
        )
    }

    Box {
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
                    onClick = { onEvent(HomeEvent.ShowItemSheet) },
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    text = { Text(text = stringResource(Res.string.add_item_fab_label)) },
                    icon = {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = stringResource(Res.string.add_item_fab_label)
                        )
                    }
                )
            }
        ) { paddingValues ->
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
                        onSave = { onEvent(HomeEvent.OnSaveTransaction) },
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
                            onEdit = {
                                onEvent(
                                    HomeEvent.OnSetItem(
                                        itemId = item.id,
                                        itemName = item.name,
                                        itemPrice = item.price.toString()
                                    )
                                )
                            },
                            onAdd = {
                                onEvent(HomeEvent.OnAddItem(item))
                            }
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

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        state = HomeState(),
        onEvent = { },
        onNavigate = { }
    )
}