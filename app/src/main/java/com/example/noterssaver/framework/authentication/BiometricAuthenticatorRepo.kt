package com.example.noterssaver.framework.authentication

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.flow.StateFlow

/*
 * Created by Shahid Iqbal on 4/18/2023.
 */

interface BiometricAuthenticatorRepo {

    suspend fun initBiometricPrompt(context: AppCompatActivity,startState: Boolean)

    fun authenticateState(): StateFlow<AuthState>

}
