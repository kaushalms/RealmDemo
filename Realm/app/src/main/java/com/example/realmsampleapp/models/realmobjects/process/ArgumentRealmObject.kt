package com.example.realmsampleapp.models.realmobjects.process

import io.realm.RealmObject

open class ArgumentRealmObject : RealmObject() {
    var input: String? = null
    var output: String? = null
}