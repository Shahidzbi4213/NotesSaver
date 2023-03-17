package com.example.noterssaver.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noterssaver.ui.theme.*


// Created by Shahid Iqbal on 3/13/2023.

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    @PrimaryKey val id: Int? = null,
)

class InvalidNoteException(message: String) : Exception(message)