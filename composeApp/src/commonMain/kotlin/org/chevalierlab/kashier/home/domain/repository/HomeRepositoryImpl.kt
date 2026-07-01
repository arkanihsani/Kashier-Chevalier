package org.chevalierlab.kashier.home.domain.repository

import org.chevalierlab.kashier.home.data.DummyDataSource
import org.chevalierlab.kashier.home.domain.Item

class HomeRepositoryImpl(
    private val datasource: DummyDataSource
) : HomeRepository {
    override suspend fun getItems(): List<Item> {
        return datasource.getDatas()
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
}