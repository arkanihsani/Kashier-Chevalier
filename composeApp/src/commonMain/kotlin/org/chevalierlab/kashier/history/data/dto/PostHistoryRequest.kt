package org.chevalierlab.kashier.history.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class PostHistoryRequest(

	@SerialName("total")
	val total: Int,

	@SerialName("userId")
	val userId: String,

	@SerialName("items")
	val items: Int
)