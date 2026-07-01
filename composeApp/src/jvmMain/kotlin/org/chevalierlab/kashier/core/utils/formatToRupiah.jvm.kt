package org.chevalierlab.kashier.core.utils

import java.text.NumberFormat
import java.util.Locale

actual fun formatCurrency(price: Double, currencyCode: String): String {
    val localeID = Locale(currencyCode.lowercase().substring(2), currencyCode.uppercase().substring(2))
    val currencyFormat = NumberFormat.getCurrencyInstance(localeID)
    // The formatter includes "Rp", but the image shows "Rp.", so we can customize it
    // Or simply use the standard which is more robust. Let's stick to standard.
    // To get "Rp." we would need manual string formatting.
    // For simplicity, this uses the standard "Rp" prefix from java.text
    return currencyFormat.format(price)
        .replace(",00", "") // Remove decimals if they are zero
}