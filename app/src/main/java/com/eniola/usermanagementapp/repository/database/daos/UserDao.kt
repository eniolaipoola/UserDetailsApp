package com.eniola.usermanagementapp.repository.database.daos

import androidx.room.*
import com.eniola.usermanagementapp.ui.users.UserData

/**
 * Copyright (c) 2021 Eniola Ipoola
 * All rights reserved
 * Created on 05-Feb-2021
 */

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsers(userList: ArrayList<UserData>) : List<Long>

    @Query("select * from UserData where id=:id")
    suspend fun getUser(id: String): UserData

    @Query("select * from UserData")
    suspend fun getAllUser(): List<UserData>

}