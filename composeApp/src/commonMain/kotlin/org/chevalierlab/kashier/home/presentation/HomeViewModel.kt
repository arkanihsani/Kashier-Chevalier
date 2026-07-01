package org.chevalierlab.kashier.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.chevalierlab.kashier.home.domain.models.Item
import org.chevalierlab.kashier.home.domain.repository.HomeRepository

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    private val _token = repository.getToken()
    private val _userName = repository.getUser()
    private val _state = MutableStateFlow(HomeState())
    val state = combine(_state, _userName, _token) { state, userName, token ->
        state.copy(
            userName = state.userName.ifEmpty { userName },
            tokenLoaded = token.isNotBlank()
        )
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
            is HomeEvent.OnPostItem -> postItem()
            HomeEvent.DismissItemSheet -> dismissItemSheet()
            is HomeEvent.OnItemNameChanged -> changeItemName(event.name)
            is HomeEvent.OnItemPriceChanged -> changeItemPrice(event.price)
            HomeEvent.ShowItemSheet -> showItemSheet()
            is HomeEvent.OnSetItem -> setItem(event.itemId, event.itemName, event.itemPrice)
            is HomeEvent.OnDeleteItem -> deleteItem(event.itemId)
            is HomeEvent.OnUsernameChanged -> updateUsername(event.username)
            HomeEvent.OnSaveUser -> saveUser()
            HomeEvent.ShowUserSheet -> showUserSheet()
        }
    }

    private fun showUserSheet() {
        _state.update { it.copy(userSheetOpen = true) }
    }

    private fun saveUser() {
        viewModelScope.launch {
            repository.saveUser(state.value.userName)
            _state.update { it.copy(userSheetOpen = false) }
        }
    }

    private fun updateUsername(username: String) {
        _state.update { it.copy(userName = username) }
    }

    private fun deleteItem(itemId: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            repository.deleteItem(itemId)
                .onSuccess {
                    _state.update {
                        dismissItemSheet()
                        it.copy(isLoading = false)
                    }
                }
                .onFailure { error ->
                    _state.update {
                        dismissItemSheet()
                        it.copy(isLoading = false, errorMessage = error.message)
                    }
                }
            loadData()
        }
    }

    private fun setItem( itemId: Int, itemName: String, itemPrice: String) {
        _state.update {
            it.copy(
                itemName = itemName,
                itemPrice = itemPrice,
                itemSheetOpen = true,
                isEditing = true,
                itemId = itemId
            )
        }
    }

    private fun showItemSheet() {
        _state.update { it.copy(itemSheetOpen = true) }
    }

    private fun dismissItemSheet() {
        _state.update {
            it.copy(
                itemSheetOpen = false,
                isEditing = false,
                itemName = "",
                itemPrice = ""
            )
        }
    }

    private fun changeItemName(name: String) {
        _state.update {
            it.copy(
                itemName = name
            )
        }
    }

    private fun changeItemPrice(price: String) {
        _state.update {
            it.copy(
                itemPrice = price
            )
        }
    }

    private fun postItem() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val request = Item(
                id = state.value.itemId,
                userId = state.value.userName,
                name = state.value.itemName,
                price = state.value.itemPrice.toDouble(),
            )
            if (state.value.isEditing) {
                repository.putItem(request)
            } else {
                repository.postItem(request)
            }
            loadData()
            _state.update { it.copy(isLoading = false, itemSheetOpen = false) }
        }
    }

    private fun createUser() {
        _state.update { it.copy(userSheetOpen = true) }
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
            _state.update { it.copy(isLoading = true) }
            repository.postTransaction(
                total = state.value.totalPrice.toInt(),
                userId = state.value.userName,
                items = state.value.selectedItems.count()
            )
                .onSuccess {
                    _state.update { it.copy(isLoading = false) }
                }
                .onFailure { error ->
                    _state.update { it.copy(isLoading = false, errorMessage = error.message) }
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