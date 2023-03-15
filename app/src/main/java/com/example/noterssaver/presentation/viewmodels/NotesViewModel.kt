package com.example.noterssaver.presentation.viewmodels

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noterssaver.domain.usecases.NotesUseCases
import com.example.noterssaver.util.Extensions.debug
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.random.Random


// Created by Shahid Iqbal on 3/13/2023.

class NotesViewModel(private val notesUseCases: NotesUseCases) : ViewModel() {

    private var itemClick = MutableStateFlow(false)
    val isCLicked = itemClick.asStateFlow()

    fun updateClick() {
        itemClick.value = Random.nextBoolean()
    }
}