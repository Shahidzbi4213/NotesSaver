package com.example.noterssaver.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noterssaver.presentation.setting.util.OrderType
import com.example.noterssaver.presentation.setting.util.ThemeStyle

@Entity
data class Setting(
    val currentTheme: ThemeStyle = ThemeStyle.LIGHT,
    val sortingOrder: OrderType = OrderType.TIME,
    val isAppLock: Boolean = false,
    @PrimaryKey
    val key: Int = 1
)