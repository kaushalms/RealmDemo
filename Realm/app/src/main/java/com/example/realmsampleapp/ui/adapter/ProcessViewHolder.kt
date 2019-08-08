package com.example.realmsampleapp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.realmsampleapp.R
import com.example.realmsampleapp.models.ProcessViewModel
import kotlinx.android.synthetic.main.item_process.view.*

class ProcessViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    companion object {
        fun create(parent: ViewGroup): ProcessViewHolder = ProcessViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_process, parent, false)
        )
    }

    fun bind(processViewModel: ProcessViewModel, processClickListener: ProcessClickListener) {
        itemView.processCheckBox.isChecked = processViewModel.isSelected
        itemView.processCheckBox.text = processViewModel.releaseValue.name

        itemView.processCheckBox.setOnCheckedChangeListener { _, isChecked ->
            processClickListener.onProcessClicked(processViewModel, isChecked)
        }
    }
}