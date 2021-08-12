package com.agilefreaks.freaks_catalog.features.freaks

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agilefreaks.freaks_catalog.features.freaks.FreakDetailsFragment.Companion.FREAK_ID
import com.agilefreaks.freaks_catalog.features.freaks.databinding.FragmentFreaksBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.agilefreaks.freaks_catalog.features.freaks.model.FreaksViewModel
import kotlin.math.pow
import kotlin.math.sqrt

class FreaksFragment : Fragment() {
    private val viewModel: FreaksViewModel by viewModels()
    private lateinit var viewBinding: FragmentFreaksBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_freaks, container, false)
        viewBinding.lifecycleOwner = viewLifecycleOwner
        viewBinding.freaksViewModel = viewModel
        val isPortrait =
            this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

        val layoutManager: RecyclerView.LayoutManager = when {
            !isPortrait && isTablet() -> GridLayoutManager(context, DISPLAY_IN_FOUR_COLUMNS)
            isPortrait -> GridLayoutManager(context, DISPLAY_IN_TWO_COLUMNS)
            else -> GridLayoutManager(context, DISPLAY_IN_THREE_COLUMNS)
        }

        val recyclerView = viewBinding.recycleView
        recyclerView.layoutManager = layoutManager

        val showSkillsButton: Button = viewBinding.skillsBtn
        val showProjectsButton: Button = viewBinding.projectsBtn

        showSkillsButton.setOnClickListener {
            showFilterModal(SKILL_FILTER)
        }
        showProjectsButton.setOnClickListener {
            showFilterModal(PROJECT_FILTER)
        }

        viewModel.freaks.observe(viewLifecycleOwner, { freaks ->
            recyclerView.adapter = ItemAdapter(freaks) {
                onItemClicked(it.id)
            }
        })

        return viewBinding.root
    }

    private fun onItemClicked(freakId: String) {
        findNavController().navigate(R.id.freak_details, bundleOf(FREAK_ID to freakId))
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

    private fun loadFilters(activeFilter: String) =
        if (activeFilter == SKILL_FILTER) {
            listOf("Android", "Kotlin", "Other Skill", "iOS", "Ruby", "QA")
        } else {
            listOf("Freaks Catalog", "Proj2", "Tutorial", "Altkeva")
        }

    private fun showFilterModal(activeFilter: String) {
        val dialog = BottomSheetDialog(requireContext())
        val view: ViewGroup? = null
        val bottomSheetDialog = layoutInflater.inflate(R.layout.bottom_sheet_dialog, view)
        dialog.setCancelable(true)
        val filterTitle: TextView? = bottomSheetDialog.findViewById(R.id.filter_title)
        filterTitle?.text = activeFilter
        val btReset: TextView? = bottomSheetDialog.findViewById(R.id.reset)
        val btApply: Button? = bottomSheetDialog.findViewById(R.id.apply_btn)
        val recyclerFiltersView =
            bottomSheetDialog?.findViewById<RecyclerView>(R.id.recycler_filters_view)
        val filtersList = loadFilters(activeFilter)
        recyclerFiltersView?.layoutManager = LinearLayoutManager(bottomSheetDialog.context)

        val dividerItemDecoration = DividerItemDecoration(
            recyclerFiltersView?.context,
            (recyclerFiltersView?.layoutManager as LinearLayoutManager).orientation
        )
        recyclerFiltersView?.addItemDecoration(dividerItemDecoration)
        val myAdapter = FilterAdapter(filtersList)

        recyclerFiltersView?.adapter = myAdapter
        dialog.setContentView(bottomSheetDialog)
        dialog.show()
        btReset?.setOnClickListener {
            myAdapter.resetCheckboxes()
        }

        btApply?.setOnClickListener {

        }
    }

    companion object {
        private const val DISPLAY_IN_TWO_COLUMNS = 2
        private const val DISPLAY_IN_THREE_COLUMNS = 3
        private const val DISPLAY_IN_FOUR_COLUMNS = 4
        private const val MIN_TABLET_DISPLAY = 6.5
        private const val SKILL_FILTER = "Skills"
        private const val PROJECT_FILTER = "Projects"
    }
}
