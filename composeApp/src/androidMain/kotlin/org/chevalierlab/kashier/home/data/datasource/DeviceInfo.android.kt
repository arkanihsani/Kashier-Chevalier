package org.chevalierlab.kashier.home.data.datasource

import android.os.Build

actual fun getDeviceName(): String {
    return "${Build.MANUFACTURER}_${Build.MODEL}"
}