package com.agilefreaks.freaks_catalog.features.freaks

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter for the [RecyclerView] in [FreaksFragment]
 * Displays [Freak] data object
 */
class ItemAdapter(private val context: Context?, private val freaksList: List<Freak>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    /** Provides a reference to the views for each data item
     * It holds a TextView and an ImageView for each item
     * Each data item is just an Freak object
     */
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
        val imageView: ImageView = view.findViewById(R.id.item_image)
    }

    // Create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    /**
     * Replaces the contents of a view
     * Passes a [Freak] object with the new Intent
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = freaksList[position]
        item.image = R.drawable.testimage
        holder.textView.text = item.firstName
        holder.imageView.setImageResource(item.image)

        // Opens new activity when image is clicked
        holder.imageView.setOnClickListener{
            val intent = Intent(context, DescriptionClass::class.java)
            intent.putExtra("Freak", item)
            context?.startActivity(intent)
        }
    }

    // Returns the size of the freaksList
    override fun getItemCount() = freaksList.size
}