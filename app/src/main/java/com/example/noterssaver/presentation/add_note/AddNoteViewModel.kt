package com.example.noterssaver.presentation.add_note

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noterssaver.domain.model.InvalidNoteException
import com.example.noterssaver.domain.model.Note
import com.example.noterssaver.domain.usecases.NotesUseCases
import com.example.noterssaver.util.NoteState
import kotlinx.coroutines.launch


// Created by Shahid Iqbal on 3/16/2023.

class AddNoteViewModel(private val notesUseCases: NotesUseCases) : ViewModel() {

    private var _addEditState by mutableStateOf<NoteState?>(null)
    val addEditState get() = _addEditState


    fun onSavedClick(title: String, detail: String) {
        viewModelScope.launch {
            val note = Note(
                title = title,
                content = detail,
                timestamp = System.currentTimeMillis()
            )

            _addEditState = try {
                notesUseCases.addNote.invoke(note)
                NoteState.Success()
            } catch (e: InvalidNoteException) {
                NoteState.Error(e)
            }
        }
    }


}