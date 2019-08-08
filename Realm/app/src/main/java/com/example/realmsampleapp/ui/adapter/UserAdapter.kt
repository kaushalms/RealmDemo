package com.example.realmsampleapp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.realmsampleapp.models.UserViewModel

class UserAdapter(private val userClickListener: UserClickListener) :
    RecyclerView.Adapter<UserViewHolder>() {
    private val userViewModelList = arrayListOf<UserViewModel>()
    private var lastClickedPosition: Int? = null

    override fun onBindViewHolder(viewHolder: UserViewHolder, position: Int) {
        viewHolder.bind(userViewModelList[position], userClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): UserViewHolder =
        UserViewHolder.create(parent)

    override fun getItemCount(): Int = userViewModelList.size

    fun setProcessValues(userViewModelList: List<UserViewModel>) {
        this.userViewModelList.clear()
        this.userViewModelList.addAll(userViewModelList)
        notifyDataSetChanged()
    }

    fun clearPreviousSelection(adapterPosition: Int) {
        lastClickedPosition?.let {
            userViewModelList[it].isSelected = false
            notifyItemChanged(it)
        }
        this.lastClickedPosition = adapterPosition
    }
}

interface UserClickListener {
    fun onUserClicked(userViewModel: UserViewModel, adapterClickedPosition: Int)
}