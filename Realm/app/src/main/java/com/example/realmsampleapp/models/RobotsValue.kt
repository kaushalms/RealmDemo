package com.example.realmsampleapp.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RobotsValue(
    @Json(name = "LicenseKey") val licenseKey: String?,
    @Json(name = "MachineName") val machineName: String?,
    @Json(name = "MachineId") val machineId: Int?,
    @Json(name = "Name") val name: String?,
    @Json(name = "Username") val username: String?,
    @Json(name = "Description") val description: String?,
    @Json(name = "Version") val version: String?,
    @Json(name = "Type") val type: String?,
    @Json(name = "HostingType") val hostingType: String?,
    @Json(name = "Password") val password: String?,
    @Json(name = "Environments") val environments: EnvironmentValue?,
    @Json(name = "RobotEnvironments") val robotEnvironments: String?,
    @Json(name = "Id") val id: String?
)
