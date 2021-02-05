package com.eniola.usermanagementapp.ui.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eniola.studyapp.utility.runIO
import com.eniola.usermanagementapp.repository.UserRepository
import com.eniola.usermanagementapp.repository.remote.NetworkService
import com.eniola.usermanagementapp.repository.remote.ResultWrapper
import com.eniola.usermanagementapp.repository.remote.safeAPICall
import kotlinx.coroutines.Job
import javax.inject.Inject

/**
 * Copyright (c) 2021 Eniola Ipoola
 * All rights reserved
 * Created on 05-Feb-2021
 * Fetches data from api and relates with view
 */

class UserViewModel @Inject constructor(
    private val networkService: NetworkService,
    private val userRepository: UserRepository
) : ViewModel() {

    val state = MutableLiveData<ViewState>()
    private val job = Job()

    fun cancelJob() {
        job.cancel()
    }

    /**
     *  make api call to fetch all users
     *  save fetched data in database
     *  */

    fun fetchAllUsers(apiKey: String){
        state.postValue(ViewState.LOADING(true))
        runIO {
            when(val allUsers = safeAPICall {
                networkService.apiService.fetchAllUsers(apiKey, 50)
            }) {
                //api call is successful, save into database
                is ResultWrapper.Success ->  when (val localUser = safeAPICall {
                    //run saving into database on an IO coroutine dispatcher
                    userRepository.insertIntoUser(allUsers.value.data)
                }){
                    is ResultWrapper.Success -> {
                        //successfully saved into database
                        state.postValue(ViewState.LOADING(false))
                    }

                    is ResultWrapper.Error -> {
                        state.postValue(localUser.errorMessage?.let { ViewState.ERROR(it) })
                        state.postValue(ViewState.LOADING(false))
                    }
                }

                //api called failed
                is ResultWrapper.Error -> {
                    state.postValue(ViewState.LOADING(false))
                    state.postValue(allUsers.errorMessage?.let { ViewState.ERROR(it) })
                }
            }
        }
    }

    fun getUserFromDatabase(){
        state.postValue(ViewState.LOADING(true))
        runIO {
            //fetch user from database
            when (val getUser = safeAPICall {
                userRepository.fetchAllUsers()
            }) {
                is ResultWrapper.Success -> {
                    //successfully saved into database
                    val allUsers = getUser.value
                    state.postValue(ViewState.LOADING(false))
                    state.postValue(ViewState.SUCCESS(
                        "User list successfully fetched", allUsers))
                }

                is ResultWrapper.Error -> {
                    state.postValue(getUser.errorMessage?.let { ViewState.ERROR(it) })
                    state.postValue(ViewState.LOADING(false))
                }
            }
        }
    }

}

sealed class ViewState {
    data class SUCCESS(val message: String, val data: List<UserData>): ViewState()
    data class LOADING(val loading: Boolean = false) : ViewState()
    data class ERROR(val errorMessage: String): ViewState()

}