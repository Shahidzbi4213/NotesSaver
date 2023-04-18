package com.example.noterssaver.domain.usecase.authentication

import com.example.noterssaver.framework.authentication.AuthState
import com.example.noterssaver.framework.authentication.BiometricAuthenticatorRepo
import kotlinx.coroutines.flow.StateFlow

/*
 * Created by Shahid Iqbal on 4/18/2023.
 */

class BiometricAuthenticationState(private val repo: BiometricAuthenticatorRepo) {
    operator fun invoke(): StateFlow<AuthState> = repo.authenticateState()
}