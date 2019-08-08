package com.example.realmsampleapp.models.realmobjects.user

import com.example.realmsampleapp.models.realmobjects.process.ReleaseRealmObject
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.RealmField
import io.realm.annotations.RealmNamingPolicy

@RealmClass(name = "UserRealmObject", fieldNamingPolicy = RealmNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
open class UserRealmObject : RealmObject() {
    @RealmField("url")
    var url: String? = null
    @RealmField("tenant_id")
    var tenantId: String? = null
    @RealmField("user_id")
    var userId: String? = null
    @RealmField("user_name")
    var userName: String? = null
    @RealmField("favorite_processes")
    var favoriteProcesses: RealmList<ReleaseRealmObject> = RealmList()

    @PrimaryKey
    @RealmField("id")
    var id: String? = null
}