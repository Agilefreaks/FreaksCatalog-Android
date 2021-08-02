package com.agilefreaks.freaks_catalog.features.freaks

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView

class FilterAdapter(private val filters: List<String>) :
    RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {

    class FilterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.findViewById(R.id.skill)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.filter_item, parent, false)
        Log.d("Testing", "onCreateViewHolder")
        return FilterViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val filter = filters[position]
        holder.checkBox.text = filter
        Log.d("Testing", filter)
    }

    override fun getItemCount() = filters.size
}
