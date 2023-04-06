package com.example.noterssaver.presentation.setting

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import com.example.noterssaver.R

data class SettingOption(
    val title: String,
    @DrawableRes val image: Int
) {

    companion object {
        val SETTING_MENU = listOf(
            SettingOption("Appearance", R.drawable.theme),
            SettingOption("Fingerprint", R.drawable.lock),
            SettingOption("Sort", R.drawable.sort),
            SettingOption("Clear", R.drawable.delete),
        )
    }
}

