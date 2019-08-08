package com.example.realmsampleapp.models.realmobjects.process

import com.example.realmsampleapp.models.realmobjects.user.UserRealmObject
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects

open class ReleaseRealmObject : RealmObject() {
    var key: String? = null
    var processKey: String? = null
    var processVersion: String? = null
    var isLatestVersion: Boolean? = null
    var isProcessDeleted: Boolean? = null
    var description: String? = null
    var name: String? = null
    var environmentId: Int? = null
    var environmentName: String? = null
    var environment: EnvironmentRealmObject? = null
    var inputArguments: String? = null
    var currentVersion: VersionRealmObject? = null
    var releaseVersions: RealmList<VersionRealmObject> = RealmList()
    var arguments: ArgumentRealmObject? = null
    var id: Int? = null

    @LinkingObjects("favoriteProcesses")
    val users: RealmResults<UserRealmObject>? = null
}