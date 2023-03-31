package com.example.noterssaver.domain.usecases.settings

import com.example.noterssaver.domain.usecases.notes.ClearAllNotes
import com.example.noterssaver.domain.usecases.notes.NotesUseCases

/*
 * Created by Shahid Iqbal on 3/31/2023.
 */

class ClearAll(private val notesUseCases: NotesUseCases) {

    suspend operator fun invoke() =
        notesUseCases.clearAllNotes()

}