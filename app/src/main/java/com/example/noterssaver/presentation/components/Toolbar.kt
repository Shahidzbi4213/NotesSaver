package com.example.noterssaver.presentation.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.noterssaver.R.drawable
import com.example.noterssaver.ui.theme.Purple40
import com.example.noterssaver.ui.theme.Purple80


// Created by Shahid Iqbal on 3/13/2023.


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(isDark: Boolean, onClick: () -> Unit) {
    val iconId = if (isDark) drawable.ic_light else drawable.ic_dark
    TopAppBar(title = { Text(text = "Notes Saver") }, actions = {
        IconButton(onClick = { onClick() }) {
            Icon(painter = painterResource(id = iconId), contentDescription = null)
        }
    }, colors = TopAppBarDefaults.mediumTopAppBarColors(
        containerColor = Purple40,
        titleContentColor = Color.White,
        actionIconContentColor = Color.White
    )
    )
}