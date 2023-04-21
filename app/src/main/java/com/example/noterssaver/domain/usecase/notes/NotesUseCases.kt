package com.example.noterssaver.domain.usecase.notes

data class NotesUseCases(
    val getNotesUseCase: GetNotesUseCase,
    val addNoteUseCase: AddNoteUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val deleteOldNotesUseCase: DeleteOldNotesUseCase,
    val deleteAllNotesUseCase: DeleteAllNotesUseCase
)
