package com.example.noterssaver.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.noterssaver.R.drawable
import com.example.noterssaver.R.drawable.ic_color
import com.example.noterssaver.R.drawable.ic_dark
import com.example.noterssaver.presentation.NavGraphs
import com.example.noterssaver.presentation.appCurrentDestinationAsState
import com.example.noterssaver.presentation.destinations.AddNoteDestination
import com.example.noterssaver.presentation.destinations.Destination
import com.example.noterssaver.presentation.startAppDestination
import com.example.noterssaver.presentation.viewmodels.NotesViewModel
import com.example.noterssaver.util.Extensions.debug
import org.koin.androidx.compose.koinViewModel


// Created by Shahid Iqbal on 3/13/2023.


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    navController: NavController,
    onClick: (String) -> Unit
) {

    val currentDestination: Destination =
        navController.appCurrentDestinationAsState().value ?: NavGraphs.root.startAppDestination

    TopAppBar(
        title = { Text(text = "Notes Saver") },

        actions = {
            if (currentDestination is AddNoteDestination)
                IconToShow(ic_color){}
            else IconToShow(ic_dark){onClick.invoke("Dark")}


        }, colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )
}

@Composable
private fun IconToShow(id: Int,onClick:() -> Unit) {

    IconButton(onClick = { onClick() }) {

        Icon(painter = painterResource(id = id), contentDescription = null)
    }

}