package com.example.realmsampleapp.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReleaseValue(
    @Json(name = "Key") val key: String?,
    @Json(name = "ProcessKey") val processKey: String?,
    @Json(name = "ProcessVersion") val processVersion: String?,
    @Json(name = "IsLatestVersion") val isLatestVersion: Boolean?,
    @Json(name = "IsProcessDeleted") val isProcessDeleted: Boolean?,
    @Json(name = "Description") val description: String?,
    @Json(name = "Name") val name: String?,
    @Json(name = "EnvironmentId") val environmentId: Int?,
    @Json(name = "EnvironmentName") val environmentName: String?,
    @Json(name = "Environment") val environment: EnvironmentValue?,
    @Json(name = "InputArguments") val inputArguments: String?,
    @Json(name = "CurrentVersion") val currentVersion: Version?,
    @Json(name = "ReleaseVersions") val releaseVersions: List<Version>?,
    @Json(name = "Arguments") val arguments: Argument?,
    @Json(name = "Id") val id: Int?
)