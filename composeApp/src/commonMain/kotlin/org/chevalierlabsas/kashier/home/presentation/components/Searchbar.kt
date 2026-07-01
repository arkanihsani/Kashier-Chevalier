package org.chevalierlabsas.kashier.home.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kashier.composeapp.generated.resources.Res
import kashier.composeapp.generated.resources.searchbar_hint
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Searchbar(
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        modifier = modifier,
        value = "",
        onValueChange = { },
        shape = RoundedCornerShape(8.dp),
        placeholder = {
            Text(stringResource(Res.string.searchbar_hint))
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(Res.string.searchbar_hint)
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
            unfocusedBorderColor = MaterialTheme.colorScheme.tertiaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
            focusedTrailingIconColor = MaterialTheme.colorScheme.onTertiaryContainer,
            unfocusedTrailingIconColor = MaterialTheme.colorScheme.onTertiaryContainer,
            focusedTextColor = MaterialTheme.colorScheme.onTertiaryContainer
        )
    )
}

@Preview
@Composable
fun SearchbarPreview() {
    Surface {
        Searchbar(
            modifier = Modifier.padding(16.dp)
        )
    }
}