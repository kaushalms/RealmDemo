package com.example.realmsampleapp.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Version(
    @Json(name = "ReleaseId") val releaseId: Int?,
    @Json(name = "VersionNumber") val versionNumber: String?,
    @Json(name = "CreationTime") val creationTime: String?,
    @Json(name = "Id") val id: Int?
)