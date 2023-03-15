package com.example.noterssaver.presentation.add_note

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noterssaver.presentation.components.NotesColor
import com.example.noterssaver.presentation.viewmodels.NotesViewModel
import com.example.noterssaver.util.Extensions.debug
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import org.koin.androidx.compose.koinViewModel


// Created by Shahid Iqbal on 3/13/2023.


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun AddNote(modifier: Modifier = Modifier, vm: NotesViewModel = koinViewModel()) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    var backgroundColor by remember {
        mutableStateOf(Color.White)
    }


    val value by vm.isCLicked.collectAsState()

    value.debug()


    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(backgroundColor)
            .clickable { vm.updateClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        NotesColor { backgroundColor = it }

    }

}