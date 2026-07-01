package org.chevalierlab.kashier.history.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class PostHistoryResponse(

	@SerialName("history")
	val history: HistoriesItem,

	@SerialName("message")
	val message: String,

	@SerialName("status")
	val status: Int
)