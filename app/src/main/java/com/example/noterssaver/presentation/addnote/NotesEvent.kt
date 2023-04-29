package com.example.noterssaver.presentation.addnote

import com.example.noterssaver.data.model.Note

sealed interface NotesEvent {
    object NoEvent : NotesEvent
    object SaveNote : NotesEvent
    data class EditNote(val note: Note):NotesEvent
}