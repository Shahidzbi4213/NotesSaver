package com.example.noterssaver.di

import com.example.noterssaver.presentation.MainViewModel
import com.example.noterssaver.worker.DeleteNotesWorker
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val appModule = module {

    viewModelOf(::MainViewModel)
    worker { DeleteNotesWorker(get(), get(), get()) }

}