package com.agilefreaks.freaks_catalog.features.freaks

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.agilefreaks.freaks_catalog.features.freaks.databinding.FreakDescriptionLayoutBinding
import com.squareup.picasso.Picasso

class FreakDescription : AppCompatActivity() {

    private lateinit var binding: FreakDescriptionLayoutBinding
    private lateinit var freak: Freak

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.PhoneScreen)
        binding = FreakDescriptionLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        freak = intent.extras!!.getSerializable("Freak") as Freak
        initBar()
        initViews()
    }

    private fun initViews() {
        binding.descriptionTV.text = freak.description
        binding.skillsTV.text = getString(R.string.skills_template, freak.skills.joinToString(", "))
        binding.projectsTV.text =
            getString(R.string.projects_template, freak.projects.joinToString(", "))
        binding.titleTV.text = getString(R.string.title_template, freak.role, freak.norm)
        binding.levelTV.text = getString(R.string.level_project, freak.level)
        //getting the image
        Picasso.get().load(freak.image).into(binding.imageView)
    }

    private fun initBar(){
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(
            getString(
                R.string.name_template,
                freak.firstName,
                freak.lastName
            )
        )
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.getItemId()) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(menuItem)
    }
}