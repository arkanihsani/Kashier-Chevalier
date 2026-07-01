package org.chevalierlab.kashier.home.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class CreateUserResponse(

	@SerialName("message")
	val message: String,

	@SerialName("user")
	val user: User,

	@SerialName("status")
	val status: Int
)