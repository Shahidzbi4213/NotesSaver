package com.example.noterssaver.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noterssaver.framework.authentication.AuthState
import com.example.noterssaver.presentation.authentication.AuthenticationViewModel
import com.example.noterssaver.presentation.destinations.ShowNotesDestination
import com.example.noterssaver.presentation.setting.SettingViewModel
import com.example.noterssaver.presentation.setting.currentAppTheme
import com.example.noterssaver.presentation.util.theme.ReplyTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val authViewModel by viewModel<AuthenticationViewModel>()
    private val settingViewModel by viewModel<SettingViewModel>()
    private var appLockState = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()



        setContent {
            ReplyTheme(dynamicColor = true, darkTheme = currentAppTheme()) {

                val authenticationState by authViewModel.biometricAuthenticationState.collectAsStateWithLifecycle()

                LaunchedEffect(key1 = Unit) {
                    authViewModel.startAuthentication(this@MainActivity)

                    settingViewModel.appLockState.collect {
                        appLockState = it
                    }
                }

                if (appLockState)
                    when (authenticationState) {
                        AuthState.Authenticated -> SetNavHost()

                        AuthState.Authenticating -> {}

                        AuthState.AuthenticationFailed -> finishAffinity()
                    }
                else SetNavHost()

            }
        }
    }

    @Composable
    private fun SetNavHost() = DestinationsNavHost(
        navGraph = NavGraphs.root,
        startRoute = ShowNotesDestination,
    )

}
