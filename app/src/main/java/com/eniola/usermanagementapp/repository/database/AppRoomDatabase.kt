package com.eniola.usermanagementapp.repository.database

import android.content.Context
import androidx.room.*
import com.eniola.usermanagementapp.BuildConfig
import com.eniola.usermanagementapp.repository.database.daos.UserDao
import com.eniola.usermanagementapp.ui.users.UserData

/**
 * Copyright (c) 2021 Eniola Ipoola
 * All rights reserved
 * Created on 19-Jan-2021
 */

@Database(entities = [UserData::class], version = 1, exportSchema = false)

abstract class AppRoomDatabase: RoomDatabase()  {
    companion object {
        private val DATABASE_NAME =
            if(BuildConfig.DEBUG) "user_management" else "user_management@@@.db"
        private var sInstance: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase? {
            if(sInstance == null){
               synchronized(AppRoomDatabase::class.java){
                   sInstance = Room.databaseBuilder(context,
                       AppRoomDatabase::class.java, DATABASE_NAME)
                       .build()
               }
            }
            return sInstance
        }
    }

    abstract fun userDao(): UserDao


}