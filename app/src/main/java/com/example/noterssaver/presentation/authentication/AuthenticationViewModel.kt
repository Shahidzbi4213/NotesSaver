package com.example.noterssaver.presentation.authentication

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noterssaver.presentation.authentication.utils.AuthState
import com.example.noterssaver.presentation.authentication.utils.BiometricExistsStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/*
 * Created by Shahid Iqbal on 4/7/2023.
 */

class AuthenticationViewModel(
    private val biometricAuth: BiometricAuth,
) : ViewModel() {

    private var _biometricAvailabilityState by
        mutableStateOf<BiometricExistsStatus>(BiometricExistsStatus.Empty)
    val isBiometricExistAndEnabled: BiometricExistsStatus get() = _biometricAvailabilityState

    val authenticationState: StateFlow<AuthState> = biometricAuth.authenticationState
    private var _isAuthenticationStarted = MutableStateFlow(false)


    init {
        viewModelScope.launch {
            _biometricAvailabilityState = biometricAuth.userCanAuthenticate()
        }
    }

    fun startAuthentication(activity: AppCompatActivity) {
        viewModelScope.launch {
            biometricAuth.biometricAuthentication(
                activity = activity,
                isAuthStated = _isAuthenticationStarted.value
            )
            _isAuthenticationStarted.value = true

        }
    }

}