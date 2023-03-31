package com.example.noterssaver.domain.usecases.notes

import com.example.noterssaver.domain.repository.NotesRepo

/*
 * Created by Shahid Iqbal on 3/31/2023.
 */

class ClearAllNotes(private val repo: NotesRepo) {

    suspend operator fun invoke() = repo.deleteAllNotes()

}