package org.chevalierlab.kashier.home.data.datasource

import org.chevalierlab.kashier.home.data.dto.CreateTransactionRequest

interface TransactionRemoteDataSource {

    suspend fun createTransaction(request: CreateTransactionRequest): Int

}