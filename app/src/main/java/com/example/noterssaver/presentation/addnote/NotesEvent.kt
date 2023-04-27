package com.example.noterssaver.presentation.addnote

sealed interface NotesEvent {
    object NoEvent : NotesEvent
    object SaveNote : NotesEvent
}