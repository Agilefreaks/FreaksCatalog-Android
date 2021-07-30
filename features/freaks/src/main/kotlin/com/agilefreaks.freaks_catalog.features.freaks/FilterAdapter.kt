package com.agilefreaks.freaks_catalog.features.freaks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter for the [RecyclerView] in [FreaksFragment]
 */
/*class FilterAdapter(private val context: Context?, private val checkBoxes: List<String>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

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
        val item = checkBoxes[position]
        val checkbox: CheckBox = CheckBox(context)
        holder.textView.text = item.toString()
    }

    // Returns the size of the freaksList
    override fun getItemCount() = checkBoxes.size
}
*/