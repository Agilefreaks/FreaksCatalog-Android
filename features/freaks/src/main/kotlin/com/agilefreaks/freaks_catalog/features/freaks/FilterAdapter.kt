package com.agilefreaks.freaks_catalog.features.freaks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agilefreaks.freaks_catalog.features.freaks.databinding.FilterItemBinding

class FilterAdapter :
    ListAdapter<FilterItem, FilterAdapter.ViewHolder>(SkillDiffUtilCallback()) {

    class ViewHolder(private val binding: FilterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(filterItem: FilterItem) {
            binding.model = filterItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FilterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewModel = getItem(position)
        holder.bind(viewModel)
    }
}

class SkillDiffUtilCallback : DiffUtil.ItemCallback<FilterItem>() {
    override fun areItemsTheSame(oldItem: FilterItem, newItem: FilterItem): Boolean = false

    override fun areContentsTheSame(oldItem: FilterItem, newItem: FilterItem): Boolean = false
}
