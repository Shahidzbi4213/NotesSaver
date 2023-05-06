package com.example.noterssaver.presentation.setting.util

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

object LanguageUtils {


    fun currentLanguage(): LANGUAGES {
        return when (AppCompatDelegate.getApplicationLocales().toLanguageTags()) {
            "en" -> LANGUAGES.ENGLISH
            "ur" -> LANGUAGES.URDU
            "hi" -> LANGUAGES.HINDI
            else -> LANGUAGES.ENGLISH
        }
    }

    fun setCurrentLanguage(language: LANGUAGES) {
        val tag = when (language) {
            LANGUAGES.ENGLISH -> "en"
            LANGUAGES.URDU -> "ur"
            LANGUAGES.HINDI -> "hi"
        }
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(tag))
    }
}