package org.chevalierlab.kashier.home.data.datasource

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.chevalierlab.kashier.core.network.BASE_URL
import org.chevalierlab.kashier.core.network.V1
import org.chevalierlab.kashier.home.data.dto.CreateUserRequest
import org.chevalierlab.kashier.home.data.dto.CreateUserResponse

class UserRemoteDataSourceImpl(
    private val client: HttpClient
) : UserRemoteDataSource {
    override suspend fun createUser(request: CreateUserRequest): Result<CreateUserResponse> {
        val response = client.post(urlString = "$BASE_URL/$V1/user") {
            setBody(request)
        }
        return when (response.status.value) {
            201 -> Result.success(response.body())
            else -> Result.failure(Exception(response.status.description))
        }
    }
}