package com.example.noterssaver.presentation.authentication.utils

sealed class AuthState {
     object Authenticating : AuthState()
    object Authenticated : AuthState()
    object AuthenticationFailed : AuthState()
}