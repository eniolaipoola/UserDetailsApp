package com.eniola.usermanagementapp.repository

import com.eniola.usermanagementapp.repository.database.AppRoomDatabase
import com.eniola.usermanagementapp.ui.users.UserData
import com.eniola.usermanagementapp.ui.users.UserDetail
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

    suspend fun insertIntoUser(allUsers: ArrayList<UserData>) {
        return database.userDao().insertAllUsers(allUsers)
    }

    suspend fun fetchAUser(id:String): UserData? {
        return database.userDao().getUser(id)
    }

    suspend fun saveUserDetail(userDetail: UserDetail) {
        return database.userDao().saveUserDetail(userDetail)
    }

    suspend fun fetchUserDetail(userId: String) : UserDetail{
        return database.userDao().fetchUserDetail(userId)
    }

}