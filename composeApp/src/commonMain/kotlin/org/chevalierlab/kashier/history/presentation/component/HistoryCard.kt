package org.chevalierlab.kashier.history.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import org.chevalierlab.kashier.core.utils.formatCurrency
import org.chevalierlab.kashier.core.utils.formatDateToIndonesian
import org.chevalierlab.kashier.history.domain.History
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HistoryCard(
    modifier: Modifier = Modifier,
    history: History
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF2E8E8)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            InfoRow(label = "Total Harga", value = formatCurrency(history.totalPrice))
            InfoRow(label = "Total Barang", value = history.items.toString())
            InfoRow(label = "Tanggal", value = formatDateToIndonesian(history.date))
        }
    }
}

@Preview
@Composable
fun HistoryCardPreview() {
    val sampleHistory = History(
        id = 1,
        date = LocalDate(2025, 7, 26),
        totalPrice = 250000.0,
        items = 10
    )
    HistoryCard(
        history = sampleHistory
    )
}