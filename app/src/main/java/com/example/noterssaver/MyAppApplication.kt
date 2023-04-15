package com.example.noterssaver

import android.app.Application
import com.example.noterssaver.di.appModule
import com.example.noterssaver.di.databaseModule
import com.example.noterssaver.app.worker.DeleteWorkScheduler.doWork
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.component.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class MyAppApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyAppApplication)
            workManagerFactory()
            loadKoinModules(listOf(appModule, databaseModule))
        }

        //Deleting Notes Worker
        this.doWork()
    }
}