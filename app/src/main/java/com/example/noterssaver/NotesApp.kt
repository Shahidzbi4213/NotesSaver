package com.example.noterssaver

import android.app.Application
import com.example.noterssaver.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin


// Created by Shahid Iqbal on 3/13/2023.

class NotesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NotesApp)
            loadKoinModules(Modules.appModules)
        }
    }
}