package com.example.realmsampleapp.models.realmobjects.user

import com.example.realmsampleapp.models.realmobjects.process.ReleaseRealmObject
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserRealmObject : RealmObject() {
    var url: String? = null
    var tenantId: String? = null
    var userId: String? = null
    var userName: String? = null
    var favoriteProcesses: RealmList<ReleaseRealmObject>? = RealmList()

    @PrimaryKey
    var id: String? = null
}