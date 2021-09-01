package com.agilefreaks.freaks_catalog.features.projects

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agilefreaks.freaks_catalog.features.projects.databinding.ListProjectsBinding

class ProjectAdapter(private val projectsList: List<Project>, private val onItemCLicked: (Project) -> Unit) :
    RecyclerView.Adapter<ProjectAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ListProjectsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val project = projectsList[position]
        holder.bind(project)
    }

    override fun getItemCount(): Int = projectsList.size

    class ItemViewHolder(private val binding: ListProjectsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(project: Project) {
            binding.project = project
        }
    }
}
