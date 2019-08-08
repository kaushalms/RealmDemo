package com.example.realmsampleapp.models.realmobjects.process

import io.realm.RealmObject

open class VersionRealmObject : RealmObject() {
    var releaseId: Int? = null
    var versionNumber: String? = null
    var creationTime: String? = null
    var id: Int? = null
}