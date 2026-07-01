package org.chevalierlab.kashier.home.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class User(

	@SerialName("createdAt")
	val createdAt: String,

	@SerialName("name")
	val name: String,

	@SerialName("id")
	val id: String,

	@SerialName("token")
	val token: String,

	@SerialName("updatedAt")
	val updatedAt: String
)