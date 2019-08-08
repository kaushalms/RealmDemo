package com.example.realmsampleapp.models.realmobjects.process

import io.realm.RealmList
import io.realm.RealmObject

open class EnvironmentRealmObject : RealmObject() {
    var name: String? = null
    var description: String? = null
    var type: String? = null
    var id: Int? = null
    var robots: RealmList<RobotRealmObject> = RealmList()
}