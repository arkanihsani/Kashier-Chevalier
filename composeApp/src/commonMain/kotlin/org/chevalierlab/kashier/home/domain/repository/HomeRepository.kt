package org.chevalierlab.kashier.home.domain.repository

import org.chevalierlab.kashier.home.domain.Item

interface HomeRepository {

    suspend fun getItems(): List<Item>

    suspend fun postItem(item: Item): Result<Boolean>

    suspend fun deleteItem(id: Int): Result<Boolean>

    suspend fun putItem(item: Item): Result<Boolean>

    suspend fun postTransaction(): Result<Boolean>

}