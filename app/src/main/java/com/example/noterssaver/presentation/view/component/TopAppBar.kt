package com.example.noterssaver.presentation.view.component;

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.noterssaver.presentation.NavGraphs
import com.example.noterssaver.presentation.appCurrentDestinationAsState
import com.example.noterssaver.presentation.destinations.SettingScreenDestination
import com.example.noterssaver.presentation.destinations.ShowNotesDestination
import com.example.noterssaver.presentation.startAppDestination
import com.example.noterssaver.presentation.util.Extensions.debug
import com.example.noterssaver.presentation.util.topBarTitleId

/*
 * Created by Shahid Iqbal on 4/25/2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(navController: NavController) {

    val currentDestination = navController.currentAppDestination()
    val title = stringResource(id = currentDestination.topBarTitleId())

    TopAppBar(title = {

        Text(
            text = title, fontWeight = FontWeight.Bold
        )
    }, actions = {
        if (currentDestination is ShowNotesDestination)
            IconButton(onClick = { navController.navigate(SettingScreenDestination.route) }) {
                Icon(
                    imageVector = Icons.Default.Settings, contentDescription = "Settings"
                )
            }
    }, navigationIcon = {

        if (currentDestination !is ShowNotesDestination)
            IconButton(
                onClick = { navController.navigateUp() },
                enabled = currentDestination !is ShowNotesDestination
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack, contentDescription = "Back"
                )
            }
    }

    )
}