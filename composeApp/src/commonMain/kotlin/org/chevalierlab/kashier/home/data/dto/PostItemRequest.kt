package org.chevalierlab.kashier.home.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class PostItemRequest(

	@SerialName("price")
	val price: Int,

	@SerialName("name")
	val name: String,

	@SerialName("userId")
	val userId: String
)