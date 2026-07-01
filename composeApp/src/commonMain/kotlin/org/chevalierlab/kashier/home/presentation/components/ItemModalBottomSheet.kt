package org.chevalierlab.kashier.home.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TextSnippet
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kashier.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemModalBottomSheet(
    itemId: Int?,
    itemName: String,
    onItemNameChange: (String) -> Unit,
    itemPrice: String,
    onItemPriceChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        content = {
            Column {
                Text(
                    text = if (itemId != null) {
                        stringResource(Res.string.edit_item)
                    } else stringResource(Res.string.add_item),
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = stringResource(Res.string.item_name_label),
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(Modifier.height(4.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = itemName,
                    onValueChange = onItemNameChange,
                    placeholder = { Text(stringResource(Res.string.item_name_hint)) },
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.TextSnippet,
                            contentDescription = stringResource(Res.string.item_name_hint)
                        )
                    }
                )
                Text(
                    text = stringResource(Res.string.item_price_label),
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(Modifier.height(4.dp))
                OutlinedTextField(
                    value = itemPrice,
                    onValueChange = onItemPriceChange,
                    placeholder = { Text(stringResource(Res.string.item_price_hint)) },
                    singleLine = true,
                    prefix = {
                        Text(
                            text = stringResource(Res.string.rupiah_prefix),
                            fontWeight = FontWeight.Bold,
                        )
                    }
                )
                SaveButton(
                    onSave = onSubmit
                )
            }
        }
    )
}