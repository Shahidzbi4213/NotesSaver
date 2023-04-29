package com.example.noterssaver.worker


import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object DeleteWorkScheduler {

    private val deleteRequest = PeriodicWorkRequestBuilder<DeleteNotesWorker>(7, TimeUnit.DAYS)
        .setInitialDelay(5, TimeUnit.MINUTES)
        .build()

    fun doWork(context: Context) {
        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                "Delete Notes",
                ExistingPeriodicWorkPolicy.KEEP,
                deleteRequest
            )
    }
}