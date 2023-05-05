package com.example.noterssaver.presentation.shownotes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noterssaver.data.model.Note
import com.example.noterssaver.domain.usecase.notes.NotesUseCases
import com.example.noterssaver.presentation.util.NoteState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GetNotesViewModel(private val notesUseCases: NotesUseCases) : ViewModel() {

    var deleteState by mutableStateOf<NoteState?>(null)
        private set

    var copyClickState by mutableStateOf(false)
        private set

    var searchText = MutableStateFlow("")
        private set

    val currentNotes = notesUseCases.getNotesUseCase.invoke(searchFlow = searchText).stateIn(
        viewModelScope, started = SharingStarted.WhileSubscribed(5000), emptyList()
    )

    fun onSearchTextChange(text: String) {
        searchText.value = text
    }

    fun onCopied(newState: Boolean) {
        copyClickState = newState
    }

    fun updateDeleteState(){
        deleteState = null
    }


    fun onDelete(note: Note) {
        viewModelScope.launch {
            deleteState = try {
                notesUseCases.deleteNoteUseCase.invoke(note)
                NoteState.Success("Note Deleted")
            } catch (e: Exception) {
                NoteState.Error(e)
            }
        }
    }

    fun saveOnUndo(lastDeletedNote: Note) {
        viewModelScope.launch {
            notesUseCases.addNoteUseCase.invoke(lastDeletedNote)
        }
    }
}