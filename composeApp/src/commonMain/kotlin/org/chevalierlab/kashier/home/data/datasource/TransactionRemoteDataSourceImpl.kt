package org.chevalierlab.kashier.home.data.datasource

import io.ktor.client.*
import io.ktor.client.request.*
import org.chevalierlab.kashier.core.network.BASE_URL
import org.chevalierlab.kashier.core.network.V2
import org.chevalierlab.kashier.home.data.dto.CreateTransactionRequest

class TransactionRemoteDataSourceImpl(
    private val client: HttpClient
) : TransactionRemoteDataSource {
    override suspend fun createTransaction(request: CreateTransactionRequest): Int {
        val response = client.post(urlString = "$BASE_URL/$V2/transaction") {
            setBody(request)
        }
        return response.status.value
    }
}