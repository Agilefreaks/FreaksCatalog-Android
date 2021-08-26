package com.agilefreaks.freaks_catalog.features.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.agilefreaks.freaks_catalog.features.projects.R
import com.agilefreaks.freaks_catalog.features.projects.databinding.FragmentProjectDetailsBinding
import com.agilefreaks.freaks_catalog.features.projects.details.DetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProjectDetailsFragment : Fragment() {
    private lateinit var viewBinding: FragmentProjectDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setAppBarTitle("")

        listenToEvents()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_project_details, container, false)

        viewBinding.lifecycleOwner = this

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun listenToEvents() {

    }

    private fun setAppBarTitle(title: String) {
        val mainActivityToolBar = (activity as AppCompatActivity).supportActionBar

        mainActivityToolBar?.title = title
    }
}
