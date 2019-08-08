package com.example.realmsampleapp.models.realmobjects.process

import io.realm.RealmObject
import io.realm.annotations.RealmClass
import io.realm.annotations.RealmField
import io.realm.annotations.RealmNamingPolicy

@RealmClass(name = "ArgumentRealmObject", fieldNamingPolicy = RealmNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
open class ArgumentRealmObject : RealmObject() {
    @RealmField("input")
    var input: String? = null
    @RealmField("output")
    var output: String? = null
}