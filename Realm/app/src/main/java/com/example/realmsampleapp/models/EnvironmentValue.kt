package com.example.realmsampleapp.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EnvironmentValue(
    @Json(name = "Name") val name: String?,
    @Json(name = "Description") val description: String?,
    @Json(name = "Type") val type: String?,
    @Json(name = "Id") val id: Int?,
    @Json(name = "Robots") val robots: List<RobotsValue>?
)
