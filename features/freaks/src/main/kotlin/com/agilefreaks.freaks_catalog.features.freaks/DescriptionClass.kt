package com.agilefreaks.freaks_catalog.features.freaks

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DescriptionClass : AppCompatActivity(){
    companion object {
        const val FREAK_KEY = "freak"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.description_layout)

        val imageView: ImageView = findViewById(R.id.description_item_image)
        val textView: TextView = findViewById(R.id.description_item_title)

        val freak = intent.getSerializableExtra(FREAK_KEY) as Freak

        textView.text = freak.toString()
        imageView.setImageResource(freak.image)
    }
}
