package com.eniola.usermanagementapp.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.eniola.usermanagementapp.TestUserFactory
import com.eniola.usermanagementapp.repository.database.AppRoomDatabase
import com.eniola.usermanagementapp.ui.users.UserData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Copyright (c) 2021 Eniola Ipoola
 * All rights reserved
 * Created on 17-May-2021
 */

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDaoTest {

    private lateinit var database: AppRoomDatabase

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDb() {
        // Using an in-memory database so that the information stored here disappears when the
        // process is killed.
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppRoomDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @Test
    fun insertUsers() = runBlocking {
        val users = TestUserFactory.makeUsers() as ArrayList<UserData>
        database.userDao().insertAllUsers(users)

        // WHEN - Get the contact by id from the database.
        val fetchUser = database.userDao().getUser(users[0].id)

        // THEN - The loaded data contains the expected values.
        assertThat(fetchUser, CoreMatchers.notNullValue())
        assertThat(fetchUser.firstName, `is`(users[0].firstName))
        assertThat(fetchUser.lastName, `is`(users[0].lastName))
        assertThat(fetchUser.email, `is`(users[0].email))
        assertThat(fetchUser.title, `is`(users[0].title))
    }

    @After
    fun closeDb() = database.close()


}