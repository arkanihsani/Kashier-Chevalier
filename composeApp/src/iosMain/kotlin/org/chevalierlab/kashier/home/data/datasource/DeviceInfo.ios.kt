package org.chevalierlab.kashier.home.data.datasource

import platform.UIKit.UIDevice

actual fun getDeviceName(): String {
    return UIDevice.currentDevice.name
}