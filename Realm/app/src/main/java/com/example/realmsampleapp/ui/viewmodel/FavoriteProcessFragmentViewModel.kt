package com.example.realmsampleapp.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.realmsampleapp.models.ProcessViewModel
import com.example.realmsampleapp.models.ReleaseValue
import com.example.realmsampleapp.models.UserViewModel
import com.example.realmsampleapp.utils.FavProcessRealmOperations
import com.example.realmsampleapp.utils.LiveEvent
import com.example.realmsampleapp.utils.ProcessListCreator
import com.example.realmsampleapp.utils.convertProcessToRealmObject

class FavoriteProcessFragmentViewModel : ViewModel() {
    private lateinit var userViewModel: UserViewModel
    var processList: List<ReleaseValue> = ProcessListCreator().processList()
    private val realmOperations = FavProcessRealmOperations()

    private val favoriteProcessViewModelList = LiveEvent<List<ProcessViewModel>>()
    fun favoriteProcessViewModelList(): LiveData<List<ProcessViewModel>> = favoriteProcessViewModelList

    private fun createUserViewModelList(): List<ProcessViewModel>? =
        processList.map {
            ProcessViewModel(it, realmOperations.isProcessInFavoriteList(userViewModel, it))
        }

    fun onCreateWithData(userViewModel: UserViewModel) {
        this.userViewModel = userViewModel
        favoriteProcessViewModelList.value = createUserViewModelList()
    }

    fun onFavoriteProcessClicked(processViewModel: ProcessViewModel, favorited: Boolean) {
        val processRealmObject = convertProcessToRealmObject(processViewModel.releaseValue)
        if (favorited) {
            realmOperations.addProcessToFavoriteList(userViewModel, processRealmObject)
        } else {
            realmOperations.removeProcessFromFavoriteList(userViewModel, processRealmObject)
        }
    }

    override fun onCleared() {
        super.onCleared()
        realmOperations.realm.close()
    }
}
