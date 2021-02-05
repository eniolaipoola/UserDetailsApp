package com.eniola.usermanagementapp.repository.remote

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


suspend fun <T> safeAPICall(dispatcher: CoroutineDispatcher = Dispatchers.IO,
                            apiCall: suspend () -> T) : ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke())

        } catch (throwable: Throwable){
            throwable.printStackTrace()
            when(throwable) {
                is IOException ->
                    ResultWrapper.Error(103, "Not connected to the internet")
                is UnknownHostException ->
                    ResultWrapper.Error(101, ErrorStatus.NO_CONNECTION)
                is SocketTimeoutException ->
                    ResultWrapper.Error(102, ErrorStatus.TIMEOUT)
                is HttpException -> {
                    ResultWrapper.Error(401, throwable.message())

                }
                else -> {
                    ResultWrapper.Error(null, throwable.localizedMessage)
                }
            }
        }
    }

}