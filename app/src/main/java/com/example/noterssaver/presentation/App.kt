package com.example.noterssaver.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.noterssaver.presentation.add_note.AddNote
import com.example.noterssaver.presentation.components.Toolbar


// Created by Shahid Iqbal on 3/13/2023.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {

    Scaffold(topBar = { Toolbar(isDark = false, onClick = {}) }) {
        AddNote(Modifier.padding(it))
    }
}