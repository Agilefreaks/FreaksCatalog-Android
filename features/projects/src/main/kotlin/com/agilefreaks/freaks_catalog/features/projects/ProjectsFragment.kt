package com.agilefreaks.freaks_catalog.features.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agilefreaks.freaks_catalog.features.projects.databinding.FragmentProjectsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProjectsFragment : Fragment() {
    private lateinit var viewBinding: FragmentProjectsBinding
    private lateinit var recyclerView: RecyclerView
    private val viewModel: ProjectsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.loadProjects()
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_projects, container, false)
        viewBinding.lifecycleOwner = viewLifecycleOwner
        viewBinding.projectsViewModel = viewModel

        setupRecyclerView()

        return viewBinding.root
    }

    private fun setupRecyclerView() {
        recyclerView = viewBinding.projectsRecyclerview
        recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.projects.observe(viewLifecycleOwner) { projects ->
            recyclerView.adapter = ProjectAdapter(projects)
        }

    }
}
