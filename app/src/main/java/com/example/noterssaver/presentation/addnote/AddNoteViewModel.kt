package com.example.noterssaver.presentation.addnote

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noterssaver.data.model.InvalidNoteException
import com.example.noterssaver.data.model.Note
import com.example.noterssaver.domain.usecase.notes.NotesUseCases
import com.example.noterssaver.presentation.util.Extensions.debug
import com.example.noterssaver.presentation.util.NoteState
import kotlinx.coroutines.launch

class AddNoteViewModel(private val notesUseCases: NotesUseCases) : ViewModel() {

    var addEditState by mutableStateOf<NoteState?>(null)
        private set

    var currentNoteToEdit by mutableStateOf<Note?>(null)
        private set

    var title by mutableStateOf("")
        private set

    var content by mutableStateOf("")
        private set


    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.NoEvent -> Unit
           is NotesEvent.SaveNote -> saveNote()

        }
    }

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
                timestamp = System.currentTimeMillis()
            )

            addEditState = try {
                notesUseCases.addNoteUseCase.invoke(note)
                NoteState.Success()
            } catch (e: InvalidNoteException) {
                NoteState.Error(e)
            }
        }
    }


}