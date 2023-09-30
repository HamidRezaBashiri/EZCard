package com.hamidrezabashiri.ezcard.utils

sealed class ResponseState {
    object Idle : ResponseState()
    object Loading : ResponseState()
    data class Success(val token: String) : ResponseState()
    data class Error(val errorMessage: String) : ResponseState()
}
