package com.example.noterssaver.presentation.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.noterssaver.domain.usecase.notes.NotesUseCases

class DeleteNotesWorker(
    private val notesUseCases: NotesUseCases,
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        notesUseCases.deleteOldNotesUseCase()
        return Result.success()
    }
}