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

    private fun createUserViewModelList(): List<UserViewModel>? {
        val userViewModelList = ArrayList<UserViewModel>()
        userList.map {
            userViewModelList.add(UserViewModel(it, false))
        }
        return userViewModelList
    }

    private fun addUsersToDb(users: List<UserViewModel>?) {
        users?.forEach { userViewModel ->
            realm.executeTransaction { realm ->
                val userPrimaryKey = userViewModel.user.tenantId + "_" +
                        userViewModel.user.userId + "_" +
                        userViewModel.user.userName + "_" +
                        userViewModel.user.url

                val existingUser = realm.where(UserRealmObject::class.java).equalTo("id", userPrimaryKey).findFirst()

                if (existingUser == null) {
                    val user = realm.createObject<UserRealmObject>(userPrimaryKey)
                    user.tenantId = userViewModel.user.tenantId
                    user.userId = userViewModel.user.userId
                    user.userName = userViewModel.user.userName
                    user.url = userViewModel.user.url
                }
            }
        }
    }
}