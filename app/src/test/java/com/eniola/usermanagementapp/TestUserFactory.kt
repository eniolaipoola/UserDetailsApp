package com.eniola.usermanagementapp

import com.eniola.usermanagementapp.ui.users.UserData
import java.util.*
import java.util.concurrent.ThreadLocalRandom


object TestUserFactory {

    fun randomInt(): Int {
        return ThreadLocalRandom.current().nextInt(1, 1000 + 1)
    }

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun createUser(): UserData {
        return UserData(randomString(), randomString(), randomString(),
                randomString(), randomString(), randomString())
    }

    fun makeUsers(): List<UserData>{
        return listOf(UserData(
                randomString(), randomString(), randomString(),
                randomString(), randomString(), randomString()))
    }

}