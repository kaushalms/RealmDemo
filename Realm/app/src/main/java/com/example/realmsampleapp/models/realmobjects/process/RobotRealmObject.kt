package com.example.realmsampleapp.models.realmobjects.process

import io.realm.RealmObject

open class RobotRealmObject : RealmObject() {
    var licenseKey: String? = null
    var machineName: String? = null
    var machineId: Int? = null
    var name: String? = null
    var username: String? = null
    var description: String? = null
    var version: String? = null
    var type: String? = null
    var hostingType: String? = null
    var password: String? = null
    var environments: EnvironmentRealmObject? = null
    var robotEnvironments: String? = null
    var id: String? = null
}