package com.eniola.usermanagementapp.ui.users

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Copyright (c) 2021 Eniola Ipoola
 * All rights reserved
 * Created on 05-Feb-2021
 */

@Parcelize
@Entity
data class UserData(
        @PrimaryKey
        @SerializedName("id")
        val id: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("firstName")
        val firstName: String,
        @SerializedName("lastName")
        val lastName: String,
        @SerializedName("gender")
        val gender: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("dateOfBirth")
        val dateOfBirth: String,
        @SerializedName("registerDate")
        val registerDate: String,
        @SerializedName("phone")
        val phone: String,
        @SerializedName("picture")
        val picture: String,
        @SerializedName("location")
        val location: Location
) : Parcelable

@Parcelize
data class Location(
        @SerializedName("street")
        val street: String,
        @SerializedName("city")
        val city: String,
        @SerializedName("state")
        val state: String,
        @SerializedName("country")
        val country: String,
        @SerializedName("timezone")
        val timezone: String
): Parcelable