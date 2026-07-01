package org.chevalierlab.kashier.home.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kashier.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemBottomSheet(
    onDismissRequest: () -> Unit,
    onSave: (name: String, price: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val sheetState = rememberModalBottomSheetState()
    var itemName by remember { mutableStateOf("") }
    var itemPrice by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = Color(0xFFFFF8F8),
        dragHandle = {
            BottomSheetDefaults.DragHandle(
                color = Color.Black.copy(alpha = 0.4f),
                width = 32.dp,
                height = 4.dp,
            )
        },
        modifier = modifier
    ) {
        AddItemContent(
            itemName = itemName,
            onItemNameChange = { itemName = it },
            itemPrice = itemPrice,
            onItemPriceChange = { itemPrice = it },
            onSave = { onSave(itemName, itemPrice) }
        )
    }
}

@Composable
fun AddItemContent(
    itemName: String,
    onItemNameChange: (String) -> Unit,
    itemPrice: String,
    onItemPriceChange: (String) -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(Res.string.add_item_title),
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

        Button(
            onClick = onSave,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
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

@Preview
@Composable
fun AddItemContentPreview() {
    MaterialTheme {
        Surface(color = Color(0xFFFFF8F8)) {
            AddItemContent(
                itemName = "",
                onItemNameChange = {},
                itemPrice = "",
                onItemPriceChange = {},
                onSave = {}
            )
        }
    }
}
