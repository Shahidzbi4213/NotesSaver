package com.example.noterssaver.domain.usecase.notes

import com.example.noterssaver.domain.repository.NotesRepo

class DeleteOldNotesUseCase(private val notesRepo: NotesRepo) {
    suspend operator fun invoke() {
        try {
            notesRepo.deleteOldNotes()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}