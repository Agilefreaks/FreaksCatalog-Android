package com.agilefreaks.freaks_catalog.features.freaks

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ItemAdapter(private val freaksList: FreaksListQuery.Freaks?, val onItemCLicked: (FreaksListQuery.Node?) -> Unit) :
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
        val item = freaksList?.nodes?.get(position)
        val firstName = item?.name?.substring(0, item.name.indexOf(' '));
        holder.textView.text = firstName
        holder.imageView.setImageResource(R.drawable.testimage)
        val uri: Uri? = Uri.parse(item?.photo?.uri as String?)
        Picasso.get().load(uri).into(holder.imageView)
        holder.imageView.setOnClickListener {
            onItemCLicked(item)
        }
    }

    override fun getItemCount(): Int = freaksList?.nodes?.size!!
}
