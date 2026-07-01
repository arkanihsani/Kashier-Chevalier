package org.chevalierlab.kashier.home.data.dto

data class GetHistoryResponse(
	val histories: List<HistoriesItem>,
	val message: String,
	val status: Int
)

data class HistoriesItem(
	val createdAt: String,
	val total: Int,
	val id: Int,
	val items: Int,
	val updatedAt: String
)

