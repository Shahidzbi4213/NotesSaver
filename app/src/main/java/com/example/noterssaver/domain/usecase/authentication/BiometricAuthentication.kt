package com.example.noterssaver.domain.usecase.authentication

import androidx.appcompat.app.AppCompatActivity
import com.example.noterssaver.framework.authentication.BiometricAuthenticatorRepo
import com.example.noterssaver.presentation.MainActivity

/*
 * Created by Shahid Iqbal on 4/18/2023.
 */

class BiometricAuthentication(
    private val repo: BiometricAuthenticatorRepo,
) {

    suspend operator fun invoke(activity: AppCompatActivity) {
        repo.initBiometricPrompt(activity)
    }
}