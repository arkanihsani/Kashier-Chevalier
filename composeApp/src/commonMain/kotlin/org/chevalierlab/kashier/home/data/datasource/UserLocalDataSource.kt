package org.chevalierlab.kashier.home.data.datasource

import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {

    suspend fun saveUser(user: String)

    fun getUser(): Flow<String>

}