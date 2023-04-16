package com.example.noterssaver.domain.usecase.notes

import com.example.noterssaver.domain.repository.NotesRepo

class ClearAllNotesUseCase(private val repo: NotesRepo) {

    suspend operator fun invoke() = repo.deleteAllNotes()
}