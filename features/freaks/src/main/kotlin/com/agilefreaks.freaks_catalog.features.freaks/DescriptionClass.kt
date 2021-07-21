package com.agilefreaks.freaks_catalog.features.freaks

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DescriptionClass : AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.description_layout)

        val imageView: ImageView = findViewById(R.id.description_item_image)
        val textView: TextView = findViewById(R.id.description_item_title)

        imageView.setImageResource(intent.getIntExtra("image", 0))
        textView.text = intent.getStringExtra("name")

        Log.d("Testtt", intent.getIntExtra("image", 0).toString())
        Log.d("Testtt", intent.getStringExtra("name").toString())

    }

}