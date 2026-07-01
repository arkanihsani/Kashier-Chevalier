package org.chevalierlab.kashier.home.data.datasource

import kotlinx.coroutines.flow.Flow
import org.chevalierlab.kashier.core.preferences.AppPreferences

class UserLocalDataSourceImpl(
    private val pref: AppPreferences
): UserLocalDataSource {
    override suspend fun saveUser(name: String) {
        pref.saveUserKey(name)
    }

    override fun getUser(): Flow<String> {
        return pref.getUserKey()
    }
}