package com.agilefreaks.freaks_catalog.features.freaks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView

class FilterAdapter(private val filters: List<String>) :
    RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {
    var filterReset: Boolean = false

    class FilterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val filterCheckBox: CheckBox = view.findViewById(R.id.filter_checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.filter_item, parent, false)
        return FilterViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val filter = filters[position]
        holder.filterCheckBox.text = filter
        if (filterReset) {
            holder.filterCheckBox.isChecked = false
        }
    }

    fun resetCheckboxes() {
        filterReset = true
        notifyItemRangeChanged(0, filters.size)
    }

    override fun getItemCount() = filters.size
}
