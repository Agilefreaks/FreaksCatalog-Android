package com.agilefreaks.freaks_catalog.features.freaks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val freaksList: List<Freak>, val onItemCLicked: (Freak) -> Unit) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
        val imageView: ImageView = view.findViewById(R.id.item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = freaksList[position]
        holder.textView.text = item.firstName
        holder.imageView.setImageResource(R.drawable.testimage)

        holder.imageView.setOnClickListener {
            holder.itemView.findNavController()
                .navigate(R.id.freak_details, bundleOf("freak" to item))
            onItemCLicked(item)
        }
    }

    override fun getItemCount() = freaksList.size
}
