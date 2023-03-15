package com.example.noterssaver.presentation.show_notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.noterssaver.presentation.destinations.AddNoteDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


// Created by Shahid Iqbal on 3/13/2023.

@Composable
@Destination
@RootNavGraph(start = true)
fun ShowNotes(modifier: Modifier = Modifier,navigator: DestinationsNavigator ) {

    //Bingo
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        navigator.navigate(AddNoteDestination)
    }
}