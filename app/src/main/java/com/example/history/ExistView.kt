package com.example.history

interface ExistView {
    fun onAuthLoading()
    fun onAuthSuccess(body : Boolean)
    fun onAuthFailure()
}