package com.agilefreaks.freaks_catalog.features.freaks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agilefreaks.freaks_catalog.features.freaks.databinding.FreakDescriptionLayoutBinding
import com.squareup.picasso.Picasso

class DescriptionClass : AppCompatActivity(){

    private lateinit var binding: FreakDescriptionLayoutBinding
    private lateinit var freak:Freak

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FreakDescriptionLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        freak = intent.extras!!.getSerializable("Freak") as Freak
        initViews()

    }

    private fun initViews(){
        binding.descriptionTV.text=freak.description
        binding.skillsTV.text = getString(R.string.skills_template, freak.skills.joinToString(", "))
        binding.projectsTV.text = getString(R.string.projects_template, freak.projects.joinToString(", "))
        binding.titleTV.text = getString(R.string.title_template,freak.role,freak.norm)
        binding.levelTV.text = getString(R.string.level_project,freak.level)
        binding.nameTB.text=getString(R.string.name_template,freak.firstName,freak.lastName)
        binding.backTB.setOnClickListener { finish() }
        //getting the image
        Picasso.get().load(freak.image).into(binding.profilePicture)
    }
}