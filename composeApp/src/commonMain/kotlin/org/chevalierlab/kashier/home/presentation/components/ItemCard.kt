package org.chevalierlab.kashier.home.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kashier.composeapp.generated.resources.Res
import kashier.composeapp.generated.resources.add_item
import kashier.composeapp.generated.resources.edit_item
import org.chevalierlab.kashier.core.utils.formatCurrency
import org.chevalierlab.kashier.home.domain.models.Item
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    item: Item,
    onEdit: (item: Item) -> Unit,
    onAdd: (item: Item) -> Unit,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(start = 16.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(3f)
            ) {
                Text(text = item.name)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = formatCurrency(item.price),
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier.weight(1f)
            ) {
                IconButton(
                    onClick = { onEdit(item) },
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Edit,
                        contentDescription = stringResource(Res.string.edit_item),
                    )
                }
                IconButton(
                    onClick = { onAdd(item) },
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = stringResource(Res.string.add_item),
                    )
                }


            }
        }
    }
}

@Preview
@Composable
fun ItemCardPreview() {
    ItemCard(
        item = Item(
            id = 1,
            userId = 1,
            name = "Lorem ipsum",
            price = 100000.0
        ),
        onEdit = { },
        onAdd = { }
    )
}