package com.eniola.usermanagementapp.repository.database

import androidx.room.TypeConverter
import com.eniola.usermanagementapp.ui.users.Location
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun fromLocation(location: Location): String {
        val gson = Gson()
        return gson.toJson(location)
    }

    @TypeConverter
    fun toLocation(location: String): Location {
        val gson = Gson()
        val type = object :
            TypeToken<Location>() {}.type
        return gson.fromJson(location, type)
    }

}