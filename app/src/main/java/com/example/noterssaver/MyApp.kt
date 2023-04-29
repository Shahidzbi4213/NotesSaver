package com.example.noterssaver

import android.app.Application
import com.example.noterssaver.di.appModule
import com.example.noterssaver.di.biometricModule
import com.example.noterssaver.di.databaseModule
import com.example.noterssaver.di.notesModule
import com.example.noterssaver.di.settingModule
import com.example.noterssaver.worker.DeleteWorkScheduler.doWork
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.component.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin


// Created by Shahid Iqbal on 3/13/2023.

class MyApp : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            workManagerFactory()
            loadKoinModules(
                listOf(
                    appModule,
                    notesModule,
                    settingModule,
                    databaseModule,
                    biometricModule
                )
            )
        }

        //Deleting Notes Worker
        doWork(context = this)

    }

}