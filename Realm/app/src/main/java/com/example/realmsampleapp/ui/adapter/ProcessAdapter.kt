package com.example.realmsampleapp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.realmsampleapp.models.ProcessViewModel

class ProcessAdapter(private val processClickListener: ProcessClickListener) :
    RecyclerView.Adapter<ProcessViewHolder>() {
    private val processViewModelList = arrayListOf<ProcessViewModel>()

    override fun onBindViewHolder(viewHolder: ProcessViewHolder, position: Int) {
        viewHolder.bind(processViewModelList[position], processClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ProcessViewHolder =
        ProcessViewHolder.create(parent)

    override fun getItemCount(): Int = processViewModelList.size

    fun setProcessValues(processViewModelList: List<ProcessViewModel>) {
        this.processViewModelList.clear()
        this.processViewModelList.addAll(processViewModelList)
        notifyDataSetChanged()
    }
}

interface ProcessClickListener {
    fun onProcessClicked(process: ProcessViewModel, isFavorited: Boolean)
}