package com.example.noterssaver.framework.authentication

sealed class AuthState {
    object Authenticating : AuthState()
    object Authenticated : AuthState()
    object AuthenticationFailed : AuthState()
}