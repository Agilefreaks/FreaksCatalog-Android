package com.agilefreaks.freaks_catalog.commons.ui.bindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, imageUrl: String?) {
    val url: String = if (imageUrl.isNullOrEmpty()) {
        "https://www.gravatar.com/avatar/00000000000000000000000000000000"
    } else {
        imageUrl
    }
    Picasso.get()
        .load(url)
        .into(imageView)
}
