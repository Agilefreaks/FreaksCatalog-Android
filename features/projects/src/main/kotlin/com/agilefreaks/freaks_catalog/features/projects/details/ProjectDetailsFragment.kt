package com.agilefreaks.freaks_catalog.features.projects.details

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
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProjectDetailsFragment : Fragment() {
    private lateinit var viewBinding: FragmentProjectDetailsBinding

    private val args: ProjectDetailsFragmentArgs by navArgs()

    private val viewModel: ProjectDetailsViewModel by viewModel()

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

        viewBinding.viewModel = viewModel
        viewBinding.lifecycleOwner = this

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val projectId = args.projectId
        viewModel.loadProject(projectId)
    }

    private fun listenToEvents() {
        viewModel.project.observe(this, {
            setAppBarTitle(it.name)
        })
    }

    private fun setAppBarTitle(title: String) {
        val mainActivityToolBar = (activity as AppCompatActivity).supportActionBar

        mainActivityToolBar?.title = title
    }
}
