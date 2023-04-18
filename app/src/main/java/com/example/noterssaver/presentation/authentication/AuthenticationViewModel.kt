package com.example.noterssaver.presentation.authentication

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noterssaver.domain.usecase.authentication.BiometricUseCases
import com.example.noterssaver.framework.util.BiometricAuthResult
import com.example.noterssaver.presentation.MainActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/*
 * Created by Shahid Iqbal on 4/7/2023.
 */

class AuthenticationViewModel(
    private val biometricUseCases: BiometricUseCases,
) : ViewModel() {

    var biometricAvailabilityState by mutableStateOf<BiometricAuthResult>(BiometricAuthResult.Empty)
    var biometricAuthenticationState = biometricUseCases.biometricAuthenticationState.invoke()

    var isAuthenticationStarted by mutableStateOf(false)
        private set


    init {
        viewModelScope.launch {
            biometricAvailabilityState = biometricUseCases.biometricAvailability.invoke()
        }
    }

    fun startAuthentication(activity: AppCompatActivity) {
        viewModelScope.launch {
            biometricUseCases.biometricAuthentication.invoke(activity)
        }
    }

    fun authStartState() {
        isAuthenticationStarted = !isAuthenticationStarted
    }
}