package org.chevalierlab.kashier.home.data.datasource

import org.chevalierlab.kashier.home.data.dto.CreateUserRequest
import org.chevalierlab.kashier.home.data.dto.CreateUserResponse

interface UserRemoteDataSource {

    suspend fun createUser(request: CreateUserRequest): Result<CreateUserResponse>

}