package org.chevalierlab.kashier.history.data.dto

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.chevalierlab.kashier.history.domain.History
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

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
) {
    @OptIn(ExperimentalTime::class)
    fun toDomain(): History {
        return History(
            id = id,
            date = Instant
                .parse(createdAt)
                .toLocalDateTime(TimeZone.currentSystemDefault())
                .date,
            totalPrice = total.toDouble(),
            items = items,
        )
    }
}