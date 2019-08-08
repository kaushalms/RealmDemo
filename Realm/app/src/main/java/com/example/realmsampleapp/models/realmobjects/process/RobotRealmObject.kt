package com.example.realmsampleapp.models.realmobjects.process

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.RealmField
import io.realm.annotations.RealmNamingPolicy

@RealmClass(name = "RobotRealmObject", fieldNamingPolicy = RealmNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
open class RobotRealmObject : RealmObject() {
    @RealmField("license_key")
    var licenseKey: String? = null
    @RealmField("machine_name")
    var machineName: String? = null
    @RealmField("machine_id")
    var machineId: Int? = null
    @RealmField("name")
    var name: String? = null
    @RealmField("username")
    var username: String? = null
    @RealmField("description")
    var description: String? = null
    @RealmField("version")
    var version: String? = null
    @RealmField("type")
    var type: String? = null
    @RealmField("hosting_type")
    var hostingType: String? = null
    @RealmField("password")
    var password: String? = null
    @RealmField("environments")
    var environments: EnvironmentRealmObject? = null
    @RealmField("robot_environments")
    var robotEnvironments: String? = null

    @PrimaryKey
    @RealmField("release_id")
    var id: String? = null
}