package org.chevalierlab.kashier.home.presentation

import org.chevalierlab.kashier.home.domain.models.Item

data class HomeState(
    val searchQuery: String = "",
    val selectedItemVisible: Boolean = true,
    val allItemsVisible: Boolean = true,
    val items: List<Item> = emptyList(),
    val selectedItems: List<Item> = emptyList(),
    val totalPrice: Double = 0.0,
    val userName: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)