package com.example.noterssaver.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.noterssaver.ui.theme.NotersSaverTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotersSaverTheme(darkTheme = false) {
                MyApp()
            }
        }
    }
}

