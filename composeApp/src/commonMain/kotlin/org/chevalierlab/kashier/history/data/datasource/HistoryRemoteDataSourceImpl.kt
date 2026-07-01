package org.chevalierlab.kashier.history.data.datasource

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.chevalierlab.kashier.core.network.BASE_URL
import org.chevalierlab.kashier.core.network.V1
import org.chevalierlab.kashier.history.data.dto.GetHistoriesResponse

class HistoryRemoteDataSourceImpl(
    private val client: HttpClient
) : HistoryRemoteDataSource {
    override suspend fun getHistories(
        userId: String
    ): Result<GetHistoriesResponse> {
        val response = client.get(urlString = "$BASE_URL/$V1/history/$userId")

        return when (response.status.value) {
            200 -> Result.success(response.body())
            else -> Result.failure(Exception("Unexpected error"))
        }
    }

}