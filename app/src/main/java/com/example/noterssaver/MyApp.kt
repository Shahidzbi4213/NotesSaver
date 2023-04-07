package com.example.noterssaver

import android.app.Application
import com.example.noterssaver.data.worker.DeleteWorkScheduler.doWork
import com.example.noterssaver.di.BiometricModule
import com.example.noterssaver.di.MainModule
import com.example.noterssaver.di.NotesModule
import com.example.noterssaver.di.SettingModule
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
                    MainModule.mainModule,
                    NotesModule.notesModule,
                    SettingModule.settingModule,
                    BiometricModule.biometricModule
                )
            )
        }

        //Deleting Notes Worker
        this.doWork()

    }

}