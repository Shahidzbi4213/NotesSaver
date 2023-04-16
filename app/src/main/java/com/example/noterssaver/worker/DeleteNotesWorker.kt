package com.example.noterssaver.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.noterssaver.domain.usecases.notes.NotesUseCases

/*
 * Created by Shahid Iqbal on 3/27/2023.
 */

class DeleteNotesWorker(
    private val notesUseCases: NotesUseCases,
    appContext: Context,
    params: WorkerParameters
) :
    CoroutineWorker(appContext, params) {


    override suspend fun doWork(): Result {
        notesUseCases.deleteOldNotes()
        return Result.success()
    }
}