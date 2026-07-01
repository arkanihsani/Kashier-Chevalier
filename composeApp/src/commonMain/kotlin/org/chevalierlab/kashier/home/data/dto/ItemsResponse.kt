package org.chevalierlab.kashier.home.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ItemsResponse(

	@SerialName("status")
	val status: Int,

	@SerialName("message")
	val message: String,

	@SerialName("items")
	val items: List<ItemsItem>
)