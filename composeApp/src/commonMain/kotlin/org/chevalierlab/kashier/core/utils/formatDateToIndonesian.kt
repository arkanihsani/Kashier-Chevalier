package org.chevalierlab.kashier.core.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month

fun formatDateToIndonesian(date: LocalDate): String {
    val day = date.day
    val year = date.year
    val monthName = when (date.month) {
        Month.JANUARY -> "Januari"
        Month.FEBRUARY -> "Februari"
        Month.MARCH -> "Maret"
        Month.APRIL -> "April"
        Month.MAY -> "Mei"
        Month.JUNE -> "Juni"
        Month.JULY -> "Juli"
        Month.AUGUST -> "Agustus"
        Month.SEPTEMBER -> "September"
        Month.OCTOBER -> "Oktober"
        Month.NOVEMBER -> "November"
        Month.DECEMBER -> "Desember"
    }
    return "$day $monthName $year"
}