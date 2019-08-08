package com.example.realmsampleapp.utils

import com.example.realmsampleapp.models.*
import com.example.realmsampleapp.models.realmobjects.process.*
import io.realm.RealmList

fun convertProcessToRealmObject(process: ReleaseValue): ReleaseRealmObject {
    val processRealmObject = ReleaseRealmObject()
    processRealmObject.key = process.key
    processRealmObject.processKey = process.processKey
    processRealmObject.processVersion = process.processVersion
    processRealmObject.isLatestVersion = process.isLatestVersion
    processRealmObject.isProcessDeleted = process.isProcessDeleted
    processRealmObject.description = process.description
    processRealmObject.name = process.name
    processRealmObject.environmentId = process.environmentId
    processRealmObject.environmentName = process.environmentName
    processRealmObject.environment = convertEnvironmentToRealmObject(process.environment)
    processRealmObject.inputArguments = process.inputArguments
    processRealmObject.currentVersion = covertToVersionRealmObject(process.currentVersion)
    processRealmObject.releaseVersions = createReleaseVersionRealmList(process.releaseVersions)
    processRealmObject.arguments = convertToArgumentRealmObject(process.arguments)
    processRealmObject.id = process.id ?: 0
    return processRealmObject
}

private fun createReleaseVersionRealmList(releaseVersions: List<Version>?): RealmList<VersionRealmObject> {
    val versionRealmList = RealmList<VersionRealmObject>()
    releaseVersions?.map { version ->
        versionRealmList.add(convertToVersionRealmObject(version))
    }
    return versionRealmList
}

private fun convertToVersionRealmObject(version: Version): VersionRealmObject? {
    val versionRealmObject = VersionRealmObject()
    versionRealmObject.creationTime = version.creationTime
    versionRealmObject.id = version.id
    versionRealmObject.releaseId = version.releaseId
    versionRealmObject.versionNumber = version.versionNumber
    return versionRealmObject
}

private fun convertToArgumentRealmObject(arguments: Argument?): ArgumentRealmObject? {
    val argumentRealmObject = ArgumentRealmObject()
    argumentRealmObject.input = arguments?.input
    argumentRealmObject.output = arguments?.output
    return argumentRealmObject
}

private fun covertToVersionRealmObject(currentVersion: Version?): VersionRealmObject? {
    val versionRealmObject = VersionRealmObject()
    versionRealmObject.releaseId = currentVersion?.releaseId
    versionRealmObject.versionNumber = currentVersion?.versionNumber
    versionRealmObject.creationTime = currentVersion?.creationTime
    versionRealmObject.id = currentVersion?.id
    return versionRealmObject
}

private fun convertEnvironmentToRealmObject(environment: EnvironmentValue?): EnvironmentRealmObject? {
    val environmentRealmObject = EnvironmentRealmObject()
    environmentRealmObject.name = environment?.name
    environmentRealmObject.description = environment?.description
    environmentRealmObject.type = environment?.name
    environmentRealmObject.id = environment?.id
    environmentRealmObject.robots = createRobotRealmList(environment?.robots)
    return environmentRealmObject
}

private fun createRobotRealmList(robots: List<RobotsValue>?): RealmList<RobotRealmObject> {
    val robotsRealmList = RealmList<RobotRealmObject>()
    robots?.map { robotValue ->
        robotsRealmList.add(convertToRobotRealmObject(robotValue))
    }
    return robotsRealmList
}

private fun convertToRobotRealmObject(robotValue: RobotsValue): RobotRealmObject? {
    val robotRealmObJect = RobotRealmObject()
    robotRealmObJect.licenseKey = robotValue.licenseKey
    robotRealmObJect.machineName = robotValue.machineName
    robotRealmObJect.machineId = robotValue.machineId
    robotRealmObJect.name = robotValue.name
    robotRealmObJect.username = robotValue.username
    robotRealmObJect.description = robotValue.description
    robotRealmObJect.version = robotValue.version
    robotRealmObJect.type = robotValue.type
    robotRealmObJect.hostingType = robotValue.hostingType
    robotRealmObJect.password = robotValue.password
    robotRealmObJect.environments = convertEnvironmentToRealmObject(robotValue.environments)
    robotRealmObJect.robotEnvironments = robotValue.robotEnvironments
    robotRealmObJect.id = robotValue.id
    return robotRealmObJect
}
