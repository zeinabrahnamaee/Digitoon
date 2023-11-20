package com.example.divartask.presentation.util

import androidx.annotation.StringRes

interface BaseViewState<out DATA> {
    object Loading : BaseViewState<Nothing>
    data class ErrorString(val message: String) : BaseViewState<Nothing>
    data class Success<DATA>(val data:DATA): BaseViewState<DATA>
}