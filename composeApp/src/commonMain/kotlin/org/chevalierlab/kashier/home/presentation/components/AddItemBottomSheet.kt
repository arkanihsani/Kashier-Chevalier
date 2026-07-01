package org.chevalierlab.kashier.home.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kashier.composeapp.generated.resources.Res
import kashier.composeapp.generated.resources.add_item_title
import kashier.composeapp.generated.resources.delete_button_label
import kashier.composeapp.generated.resources.edit_item_title
import kashier.composeapp.generated.resources.item_name_label
import kashier.composeapp.generated.resources.item_name_placeholder
import kashier.composeapp.generated.resources.item_price_label
import kashier.composeapp.generated.resources.item_price_placeholder
import kashier.composeapp.generated.resources.save_button_label
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemBottomSheet(
    onDismissRequest: () -> Unit,
    onSave: () -> Unit,
    onDelete: () -> Unit,
    onItemNameChange: (String) -> Unit,
    onItemPriceChange: (String) -> Unit,
    itemName: String,
    itemPrice: String,
    isEditing: Boolean, // TODO: Berikan state `isEditing`
    modifier: Modifier = Modifier,
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        dragHandle = {
            BottomSheetDefaults.DragHandle(
                width = 32.dp,
                height = 4.dp,
            )
        },
        modifier = modifier
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = if (isEditing)
                    stringResource(Res.string.edit_item_title)
                else stringResource(Res.string.add_item_title),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                color = Color.Black
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = stringResource(Res.string.item_name_label),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black
                )
                OutlinedTextField(
                    value = itemName,
                    onValueChange = onItemNameChange,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = stringResource(Res.string.item_name_placeholder),
                            color = Color(0xFF8B4513).copy(alpha = 0.6f)
                        )
                    },
                    leadingIcon = {
                        Text(
                            text = "T",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Normal,
                                color = Color.Black
                            ),
                            modifier = Modifier.padding(start = 12.dp)
                        )
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFFDE2C4),
                        unfocusedContainerColor = Color(0xFFFDE2C4),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        cursorColor = Color.Black,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    )
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = stringResource(Res.string.item_price_label),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black
                )
                OutlinedTextField(
                    value = itemPrice,
                    onValueChange = onItemPriceChange,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = stringResource(Res.string.item_price_placeholder),
                            color = Color(0xFF8B4513).copy(alpha = 0.6f)
                        )
                    },
                    leadingIcon = {
                        Text(
                            text = "Rp.",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            ),
                            modifier = Modifier.padding(start = 12.dp)
                        )
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFFDE2C4),
                        unfocusedContainerColor = Color(0xFFFDE2C4),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        cursorColor = Color.Black,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    )
                )
            }

            Row {
                if (isEditing) {
                    OutlinedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .weight(1f),
                        onClick = onDelete,
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(
                            1.dp,
                            MaterialTheme.colorScheme.error,
                        ),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.error,
                        )
                    ) {
                        Text(
                            text = stringResource(Res.string.delete_button_label),
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                }
                Button(
                    onClick = onSave,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF8E5060),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = stringResource(Res.string.save_button_label),
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
    }
}