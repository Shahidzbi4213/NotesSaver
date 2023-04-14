package com.example.noterssaver.domain.usecase.settings

import com.example.noterssaver.domain.usecase.notes.NotesUseCases

class ClearAllUseCase(private val notesUseCases: NotesUseCases) {

    suspend operator fun invoke() = notesUseCases.clearAllNotesUseCase()
}