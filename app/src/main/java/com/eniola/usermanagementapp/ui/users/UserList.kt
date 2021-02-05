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
data class UserList(
        @SerializedName("name")
        val name: String,
        @PrimaryKey
        @SerializedName("id")
        val id: Int
) : Parcelable