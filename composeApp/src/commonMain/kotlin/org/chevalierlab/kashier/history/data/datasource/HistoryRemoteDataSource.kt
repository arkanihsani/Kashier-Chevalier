package org.chevalierlab.kashier.history.data.datasource

import org.chevalierlab.kashier.history.data.dto.GetHistoriesResponse
import org.chevalierlab.kashier.history.data.dto.PostHistoryRequest
import org.chevalierlab.kashier.history.data.dto.PostHistoryResponse

interface HistoryRemoteDataSource {

    suspend fun getHistories(token: String, userId: String): Result<GetHistoriesResponse>

    suspend fun postHistory(token: String, request: PostHistoryRequest): Result<PostHistoryResponse>

}