package com.example.noterssaver.presentation.authentication.utils;

import android.content.Context
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricPrompt
import com.example.noterssaver.R

/*
 * Created by Shahid Iqbal on 4/17/2023.
 */

object BiometricPrompt {

    fun Context.getBiometricPrompt() = BiometricPrompt.PromptInfo.Builder()
        .setTitle(getString(R.string.biometric_title))
        .setSubtitle(getString(R.string.biometric_subtitle))
        .setNegativeButtonText(getString(R.string.cancel))
        .setAllowedAuthenticators(BIOMETRIC_STRONG)
        .build()
}
