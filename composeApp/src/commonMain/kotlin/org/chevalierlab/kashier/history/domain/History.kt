package org.chevalierlab.kashier.history.domain

import kotlinx.datetime.LocalDate

data class History(
    val id: Int,
    val date: LocalDate,
    val totalPrice: Double,
    val items: Int,
)
