package com.example.realmsampleapp.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.realmsampleapp.models.User
import com.example.realmsampleapp.models.UserViewModel
import com.example.realmsampleapp.models.realmobjects.user.UserRealmObject
import com.example.realmsampleapp.utils.LiveEvent
import com.example.realmsampleapp.utils.UserListCreator
import io.realm.Realm
import io.realm.kotlin.createObject

class UsersFragmentViewModel : ViewModel() {

    private lateinit var realm: Realm

    var userList: List<User> = UserListCreator().userList()

    private val userViewModelList = LiveEvent<List<UserViewModel>>()
    fun userViewModelList(): LiveData<List<UserViewModel>> = userViewModelList

    fun onCreate() {
        realm = Realm.getDefaultInstance()
        userViewModelList.value = createUserViewModelList()
        addUsersToDb(userViewModelList.value)
    }

    private fun createUserViewModelList(): List<UserViewModel>? =
        userList.map {
            UserViewModel(it, false)
        }

    private fun addUsersToDb(users: List<UserViewModel>?) {
        realm.beginTransaction()
        users?.forEach { userViewModel ->
            val userPrimaryKey = userViewModel.user.tenantId + "_" +
                    userViewModel.user.userId + "_" +
                    userViewModel.user.userName + "_" +
                    userViewModel.user.url

            if (realm.where(UserRealmObject::class.java).equalTo("id", userPrimaryKey).count() == 0L) {
                val user = realm.createObject<UserRealmObject>(userPrimaryKey)
                user.tenantId = userViewModel.user.tenantId
                user.userId = userViewModel.user.userId
                user.userName = userViewModel.user.userName
                user.url = userViewModel.user.url
            }
        }
        realm.commitTransaction()
    }

    override fun onCleared() {
        super.onCleared()
        realm.close()
    }
}