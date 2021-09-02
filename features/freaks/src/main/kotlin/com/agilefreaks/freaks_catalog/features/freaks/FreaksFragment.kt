package com.agilefreaks.freaks_catalog.features.freaks

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agilefreaks.freaks_catalog.features.freaks.databinding.FragmentFreaksBinding
import com.agilefreaks.freaks_catalog.features.freaks.filter.FilterViewModel
import com.agilefreaks.freaks_catalog.features.freaks.model.FilterItem
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.pow
import kotlin.math.sqrt

class FreaksFragment : Fragment() {
    private val viewModel: FreaksViewModel by viewModel()
    private val filterViewModel: FilterViewModel by viewModel()
    private lateinit var viewBinding: FragmentFreaksBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_freaks, container, false)
        viewBinding.lifecycleOwner = viewLifecycleOwner
        viewBinding.freaksViewModel = viewModel
        viewBinding.filterViewModel = filterViewModel
        val isPortrait =
            this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

        val layoutManager: RecyclerView.LayoutManager = when {
            isPortrait && !isTablet() -> GridLayoutManager(context, DISPLAY_IN_TWO_COLUMNS)
            !isPortrait && isTablet() -> GridLayoutManager(context, DISPLAY_IN_FOUR_COLUMNS)
            else -> GridLayoutManager(context, DISPLAY_IN_THREE_COLUMNS)
        }

        val recyclerView = viewBinding.recycleView
        recyclerView.layoutManager = layoutManager

        val showSkillsButton: Button = viewBinding.skillsButton
        val showProjectsButton: Button = viewBinding.projectsButton

        showSkillsButton.setOnClickListener {
            showFilterModal(viewModel.technologies.value!!, SKILLS)
        }
        showProjectsButton.setOnClickListener {
            showFilterModal(viewModel.projects.value!!, PROJECTS)
        }

        viewModel.filteredFreaks.observe(viewLifecycleOwner) { freaks ->
            recyclerView.adapter = FreakItemAdapter(freaks) {
                onItemClicked(it.id)
            }
        }

        return viewBinding.root
    }

    private fun onItemClicked(freakId: String) {
        val action = FreaksFragmentDirections.actionFreaksToFreakDetails(freakId)
        findNavController().navigate(action)
    }

    private fun isTablet(): Boolean {
        val metrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            context?.display?.getRealMetrics(metrics)
        } else {
            activity?.windowManager?.defaultDisplay?.getMetrics(metrics)
        }

        val widthInches = metrics.widthPixels / metrics.xdpi
        val heightInches = metrics.heightPixels / metrics.ydpi
        val diagonalInches =
            sqrt(widthInches.toDouble().pow(2.0) + heightInches.toDouble().pow(2.0))
        return diagonalInches >= MIN_TABLET_DISPLAY
    }


    private fun showFilterModal(
        list: List<FilterItem>,
        name: String
    ) {
        val dialog = FilterBottomSheetDialog(list, name, requireContext(),
            { viewModel.onApplyFilterClicked() },
            { viewModel.onResetButtonClicked(name) }
        )
        val modal = dialog.setupFilterModal()
        val isLandscape =
            this.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        if (isLandscape) {
            dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        dialog.setCancelable(true)
        dialog.setContentView(modal)
        dialog.show()
    }

    companion object {
        private const val DISPLAY_IN_TWO_COLUMNS = 2
        private const val DISPLAY_IN_THREE_COLUMNS = 3
        private const val DISPLAY_IN_FOUR_COLUMNS = 4
        private const val MIN_TABLET_DISPLAY = 6.5
        const val SKILLS = "SKILLS"
        const val PROJECTS = "PROJECTS"
    }
}
