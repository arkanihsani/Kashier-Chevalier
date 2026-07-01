package org.chevalierlab.kashier.home.presentation

import org.chevalierlab.kashier.home.domain.models.Item

sealed interface HomeEvent {
    data class OnSearchQueryChange(val query: String) : HomeEvent
    data object OnSearchQuerySubmit : HomeEvent
    data class OnSelectedItemVisibilityChange(val isVisible: Boolean) : HomeEvent
    data class OnAllItemVisibilityChange(val isVisible: Boolean) : HomeEvent
    data class OnAddItem(val item: Item) : HomeEvent
    data object OnPostItem : HomeEvent
    data class OnRemoveItem(val item: Item) : HomeEvent
    data object OnSaveTransaction : HomeEvent
    data object OnLoadData : HomeEvent
    data object CreateUserName : HomeEvent
    data class OnSetItem(val itemId: Int, val itemName: String, val itemPrice: String) : HomeEvent
    data object DismissItemSheet : HomeEvent
    data object ShowItemSheet : HomeEvent
    data class OnItemNameChanged(val name: String) : HomeEvent
    data class OnItemPriceChanged(val price: String) : HomeEvent
    data class OnDeleteItem(val itemId: Int) : HomeEvent
}