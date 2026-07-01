package org.chevalierlab.kashier.history.presentation

sealed class HistoryEvent {
    data object OnLoadData : HistoryEvent()
}