package org.chevalierlab.kashier.home.presentation

import org.chevalierlab.kashier.home.domain.models.Item

sealed interface HomeEvent {
    data class OnSearchQueryChange(val query: String): HomeEvent
    data object OnSearchQuerySubmit: HomeEvent
    data class OnSelectedItemVisibilityChange(val isVisible: Boolean): HomeEvent
    data class OnAllItemVisibilityChange(val isVisible: Boolean): HomeEvent
    data class OnAddItem(val item: Item): HomeEvent
    data class OnPostItem(val name: String, val price: String): HomeEvent
    data class OnRemoveItem(val item: Item): HomeEvent
    data object OnSaveTransaction: HomeEvent
    data object OnLoadData: HomeEvent
    data object CreateUserName: HomeEvent
}