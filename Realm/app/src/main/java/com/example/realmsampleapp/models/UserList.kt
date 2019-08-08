package com.example.realmsampleapp.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserList(val userList: List<User>)