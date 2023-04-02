package com.example.noterssaver.presentation.show_notes

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.Placeholder

/*
 * Created by Shahid Iqbal on 4/2/2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Searchbar(
    searchText: String,
    placeholderText: String = "Search a note",
    focusRequester: FocusRequester,
    onSearchTextChange: (String) -> Unit
) {

    TextField(
        value = searchText,
        onValueChange = { onSearchTextChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(start = 10.dp, end = 10.dp)
            .focusRequester(focusRequester),
        textStyle = MaterialTheme.typography.titleMedium,
        singleLine = true,
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        },
        placeholder = { Text(text = placeholderText) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(50.dp)
    )
}

