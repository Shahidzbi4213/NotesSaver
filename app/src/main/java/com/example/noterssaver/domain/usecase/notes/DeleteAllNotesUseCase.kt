package com.example.noterssaver.domain.usecase.notes

import com.example.noterssaver.domain.repository.NotesRepo

class DeleteAllNotesUseCase(private val repo: NotesRepo) {

    suspend operator fun invoke() = repo.deleteAllNotes()
}