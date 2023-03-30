package com.example.noterssaver

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.noterssaver.data.worker.DeleteWorkScheduler
import com.example.noterssaver.data.worker.DeleteWorkScheduler.doWork
import com.example.noterssaver.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.component.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication


// Created by Shahid Iqbal on 3/13/2023.

class MyApp : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            workManagerFactory()
            loadKoinModules(Modules.appModules)
        }

        //Deleting Notes Worker
        this.doWork()

    }

}