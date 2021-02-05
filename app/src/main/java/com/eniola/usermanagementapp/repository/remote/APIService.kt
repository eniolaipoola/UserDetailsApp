package com.eniola.usermanagementapp.repository.remote

import com.eniola.usermanagementapp.ui.users.UserData
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject

/**
 * Copyright (c) 2021 Eniola Ipoola
 * All rights reserved
 * Created on 19-Jan-2021
 */
interface APIService {

    @GET("user")
    suspend fun fetchAllUsers(
        @Header("app-id") appId: String,
        @Query("limit") limit: Int
    ): UserListResponse<ArrayList<UserData>>

    @GET("user/{userId}")
    suspend fun fetchUser(@Header("app_id") appId: String,
                          @Path("userId") userId: Int): UserListResponse<UserData>

}

//provide apiService instance for viewModel classes
class NetworkService @Inject constructor(private val retrofit: Retrofit){
    val apiService: APIService get() =
        retrofit.create(APIService::class.java)
}