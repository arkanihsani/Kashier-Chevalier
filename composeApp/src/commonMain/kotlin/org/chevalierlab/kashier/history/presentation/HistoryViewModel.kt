package org.chevalierlab.kashier.history.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.*
import org.chevalierlab.kashier.history.domain.History
import org.chevalierlab.kashier.history.domain.HistoryRepository
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class HistoryViewModel(
    private val repository: HistoryRepository
) : ViewModel() {

    private val _userId = repository.getUserId()
    private val _state = MutableStateFlow(HistoryState())
    val state = combine(_state, _userId) { state, userId ->
        state.copy(userId = userId)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HistoryState())

    fun onEvent(event: HistoryEvent) {
        when (event) {
            is HistoryEvent.OnLoadData -> loadData()
        }
    }

    private fun loadData() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            repository.getHistoryItems(state.value.userId)
                .onSuccess { historyItems ->
                    _state.update { it.copy(histories = groupHistoryByDate(historyItems), isLoading = false) }
                }
        }
    }

    @OptIn(ExperimentalTime::class)
    private fun groupHistoryByDate(histories: List<History>): Map<String, List<History>> {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        today.minus(today.dayOfWeek.isoDayNumber - 1, DateTimeUnit.DAY)
        val startOfThisMonth = today.minus(today.day - 1, DateTimeUnit.DAY)
        val startOfLastMonth = startOfThisMonth.minus(1, DateTimeUnit.MONTH)

        return histories.groupBy { history ->
            when {
                history.date == today -> "Hari ini"
                history.date >= today.minus(1, DateTimeUnit.WEEK) -> "Minggu ini"
                history.date >= startOfThisMonth -> "Bulan ini"
                history.date >= startOfLastMonth -> {
                    startOfLastMonth.month.getDisplayName()
                }

                else -> {
                    val monthName = history.date.month.getDisplayName()
                    "$monthName ${history.date.year}"
                }
            }
        }
    }

    private fun Month.getDisplayName(): String {
        return this.name.lowercase().replaceFirstChar { it.titlecase() }
    }
}