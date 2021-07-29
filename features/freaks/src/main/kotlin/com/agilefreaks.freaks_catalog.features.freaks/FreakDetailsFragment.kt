package com.agilefreaks.freaks_catalog.features.freaks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.agilefreaks.freaks_catalog.features.freaks.databinding.FragmentFreakDetailsBinding

class FreakDetailsFragment : Fragment() {
    private lateinit var viewBinding: FragmentFreakDetailsBinding

    private var freak = Freak(
        "Ciprian",
        "Hotea",
        "Android Intern",
        "Full time",
        "Beginner",
        "Description1",
        0,
        listOf("Kotlin"),
        listOf("Freaks Catalog")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_freak_details, container, false)

        initViews()
        initBar()


        return viewBinding.root
    }


    private fun initBar() {
        val mainActivityTB = (activity as AppCompatActivity).supportActionBar
        mainActivityTB?.setTitle(getString(R.string.name_template, freak.firstName, freak.lastName))
        mainActivityTB?.setHomeButtonEnabled(true)
        mainActivityTB?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initViews() {
        viewBinding.descriptionTV.text = freak.description
        viewBinding.skillsTV.text =
            getString(R.string.skills_template, freak.skills.joinToString(", "))
        viewBinding.projectsTV.text =
            getString(R.string.projects_template, freak.projects.joinToString(", "))
        viewBinding.titleTV.text = getString(R.string.title_template, freak.role, freak.norm)
        viewBinding.levelTV.text = getString(R.string.level_project, freak.level)
    }

}


//    private fun initBar(){
//        supportActionBar?.setHomeButtonEnabled(true)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setTitle(
//            getString(
//                R.string.name_template,
//                freak.firstName,
//                freak.lastName
//            )
//        )
//    }

//    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
//        when (menuItem.getItemId()) {
//            android.R.id.home -> {
//                finish()
//            }
//        }
//        return super.onOptionsItemSelected(menuItem)
//    }
