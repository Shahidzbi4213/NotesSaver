package com.example.noterssaver.presentation.add_note

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noterssaver.domain.model.InvalidNoteException
import com.example.noterssaver.domain.model.Note
import com.example.noterssaver.domain.usecases.NotesUseCases
import com.example.noterssaver.util.NoteState
import kotlinx.coroutines.launch


// Created by Shahid Iqbal on 3/16/2023.

class AddNoteViewModel(private val notesUseCases: NotesUseCases) : ViewModel() {

    var addEditState by mutableStateOf<NoteState?>(null)
        private set



    fun onSavedClick(title: String, detail: String) {
        viewModelScope.launch {
            val note = Note(
                title = title,
                content = detail,
                timestamp = System.currentTimeMillis()
            )

            addEditState = try {
                notesUseCases.addNote.invoke(note)
                NoteState.Success()
            } catch (e: InvalidNoteException) {
                NoteState.Error(e)
            }
        }
    }



}