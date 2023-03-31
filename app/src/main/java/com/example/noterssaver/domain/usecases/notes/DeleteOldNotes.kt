package com.example.noterssaver.domain.usecases.notes

import com.example.noterssaver.domain.repository.NotesRepo

/*
 * Created by Shahid Iqbal on 3/30/2023.
 */

class DeleteOldNotes(private val notesRepo: NotesRepo) {

    suspend operator fun invoke(){
        try {
            notesRepo.deleteOldNotes()
        }catch (e:Exception){e.printStackTrace()}
    }
}