package org.chevalierlab.kashier.home.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class CreateTransactionRequest(

	@SerialName("total")
	val total: Int,

	@SerialName("userId")
	val userId: String,

	@SerialName("items")
	val items: Int
)