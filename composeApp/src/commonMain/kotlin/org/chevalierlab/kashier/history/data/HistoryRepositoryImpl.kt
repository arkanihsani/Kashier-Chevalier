package org.chevalierlab.kashier.history.data

import kotlinx.coroutines.flow.Flow
import org.chevalierlab.kashier.history.data.datasource.HistoryRemoteDataSource
import org.chevalierlab.kashier.history.domain.History
import org.chevalierlab.kashier.history.domain.HistoryRepository
import org.chevalierlab.kashier.home.data.datasource.UserLocalDataSource

class HistoryRepositoryImpl(
    private val historyRemoteDataSource: HistoryRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource,
) : HistoryRepository {
    override suspend fun getHistoryItems(userId: String): Result<List<History>> {
        val result = historyRemoteDataSource.getHistories(userId.replace(" ", "_"))

        return if (result.isSuccess) {
            try {
                val itemsResponse = result.getOrThrow()
                Result.success(itemsResponse.histories.map { it.toDomain() })
            } catch (e: Exception) {
                Result.failure(e)
            }
        } else {
            Result.failure(result.exceptionOrNull() ?: Exception("Unknown remote error."))
        }
    }

    override fun getUserId(): Flow<String> {
        return userLocalDataSource.getUser()
    }
}