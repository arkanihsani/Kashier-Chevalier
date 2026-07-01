package org.chevalierlab.kashier.home.domain.models

data class Item(
    val id: Int,
    val userId: String,
    val name: String,
    val price: Double,
)