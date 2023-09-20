package com.example.sports

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class SportListAdapter(private val listener: OnClickListener) : ListAdapter<Sport, RecyclerView.ViewHolder>(SportDiffCallBack()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }
    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view){

    }

    class SportDiffCallBack: DiffUtil.ItemCallback<Sport>(){
        override fun areItemsTheSame(oldItem: Sport, newItem: Sport): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Sport, newItem: Sport): Boolean = oldItem == newItem
}


}