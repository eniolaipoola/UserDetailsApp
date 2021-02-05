package com.eniola.usermanagementapp.repository

import com.eniola.usermanagementapp.repository.database.AppRoomDatabase
import com.eniola.usermanagementapp.ui.users.UserData
import javax.inject.Inject

/**
 * Saves and fetches data from the database
 * to be made available to the view
 */

class UserRepository @Inject constructor(
    private val database: AppRoomDatabase
) {

    suspend fun fetchAllUsers(): List<UserData> {
        return database.userDao().getAllUser()
    }

    suspend fun fetchAUser(id:String): UserData? {
        return database.userDao().getUser(id)
    }
}