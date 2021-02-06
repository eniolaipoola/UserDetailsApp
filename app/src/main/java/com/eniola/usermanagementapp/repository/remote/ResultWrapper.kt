package com.eniola.usermanagementapp.repository.remote


sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class Error(
        val errorCode: Int? = null,
        val errorMessage: String? = null): ResultWrapper<Nothing>()
    data class Loading<out T>(val value: T): ResultWrapper<T>()

}