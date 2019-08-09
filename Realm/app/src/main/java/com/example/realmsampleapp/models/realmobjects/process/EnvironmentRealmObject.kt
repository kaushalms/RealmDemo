package com.example.realmsampleapp.models.realmobjects.process

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import io.realm.annotations.RealmField
import io.realm.annotations.RealmNamingPolicy

@RealmClass(name = "EnvironmentRealmObject", fieldNamingPolicy = RealmNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
open class EnvironmentRealmObject : RealmObject() {
    @RealmField("name")
    var name: String? = null
    @RealmField("description")
    var description: String? = null
    @RealmField("type")
    var type: String? = null
    @RealmField("id")
    var id: Int? = null
    @RealmField("robots")
    var robots: RealmList<RobotRealmObject> = RealmList()
}