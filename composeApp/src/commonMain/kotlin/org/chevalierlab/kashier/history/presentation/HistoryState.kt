package org.chevalierlab.kashier.history.presentation

import org.chevalierlab.kashier.history.domain.History

data class HistoryState(
    val histories: Map<String, List<History>> = emptyMap()
)
