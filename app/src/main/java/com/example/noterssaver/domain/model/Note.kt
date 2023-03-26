package com.example.noterssaver.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


// Created by Shahid Iqbal on 3/13/2023.

@Entity
@Parcelize
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    @PrimaryKey val id: Int? = null,
) : Parcelable

class InvalidNoteException(message: String) : Exception(message)