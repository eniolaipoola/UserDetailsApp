package com.eniola.usermanagementapp.repository.remote


data class UserListResponse<D>(
    val data : D,
    val total : Int,
    val page : Int,
    val limit : Int
)

data class ApiResponse<D>(
    val data : D
)