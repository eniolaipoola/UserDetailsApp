package com.eniola.usermanagementapp.base

sealed class ViewState<T> {
    data class DATA<T>(val data: List<T>) : ViewState<T>()
    data class LOADING<T>(val loading: Boolean = false): ViewState<T>()
    data class ERROR<T>(val errorMessage: String): ViewState<T>()
}