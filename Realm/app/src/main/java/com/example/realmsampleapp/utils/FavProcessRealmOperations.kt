package com.example.realmsampleapp.utils

import com.example.realmsampleapp.models.ReleaseValue
import com.example.realmsampleapp.models.UserViewModel
import com.example.realmsampleapp.models.realmobjects.process.ReleaseRealmObject
import com.example.realmsampleapp.models.realmobjects.user.UserRealmObject
import io.realm.Realm

class FavProcessRealmOperations {

    val realm: Realm = Realm.getDefaultInstance()

    fun isProcessInFavoriteList(userViewModel: UserViewModel, process: ReleaseValue): Boolean {
        val userPrimaryKey = userViewModel.user.tenantId + "_" +
                userViewModel.user.userId + "_" +
                userViewModel.user.userName + "_" +
                userViewModel.user.url

        val userRealmObject = realm.where(UserRealmObject::class.java).equalTo("id", userPrimaryKey).findFirst()
        return userRealmObject?.favoriteProcesses?.any { favoriteProcess ->
            favoriteProcess.id == process.id
        } ?: false
    }

    fun removeProcessFromFavoriteList(userViewModel: UserViewModel, processRealmObject: ReleaseRealmObject) {
        val userPrimaryKey = userViewModel.user.tenantId + "_" +
                userViewModel.user.userId + "_" +
                userViewModel.user.userName + "_" +
                userViewModel.user.url

        val userRealmObject = realm.where(UserRealmObject::class.java).equalTo("id", userPrimaryKey).findFirst()

        realm.executeTransaction {
            val indexTobeDeleted: Int? = userRealmObject?.favoriteProcesses?.indexOfFirst { releaseRealmObject ->
                releaseRealmObject.id == processRealmObject.id
            }

            indexTobeDeleted?.let { index ->
                userRealmObject.favoriteProcesses.removeAt(index)
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

        val isCurrentProcessInFavoriteList = favoriteProcesses?.any { releaseRealmObject ->
            releaseRealmObject.id == processRealmObject.id
        }

        if (isCurrentProcessInFavoriteList == false) {
            realm.executeTransaction {
                userRealmObject.favoriteProcesses.add(processRealmObject)
            }
        }
    }
}
