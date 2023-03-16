package com.example.noterssaver.presentation.add_note

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.noterssaver.R
import com.example.noterssaver.presentation.components.topAppBarColors
import com.example.noterssaver.presentation.note_app.MainScaffold
import com.example.noterssaver.util.Extensions.debug
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel


// Created by Shahid Iqbal on 3/13/2023.


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun AddNote(modifier: Modifier = Modifier, viewModel: AddNoteViewModel = koinViewModel()) {

    var title by remember {
        mutableStateOf("")
    }
    var detail by remember {
        mutableStateOf("")
    }



    MainScaffold(topBar = {
        TopAppBar(title = { Text(text = "Add Note") }, actions = {
            IconButton(onClick = { viewModel.dialogState() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_color),
                    contentDescription = null
                )
            }
        }, colors = topAppBarColors()
        )
    }, floatingIcon = Icons.Default.Check, floatingButtonClick = {
        viewModel.onSavedClick(title, detail)
    }

    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(10.dp),
        ) {

            Text(
                text = "Title",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(3.dp))
            TextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.titleMedium,
                singleLine = true,
                placeholder = { Text(text = "Title Of Note") },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    textColor = MaterialTheme.colorScheme.onSurface,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent

                )
            )


            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "Detail About Note",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(3.dp))
            TextField(
                value = detail,
                onValueChange = { detail = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 10.dp),
                placeholder = { Text(text = "Write here what u want to save.") },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    textColor = MaterialTheme.colorScheme.onSurface,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

        }
    }

    val localContext = LocalContext.current
    val currentSate = viewModel.addEditState
    val focusManager = LocalFocusManager.current

    currentSate?.let {
        Toast.makeText(localContext, it, Toast.LENGTH_SHORT).show()
        title = ""
        detail = ""
        focusManager.clearFocus()

    }

    if (viewModel.isDialogShow) {
        ColorPicker {
            viewModel.dialogState()
        }
    }

}