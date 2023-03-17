package com.example.noterssaver.presentation.show_notes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noterssaver.domain.model.Note
import com.example.noterssaver.domain.usecases.NotesUseCases
import com.example.noterssaver.util.NoteState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


// Created by Shahid Iqbal on 3/16/2023.

class GetNotesViewModel(private val notesUseCases: NotesUseCases) : ViewModel() {

    val currentNotes = notesUseCases.getNotes.invoke().stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5000), emptyList()
    )

    var deleteState by mutableStateOf<NoteState?>(null)
        private set

    var copyClick by mutableStateOf<Boolean>(false)
        private set

    fun onCopied(newState: Boolean) {
        copyClick = newState
    }

    fun onDelete(note: Note) {
        viewModelScope.launch {
            deleteState = try {
                notesUseCases.deleteNote.invoke(note)
                NoteState.Success("Note Deleted")
            } catch (e: Exception) {
                NoteState.Error(e)
            }
            deleteState = null
        }
    }
}