package org.chevalierlab.kashier.history.domain

import kotlinx.coroutines.flow.Flow
import org.chevalierlab.kashier.home.domain.models.Item

interface HistoryRepository {

    suspend fun getHistoryItems(userId: String): Result<List<History>>

    fun getUserId(): Flow<String>

}