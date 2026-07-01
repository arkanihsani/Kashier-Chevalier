package org.chevalierlab.kashier.home.presentation

import org.chevalierlab.kashier.home.domain.Item

sealed interface HomeEvent {
    data class OnSearchQueryChange(val query: String): HomeEvent
    data object OnSearchQuerySubmit: HomeEvent
    data class OnSelectedItemVisibilityChange(val isVisible: Boolean): HomeEvent
    data class OnAllItemVisibilityChange(val isVisible: Boolean): HomeEvent
    data class OnAddItem(val item: Item): HomeEvent
    data class OnRemoveItem(val item: Item): HomeEvent

    data class OnShowBottomSheet(val show: Boolean): HomeEvent
    data object OnSaveTransaction: HomeEvent
}