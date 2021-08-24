package com.agilefreaks.freaks_catalog.features.freaks

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.agilefreaks.freaks_catalog.features.freaks.databinding.ListItemBinding
import com.squareup.picasso.Picasso

class ItemAdapter(private val freaksList: List<Freak>, private val onItemCLicked: (Freak) -> Unit) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val freak = freaksList[position]
        holder.bind(freak, onItemCLicked)
    }

    override fun getItemId(position: Int): Long {
        return freaksList[position].freakId.toLong()
    }

    override fun getItemCount(): Int = freaksList.size

    class ItemViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(freak: Freak, onItemCLicked: (Freak) -> Unit) {
            binding.freak = freak
            binding.itemImage.setOnClickListener { onItemCLicked(freak) }
            Log.d("TestFreaks", binding.freak.toString())
        }

    }
}
