package org.chevalierlab.kashier.core.utils

import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterCurrencyStyle

actual fun formatCurrency(price: Double, currencyCode: String): String {
    val formatter = NSNumberFormatter()
    formatter.numberStyle = NSNumberFormatterCurrencyStyle
    formatter.currencyCode = currencyCode
    return formatter.stringFromNumber(NSNumber(price)) ?: ""
}