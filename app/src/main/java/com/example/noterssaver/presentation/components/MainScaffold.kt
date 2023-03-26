package com.example.noterssaver.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import com.example.noterssaver.presentation.MainViewModel
import com.example.noterssaver.util.Extensions.debug
import org.koin.androidx.compose.koinViewModel


// Created by Shahid Iqbal on 3/16/2023.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    snackBarHost: @Composable (() -> Unit) = {},
    floatingIcon: ImageVector,
    floatingButtonClick: () -> Unit,
    viewModel: MainViewModel = koinViewModel(),
    content: @Composable (PaddingValues) -> Unit
) {

    val isScrollUp = viewModel.isScrollUp


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = viewModel.topBarTitle,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        snackbarHost = snackBarHost,
        floatingActionButton = {
            if (isScrollUp) FloatingButton(icon = floatingIcon) { floatingButtonClick.invoke() }
        },
        content = content
    )
}

@Composable
fun FloatingButton(
    icon: ImageVector,
    onClick: () -> Unit
) {

    FloatingActionButton(
        onClick = {
            onClick.invoke()
        }
    ) {
        Icon(imageVector = icon, contentDescription = null)
    }


}
