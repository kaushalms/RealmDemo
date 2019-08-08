package com.example.realmsampleapp.models.realmobjects.process

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.RealmField
import io.realm.annotations.RealmNamingPolicy

@RealmClass(name = "VersionRealmObject", fieldNamingPolicy = RealmNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
open class VersionRealmObject : RealmObject() {
    @RealmField("release_id")
    var releaseId: Int? = null
    @RealmField("version_name")
    var versionNumber: String? = null
    @RealmField("creation_time")
    var creationTime: String? = null

    @RealmField("id")
    @PrimaryKey
    var id: Int? = null
}