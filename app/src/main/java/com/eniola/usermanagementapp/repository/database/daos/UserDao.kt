package com.eniola.usermanagementapp.repository.database.daos

import androidx.room.*
import com.eniola.usermanagementapp.ui.users.UserData
import com.eniola.usermanagementapp.ui.users.UserDetail

/**
 * Copyright (c) 2021 Eniola Ipoola
 * All rights reserved
 * Created on 05-Feb-2021
 */

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsers(userList: ArrayList<UserData>)

    @Query("select * from UserData where id=:id")
    suspend fun getUser(id: String): UserData

    @Query("select * from UserData")
    suspend fun getAllUser(): List<UserData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserDetail(userDetail: UserDetail)

    @Query("select * from UserDetail where id=:userId")
    suspend fun fetchUserDetail(userId: String): UserDetail


}