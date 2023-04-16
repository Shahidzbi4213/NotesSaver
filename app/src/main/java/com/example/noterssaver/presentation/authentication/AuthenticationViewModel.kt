package com.example.noterssaver.presentation.authentication

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noterssaver.domain.usecases.authentication.BiometricAvailability
import com.example.noterssaver.framework.authentication.BiometricAuthResult
import kotlinx.coroutines.launch

/*
 * Created by Shahid Iqbal on 4/7/2023.
 */

class AuthenticationViewModel(private val biometricAvailability: BiometricAvailability) :
    ViewModel() {

    var biometricAvailabilityState by mutableStateOf<BiometricAuthResult>(BiometricAuthResult.Empty)

    init {
        viewModelScope.launch {
            biometricAvailabilityState = biometricAvailability.invoke()
        }
    }

}