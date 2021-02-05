package com.eniola.studyapp.utility

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


fun<T: Any> LifecycleOwner.observe(data: LiveData<T>, body:(T) -> Unit){
    data.observe(this, Observer(body))
}

fun parseError(exception: Exception, errorCallBack: ((String) -> Unit)){
    when(exception){
        is UnknownHostException, is SocketTimeoutException, is ConnectException -> {
            errorCallBack("Check your internet connection")
        }
        else -> {
            errorCallBack("Something went wrong")
        }
    }
    exception.printStackTrace()
}

fun ViewModel.runIO(function: suspend CoroutineScope.() -> Unit){
    viewModelScope.launch (Dispatchers.IO) {
        function()
    }
}