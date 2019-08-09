package com.example.realmsampleapp.models.realmobjects.process

import com.example.realmsampleapp.models.realmobjects.user.UserRealmObject
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.*

@RealmClass(name = "ReleaseRealmObject", fieldNamingPolicy = RealmNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
open class ReleaseRealmObject : RealmObject() {
    @RealmField("key")
    var key: String? = null
    @RealmField("process_key")
    var processKey: String? = null
    @RealmField("process_version")
    var processVersion: String? = null
    @RealmField("is_latest_version")
    var isLatestVersion: Boolean? = null
    @RealmField("is_process_deleted")
    var isProcessDeleted: Boolean? = null
    @RealmField("description")
    var description: String? = null
    @RealmField("name")
    var name: String? = null
    @RealmField("environment_id")
    var environmentId: Int? = null
    @RealmField("environment_name")
    var environmentName: String? = null
    @RealmField("environment")
    var environment: EnvironmentRealmObject? = null
    @RealmField("input_arguments")
    var inputArguments: String? = null
    @RealmField("current_version")
    var currentVersion: VersionRealmObject? = null
    @RealmField("release_versions")
    var releaseVersions: RealmList<VersionRealmObject> = RealmList()
    @RealmField("arguments")
    var arguments: ArgumentRealmObject? = null

    @PrimaryKey
    @RealmField("id")
    var id: Int? = null

    @RealmField("users")
    @LinkingObjects("favoriteProcesses")
    val users: RealmResults<UserRealmObject>? = null
}