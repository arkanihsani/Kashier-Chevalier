package org.chevalierlab.kashier.history.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class HistoriesItem(

	@SerialName("createdAt")
	val createdAt: String,

	@SerialName("total")
	val total: Int,

	@SerialName("id")
	val id: Int,

	@SerialName("items")
	val items: Int,

	@SerialName("userId")
	val userId: String,

	@SerialName("updatedAt")
	val updatedAt: String
)