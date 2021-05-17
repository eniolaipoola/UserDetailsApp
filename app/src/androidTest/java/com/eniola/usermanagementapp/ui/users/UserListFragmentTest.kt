package com.eniola.usermanagementapp.ui.users

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Copyright (c) 2021 Eniola Ipoola
 * All rights reserved
 * Created on 17-May-2021
 */

@RunWith(AndroidJUnit4::class)
@MediumTest
class UserListFragmentTest {

    @Test
    fun userListFragment_displayedInUi(){
        val currentUser = UserData("5aZRSdkcBOM6j3lkWEoP", "Miss", "Eniola",
            "Ipoola", "eniolaipoola@gmail.com", "")
        val fragmentArgs = bundleOf("userId" to currentUser.id)
        launchFragmentInContainer<UserListFragment>(fragmentArgs)

        //launchFragmentInContainer<UserListFragment>(fragmentArgs, R.style.ThemeOverlay_AppCompat)
        Thread.sleep(2000)

    }
}