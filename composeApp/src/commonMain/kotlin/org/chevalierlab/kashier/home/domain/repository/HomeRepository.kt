package org.chevalierlab.kashier.home.domain.repository

import kotlinx.coroutines.flow.Flow
import org.chevalierlab.kashier.home.domain.models.Item

interface HomeRepository {

    suspend fun getItems(): List<Item>

    suspend fun postItem(item: Item): Result<Boolean>

    suspend fun deleteItem(id: Int): Result<Boolean>

    suspend fun putItem(item: Item): Result<Boolean>

    suspend fun postTransaction(): Result<Boolean>

    suspend fun createUser()

    fun getUser(): Flow<String>

}