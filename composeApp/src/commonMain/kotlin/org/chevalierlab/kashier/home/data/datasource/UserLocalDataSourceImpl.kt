package org.chevalierlab.kashier.home.data.datasource

import kotlinx.coroutines.flow.Flow
import org.chevalierlab.kashier.core.preferences.AppPreferences

class UserLocalDataSourceImpl(
    private val preferences: AppPreferences
) : UserLocalDataSource {

    override suspend fun saveUser(user: String) {
        preferences.saveUserKey(user)
    }

    override fun getUser(): Flow<String> {
        return preferences.getUserKey()
    }

}