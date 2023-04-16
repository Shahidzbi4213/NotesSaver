<<<<<<<< HEAD:app/src/main/java/com/example/noterssaver/worker/DeleteNotesWorker.kt
package com.example.noterssaver.worker
========
package com.example.noterssaver.app.worker
>>>>>>>> origin/master:app/src/main/java/com/example/noterssaver/app/worker/DeleteNotesWorker.kt

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