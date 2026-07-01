package org.chevalierlab.kashier.home.data.datasource

actual fun getDeviceName(): String {
    return System.getProperty("os.name") + " " + System.getProperty("os.version")
}