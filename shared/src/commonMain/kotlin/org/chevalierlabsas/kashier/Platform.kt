package org.chevalierlabsas.kashier

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform