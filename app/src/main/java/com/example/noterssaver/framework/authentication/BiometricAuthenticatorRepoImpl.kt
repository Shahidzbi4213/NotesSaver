package com.example.noterssaver.framework.authentication

import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.noterssaver.data.source.local.SettingDao
import com.example.noterssaver.framework.util.BiometricPrompt.getBiometricPrompt
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first

class BiometricAuthenticatorRepoImpl(private val settingDao: SettingDao) :
    BiometricAuthenticatorRepo {

    private val authenticationState = MutableStateFlow<AuthState>(AuthState.Authenticating)


    override suspend fun initBiometricPrompt(context: AppCompatActivity) {

        settingDao.getAppLockStatus().collect { value ->
            if (value)
            {
                val executor = ContextCompat.getMainExecutor(context)
                BiometricPrompt(
                    (context),
                    executor,
                    object : BiometricPrompt.AuthenticationCallback() {

                        override fun onAuthenticationError(
                            errorCode: Int,
                            errString: CharSequence
                        ) {
                            super.onAuthenticationError(errorCode, errString)
                            authenticationState.value = AuthState.AuthenticationFailed
                        }

                        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                            super.onAuthenticationSucceeded(result)
                            authenticationState.value = AuthState.Authenticated

                        }

                        override fun onAuthenticationFailed() {
                            super.onAuthenticationFailed()
                            authenticationState.value = AuthState.AuthenticationFailed
                        }
                    }).authenticate(context.getBiometricPrompt())
            }
        }




    }

    override fun authenticateState(): StateFlow<AuthState> {
        return authenticationState.asStateFlow()
    }


}