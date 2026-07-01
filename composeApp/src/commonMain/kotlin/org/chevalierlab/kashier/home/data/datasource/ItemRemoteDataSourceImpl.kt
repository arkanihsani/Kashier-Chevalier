package org.chevalierlab.kashier.home.data.datasource

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.chevalierlab.kashier.core.network.BASE_URL
import org.chevalierlab.kashier.core.network.V1
import org.chevalierlab.kashier.home.data.dto.ItemsResponse
import org.chevalierlab.kashier.home.data.dto.PostItemRequest
import org.chevalierlab.kashier.home.data.dto.PostItemResponse

class ItemRemoteDataSourceImpl(
    private val client: HttpClient
) : ItemRemoteDataSource {
    override suspend fun getItems(userId: String): Result<ItemsResponse> {
        val response = client.get(urlString = "${BASE_URL}/$V1/item/$userId")
        return when (response.status.value) {
            200 -> Result.success(response.body())
            else -> Result.failure(Exception(response.status.description))
        }
    }

    override suspend fun postItem(request: PostItemRequest): Result<PostItemResponse> {
        val response = client.post("$BASE_URL/$V1/item") {
            setBody(request)
        }
        return when (response.status.value) {
            201 -> Result.success(response.body())
            else -> Result.failure(Exception(response.status.description))
        }
    }

    override suspend fun updateItem(
        id: Int,
        request: PostItemRequest
    ): Result<Boolean> {
        val response = client.put("$BASE_URL/$V1/item/$id") {
            setBody(request)
        }
        return when (response.status.value) {
            200 -> Result.success(true)
            else -> Result.failure(Exception(response.status.description))
        }
    }

    override suspend fun deleteItem(id: Int): Result<Boolean> {
        val response = client.delete("$BASE_URL/$V1/item/$id")
        return when (response.status.value) {
            200 -> Result.success(true)
            else -> Result.failure(Exception(response.status.description))
        }
    }
}