package org.chevalierlab.kashier.core.utils

import java.text.NumberFormat
import java.util.Locale

actual fun formatCurrency(price: Double, currencyCode: String): String {
    val localeID = Locale("id", "ID")
    val currencyFormat = NumberFormat.getCurrencyInstance(localeID)
    return currencyFormat.format(price).replace(",00", "")
}