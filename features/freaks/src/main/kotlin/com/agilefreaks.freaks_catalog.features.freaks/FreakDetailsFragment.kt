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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_freak_details, container, false)
        setViews()
        return viewBinding.root
    }

    private fun setViews() {
        arguments?.let {
            val freak = it.getSerializable("freak") as Freak
            activity
            viewBinding.descriptionTV.text = freak.description
            viewBinding.skillsTV.text =
                getString(R.string.skills_template, freak.skills.joinToString(", "))
            viewBinding.projectsTV.text =
                getString(R.string.projects_template, freak.projects.joinToString(", "))
            viewBinding.titleTV.text = getString(R.string.title_template, freak.role, freak.norm)
            viewBinding.levelTV.text = getString(R.string.level_project, freak.level)
            initBar(freak.firstName,freak.lastName)
        }
    }

    fun initBar(firstName:String, lastName:String) {
        val mainActivityTB = (activity as AppCompatActivity).supportActionBar
        mainActivityTB?.setTitle(getString(R.string.name_template, firstName, lastName))
    }
}
