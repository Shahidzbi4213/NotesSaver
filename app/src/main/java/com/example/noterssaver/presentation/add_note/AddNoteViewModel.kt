package com.example.noterssaver.presentation.add_note

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noterssaver.domain.model.InvalidNoteException
import com.example.noterssaver.domain.model.Note
import com.example.noterssaver.domain.usecases.notes.NotesUseCases
import com.example.noterssaver.util.Extensions.debug
import com.example.noterssaver.util.NoteState
import kotlinx.coroutines.launch


// Created by Shahid Iqbal on 3/16/2023.

class AddNoteViewModel(private val notesUseCases: NotesUseCases) :
    ViewModel() {


    var addEditState by mutableStateOf<NoteState?>(null)
        private set

    var currentNoteToEdit by mutableStateOf<Note?>(null)
        private set

    var title by mutableStateOf("")
        private set

    var content by mutableStateOf("")
        private set


    fun onTitleChange(newTitle: String) {
        title = newTitle
    }

    fun onContentChange(newContent: String) {
        content = newContent
    }

    fun updateCurrentEditableNote(note: Note) {
        currentNoteToEdit = note
    }

    fun saveNote(currentNote: Note? = null) {
        viewModelScope.launch {
            val note = currentNote ?: Note(
                title = title,
                content = content,
                timestamp = System.currentTimeMillis(),
                id = currentNoteToEdit?.id
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