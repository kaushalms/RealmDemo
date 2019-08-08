package com.example.realmsampleapp.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    val url: String,
    val tenantId: String,
    val userId: String,
    val userName: String
)
