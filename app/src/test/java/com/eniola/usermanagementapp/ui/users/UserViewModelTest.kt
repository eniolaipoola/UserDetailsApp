package com.eniola.usermanagementapp.ui.users

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.eniola.usermanagementapp.repository.database.AppRoomDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith

/**
 * Copyright (c) 2021 Eniola Ipoola
 * All rights reserved
 * Created on 16-May-2021
 */

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class UserViewModelTest {

    private lateinit var database: AppRoomDatabase

   /* @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()*/

    private lateinit var userViewModel: UserViewModel


    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {

    }

    @Test
    fun fetchAllUsers() {
    }

    @Test
    fun fetchUserDetailFromApi() {
    }

    @Test
    fun getUserFromDatabase() {
    }

    @Test
    fun getUserDetailFromDatabase() {
    }
}