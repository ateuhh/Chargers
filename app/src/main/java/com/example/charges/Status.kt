package com.example.charges

interface Status {

    data object Loading : Status

    data object Success : Status

    data class Error(val message: String, val throwable: Throwable?): Status
}