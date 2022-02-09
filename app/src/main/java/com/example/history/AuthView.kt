package com.example.history

interface AuthView {
    fun onAuthLoading()
    fun onAuthSuccess(tokenBody: TokenBody)
    fun onAuthFailure()
}