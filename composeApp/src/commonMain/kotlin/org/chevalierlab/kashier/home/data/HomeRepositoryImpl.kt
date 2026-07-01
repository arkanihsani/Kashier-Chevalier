package org.chevalierlab.kashier.home.data

import kotlinx.coroutines.flow.Flow
import org.chevalierlab.kashier.home.data.datasource.*
import org.chevalierlab.kashier.home.data.dto.CreateTransactionRequest
import org.chevalierlab.kashier.home.data.dto.CreateUserRequest
import org.chevalierlab.kashier.home.data.dto.PostItemRequest
import org.chevalierlab.kashier.home.domain.models.Item
import org.chevalierlab.kashier.home.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val transactionDataSource: TransactionRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource,
    private val itemRemoteDataSource: ItemRemoteDataSource,
    private val userRemoteDataSource: UserRemoteDataSource,
) : HomeRepository {

    override suspend fun getItems(userId: String): Result<List<Item>> {
        val result = itemRemoteDataSource.getItems(userId.replace(" ", "_"))

        return if (result.isSuccess) {
            try {
                val itemsResponse = result.getOrThrow()
                Result.success(itemsResponse.items.map { it.toDomain() })
            } catch (e: Exception) {
                Result.failure(e)
            }
        } else {
            Result.failure(result.exceptionOrNull() ?: Exception("Unknown remote error."))
        }
    }

    override suspend fun postItem(item: Item): Result<Boolean> {
        val request = PostItemRequest(
            price = item.price.toInt(),
            name = item.name,
            userId = item.userId.replace(" ", "_")
        )
        val result = itemRemoteDataSource.postItem(request)
        return if (result.isSuccess) {
            Result.success(true)
        } else {
            Result.failure(result.exceptionOrNull() ?: Exception("Unknown error."))
        }
    }

    override suspend fun postTransaction(total: Int, userId: String, items: Int): Result<Boolean> {
        val request = CreateTransactionRequest(total, userId, items)
        val result = transactionDataSource.createTransaction(request)

        return when (result) {
            201 -> Result.success(true)
            400 -> Result.failure(Exception("Bad Request."))
            401 -> Result.failure(Exception("Unauthorized."))
            500 -> Result.failure(Exception("Server Error."))
            else -> Result.failure(Exception("Transaction failed."))
        }
    }

    override fun getUser(): Flow<String> {
        return userLocalDataSource.getUser()
    }

    override fun getToken(): Flow<String> {
        return userLocalDataSource.getToken()
    }

    override suspend fun saveUser(user: String): Result<Boolean> {
        val request = CreateUserRequest(user.replace(" ", "_"))
        val result = userRemoteDataSource.createUser(request)
        return if (result.isSuccess) {
            userLocalDataSource.setToken(result.getOrNull()?.user?.token ?: "")
            userLocalDataSource.saveUser(result.getOrNull()?.user?.name ?: "")
            Result.success(true)
        } else Result.failure(result.exceptionOrNull() ?: Exception("Unknown error."))
    }

    override suspend fun deleteItem(id: Int): Result<Boolean> {
        val result = itemRemoteDataSource.deleteItem(id)
        return if (result.isSuccess) {
            Result.success(true)
        } else {
            Result.failure(result.exceptionOrNull() ?: Exception("Unknown error."))
        }
    }

    override suspend fun putItem(item: Item): Result<Boolean> {
        val request = PostItemRequest(
            price = item.price.toInt(),
            name = item.name,
            userId = item.userId.replace(" ", "_")
        )
        val result = itemRemoteDataSource.updateItem(item.id, request)
        return if (result.isSuccess) {
            Result.success(true)
        } else {
            Result.failure(result.exceptionOrNull() ?: Exception("Unknown error."))
        }
    }

}