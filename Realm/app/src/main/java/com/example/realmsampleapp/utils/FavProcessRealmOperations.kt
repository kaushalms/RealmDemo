package com.example.realmsampleapp.utils

import com.example.realmsampleapp.models.ReleaseValue
import com.example.realmsampleapp.models.UserViewModel
import com.example.realmsampleapp.models.realmobjects.process.ReleaseRealmObject
import com.example.realmsampleapp.models.realmobjects.user.UserRealmObject
import io.realm.Realm
import io.realm.RealmConfiguration

class FavProcessRealmOperations {

    private var realm: Realm = Realm.getDefaultInstance()

    fun isProcessInFavoritedList(userViewModel: UserViewModel, process: ReleaseValue): Boolean {
        val userPrimaryKey = userViewModel.user.tenantId + "_" +
                userViewModel.user.userId + "_" +
                userViewModel.user.userName + "_" +
                userViewModel.user.url

        val userRealmObject = realm.where(UserRealmObject::class.java).equalTo("id", userPrimaryKey).findFirst()
        val favoriteProcesses = userRealmObject?.favoriteProcesses
        favoriteProcesses?.forEach { favoriteProcess ->
            if (favoriteProcess.id == process.id)
                return true
        }
        return false
    }

    fun removeProcessFromFavoriteList(userViewModel: UserViewModel, processRealmObject: ReleaseRealmObject) {
        val userPrimaryKey = userViewModel.user.tenantId + "_" +
                userViewModel.user.userId + "_" +
                userViewModel.user.userName + "_" +
                userViewModel.user.url

        val userRealmObject = realm.where(UserRealmObject::class.java).equalTo("id", userPrimaryKey).findFirst()

        realm.executeTransaction {
            var indexTobeDeleted: Int? = null

            userRealmObject?.favoriteProcesses?.forEachIndexed { index, releaseRealmObject ->
                if (releaseRealmObject.id == processRealmObject.id) {
                    indexTobeDeleted = index
                    return@forEachIndexed
                }
            }

            indexTobeDeleted?.let { index ->
                userRealmObject?.favoriteProcesses?.removeAt(index)
            }
        }
    }

    fun addProcessToFavoriteList(userViewModel: UserViewModel, processRealmObject: ReleaseRealmObject) {
        val userPrimaryKey = userViewModel.user.tenantId + "_" +
                userViewModel.user.userId + "_" +
                userViewModel.user.userName + "_" +
                userViewModel.user.url

        val userRealmObject = realm.where(UserRealmObject::class.java).equalTo("id", userPrimaryKey).findFirst()

        val favoriteProcesses = userRealmObject?.favoriteProcesses

        var isCurrentProcessInFavoriteList = false

        favoriteProcesses?.forEach { releaseRealmObject ->
            if (releaseRealmObject.id == processRealmObject.id) {
                isCurrentProcessInFavoriteList = true
                return
            }
        }

        if (!isCurrentProcessInFavoriteList) {
            realm.executeTransaction {
                userRealmObject?.favoriteProcesses?.add(processRealmObject)
            }
        }
    }
}
