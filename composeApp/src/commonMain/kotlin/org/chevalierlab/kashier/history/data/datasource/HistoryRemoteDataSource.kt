package org.chevalierlab.kashier.history.data.datasource

import org.chevalierlab.kashier.history.data.dto.GetHistoriesResponse
import org.chevalierlab.kashier.history.data.dto.PostHistoryRequest
import org.chevalierlab.kashier.history.data.dto.PostHistoryResponse

interface HistoryRemoteDataSource {

    suspend fun getHistories(userId: String): Result<GetHistoriesResponse>

}