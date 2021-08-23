package com.agilefreaks.freaks_catalog.commons.ui.bindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun loadImage(profilePicture: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Picasso.get()
            .load(imageUrl)
            .into(profilePicture)
    }
}
