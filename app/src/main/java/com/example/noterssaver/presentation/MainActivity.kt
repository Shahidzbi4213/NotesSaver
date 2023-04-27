package com.example.noterssaver.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noterssaver.presentation.authentication.AuthenticationViewModel
import com.example.noterssaver.presentation.authentication.utils.AuthState
import com.example.noterssaver.presentation.setting.SettingViewModel
import com.example.noterssaver.presentation.setting.component.currentAppTheme
import com.example.noterssaver.presentation.util.theme.ReplyTheme
import com.example.noterssaver.presentation.view.component.NotesApp
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val authViewModel by viewModel<AuthenticationViewModel>()
    private val settingViewModel by viewModel<SettingViewModel>()
    private var appLockState = false
    private var startState = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()


        setContent {
            ReplyTheme(dynamicColor = false, darkTheme = currentAppTheme()) {

                val authenticationState by authViewModel.authenticationState.collectAsStateWithLifecycle()


                LaunchedEffect(key1 = Unit) {
                    settingViewModel.appLockState.collect {
                        appLockState = it
                    }
                }

                if (!startState) {
                    if (appLockState) {
                        authViewModel.startAuthentication(this@MainActivity)
                        when (authenticationState) {
                            AuthState.Authenticated -> NotesApp()

                            AuthState.Authenticating -> Unit

                            AuthState.AuthenticationFailed -> finishAffinity()

                        }
                    } else NotesApp()
                }

            }
        }
    }

}
