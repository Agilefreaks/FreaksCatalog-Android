package com.agilefreaks.freaks_catalog.features.freaks

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val context: Context?, private val freaksList: List<Freak>):
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
        val imageView: ImageView = view.findViewById(R.id.item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = freaksList[position]
        holder.textView.text = item.firstName
        holder.imageView.setImageResource(R.drawable.testimage)

        holder.imageView.setOnClickListener{
            val intent = Intent(context, DescriptionClass::class.java)
            intent.putExtra("Freak", item)
            context?.startActivity(intent)
        }
    }

    override fun getItemCount() = freaksList.size
}
