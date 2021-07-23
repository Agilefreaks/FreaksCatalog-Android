package com.agilefreaks.freaks_catalog.features.freaks

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Dummy class to check if the right [Freak] objects is passed
 * Will be modified later, when more functionality is added
 */
class DescriptionClass : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.description_layout)

        val imageView: ImageView = findViewById(R.id.description_item_image)
        val textView: TextView = findViewById(R.id.description_item_title)

        // Gets the [Freak] objects passed with the Intent
        val freak = intent.getSerializableExtra("Freak") as Freak

        // Updates views
        textView.text = freak.printFreak()
        imageView.setImageResource(freak.image)

        // Used for debug purposes
        Log.d("ObjectPass", intent.getSerializableExtra("Freak").toString())

    }

}