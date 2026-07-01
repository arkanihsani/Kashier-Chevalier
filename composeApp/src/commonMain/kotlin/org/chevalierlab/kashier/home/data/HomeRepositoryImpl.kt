package org.chevalierlab.kashier.home.data

import kotlinx.coroutines.flow.Flow
import org.chevalierlab.kashier.home.data.datasource.DummyDataSource
import org.chevalierlab.kashier.home.data.datasource.UserLocalDataSource
import org.chevalierlab.kashier.home.data.datasource.getDeviceName
import org.chevalierlab.kashier.home.domain.models.Item
import org.chevalierlab.kashier.home.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val dataSource: DummyDataSource,
    private val userLocalDataSource: UserLocalDataSource,
): HomeRepository {

    override suspend fun getItems(): List<Item> {
        return dataSource.getDatas()
    }

    override suspend fun postItem(item: Item): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteItem(id: Int): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun putItem(item: Item): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun postTransaction(): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun createUser() {
        val allowedChars = ('0'..'9')
        val randomIdentifier = (1..10).map {
            // '1', '2', '3'
            allowedChars.random()
        }.joinToString("") // "123"

        // "iPhone_17_Pro_984689746"
        val userIdentifier = "${getDeviceName()}_$randomIdentifier"
        userLocalDataSource.saveUser(userIdentifier)
    }

    override fun getUser(): Flow<String> {
        return userLocalDataSource.getUser()
    }

}