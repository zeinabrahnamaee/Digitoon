package com.example.digitoon.presentation.util

interface BaseViewState<out DATA> {
    object Loading : BaseViewState<Nothing>
    data class ErrorString(val message: String) : BaseViewState<Nothing>
    data class Success<DATA>(val data:DATA): BaseViewState<DATA>
}