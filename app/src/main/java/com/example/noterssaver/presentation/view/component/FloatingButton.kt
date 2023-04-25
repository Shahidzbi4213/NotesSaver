package com.example.noterssaver.presentation.view.component;

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.example.noterssaver.presentation.destinations.AddNoteDestination
import com.example.noterssaver.presentation.destinations.ShowNotesDestination
import com.example.noterssaver.presentation.util.Extensions.debug

/*
 * Created by Shahid Iqbal on 4/25/2023.
 */

@Composable
fun FloatingButton(navController: NavController, isScrollUp: Boolean) {

    val destination = navController.currentAppDestination()
    val icon: ImageVector? = when (destination) {
        is ShowNotesDestination -> Icons.Default.Add

        is AddNoteDestination -> Icons.Default.Check

        else -> null

    }

    val visible = when (destination) {
        is ShowNotesDestination -> isScrollUp
        is AddNoteDestination -> true
        else -> false
    }

    if (visible)
        FloatingActionButton(onClick = {

            //Solve That Problem
            //Save Note on  Floating Button Click
            if (destination == ShowNotesDestination)
                navController.navigate(AddNoteDestination.route)
            else navController.navigate(ShowNotesDestination.route)
        }) {
            icon?.let { Icon(imageVector = it, contentDescription = null) }
        }

}