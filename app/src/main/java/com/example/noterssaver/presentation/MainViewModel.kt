package com.example.noterssaver.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.noterssaver.presentation.util.Extensions.debug

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
