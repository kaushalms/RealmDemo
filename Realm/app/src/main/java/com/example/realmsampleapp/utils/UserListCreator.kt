package com.example.realmsampleapp.utils

import com.example.realmsampleapp.models.UserList
import com.squareup.moshi.Moshi

class UserListCreator {
    private val moshi = Moshi.Builder().build()
    private val userListListJsonAdapter = moshi.adapter(UserList::class.java)

    private val userListJson = """
        {
            "userList":
              [
                 {
                 "url":"www.user-one.com",
                 "tenantId":"1",
                 "userId":"1",
                 "userName":"User One"
                 },
                 {
                 "url":"www.user-two.com",
                 "tenantId":"2",
                 "userId":"1",
                 "userName":"User Two"
                 },
                 {
                 "url":"www.user-one.com",
                 "tenantId":"3",
                 "userId":"2",
                 "userName":"User Three"
                 },
                 {
                 "url":"www.user-one.com",
                 "tenantId":"1",
                 "userId":"4",
                 "userName":"User Four"
                }
            ]

    }"""

    private val userList: UserList? = userListListJsonAdapter.fromJson(userListJson)
    fun userList() = userList?.userList ?: emptyList()
}