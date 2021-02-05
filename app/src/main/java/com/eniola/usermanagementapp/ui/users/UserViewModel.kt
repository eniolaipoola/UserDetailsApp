package com.eniola.usermanagementapp.ui.users

import androidx.lifecycle.ViewModel
import com.eniola.usermanagementapp.repository.UserRepository
import com.eniola.usermanagementapp.repository.remote.NetworkService
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




}