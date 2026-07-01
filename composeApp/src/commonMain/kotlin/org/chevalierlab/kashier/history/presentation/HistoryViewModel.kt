package org.chevalierlab.kashier.history.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.datetime.*
import org.chevalierlab.kashier.history.data.datasource.DummyHistoryDataSource
import org.chevalierlab.kashier.history.domain.History
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class HistoryViewModel : ViewModel() {

    private val _histories = DummyHistoryDataSource().getHistories()
    private val _state = MutableStateFlow(HistoryState(histories = groupHistoryByDate(_histories)))
    val state = _state.asStateFlow()

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