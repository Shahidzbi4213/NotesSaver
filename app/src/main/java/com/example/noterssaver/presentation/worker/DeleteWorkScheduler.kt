package com.example.noterssaver.presentation.worker

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object DeleteWorkScheduler {

    private val deleteRequest = PeriodicWorkRequestBuilder<DeleteNotesWorker>(1, TimeUnit.DAYS)
        .setInitialDelay(5, TimeUnit.MINUTES)
        .build()

    fun Context.doWork() {
        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                "Delete Notes",
                ExistingPeriodicWorkPolicy.KEEP,
                deleteRequest
            )
    }
}