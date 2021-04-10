package com.baldeagles.raksha.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.baldeagles.raksha.databinding.SafehouseItemBinding
import com.baldeagles.raksha.data.models.SafeHouse

class SafeHouseAdapter() :
    ListAdapter<SafeHouse, SafeHouseAdapter.viewHolder>(DiffCall()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val binding = SafehouseItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class viewHolder(private val binding: SafehouseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SafeHouse) {
            binding.safehouse = data
        }
    }

    class DiffCall : DiffUtil.ItemCallback<SafeHouse>() {
        override fun areItemsTheSame(
            oldItem: SafeHouse,
            newItem: SafeHouse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SafeHouse,
            newItem: SafeHouse
        ): Boolean {
            return oldItem == newItem
        }
    }

}