package org.chevalierlab.kashier.home.domain.repository

import kotlinx.coroutines.flow.Flow
import org.chevalierlab.kashier.home.domain.models.Item

interface HomeRepository {

    suspend fun getItems(userId: String): Result<List<Item>>

    suspend fun postItem(item: Item): Result<Boolean>

    suspend fun deleteItem(id: Int): Result<Boolean>

    suspend fun putItem(item: Item): Result<Boolean>

    suspend fun postTransaction(total: Int, userId: String, items: Int): Result<Boolean>

    suspend fun createUser()

    fun getUser(): Flow<String>

    suspend fun saveUser(user: String): Result<Boolean>

}