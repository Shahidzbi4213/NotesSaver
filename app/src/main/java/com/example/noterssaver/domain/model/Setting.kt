package com.example.noterssaver.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 * Created by Shahid Iqbal on 3/31/2023.
 */

@Entity
data class Setting(
    val isDarkMode: Boolean = false,
    val isAppLock: Boolean = false,
    val currentPassword: String? = null,
    val hint: String? = null,
    @PrimaryKey
    val key: Int = 1
)