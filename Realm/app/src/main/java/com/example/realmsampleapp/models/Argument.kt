package com.example.realmsampleapp.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Argument(
    @Json(name = "Input") val input: String?,
    @Json(name = "Output") val output: String?
)