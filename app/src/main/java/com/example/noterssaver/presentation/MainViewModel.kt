package com.example.noterssaver.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.noterssaver.presentation.util.Extensions.debug

class MainViewModel : ViewModel() {

    var isScrollUp: Boolean by mutableStateOf(false)
        private set

    fun updateScrollSate(newScrollState: Boolean) {
        isScrollUp = newScrollState
    }
}
