package com.example.noterssaver.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.noterssaver.domain.model.Note
import com.example.noterssaver.util.Extensions.debug


// Created by Shahid Iqbal on 3/17/2023.

class MainViewModel : ViewModel() {

    var isScrollUp: Boolean by mutableStateOf(true)
        private set

    var topBarTitle by mutableStateOf("Notes Saver")
        private set


    fun updateTitle(newTitle: String) {
        topBarTitle = newTitle
    }

    fun updateScrollSate(newScrollState: Boolean) {
        isScrollUp = newScrollState
    }

}