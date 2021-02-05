package com.eniola.usermanagementapp.ui.users

import android.app.Service
import android.content.Intent
import android.os.IBinder

class FetchUserService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)

        //make api call to fetch users

        //on successful fetching, save users in database

        //if size isn't up to 100, make another api to fetch users, until size is 100



    }

    override fun onCreate() {
        super.onCreate()
    }


    override fun onDestroy() {
        super.onDestroy()
        //close viewModel
    }
}