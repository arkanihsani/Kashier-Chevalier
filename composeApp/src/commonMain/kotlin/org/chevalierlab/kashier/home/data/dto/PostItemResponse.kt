package org.chevalierlab.kashier.home.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.chevalierlab.kashier.home.domain.models.Item

@Serializable
data class PostItemResponse(

    @SerialName("item")
    val item: ItemsItem,

    @SerialName("message")
    val message: String,

    @SerialName("status")
    val status: Int
)