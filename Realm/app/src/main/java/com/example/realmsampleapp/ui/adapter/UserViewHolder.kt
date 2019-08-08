package com.example.realmsampleapp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.realmsampleapp.R
import com.example.realmsampleapp.models.UserViewModel
import kotlinx.android.synthetic.main.item_user.view.*

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    companion object {
        fun create(parent: ViewGroup): UserViewHolder = UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user, parent, false)
        )
    }

    fun bind(userViewModel: UserViewModel, processClickListener: UserClickListener) {
        itemView.userRadioButton.setOnCheckedChangeListener(null)
        itemView.userRadioButton.setOnClickListener {
            processClickListener.onUserClicked(userViewModel, adapterPosition)
        }

        itemView.userRadioButton.isChecked = userViewModel.isSelected

        itemView.userRadioButton.text = userViewModel.user.userName
    }
}