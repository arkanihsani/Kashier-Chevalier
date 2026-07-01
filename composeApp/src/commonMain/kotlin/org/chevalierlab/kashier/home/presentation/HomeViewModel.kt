package org.chevalierlab.kashier.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.chevalierlab.kashier.home.domain.models.Item
import org.chevalierlab.kashier.home.domain.repository.HomeRepository

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    private val _userName = repository.getUser()
    private val _state = MutableStateFlow(HomeState())
    val state = combine(_state, _userName) { state, userName ->
        state.copy(userName = userName)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HomeState())

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnRemoveItem -> removeItem(event.item)
            is HomeEvent.OnAddItem -> addItem(event.item)
            is HomeEvent.OnAllItemVisibilityChange -> setAllItemVisibility(event.isVisible)
            is HomeEvent.OnSelectedItemVisibilityChange -> setSelectedItemVisibility(event.isVisible)
            is HomeEvent.OnSearchQueryChange -> updateQuery(event.query)
            HomeEvent.OnSearchQuerySubmit -> search()
            HomeEvent.OnSaveTransaction -> saveTransaction()
            HomeEvent.OnLoadData -> loadData()
            HomeEvent.CreateUserName -> createUser()
            is HomeEvent.OnPostItem -> postItem(event.name, event.price)
        }
    }

    private fun postItem(name: String, price: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            repository.postItem(
                Item(0, state.value.userName, name = name, price = price.toDouble())
            )
            loadData()
            _state.update { it.copy(isLoading = false) }
        }
    }

    private fun createUser() {
        viewModelScope.launch {
            repository.createUser()
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            repository.getItems(userId = state.value.userName)
                .onSuccess { result ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            items = result
                        )
                    }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.message
                        )
                    }
                }
        }
    }

    private fun removeItem(item: Item) {
        _state.update {
            it.copy(
                selectedItems = it.selectedItems - item,
                totalPrice = it.totalPrice - item.price
            )
        }
    }

    private fun saveTransaction() {
        viewModelScope.launch {
            repository.postTransaction(
                total = state.value.totalPrice.toInt(),
                userId = state.value.userName,
                items = state.value.selectedItems.count()
            )
                .onSuccess {

                }
        }
    }

    private fun addItem(item: Item) {
        _state.update {
            it.copy(
                selectedItems = it.selectedItems + item,
                totalPrice = it.totalPrice + item.price
            )
        }
    }

    private fun setAllItemVisibility(visible: Boolean) {
        _state.update { it.copy(allItemsVisible = visible) }
    }

    private fun setSelectedItemVisibility(visible: Boolean) {
        _state.update { it.copy(selectedItemVisible = visible) }
    }

    private fun search() {
        if (state.value.searchQuery.isNotBlank()) {
            _state.update {
                it.copy(
                    items = _state.value.items.filter { item ->
                        item.name.contains(_state.value.searchQuery, ignoreCase = true)
                    }
                )
            }
        } else {
            loadData()
        }
    }

    private fun updateQuery(query: String) {
        _state.update { it.copy(searchQuery = query) }
        if (state.value.searchQuery.isBlank()) {
            loadData()
        }
    }

}