package com.example.noterssaver.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import com.example.noterssaver.presentation.MainViewModel
import org.koin.androidx.compose.koinViewModel


// Created by Shahid Iqbal on 3/16/2023.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    snackBarHost: @Composable (() -> Unit) = {},
    floatingIcon: ImageVector,
    floatingButtonClick: () -> Unit,
    viewModel: MainViewModel = koinViewModel(),
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = viewModel.topBarTitle,
                    fontWeight = FontWeight.Bold
                )
            })
        },
        snackbarHost = snackBarHost,
        floatingActionButton = { FloatingButton(icon = floatingIcon) { floatingButtonClick.invoke() } },
        content = content,
    )
}

@Composable
fun FloatingButton(icon: ImageVector, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick.invoke() }
    ) {
        Icon(imageVector = icon, contentDescription = null)
    }
}

